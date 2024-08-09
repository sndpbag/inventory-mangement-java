import java.util.Scanner;

public class InventoryManagementSystem {
    // Arrays to store product details
    private static String[] productNames = new String[100];     // Product names
    private static int[] quantities = new int[100];             // Product quantities
    private static double[] purchaseRates = new double[100];    // Purchase rates (cost prices)
    private static double[] salesRates = new double[100];       // Sales rates (selling prices)
    private static int productCount = 0;                        // Number of products in the inventory
    private static double totalRevenue = 0;                     // Total revenue from sales
    private static double totalProfit = 0;                      // Total profit from sales

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Loop to display the menu and get user input
        do {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Remove Product");
            System.out.println("4. Display Inventory");
            System.out.println("5. Calculate Total Inventory Value");
            System.out.println("6. Sell Product");
            System.out.println("7. Display Total Revenue and Profit");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // Switch case to handle user's menu choice
            switch (choice) {
                case 1:
                    addProduct(scanner);  // Add a new product to the inventory
                    break;
                case 2:
                    updateProduct(scanner);  // Update an existing product's details
                    break;
                case 3:
                    removeProduct(scanner);  // Remove a product from the inventory
                    break;
                case 4:
                    displayInventory();  // Display all products in the inventory
                    break;
                case 5:
                    calculateTotalValue();  // Calculate the total value of the inventory
                    break;
                case 6:
                    sellProduct(scanner);  // Sell a product and update inventory
                    break;
                case 7:
                    displayTotalRevenueAndProfit();  // Display total revenue and profit
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");  // Handle invalid menu choices
            }
        } while (choice != 0);  // Repeat until the user chooses to exit

        scanner.close();
    }

    // Method to add a new product to the inventory
    private static void addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter purchase rate: ");
        double purchaseRate = scanner.nextDouble();
        System.out.print("Enter sales rate: ");
        double salesRate = scanner.nextDouble();

        // Store the product details in the arrays
        productNames[productCount] = name;
        quantities[productCount] = quantity;
        purchaseRates[productCount] = purchaseRate;
        salesRates[productCount] = salesRate;
        productCount++;  // Increment the product count

        System.out.println("Product added successfully.");
    }

    // Method to update an existing product's details
    private static void updateProduct(Scanner scanner) {
        System.out.print("Enter product name to update: ");
        String name = scanner.next();
        int index = findProductIndex(name);  // Find the index of the product

        if (index != -1) {
            // If the product is found, update its details
            System.out.print("Enter new quantity: ");
            int quantity = scanner.nextInt();
            System.out.print("Enter new purchase rate: ");
            double purchaseRate = scanner.nextDouble();
            System.out.print("Enter new sales rate: ");
            double salesRate = scanner.nextDouble();

            quantities[index] = quantity;
            purchaseRates[index] = purchaseRate;
            salesRates[index] = salesRate;

            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");  // If the product is not found
        }
    }

    // Method to remove a product from the inventory
    private static void removeProduct(Scanner scanner) {
        System.out.print("Enter product name to remove: ");
        String name = scanner.next();
        int index = findProductIndex(name);  // Find the index of the product

        if (index != -1) {
            // If the product is found, remove it by shifting elements in the arrays
            for (int i = index; i < productCount - 1; i++) {
                productNames[i] = productNames[i + 1];
                quantities[i] = quantities[i + 1];
                purchaseRates[i] = purchaseRates[i + 1];
                salesRates[i] = salesRates[i + 1];
            }
            productCount--;  // Decrement the product count
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");  // If the product is not found
        }
    }

    // Method to display all products in the inventory
    private static void displayInventory() {
        if (productCount == 0) {
            System.out.println("No products in the inventory.");  // If there are no products
        } else {
            System.out.println("\nInventory:");
            for (int i = 0; i < productCount; i++) {
                // Display each product's details
                System.out.println("Product: " + productNames[i] + ", Quantity: " + quantities[i] + ", Purchase Rate: $" + purchaseRates[i] + ", Sales Rate: $" + salesRates[i]);
            }
        }
    }

    // Method to calculate the total value of the inventory
    private static void calculateTotalValue() {
        double totalValue = 0;

        for (int i = 0; i < productCount; i++) {
            // Calculate the total value based on quantities and sales rates
            totalValue += quantities[i] * salesRates[i];
        }

        System.out.println("Total Inventory Value: $" + totalValue);
    }

    // Method to sell a product and update the inventory
    private static void sellProduct(Scanner scanner) {
        System.out.print("Enter product name to sell: ");
        String name = scanner.next();
        int index = findProductIndex(name);  // Find the index of the product

        if (index != -1) {
            System.out.print("Enter quantity to sell: ");
            int quantity = scanner.nextInt();

            if (quantity <= quantities[index]) {
                // If there is sufficient stock, update the inventory and calculate revenue and profit
                quantities[index] -= quantity;
                double saleAmount = quantity * salesRates[index];
                totalRevenue += saleAmount;
                double profit = (salesRates[index] - purchaseRates[index]) * quantity;
                totalProfit += profit;
                System.out.println("Sold " + quantity + " units of " + name + " for $" + saleAmount + ". Profit: $" + profit);
            } else {
                System.out.println("Insufficient stock to complete the sale.");  // If there isn't enough stock
            }
        } else {
            System.out.println("Product not found.");  // If the product is not found
        }
    }

    // Method to display the total revenue and profit
    private static void displayTotalRevenueAndProfit() {
        System.out.println("Total Revenue from Sales: $" + totalRevenue);
        System.out.println("Total Profit: $" + totalProfit);
    }

    // Helper method to find the index of a product in the arrays
    private static int findProductIndex(String name) {
        for (int i = 0; i < productCount; i++) {
            if (productNames[i].equalsIgnoreCase(name)) {
                return i;  // Return the index if the product is found
            }
        }
        return -1;  // Return -1 if the product is not found
    }
}

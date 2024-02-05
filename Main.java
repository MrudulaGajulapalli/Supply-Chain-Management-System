import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Order {
    private List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}

class Warehouse {
    private List<Product> availableProducts;

    public Warehouse() {
        this.availableProducts = new ArrayList<>();
    }

    public void addProduct(Product product) {
        availableProducts.add(product);
    }

    public List<Product> getAvailableProducts() {
        return availableProducts;
    }
}

class Main{

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(new Product("Laptop", 800.0));
        warehouse.addProduct(new Product("Printer", 150.0));
        warehouse.addProduct(new Product("Monitor", 200.0));

        Order order = new Order();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Available Products in Warehouse:");
            warehouse.getAvailableProducts().forEach(p -> System.out.println(p.getName() + " - $" + p.getPrice()));

            System.out.println("\n1. Add Product to Order");
            System.out.println("2. View Order and Total Price");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter product name to add to order:");
                    String productName = scanner.nextLine();
                    Product selectedProduct = warehouse.getAvailableProducts().stream()
                            .filter(p -> p.getName().equalsIgnoreCase(productName))
                            .findFirst()
                            .orElse(null);

                    if (selectedProduct != null) {
                        order.addProduct(selectedProduct);
                        System.out.println("Product added to order successfully.");
                    } else {
                        System.out.println("Product not found in the warehouse.");
                    }
                    break;
                case 2:
                    System.out.println("Order Details:");
                    order.getProducts().forEach(p -> System.out.println(p.getName() + " - $" + p.getPrice()));
                    System.out.println("Total Price: $" + order.calculateTotal());
                    break;
                case 3:
                    System.out.println("Order placed. Thank you for shopping!");
                    System.exit(0);
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
}

import java.util.*;
class Customer {
    private int customerId;
    private String name;
    private int loyaltyPoints;

    public Customer(int customerId, String name, int loyaltyPoints) {
        this.customerId = customerId;
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}

// Product Class
class Product {
    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class Order {
    private int orderId;
    private int customerId;
    private Date deliveryDate;

    public Order(int orderId, int customerId, Date deliveryDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryDate = deliveryDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}


class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}


class CustomerLoyaltyComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return Integer.compare(c2.getLoyaltyPoints(), c1.getLoyaltyPoints());
    }
}

public class AmazonMenuApplication {
    public static void main(String[] args) {
    
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<Order> orderList = new ArrayList<>();

 
        HashMap<Integer, Customer> customerMap = new HashMap<>();
        HashMap<Integer, Product> productMap = new HashMap<>();

        HashSet<Product> uniqueProducts = new HashSet<>();


        TreeSet<Product> sortedProductsByPrice = new TreeSet<>(new ProductPriceComparator());
        TreeSet<Customer> sortedCustomersByLoyalty = new TreeSet<>(new CustomerLoyaltyComparator());
        Customer customer1 = new Customer(1, "Alice", 100);
        Customer customer2 = new Customer(2, "Bob", 150);

        Product product1 = new Product(1, "Laptop", 1200.99);
        Product product2 = new Product(2, "Mouse", 25.99);
        Product product3 = new Product(3, "Keyboard", 45.49);

        Order order1 = new Order(1, 1, new Date());
        Order order2 = new Order(2, 2, new Date());
        customerList.add(customer1);
        customerList.add(customer2);
        customerMap.put(customer1.getCustomerId(), customer1);
        customerMap.put(customer2.getCustomerId(), customer2);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productMap.put(product1.getProductId(), product1);
        productMap.put(product2.getProductId(), product2);
        productMap.put(product3.getProductId(), product3);

        orderList.add(order1);
        orderList.add(order2);

        uniqueProducts.add(product1);
        uniqueProducts.add(product2);

        sortedProductsByPrice.add(product1);
        sortedProductsByPrice.add(product2);
        sortedProductsByPrice.add(product3);

        sortedCustomersByLoyalty.add(customer1);
    
        System.out.println("Products sorted by price:");
        for (Product p : sortedProductsByPrice) {
            System.out.println(p);
        }
        System.out.println("\nCustomers sorted by loyalty points:");
        for (Customer c : sortedCustomersByLoyalty) {
            System.out.println(c);
        }
    }
}

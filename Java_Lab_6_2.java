import java.util.Random;

class CounterEmptyException extends Exception {
    public CounterEmptyException(String message) {
        super(message);
    }
}

class CoffeeCounter {
    private final int capacity = 3;
    private final String[] counter = new String[capacity];
    private int size = 0;
    private int front = 0;
    private int rear = 0;
    private final Random random = new Random();

    public synchronized void produceCoffee(String coffee) throws InterruptedException {
        while (size >= capacity) {
            System.out.println("Counter full! Barista is waiting...");
            wait();
        }
        counter[rear] = coffee;
        rear = (rear + 1) % capacity;
        size++;
        System.out.println("Barista made: " + coffee + " | Counter: " + printCounter());
        notifyAll();
    }

    public synchronized String consumeCoffee() throws InterruptedException, CounterEmptyException {
        while (size == 0) {
            System.out.println("Counter empty! Customer is waiting...");
            wait();
        }
        String coffee = counter[front];
        counter[front] = null;
        front = (front + 1) % capacity;
        size--;
        System.out.println("Customer picked up: " + coffee + " | Counter: " + printCounter());
        notifyAll();
        return coffee;
    }

    public synchronized void reviewCoffee() throws InterruptedException, CounterEmptyException {
        while (size == 0) {
            System.out.println("Counter empty! Reviewer is waiting...");
            wait();
        }
        int randomIndex = (front + random.nextInt(size)) % capacity;
        System.out.println("Reviewer sampled: " + counter[randomIndex] + " | Counter: " + printCounter());
        notifyAll();
    }

    private String printCounter() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < capacity; i++) {
            sb.append(counter[i] == null ? " " : counter[i]);
            if (i < capacity - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

class Barista extends Thread {
    private final CoffeeCounter counter;
    private final String[] coffeeTypes = {"Espresso", "Latte", "Cappuccino", "Mocha"};

    public Barista(CoffeeCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                String coffee = coffeeTypes[random.nextInt(coffeeTypes.length)];
                counter.produceCoffee(coffee);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Customer extends Thread {
    private final CoffeeCounter counter;

    public Customer(CoffeeCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                counter.consumeCoffee();
                Thread.sleep(2000);
            }
        } catch (InterruptedException | CounterEmptyException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Reviewer extends Thread {
    private final CoffeeCounter counter;

    public Reviewer(CoffeeCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                counter.reviewCoffee();
                Thread.sleep(3000 + random.nextInt(2000));
            }
        } catch (InterruptedException | CounterEmptyException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class CoffeeShopSimulation {
    public static void main(String[] args) {
        CoffeeCounter counter = new CoffeeCounter();

        Barista barista1 = new Barista(counter);
        Barista barista2 = new Barista(counter);
        Customer customer1 = new Customer(counter);
        Customer customer2 = new Customer(counter);
        Reviewer reviewer = new Reviewer(counter);

        barista1.start();
        barista2.start();
        customer1.start();
        customer2.start();
        reviewer.start();
    }
}


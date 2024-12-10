interface BankInterface {
    double getBalance();
    double getInterestRate();
}

class BankA implements BankInterface {
    private double balance;

    public BankA(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.0; // 7%
    }
}

class BankB implements BankInterface {
    private double balance;

    public BankB(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.4; // 7.4%
    }
}

class BankC implements BankInterface {
    private double balance;

    public BankC(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.9; // 7.9%
    }
}

public class BankDetails {
    public static void main(String[] args) {
        BankA bankA = new BankA(10000);
        BankB bankB = new BankB(150000);
        BankC bankC = new BankC(200000);

        System.out.println("Bank A: Balance = " + bankA.getBalance() + ", Interest Rate = " + bankA.getInterestRate() + "%");
        System.out.println("Bank B: Balance = " + bankB.getBalance() + ", Interest Rate = " + bankB.getInterestRate() + "%");
        System.out.println("Bank C: Balance = " + bankC.getBalance() + ", Interest Rate = " + bankC.getInterestRate() + "%");
    }
}



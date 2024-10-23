import java.util.Scanner;

public class LAB1_P1 {

    long ccNumber;

    public LAB1_P1(long ccNumber) {
        this.ccNumber = ccNumber;
    }

    public void valid() {
        if (!checkLen(ccNumber)) {
            System.out.println("Invalid credit card number");
            return;
        }

        int lastDigit = (int) (ccNumber % 10);
        long remainingNo = ccNumber / 10;

        String reversedNumber = new StringBuilder(String.valueOf(remainingNo)).reverse().toString();

        int sum = digSum(Long.parseLong(reversedNumber));
        int expectedDigit = 10 - (sum % 10);

        if (expectedDigit == lastDigit) {
            System.out.println("Card is valid");
        } else {
            System.out.println("Card is invalid");
        }
    }

    boolean checkLen(long no) {
        int c = 0;
        while (no != 0) {
            no = no / 10;
            c++;
        }
        return c == 8 || c == 9;
    }

    int digSum(long no) {
        int s = 0;
        int pos = 0;

        while (no > 0) {
            int d = (int) (no % 10);

            if (pos % 2 == 0) {
                d *= 2;
                if (d > 9) {
                    d -= 9;
                }
            }

            s += d;
            no /= 10;
            pos++;
        }

        return s;
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your credit card number: ");
        long ccNumber = sc.nextLong();

        LAB1_P1 obj = new LAB1_P1(ccNumber);
        obj.valid();
    }
}

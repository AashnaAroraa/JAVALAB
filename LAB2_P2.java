import java.util.Scanner;

public class LAB2_P2 {

    static int maxProfit;

    public static void findMaxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return;

        int[] leftProfit = new int[n];
        int minPrice = prices[0];

        for (int i = 1; i < n; i++) {
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        int[] rightProfit = new int[n];
        int maxPrice = prices[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            rightProfit[i] = Math.max(rightProfit[i + 1], maxPrice - prices[i]);
            maxPrice = Math.max(maxPrice, prices[i]);
        }

        maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, leftProfit[i] + rightProfit[i]);
        }

        System.out.println("Max Profit: " + maxProfit);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of days:");
        int n = sc.nextInt();
        int[] prices = new int[n];

        System.out.println("Enter the stock prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        findMaxProfit(prices);
    }
}

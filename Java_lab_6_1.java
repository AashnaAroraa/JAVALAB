
class CoinChange {

    static int countRecur(int[] coins, int n, int sum) {

       
        if (sum == 0) return 1;

        
        if (sum < 0 || n == 0) return 0;

        
        return countRecur(coins, n, sum - coins[n - 1]) +
                countRecur(coins, n - 1, sum);
    }

    static int count(int[] coins, int sum) {
        return countRecur(coins, coins.length, sum);
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int sum = 5;
        System.out.println(count(coins, sum));
    }
}


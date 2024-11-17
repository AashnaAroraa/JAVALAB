// Abstract Class Robber
abstract class Robber {
    public abstract void RobbingClass();

    public abstract int RowHouses(int[] houses);

    public abstract int RoundHouses(int[] houses);

    public abstract int SquareHouse(int[] houses);

    public abstract int MultiHouseBuilding(int[]... houses);

    public void MachineLearning() {
        System.out.println("I love MachineLearning.");
    }
}

// Subclass JAVAProfessionalRobber
class JAVAProfessionalRobber extends Robber {

    @Override
    public void RobbingClass() {
        System.out.println("MScAI&ML");
    }

    @Override
    public int RowHouses(int[] houses) {
        if (houses == null || houses.length == 0) return 0;
        int prev2 = 0, prev1 = 0;
        for (int house : houses) {
            int current = Math.max(prev1, prev2 + house);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    @Override
    public int RoundHouses(int[] houses) {
        if (houses == null || houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];
        return Math.max(maxRob(houses, 0, houses.length - 2), maxRob(houses, 1, houses.length - 1));
    }

    @Override
    public int SquareHouse(int[] houses) {
        return RowHouses(houses);
    }

    @Override
    public int MultiHouseBuilding(int[]... houseTypes) {
        if (houseTypes == null || houseTypes.length == 0) return 0;

        int n = houseTypes.length;    // Number of house types
        int m = houseTypes[0].length; // Number of houses per type

        // Flatten all house types into one array
        int[] combinedHouses = new int[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                combinedHouses[i * m + j] = houseTypes[i][j];
            }
        }

        // Apply RowHouses logic to the combined array
        return RowHouses(combinedHouses);
    }

    private int maxRob(int[] houses, int start, int end) {
        int prev2 = 0, prev1 = 0;
        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + houses[i]);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}

// Main Class to test
public class Main {
    public static void main(String[] args) {
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();
        robber.RobbingClass();
        robber.MachineLearning();

        System.out.println("RowHouses([1,2,3,0]) -> " + robber.RowHouses(new int[]{1, 2, 3, 0}));
        System.out.println("RoundHouses([1,2,3,4]) -> " + robber.RoundHouses(new int[]{1, 2, 3, 4}));
        System.out.println("SquareHouse([5,10,2,7]) -> " + robber.SquareHouse(new int[]{5, 10, 2, 7}));
        System.out.println("MultiHouseBuilding([5,3,8,2],[10,12,7,6],[4,9,11,5],[8,6,3,7]) -> " +
                robber.MultiHouseBuilding(
                        new int[]{5, 3, 8, 2},
                        new int[]{10, 12, 7, 6},
                        new int[]{4, 9, 11, 5},
                        new int[]{8, 6, 3, 7}
                ));
    }
}



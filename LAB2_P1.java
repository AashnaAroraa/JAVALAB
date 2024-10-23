import java.util.*;

public class LAB2_P1 {

    static int[] arr;

    public static void findTopK(int K) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]
        );

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }

        System.out.print("Output: ");
        for (int i = 0; i < K; i++) {
            int[] top = pq.poll();
            System.out.print(top[0] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of elements in the array:");
        int n = sc.nextInt();
        arr = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter the value of K:");
        int K = sc.nextInt();

        findTopK(K);
    }
}

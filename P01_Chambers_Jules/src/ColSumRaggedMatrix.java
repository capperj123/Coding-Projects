
public class ColSumRaggedMatrix {

    public static void main(String[] args) {
        int[][] a = {{5, 7, 9}, {21, 43, 65, 78, 32}, {4}};
        int[][] b = {{21, 78}, {}, {71, 18}, {4}, {23, 85, 42, 14, 91, 11}, {4, 2, 6, 11}};
        int[][] c = {{}};
        int[][] d = {{3}, {5}, {9}};
        print(colSum(a));
        print(colSum(b));
        print(colSum(c));
        print(colSum(d));

    }

    public static int[] colSum(int[][] list) {
        // Left as exercise
        //finds the longest row and saves it as the max
        int max = 0;
        for (int row = 0; row < list.length; row++) {
        	if (list[row].length > max) {
        		max = list[row].length;
        	}
        }
        //initializes the value of sums
        int[] sums = new int[max];
        //creates a new array that is full and square
        int[][] arr = new int[list.length][max];
        //takes the values from the previous array and places them in the new array
        for (int r = 0; r < list.length; r++) {
        	for (int c = 0; c < list[r].length; c++) {
        		arr[r][c] = list[r][c];
        	}
        }
        //adds the values together
        for (int ce = 0; ce < arr[0].length; ce++) {
        	for (int re = 0; re < arr.length; re++) {
        		sums[ce] += arr[re][ce];
        	}
        }
        return sums;

    }

    public static void print(int[] sums) {
        if (sums.length == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < sums.length - 1; ++i) {
                System.out.print(sums[i] + ", ");
            }
            System.out.println(sums[sums.length - 1] + "]");

        }

    }
}

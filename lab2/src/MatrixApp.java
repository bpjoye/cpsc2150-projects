import javax.sound.midi.SysexMessage;
import java.util.Arrays;
import java.util.Scanner;
public class MatrixApp {

    // all constants
    private static final int MAXWIDTH = 10;
    private static final int MINWIDTH = 1;
    private static final int MAXHEIGHT = 10;
    private static final int MINHEIGHT = 1;

    public static void main(String[] args) {

        int width = 0;
        int height = 0;

        // allows for reading data from the console
        Scanner scanner = new Scanner(System.in);
        // ask for the dimensions of the array
        while (width < MINWIDTH || width > MAXWIDTH) {
            System.out.println("How many columns should your matrix have?");
            width = scanner.nextInt();
            if (width < MINWIDTH || width > MAXWIDTH) {
                System.out.println("Error: Please enter a number 1-10");
            }
        }
        while (height < MINHEIGHT || height > MAXHEIGHT) {
            System.out.println("How many rows should your matrix have?");
            height = scanner.nextInt();
            if (height < MINHEIGHT || height > MAXHEIGHT) {
                System.out.println("Error: Please enter a number 1-10");
            }
        }
        // array of the users size
        int[][] arr = new int[height][width];

        // filling the array with data
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.println("What number should go in Row:" + row + " Col:" + col);
                arr[row][col] = scanner.nextInt();
            }
        }
        // printing the array
        System.out.println("Your matrix is:");
        System.out.println(arrToString(arr,width,height));

        int[][] transposedArray = transpose(arr, width, height);
        System.out.println("The transposed matrix is:");
        System.out.println(arrToString(transposedArray,height,width));

        int sumProduct = sumProduct(arr, width, height);
        System.out.println("The product sum is:");
        System.out.println(sumProduct);

        double averageValue = average(arr, width, height);
        System.out.println("The average is:");
        System.out.println(averageValue);


        int[] rowsumArray = rowSum(arr, width, height);
        System.out.println("The sums of each Row are:");
        for (int x=0; x<height; x++) {
            System.out.print("|" + rowsumArray[x]);
        }
        System.out.print("|");
        System.out.println();

        int[] colsumArray = colSum(arr, width, height);
        System.out.println("The sums of each Column are:");
        for (int x=0; x<width; x++) {
            System.out.print("|" + colsumArray[x]);
        }
        System.out.print("|");
    }

    // transposes the array over a diagonal line
    private static int[][] transpose(int[][] arr, int width, int height) {
        int[][] transposedArray = new int[width][height];

        // loops through array and swaps the data across the diagonal line
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                transposedArray[col][row] = arr[row][col];
            }
        }
        return transposedArray;
    }

    private static int sumProduct(int[][] arr, int width, int height) {

        int sum = 0;
        int product = 1;

        // loop through the array getting the sum of each row
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                sum += arr[row][col];
            }
            // multiply the sums together
            product *= sum;
            sum = 0;
        }
        return product;
    }

    private static float average(int[][] arr, int width, int height) {

        float sum = 0;
        // add all the values together and divide by the size of the array
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                sum += arr[row][col];
            }
        }
        return sum / (width*height);
    }

    private static int[] colSum(int[][] arr, int width, int height) {

        int[] sums = new int[width];
        int sum = 0;
        // find the sum of each column and put those values in an array
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                sum += arr[row][col];
            }
            sums[col] = sum;
            sum = 0;
        }
        return sums;
    }

    private static int[] rowSum(int[][] arr, int width, int height) {

        int[] sums = new int[height];
        int sum = 0;
        // find the sum of each row and put those values in an array
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                sum += arr[row][col];
            }
            sums[row] = sum;
            sum = 0;
        }
        return sums;
    }

    private static String arrToString(int[][] arr, int width, int height) {

        String matrix = "";
        // loop through the array and adds all the data to a string
        for (int row = 0; row < height; row++) {
            matrix += "|";
            for (int col = 0; col < width; col++) {
                matrix += Integer.toString(arr[row][col]);
                matrix += "|";
            }
            matrix += "\n";
        }
        return matrix;
    }
}
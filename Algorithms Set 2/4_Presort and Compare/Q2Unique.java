/**Name: Joseph Tassone
 * Description: Presorts two arrays, and then compares whether they're equal.
 */

import java.util.Scanner;

public class Q2Unique {

    public static int count = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the size of the arrays: ");
        final int ARRAY_SIZE = input.nextInt();

        int [] array1 = new int[ARRAY_SIZE];
        int [] array2 = new int[ARRAY_SIZE];

        System.out.print("Enter the elements of the array one: ");
        for(int i = 0; i < ARRAY_SIZE; i++) {
            array1[i] = input.nextInt();
        }

        System.out.print("Enter the elements of the array two: ");
        for(int j = 0; j < ARRAY_SIZE; j++) {
            array2[j] = input.nextInt();
        }

        //presorts the two arrays
        mergeSort(array1, 0, array1.length - 1);
        mergeSort(array2, 0, array2.length - 1);

        //flag sets to false if any of the elements aren't equal
        Boolean flag = true;
        for(int k = 0; k < ARRAY_SIZE; k++) {
            if(array1[k] != array2[k]) {
                flag = false;
                break;
            }
        }

        if(flag == true) {
            System.out.println("These two sets are equal.");
        }
        else {
            System.out.println("These two sets are not equal");
        }
    }

    //sorts the array that has been passed through recursive breakdown
    public static void mergeSort(int array[], int left, int right) {
        if (right > left) {

            //get the middle index
            int middle = (right + left) / 2;

            //sort the left side of the primary array
            mergeSort(array, left, middle);

            //sort the right side of the primary array
            mergeSort(array, middle + 1, right);

            //combine the sorted portions together
            merge(array, left, middle + 1, right);
        }
    }

    //merges the sorted halves of the primary array
    //Note: subarrays merely mean the portion that has been divided of the primary array
    public static void merge(int array[], int left, int middle, int right) {

        //temp array is used to store the values of the merge
        int [] temp = new int[array.length];

        //shifting index of the left "subarray"
        int leftSub = left;

        //shifting index of the right "subarray"
        int rightSub = middle;

        //index of the temp array
        int tempIndex = left;

        //move the smaller of the elements from the "subs" into the temp
        while ((leftSub <= middle - 1) && (rightSub <= right)) {
            if (array[leftSub] <= array[rightSub]) {
                temp[tempIndex++] = array[leftSub++];
            }
            else {
                temp[tempIndex++] = array[rightSub++];
            }
        }

        //copy left over elements of left "subarray"
        for(int i = leftSub; i <= middle - 1; i++) {
            temp[tempIndex++] = array[leftSub++];
        }

        //copy left over elements of the right "subarray"
        for(int j = rightSub; j <= right; j++) {
            temp[tempIndex++] = array[rightSub++];
        }

        //copy back the elements from temp to the primary array
        for(int k = left; k <= right; k++) {
            array[k] = temp[k];
        }
    }
}
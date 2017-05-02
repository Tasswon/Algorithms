/**Name: Joseph Tassone
 * Description: Utilizes a mergesort to determine the number of inversions in an array.
 */

import java.util.Scanner;

public class Q1Inversions {

    //global variable keeps track of the number of inversions
    public static int count = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        final int ARRAY_SIZE = input.nextInt();

        int [] array = new int[ARRAY_SIZE];

        System.out.print("Enter the elements of the array: ");
        for(int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = input.nextInt();
        }

        mergeSort(array, 0, ARRAY_SIZE - 1);

        System.out.println("The number of inversions is: " + count);
    }

    //sorts the array that has been passed through recursive breakdown
    public static void mergeSort(int array[], int left, int right) {

        //returns once the right index
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

                //count increments based on the previous count, plus middle - left
                //the reason we do this is because every in the left will be greater
                count += (middle - leftSub);
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
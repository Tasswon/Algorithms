/**Name: Joseph Tassone
 * Description: Uses a brute-force approach to count the number of inversions in an array.
 */

import java.util.Scanner;

public class QuestionOne {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the size of the array: ");
        int size = input.nextInt();

        System.out.println("Enter the values for the array: ");
        int [] array = new int[size];

        //loops adding the user selected integers to the array
        for(int k = 0; k < array.length; k++) {
            array[k] = input.nextInt();
        }

        int count = inversions(array);

        System.out.println("Therefore the number of inversions is: " + count);
    }

    //takes in an array of ints and returns the number of inversions
    public static int inversions(int [] array) {
        int count = 0;

        for(int i = 0; i < array.length - 1; i++) {
            //inner loop goes through the array and increments the count if first element is greater than second
            for(int j = i + 1; j < array.length; j++) {
                if(array[i] > array[j]) {
                    count++;
                }
            }
        }
        return count;
    }
}

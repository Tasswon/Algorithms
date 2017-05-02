/**Name: Joseph Tassone
 * Description: Given a number of weeks, and set of low stress and high stress jobs; the program calculates
 * the value of the optimal plan using a dynamic programming algorithms.
 */

import java.util.Scanner;

public class test {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        //Set the number of weeks
        System.out.print("Enter the number of weeks: ");
        int weeks = input.nextInt();
        int low [] = new int [weeks];
        int high [] = new int [weeks];
        int result [] = new int[weeks + 2];

        //Set the value of each low stress job at a given week
        System.out.print("Enter the low stress jobs: ");
        for(int i = 0; i < weeks; i++) {
            low[i] = input.nextInt();
        }

        //Set the value of each high stress job at a given week
        System.out.print("Enter the high stress jobs: ");
        for(int i = 0; i < weeks; i++) {
            high[i] = input.nextInt();
        }

        int i = 2;
        int j = 0;
        int answer = 0;

        //Recursive relation = max {F(n-2) + high, F(n-1) + low}
        while(i < result.length) {
            answer = Math.max(result[i - 2] + high[j], result[i - 1] + low[j]);
            result[i] = answer;
            i++;
            j++;
        }
        System.out.println("The value of the optimal plan is: " + result[result.length - 1]);
    }
}
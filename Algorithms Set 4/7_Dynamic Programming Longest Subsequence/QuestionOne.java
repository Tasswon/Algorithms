/**Name: Joseph Tassone
 * Description: Program uses a dynamic programming solution to solve the longest common subsequence problem.
 */

import java.util.Scanner;

public class QuestionOne {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        String string1;
        String string2;

        System.out.print("Enter the first string: ");
        string1 = input.nextLine();

        System.out.print("Enter the second string: ");
        string2 = input.nextLine();

        //Fill a 2d array with the length of each subsequence
        int c[][] = new int [string1.length() + 1][string2.length() + 1];
        for (int i = 1; i < c.length; i++) {
            for (int j = 1; j < c[i].length; j++)
                //First Condition Recurrence: if x[i] = y[j] then c[i - 1, j-1] + 1
                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                }

                //Second Condition Recurrence: max(c[i][j-1], c[i - 1,j])
                else {
                    c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
                }
        }
        System.out.println();

        //Prints out the 2d array of subsequence lengths
        for(int i = 0; i < c.length; i++) {
            for(int j = 0; j < c[i].length; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        //Print out the longest common subsequence
        //Stores each char in a stringbuilder object (easier to reverse at the end)
        StringBuilder longestSub = new StringBuilder();
        int i = string1.length();
        int j = string2.length();
        //Continue checking until i and j are 0 (end of the 2d array, and also the strings)
        while (i > 0 && j > 0) {
            char one = string1.charAt(i - 1);
            char two = string2.charAt(j - 1);
            //if the characters at the given points are equal add them to the string
            if (one == two) {
                char chosen = string1.charAt(i - 1);
                longestSub.append(chosen);
                i--;
                j--;
            }
            //if the length at the above row is greater move to the previous row
            else if (c[i - 1][j] > c[i][j - 1]) {
                i--;
            }
            //if all else fails progress back along the column
            else {
                j--;
            }
        }
        //Reverse and print the string
        System.out.println(longestSub.reverse());
    }
}

/**Name: Joseph Tassone
 * Description: Uses a brute-force approach (decrease-by-one) to solve the 0/1 knapsack problem.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class QuestionTwo {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("What is the capacity of the knapsack: ");
        int capacity = input.nextInt();

        //a weight and value array will be used to represent the items
        System.out.print("How many items are there: ");
        int items = input.nextInt();
        int [] weight = new int[items];
        int [] value = new int[items];

        System.out.print("What is the weights of the items: ");
        for(int i = 0; i < weight.length; i++) {
            weight[i] = input.nextInt();
        }

        System.out.print("What is the values of the items: ");
        for(int i = 0; i < weight.length; i++) {
            value[i] = input.nextInt();
        }

        int var = knapSack(weight, value, capacity);

        //case if nothing can fit into the knapSack
        if(var == -1) {
            System.out.println("You can't fit anything in to the backpack!");
        }
        else {
            System.out.println("The largest value that fits into the knapsack is: " + var);
        }
    }

    //takes in two int arrays (weight and value) and a capacity for the knapsack, and returns the best value for capacity
    public static int knapSack(int [] weight, int [] value, int capacity) {

        int var = -1;
        int money = -1;

        //the temporary array list will hold the sum of the individual subset weights and values
        ArrayList temp1 = new ArrayList();
        ArrayList temp2 = new ArrayList();

        //sets the initial values of the knapsack (empty set condition)
        temp1.add(0);
        temp2.add(0);

        //loops adjusting the compared size for the inner loop with each iteration
        //produces the sum of the individual subsets for weight and value
        for(int i = 0; i < weight.length; i++) {
            int size = temp1.size();
            for(int j = 0; j < size; j++) {
                temp1.add((int)temp1.get(j) + weight[i]);
                temp2.add((int)temp2.get(j) + value[i]);
            }
        }

        //loops through the arraylists and does one check for weight, and also if it has the greatest value per weight
        for(int k = 0; k < temp1.size(); k++) {
            if((int)temp1.get(k) <= capacity) {
                if((int)temp2.get(k) > money) {
                    var = (int) temp2.get(k);
                    money = (int) temp2.get(k);
                }
            }
        }
        return var;
    }
}
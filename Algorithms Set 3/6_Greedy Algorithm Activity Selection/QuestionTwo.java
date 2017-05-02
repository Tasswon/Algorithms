/**Name: Joseph Tassone
 * Description: Utilizes a greedy algorithm to solve the activity selection problem.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuestionTwo {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        //holds the activities
        ArrayList<Activity> schedule = new ArrayList<>();

        //create all the activities
        System.out.print("How many activities will there be: ");
        int numActivities = input.nextInt();

        for(int i = 0; i < numActivities; i++) {
            System.out.print("A" + (i + 1) + " start time: ");
            int start = input.nextInt();

            System.out.print("A" + (i + 1) + " end time: ");
            int end = input.nextInt();

            String temp = "a" + (i + 1);
            schedule.add(new Activity(temp, start, end));
        }
        System.out.println();

        //sort the activities first (by start time)
        Collections.sort(schedule);

        ArrayList<Activity> hall = new ArrayList<>();

        int hallNum = 0;
        int hallCount = 0;

        //continues until the schedule arraylist is empty
        while(!schedule.isEmpty()) {
            hall.add(schedule.get(0));
            schedule.remove(0);

            //loops through the entire schedule
            for(int k = 0; k < schedule.size(); k++) {
                int i = hall.get(hallCount).getEnd();
                int j = schedule.get(k).getStart();

                //add if the previous endtime is less than the next start time
                if(j >= i) {
                    hall.add(schedule.get(k));
                    schedule.remove(k);
                    hallCount++;
                }
            }
            System.out.println("Hall: " + (++hallNum) + ": ");
            for(int l = 0; l < hall.size(); l++) {
                System.out.println(hall.get(l));
            }
            //resets the hall arraylist for the next group
            System.out.println();
            hallCount = 0;
            hall.clear();
        }
        System.out.println("It will take " + hallNum + " lecture halls!");
    }
}

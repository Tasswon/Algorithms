/**Name: Joseph Tassone
 * Description: Class is representation of an activity.
 */

public class Activity implements Comparable <Activity>{
    private String activity = "";
    private int start = 0;
    private int end = 0;

    public Activity(String activity, int start, int end) {
        this.activity = activity;
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    //overrides compare to in order to allow sort by start time
    @Override
    public int compareTo(Activity act) {
        if(this.start > act.start) {
            return 1;
        }
        else if(this.start < act.start) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return activity + " " + start + " " + end;
    }
}

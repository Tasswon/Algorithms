/**Name: Joseph Tassone
 * Description: Implementation of the Boyer-Moore string-matching algorithm.
 */

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class QuestionOne {

    //hashtable used for bad-character table, and arraylist for good-suffix
    public static Hashtable<Character, Integer> bad = new Hashtable<>();
    public static ArrayList <Integer> good = new ArrayList();

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        //Enter the text that will be search in
        System.out.print("Enter a text: ");
        String text = input.nextLine();

        //Enter the pattern that will be searched in the text
        System.out.print("Enter a pattern: ");
        String pattern = input.nextLine();

        //Build the bad-character table
        badCharacter(pattern);

        //Build the good-suffix table
        goodSuffix(pattern);

        System.out.println("Bad-Character Table: " + bad.toString());

        System.out.print("Good-Suffix Table: { | ");
        for(int i = 0; i < good.size(); i++) {
            System.out.print("k=" + (i + 1) + ", "+ "d2" + "=" + good.get(i) + " | ");
        }
        System.out.print("}\n");

        //print out whether there's a match or not
        if(match(pattern, text)) {
            System.out.println("'" + pattern + "' is in the text!");
        }
        else {
            System.out.println(pattern + " is not in the text!");
        }
    }

    //match method takes in the pattern and text
    //chooses which table to refer to and returns whether the pattern is in the text
    public static boolean match(String pattern, String text) {
        int textEnd = pattern.length() - 1;
        int tempEnd = textEnd;
        int subEnd = pattern.length() - 1;
        int tracker = 0;

        //loops until either a match is found or the pattern moves past the length of the text
        while(textEnd < text.length()) {

            //loops for the length of the pattern, and compares character-by-character
            for(int i = subEnd; i >= 0; i--) {

                //if the characters up till this point match, and the last two match return true
                if(pattern.charAt(i) == text.charAt(textEnd)){
                    if(i == 0) {
                        return true;
                    }
                    textEnd--;
                    tracker++;
                }

                //perform if two characters don't match
                else {

                    //perform if the first two characters don't match
                    if(tracker == 0) {
                        int d1;
                        if(bad.containsKey(text.charAt(textEnd)) == false) {
                            d1 = Math.max(tracker - bad.get('-'), 1);
                        }
                        else {
                            d1 = Math.max(tracker - bad.get(text.charAt(textEnd)), 1);
                        }
                        textEnd = tempEnd + d1;
                        tempEnd = textEnd;
                        tracker = 0;
                        break;
                    }

                    //perform if there are matching characters
                    else {
                        int d1;
                        if(bad.containsKey(text.charAt(textEnd)) == false) {
                            d1 = Math.max(tracker - bad.get('-'), 1);
                        }
                        else {
                            d1 = Math.max(tracker - bad.get(text.charAt(textEnd)), 1);
                        }
                        int d2 = good.get(tracker - 1);

                        //shifts based on the max between modified bad-character value and good-suffix
                        int shift = Math.max(d1, d2);

                        textEnd = tempEnd + shift;
                        tempEnd = textEnd;
                        tracker = 0;
                        break;
                    }
                }
            }
        }
        return false;
    }

    //builds the bad-character table
    //add distance from preceding character (closest) to the end, per character
    public static void badCharacter(String pattern) {
        bad.put('-', pattern.length());

        for(int i = pattern.length() - 1; i >= 0; i--) {
            int count = 0;
            char temp = pattern.charAt(i);
            if(bad.containsKey(temp)) {
                continue;
            }

            for(int j = pattern.length() - 2; j >= 0; j--) {
                count++;
                if(temp == pattern.charAt(j)) {
                    bad.put(temp, count);
                    break;
                }
                else if(j == 0) {
                    bad.put(temp, pattern.length());
                    break;
                }
            }
        }
    }

    //builds the good-suffix table
    public static void goodSuffix(String pattern) {
        for(int i = pattern.length() - 1; i > 0; i--) {
            int shift = goodTest1(pattern, i);

            //if the shift doesn't equal the pattern length add it to the shift table
            if (shift != pattern.length()) {
                good.add(shift);
            }
            else {

                //if the shift does equal the pattern length, perform test 2 (predicate check)
                shift = goodTest2(pattern, i);
                good.add(shift);
            }
        }
    }

    //for case one: distance between matched suffix of a size and its rightmost occurrence in the pattern
    //note: cannot be preceded by the same character as the suffix
    private static int goodTest1(String pattern, int i) {

        //check for the substring distance to the rightmost side
        String sub = pattern.substring(i);
        int subLength = sub.length();
        int startLength = pattern.length() - (subLength + 1);
        int subStart = i - subLength;
        int subEnd = (pattern.length() - 1) - subLength;

        //set the preceding char for substring
        char precedeChar = pattern.charAt(i - 1);
        char charCheck;

        for(int j = startLength; j >= 0; j--) {
            if(subStart < 0 || (subLength > pattern.length() / 2)) {
                break;
            }
            else {
                String temp = pattern.substring(subStart, subEnd + 1);

                //set the charCheck for comparison with the preceding char
                if(subStart - 1 < 0) {
                    charCheck = '^';
                }
                else {
                    charCheck = pattern.charAt(subStart - 1);
                }

                if(temp.equals(sub) && charCheck != precedeChar) {
                    return (pattern.length() - 1) - subEnd;
                }
                subStart--;
                subEnd--;
            }
        }
        return pattern.length();
    }

    //for case two: match the longest possible tail of the k-character suffix with corresponding prefix of the pattern
    //note: if there isn't any return the length of the pattern
    private static int goodTest2(String pattern, int i) {
        String sub = pattern.substring(i);
        int subLength = sub.length();
        int subStart = (pattern.length() - subLength - 1);
        int subEnd = pattern.length() - 2;
        char precedeChar = pattern.charAt(i - 1);
        char charCheck;

        //loop checking the preceding substrings until the first index is hit
        while(subStart >= 0) {
            String temp = pattern.substring(subStart, subEnd + 1);
            if(subStart - 1 < 0) {
                charCheck = '^';
            }
            else {
                charCheck = pattern.charAt(subStart - 1);
            }

            if(temp.equals(sub) && charCheck != precedeChar) {
                return (pattern.length() - 1) - subEnd;
            }
            subStart--;
            subEnd--;
        }

        subLength = sub.length() - 1;
        int tracker = subEnd;
        int tempEnd = subEnd;

        //checks until the end of the substring aligns with the first index
        while(subEnd >= 0) {
            for(int j = subEnd; j >= 0; j--) {
                char one = sub.charAt(subLength);
                char two = pattern.charAt(subEnd);
                if(one != two) {
                    break;
                }
                else if(one == two && j == 0) {
                    return (pattern.length() - 1) - tracker;
                }
                subLength--;
                subEnd--;
            }
            if(subEnd >= 0) {
                subEnd = tempEnd;
                tracker = subEnd;
                subLength = sub.length() - 1;
                tempEnd--;
            }
        }
        return pattern.length();
    }
}



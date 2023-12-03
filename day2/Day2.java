package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2
{
    public static void main(String[] args) throws FileNotFoundException
    {
        final int RED_MAX   = 12;
        final int GREEN_MAX = 13;
        final int BLUE_MAX  = 14;

        int gameID    = 1;
        int sumGameID = 0;   // Part 1 answer
        int sumPower  = 0;   // Part 2 answer
        File inFile   = new File("day2-input.txt");
        Scanner sc    = new Scanner(inFile);

        // Used to read "num color" for an entire game
        final Pattern PAT_NUM_AND_COLOR = Pattern.compile("[0-9]+ red|[0-9]+ blue|[0-9]+ green", Pattern.CASE_INSENSITIVE);
        Matcher matchLine = null;

        // Used to separate the number from the color when looking inside of a particular game
        final Pattern PAT_NUM_OR_COLOR = Pattern.compile("[0-9]+|red|green|blue");
        Matcher matchCube = null;
        while (sc.hasNextLine())
        {
            // Gets an entire game
            matchLine = PAT_NUM_AND_COLOR.matcher(sc.nextLine());
            boolean validGame = true;

            int maxRed   = Integer.MIN_VALUE;
            int maxGreen = Integer.MIN_VALUE;
            int maxBlue  = Integer.MIN_VALUE;
            while (matchLine.find())
            {
                // System.out.println(matchLine.group());
                // Gets a single "num color" from the game
                matchCube    = PAT_NUM_OR_COLOR.matcher(matchLine.group());
                int amtCube  = 0;
                String color = null;

                // First match is always the number; second is always the color due to nature of PAT_NUM_OR_COLOR
                if (matchCube.find()) { amtCube = Integer.parseInt(matchCube.group()); }
                if (matchCube.find()) { color = matchCube.group(); }

                // Part 1 - Counting valid games
                boolean validRed   = color.equals("red")   && amtCube <= RED_MAX;
                boolean validGreen = color.equals("green") && amtCube <= GREEN_MAX;
                boolean validBlue  = color.equals("blue")  && amtCube <= BLUE_MAX;
                
                if (!(validRed || validGreen || validBlue)) { validGame = false; }
                
                // Part 2 - Getting minimum cubes that can play the game
                if (color.equals("red")   && amtCube > maxRed)   { maxRed = amtCube; }
                if (color.equals("green") && amtCube > maxGreen) { maxGreen = amtCube; }
                if (color.equals("blue")  && amtCube > maxBlue)  { maxBlue = amtCube; }
            }

            if (validGame) { sumGameID += gameID; }
            // System.out.println(maxRed + " " + maxGreen + " " + maxBlue);
            sumPower += maxRed * maxGreen * maxBlue;
            ++gameID;
        }
        System.out.println(sumGameID);
        System.out.println(sumPower);
    }
}
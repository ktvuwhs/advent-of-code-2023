import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1Part2 {
    public static void main(String args[]) {
        try {
            String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
            File f = new File("advent_day1_input.txt");
            Scanner sc = new Scanner(f);
            Pattern p = Pattern.compile("[0-9]|zero|one|two|three|four|five|six|seven|eight|nine");
            Matcher m;
            int tens = 0;
            int ones = -1;
            int sum = 0;
            
            while (sc.hasNext()) {
                ones = -1;
                m = p.matcher(sc.nextLine());
                if (m.find()) {
                    if (m.group().length() > 1) {
                        tens = Arrays.asList(digits).indexOf(m.group());
                    } else {
                        tens = Integer.parseInt(m.group());
                    }
                }
        
                // m.start() gives index of the match
                // +1 so that we can successfully find "one" inside of something like "twone"
                // The +1 will find in the String "wone", to allow us to actually find the "one"
                while (!m.hitEnd() && m.find(m.start()+1)) {
                    if (m.group().length() > 1) {
                        ones = Arrays.asList(digits).indexOf(m.group());
                    } else {
                        ones = Integer.parseInt(m.group());
                    }
                }
                
                if (ones == -1) { ones = tens; }
                System.out.println((10*tens + ones));
                sum += 10 * tens + ones;
            }
            
            System.out.println(sum);
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        
    }
}

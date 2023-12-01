import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
    public static void main(String args[]) {
        try {
            File f = new File("advent_day1_input.txt");
            Scanner sc = new Scanner(f);
            Pattern p = Pattern.compile("[0-9]");
            Matcher m;
            int tens = 0;
            int ones = -1;
            int sum = 0;
            
            while (sc.hasNext()) {
                ones = -1;
                m = p.matcher(sc.nextLine());
                if (m.find()) { tens = Integer.parseInt(m.group()); }
                
                while (m.find()) {
                    ones = Integer.parseInt(m.group());
                }
                
                if (ones == -1) { ones = tens; }
                
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
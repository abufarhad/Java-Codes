import java.util.Random;

public class ErrorGenerator {
    public static String generateError(String s)
    {
        Random rand = new Random();

        int num = rand.nextInt(s.length()-1);

        char c = (char) (rand.nextInt(25) + 1);

        char ms[] = s.toCharArray();
        ms[num] = c;

        return String.valueOf(ms);

    }
}

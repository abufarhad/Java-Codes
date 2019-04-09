package checksum_bitwise;


import java.util.Random;


public class Generator 
{
    public static String generateError(String s)
    {
        Random rand = new Random();
        int n = rand.nextInt(s.length()-1);
        char alpha = (char) (rand.nextInt(26) + 'a');
        char[] ss = s.toCharArray();
        ss[n] = alpha;
        return String.valueOf(ss);
        
        
    }

    public static class generateError {

        public generateError() {
        }
    }
    
}

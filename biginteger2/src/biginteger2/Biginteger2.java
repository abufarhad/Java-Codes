package biginteger2;

import java.math.BigInteger;

public class Biginteger2 {

   public static void main(String[] args) {

      // create 2 BigInteger objects
      BigInteger bi1, bi2;

      bi1 = new BigInteger("6");
      bi2 = new BigInteger("3");

      // create int object
      int res;

      // compare bi1 with bi2
      res = bi1.compareTo(bi2);

      String str1 = "Both values are equal ";
      String str2 = "First Value is greater ";
      String str3 = "Second value is greater";

      if( res == 0 )
      System.out.println( str1 );
      else if( res == 1 )
      System.out.println( str2 );
      else if( res == -1 )
      System.out.println( str3 );
   }
}
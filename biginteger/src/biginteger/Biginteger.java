package biginteger;

import java.util.Scanner;
import java.math.BigInteger;
public class Biginteger {

   
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println(Long.MAX_VALUE); 
        BigInteger A, B,C,D; 
        System.out.println("Enter frist number : ");
       Scanner s=new Scanner(System.in);
       String firstInput =s.nextLine();
        System.out.println("Enter Second number : ");
        String secondInput =s.nextLine();
        s.close();
        
         A=new BigInteger(firstInput);
         B=new BigInteger(secondInput);
         C=A.remainder(B);
         D=A.remainder(B);
         //System.out.println(C);
         int R=C.compareTo(D);
         
        if(R==0) System.out.println("YES");
        else System.out.println("NO");
                
    }
    
}

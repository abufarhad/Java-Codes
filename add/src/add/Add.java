package add;

import java.util.Scanner;
public class Add {   
    public static void main(String[] args) {
//        int num=100,sum=12;
//        byte b=4; float c=5.5f;
//        char n='a'; int m=70; 
//        System.out.println("The number and sum is  "+num +" "+sum);
//        num=num*2;
//       int res=m/n;
//        System.out.println(res);
 
            
       int  fnum,sum; double ans;
       Scanner b =new Scanner(System.in);
             System.out.println("Enter frist number");
            // System.out.println(b.nextLine()); 
         fnum=b.nextInt();
      System.out.println("Enter second number");
       sum=b.nextInt();
       ans=fnum+sum;
       System.out.print("Sum is : "+ ans );
       //System.out.println(ans);
        
     
    }
    
}

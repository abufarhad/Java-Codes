/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add.two.number;

import java .util.Scanner;
public class AddTwoNumber {
    public static void main(String[] args) {
       double fnum,sum,ans;
       Scanner b =new Scanner(System.in);
       System.out.println("Enter frist number");
       fnum=b.nextDouble();
       System.out.println("Enter second number");
       sum=b.nextDouble();
       ans=fnum+sum;
       System.out.print("Sum is : ");
       System.out.println(ans);
      
     
    }
    
}

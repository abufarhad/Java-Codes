
package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {


    public static void main(String[] args) {
       try
       {
           Socket sock= new Socket("localhost",9999); // port
           PrintStream ps= new  PrintStream( sock.getOutputStream() );
           System.out.println("Enter Somthing.... : ");
           
           InputStreamReader ir= new InputStreamReader(System.in);
           BufferedReader br= new BufferedReader(ir );
           
           String temp =br.readLine();
           ps.println(temp);
           
           BufferedReader ok=new BufferedReader(new InputStreamReader(sock.getInputStream()) );
           
           String got=ok.readLine();
           System.out.print(got);
       }
       catch(Exception ex)
       {
                   
       }
    }
    
}

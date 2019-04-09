import java.net.*;
import java.util.Scanner;
import java.io.*;
class FrameClass implements Serializable
{
	int seq;
	int data ;
	char ack;
	char tailer;

	public FrameClass(int seq, int data,char ack,char tailer) 
        {
		this.seq = seq;
		this.data =data;
		this.ack=ack;
		this.tailer=tailer;
	}
}
public class ObjectClient {
	public static void main(String args[]) {
		try {
			//Scanner scanner=new Scanner(System.in);
			Socket s = new Socket("localhost", 2002);
			
                        
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			FrameClass frame = new FrameClass(10,20,'a','k');
			oos.writeObject(frame);
                        
			oos.close();
			s.close();
		    }
                catch (Exception e) 
                {
			System.out.println(e);
		}
	}
}
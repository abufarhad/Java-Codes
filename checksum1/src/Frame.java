
import java.io.Serializable;


public class Frame implements Serializable {
    
    public int seq;
    public int ack;
    public String data;
    public String checkSum;
}


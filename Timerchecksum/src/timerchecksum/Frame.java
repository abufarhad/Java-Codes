import java.io.Serializable;

public class Frame implements Serializable {
    private int seq;
    private String data;
    private char acknowledgement;
    private String tailer;

    public Frame(String data)
    {

        this.data = data;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public void setTailer(String tailer) {
        this.tailer = tailer;
    }

    public String getData() {
        return data;
    }

    public int getSeq() {
        return seq;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTailer() {
        return tailer;
    }

    public void setAcknowledgement(char acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    public char getAcknowledgement() {
        return acknowledgement;
    }
}

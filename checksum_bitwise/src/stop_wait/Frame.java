package stop_wait;

import java.io.Serializable;

public class Frame implements Serializable {

    private int seq;
    private String data;
    private int ack;
    private String tailer;

    public Frame() {
    }

    public Frame(int seq, String data, int ack) {
        this.seq = seq;
        this.data = data;
        this.ack = ack;
    }

    public Frame(int seq, String data, int ack, String tailer) {
        this.seq = seq;
        this.data = data;
        this.ack = ack;
        this.tailer = tailer;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getAck() {
        return ack;
    }

    public void setAck(int ack) {
        this.ack = ack;
    }

    public String getTailer() {
        return tailer;
    }

    public void setTailer(String tailer) {
        this.tailer = tailer;
    }
}

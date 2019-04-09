/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TryStopAndWait;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Frame implements Serializable {
    
    public int seq;
    public int ack;
    public String data;
    public String checkSum;
}


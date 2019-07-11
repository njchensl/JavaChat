/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpconversation;

import java.io.*;
import java.net.*;


public class MessageSender implements Runnable {

    public final static int PORT = 7331;
    private DatagramSocket sock;
    private String hostname;

    MessageSender(DatagramSocket s, String h) {
        sock = s;
        hostname = h;
    }

    public void sendMessage(String s) throws Exception {
        System.out.println(s);
        byte buf[] = s.getBytes();
        InetAddress address = InetAddress.getByName(hostname);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        sock.send(packet);
    }

    public void run() {
        boolean connected = false;
        do {
            try {
                sendMessage("GREETINGS");
                connected = true;
            } catch (Exception e) {

            }
        } while (!connected);
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                sendMessage(UDPConversation.username + "XXXSEPARATORXXX" + UDPConversation.cd.getText() + "XXXSEPARATORXXX" + UDPConversation.cd.getRecipient());
            } catch (IOException e) {
                //System.err.println(e);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}

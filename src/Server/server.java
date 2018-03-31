package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class server {

    public static void main(String[] args) {
        final int MY_PORT = 5678;
        try {
            DatagramSocket s = new DatagramSocket(MY_PORT);
            int data_size = 24;
            while (true) {
                DatagramPacket p = new DatagramPacket(new byte[data_size], data_size);
                
                s.receive(p);

                
                byte[] data = p.getData();
                
                String message = new String(data);
                String responseMsg = "";
                // Display
                System.out.println("Message received: " + message);
                // Set up address info
                InetAddress response = p.getAddress();
                System.out.println("Thank you for the message: " + response);
                int responsePort = p.getPort();

                // Break up message into components
                String [] components = message.split("%%");
                if(components[0].equals("ECHO"))
                {
                    // Do Echo logic
                    responseMsg = components[1];
                }
                // Get data to be echoed as a byte array
                byte [] responseInBytes = responseMsg.getBytes();
                // Build response packet
                DatagramPacket responsePacket = new DatagramPacket(responseInBytes, responseInBytes.length, response, responsePort);

                // Send packet
                s.send(responsePacket);
            }
        } catch (SocketException ex) {
            System.out.println("A problem occurred when creating the socket on port " + MY_PORT);
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("A problem occurred when reading in a packet.");
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
        }

    }

}

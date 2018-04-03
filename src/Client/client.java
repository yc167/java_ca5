package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import DTO.Movie;
import DAO.MySQLMovieDao;
import DAO.MovieDaoInterface;
import Exceptions.DaoException;
import DAO.MySQLDao;

public class client {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final int MY_PORT = 7891;
        int data_size = 24;
        try {
            // 1) Create a socket
            DatagramSocket s = new DatagramSocket(MY_PORT);

            // 2) Create a packet
            // Figure out address info for packet
            InetAddress receiverAddress = InetAddress.getByName("localhost");
            int receiverPort = 5678;
            String message = "";

            // Take input from user
            System.out.println("Please enter the message you wish to send to the server: (\"exit\" to end program)");
            message = input.nextLine();
            // while the user wishes to continue sending messages to the server
            while (!message.equalsIgnoreCase("exit")) {
                // Get data (in bytes) from message to be sent
                message = "SEARCH%%" + message;
                byte[] data = message.getBytes();
                // Build packet containing new data, addressed to server
                DatagramPacket p = new DatagramPacket(data, data.length, receiverAddress, receiverPort);

                // 3) Send information
                s.send(p);

                // START RECEIVING
                // Build a packet
                DatagramPacket responsePacket = new DatagramPacket(new byte[data_size], data_size);

                // Receive the information
                s.receive(responsePacket);

                // Extract the information
                byte[] responseData = responsePacket.getData();
                // Translate the data
                String response = new String(responseData);
                // Display
                System.out.println("Message received: " + response);
                
                // Take input from user
                System.out.println("Please enter the next message you wish to send to the server: (\"exit\" to end program)");
                message = input.nextLine();
            }
            System.out.println("Communication with the server will now cease.");
            // Finished communicating with the server, remember to close the socket!
            s.close();
        } catch (SocketException ex) {
            System.out.println("A problem occurred when creating the socket on port " + MY_PORT);
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
        } catch (UnknownHostException ex) {
            System.out.println("Aproblem occurred when attempting to look up host.");
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("A problem occurred when sending a packet.");
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
        }
    }
}

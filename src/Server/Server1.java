package Server;

import Exceptions.DaoException;
import Protocol.Protocol;
import java.net.*;
import java.io.*;
import org.json.JSONException;

public class Server1 {

    public static void main(String[] args) throws IOException, DaoException, JSONException {

        int portNumber = 4444;

        System.out.println("Waiting for client to connect ...");

        try ( // try-with-resources style - will autoclose sockets

                // create a (TCP) ServerSocket that will wait for a connection 
                // request from a client on specified port.
                ServerSocket serverSocket = new ServerSocket(portNumber);
                // listen at the port; block/wait until a connection is received.
                // When a connection is received, create a NEW Socket that 
                // establishes a connection between the new socket and the client.
                //"accepting a connection from a client"
                Socket clientSocket = serverSocket.accept();
                // get Output and Input streams from the socket for 
                // communicating with the Client
                PrintWriter out
                = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())); // connection is now established, using the socket,
                // with input and output streams
                ) {

            String inputLine = null, outputLine;

            // Initiate conversation with client
            Protocol p = new Protocol();
            
            //outputLine = p.processInput(null);
            //out.println(outputLine);  // will send "Knock, Knock" message

            // in.readline() waits for (and gathers) input from the socket. 
            // It returns when a newline ('\n') is encountered, and the
            // while loop   is executed using the returned data.
            //  
            // readline() will return 'null' when the stream is ended,
            // which happens when the socket is closed (by the client).
            // 
            while ((inputLine = in.readLine()) != null)  {  // get data until socket is closed
                outputLine = p.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye.")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

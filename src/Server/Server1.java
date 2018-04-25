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

        try ( 

                ServerSocket serverSocket = new ServerSocket(portNumber);

                Socket clientSocket = serverSocket.accept();
                PrintWriter out
                = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())); // connection is now established, using the socket,
                ) {

            String inputLine = null, outputLine;

            // Initiate conversation with client
            Protocol p = new Protocol();


            while ((inputLine = in.readLine()) != null) {  // get data until socket is closed
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

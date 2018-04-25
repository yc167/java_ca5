/** CLIENT
 * KnockKnockClient - uses Socket to connect this client to a server
 *
 * https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 */
package Client;

import DTO.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Client1 {

    public static void main(String[] args) throws IOException, JSONException {
        String hostName = "localhost";  // the server (running on local machine)
        int portNumber = 4444;          // port number the server is listening on

        System.out.println("This is movie client.");
        System.out.println("Connecting to server ... ");

        try ( /// try-with-resources - will autoclose resources including sockets

                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String userInput;

            System.out.println("Enter a command: ");
            userInput = stdIn.readLine();  // ask user for input
            if (userInput != null) {
                System.out.println("Sending the command to server (Client -> Server): " + userInput);
                out.println(userInput);  // send message to server
            }

            while ((fromServer = in.readLine()) != null) {  // keep reading from stream
                //System.out.println("Received (Server -> Client): " + fromServer);
                if (fromServer.equals("Bye.")) {
                    break;
                }

                Gson gson = new Gson();

                // pass the JSON String and the type of the object into the deserializer
                // and it will deserialize the JSON and create a List of Movies.
                Type type = new TypeToken<List<Movie>>() {
                }.getType();

                try {
                    List<Movie> moviesList = gson.fromJson(fromServer, type);
                   
                    for (Movie m : moviesList) {
                        
                        System.out.format("%60s%60s%60s%10s%10s%5s%8s%5s%5s%30s%3s%5s%5s%40s",m.getTitle(), m.getGenre(), m.getDirector(),m.getRuntime(), m.getLocation(),m.getPoster(),m.getRating(),m.getFormat(),m.getYear(),m.getStarring(),m.getCopies(),m.getBarcode(),m.getUser_rating(), m.getPlot() + "\n");
                    }
                } catch (com.google.gson.JsonSyntaxException e) {
                    System.out.println(fromServer);
                }

                System.out.println("\n" + "Enter next command:  ");
                userInput = stdIn.readLine();  // ask user for input
                if (userInput != null) {
                    System.out.println("Sending (Client -> Server): " + userInput);
                    out.println(userInput);  // send message to server
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }
}

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

            // read lines from socket until the socket has been closed.
            // readline() is a blocking method, so it waits until data + '\n' is entered
            // or until the socket is closed.
         //   System.out.println("now waiting for response from server.");

            while ((fromServer = in.readLine()) != null) {  // keep reading from stream
                //System.out.println("Received (Server -> Client): " + fromServer);
                if (fromServer.equals("Bye.")) {
                    break;
                }

//                // conver the json string from server into List<Movies> using JSONArray
//                JSONObject obj = new JSONObject(fromServer);
//               
//                System.out.println("JSON Object dump: " + obj.toString());
//
//                JSONArray arr = obj.getJSONArray();
//
//                // code here to convert a JSONArray to a List<Movie>  using JSON ORG
//                List<Movie> list = new ArrayList<Movie>();
//                
//
//                if (arr != null) {
//                    for (int i = 0; i < arr.length(); i++) {
//                        
//                        System.out.println("\n\n ... in loop, about to getJSONObject() ** \n\n");
//                        
//                        JSONObject o = arr.getJSONObject(i); 
//                        
//                        System.out.println("id = " + o.get("id"));
//                        
//                        
//                        //list.add(arr.get(i));
//                    }
//                }
                Gson gson = new Gson();

                // pass the JSON String and the type of the object into the deserializer
                // and it will deserialize the JSON and create a List of Movies.
                Type type = new TypeToken<List<Movie>>() {
                }.getType();

                try {
                    List<Movie> moviesList = gson.fromJson(fromServer, type);
                    for (Movie m : moviesList) {
                        System.out.println(m.getTitle());
                    }
                } catch (com.google.gson.JsonSyntaxException e) {
               //     System.out.println("NOT A JSON");
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

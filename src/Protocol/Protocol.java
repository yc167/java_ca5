/**
 * This is the Model that maintains the STATE of the interaction.
 * The PROTOCOL is implemented using logic and state transitions.
 *
 *
 * */
package Protocol;

import DAO.MovieDaoInterface;
import DAO.MySQLMovieDao;
import DTO.Movie;
import Exceptions.DaoException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Protocol {

    MovieDaoInterface movieDao = new MySQLMovieDao();

    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 5;

    private int state = WAITING; // set initial state
    private int currentJoke = 0;

    private String[] commands = {"Display", "Search"};
    private String[] clues = {"Turnip", "Little Old Lady", "Atch", "Who", "Who"};
    private String[] answers = {"Turnip the heat, it's cold in here!",
        "I didn't know you could yodel!",
        "Bless you!",
        "Is there an owl in here?",
        "Is there an echo in here?"};

    public String processInput(String theInput) throws DaoException {
        String theOutput = null;
            //String[] inputArray = theInput.split(" ");
//        if (theInput.equalsIgnoreCase("show all movies")) {
//            List<Movie> movies = movieDao.findAllMovies();
//           theOutput = "List of allmovies";
//            // turn into  json string
//            // assign it to theOutput;
//        }
        if (state == WAITING) {
            theOutput = "Enter your query: ";
            state = SENTKNOCKKNOCK;
//
        } else if (state == SENTKNOCKKNOCK) {
        if (theInput.equalsIgnoreCase("Display all movies")) {
            List<Movie> allMovies = movieDao.findAllMovies();
            movieDao.displayListFormat(allMovies);
            theOutput = allMovies.toString();
            state = SENTCLUE;
//
//
//     if(str.contains(" ")){
//        str= str.substring(0, str.indexOf(" ")); 
//        System.out.println(str);
   //         String[] components = theInput.split(":");
        } else if (theInput.substring(0,theInput.indexOf(" ")).equalsIgnoreCase("Search")) {
            // String[] components = theInput.split(":");               
            String input = theInput.substring(theInput.indexOf(" "));
            List<Movie> searchedMovies = movieDao.findMovieByTitle(input.trim());
            movieDao.displayListFormat(searchedMovies);
            theOutput = searchedMovies.toString();
            state = SENTCLUE;
        } else {
            theOutput = "You're supposed to say \"Who's there?\"! "
                    + "\nTry again. Knock! Knock!";
        }
        }
//        } else if (state == SENTCLUE) {
//            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
//                theOutput = answers[currentJoke] + "Want another? (y/n)";
//                state = ANOTHER;
//            } else {
//                theOutput = "You're supposed to say \""
//                        + clues[currentJoke]
//                        + " who?\""
//                        + "! Try again. Knock! Knock!";
//                state = SENTKNOCKKNOCK;
//            }
//        } else if (state == ANOTHER) {
//            if (theInput.equalsIgnoreCase("y")) {
//                theOutput = "Knock! Knock!";
//                if (currentJoke == (NUMJOKES - 1)) {
//                    currentJoke = 0;
//                } else {
//                    currentJoke++;
//                }
//                state = SENTKNOCKKNOCK;
//            } else {
//                theOutput = "Bye.";
//                state = WAITING;
//            }
//        }
        return theOutput;
    }
    }

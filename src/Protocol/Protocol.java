/**
 * This is the Model that maintains the STATE of the interaction.
 * The PROTOCOL is implemented using logic and state transitions.
 *
 *
 * */
package Protocol;
//hello my name is hello 

import DAO.MovieDaoInterface;
import DAO.MySQLMovieDao;
import DTO.Movie;
import Exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Protocol {
  
    MovieDaoInterface movieDao = new MySQLMovieDao();
    List<Movie> watchedMovies = new ArrayList<>();
    
    
    public String processInput(String theInput) throws DaoException, JSONException {
        String theOutput = null;

        if (theInput.equalsIgnoreCase("Display all movies")) {
            List<Movie> allMovies = movieDao.findAllMovies();
            theOutput = createJSON(allMovies);

        } else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("Search")) {
            String input = theInput.substring(theInput.indexOf(" "));
            List<Movie> searchedMovies = movieDao.findMovieByTitle(input.trim());

            theOutput = createJSON(searchedMovies);

            if ("[]".equals(theOutput)) {
                List<Movie> searchedDirector = movieDao.findMovieByDirector(input.trim());

                theOutput = createJSON(searchedDirector);
            }

        } else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("Add")) {
            //exp: ADD TITLE movietitle GENRE moviegenre DIRECTOR moviedirector
            String[] components = theInput.split(" ");
            movieDao.addMovie(components[2], components[4], components[6]);
            theOutput = "Movie added to the database!";

        } else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("Delete")) {
            String[] components = theInput.split(" ");
            movieDao.deleteMovie(components[1]);
            theOutput = "Movie deleted from the database!";

        } 
        
        else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("Update_title")) {
            String[] components = theInput.split(" ");
            movieDao.updateMovieByTitle(components[1], components[3]);
            
            theOutput = "Movie Title updated!"; 
        } else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("Update_genre")) {
            String[] components = theInput.split(" ");
            movieDao.updateMovieByGenre(components[1], components[3]);
            
            theOutput = "Movie Genre updated!";
        } else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("Update_director")) {
            String[] components = theInput.split(" ");
            movieDao.updateMovieByDirector(components[1], components[3]);
            
            theOutput = "Movie Director updated!";
        } 
        
        
        
        else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("Recommend")) {
            //exp: recommend horror movies
            String[] components = theInput.split(" ");
            List<Movie> recommendedMovies = movieDao.findMovieByGenre(components[1]);
            theOutput = createJSON(recommendedMovies);
 
        } else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("Watch")) {
            String input = theInput.substring(theInput.indexOf(" "));
            Movie m = movieDao.watchMovie(input.trim());
            watchedMovies.add(m);
            theOutput = "Movie Watched..";
        }
        
        else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("View")) {
            theOutput = createJSON(watchedMovies);
        }
       
          else {
            theOutput = "Error!";
        }
        
        return theOutput;
    }

    public String createJSON(List<Movie> movieList) throws JSONException {

        Gson gson = new Gson();
        Type type = new TypeToken<List<Movie>>() {
        }.getType();

        // we pass the list and its type into the Gson serializer method
        String json = gson.toJson(movieList, type); // serializes target to Json

        return json;
    }


}

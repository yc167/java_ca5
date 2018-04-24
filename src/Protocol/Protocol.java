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
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Protocol {

    MovieDaoInterface movieDao = new MySQLMovieDao();

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

                theOutput  = createJSON(searchedDirector);
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

        } else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("Update")) {
            //exp: UPDATE oldmovietitle TO newmovietitle
            String[] components = theInput.split(" ");
            movieDao.updateMovieByTitle(components[1], components[3]);
            theOutput = "Movie updated!";
            
        } else if (theInput.substring(0, theInput.indexOf(" ")).equalsIgnoreCase("Recommend")) {
            //exp: recommend horror movies
            String[] components = theInput.split(" ");
            List<Movie> recommendedMovies = movieDao.findMovieByGenre(components[1]);
            theOutput = createJSON(recommendedMovies);
            
        } else {
            theOutput ="Error!";
        }

        return theOutput;
    }

    public String createJSON(List<Movie> movieList) throws JSONException {

//        JSONObject jsonObject = new JSONObject().put("movies", new JSONArray(movieList));
//  
//        System.out.println("Server - Protocol - createJSON():" + jsonObject.toString());
//        
//        
//                for (Movie m : movieList) {
//                    System.out.println(m + "\n");
//        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Movie>>() {
        }.getType();

        // we pass the list and its type into the Gson serializer method
        String json = gson.toJson(movieList, type); // serializes target to Json

        return json;
    }
//    
//        public String createJson(List<Movie> movies) throws JSONException {
//        JSONObject json = new JSONObject();
//
//        for (Movie m : movies) {
//            JSONObject jsonMovie = new JSONObject();
//            jsonMovie.put("id", m.getId());
//            jsonMovie.put("title", m.getTitle());
//            jsonMovie.put("genre", m.getGenre());
//            jsonMovie.put("director", m.getDirector());
//
//            //userArray.put(jsonUser);
//            json.append("movies", jsonMovie);
//        }
//           
//        String output = json.toString().replace("},{", "},\n{");
//        
//        return output;

}

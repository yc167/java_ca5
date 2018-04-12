package DAO;

import DTO.Movie;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MySQLMovieDao extends MySQLDao implements MovieDaoInterface {

    public List<Movie> findAllMovies() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList<Movie>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM movies";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String genre = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                String copies = rs.getString("COPIES");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                Movie m = new Movie(id, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                movies.add(m);
            }

        } catch (SQLException e) {
            throw new DaoException("findAllMovies() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllMovies() " + e.getMessage());
            }
        }
        return movies;     // may be empty
    }

    @Override
    public List findMovieByTitle(String title) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> moviesByTitle = new ArrayList<Movie>();
        try {
            con = this.getConnection();

            String query = "SELECT * FROM movies WHERE title LIKE  '%" + title + "%'";
            ps = con.prepareStatement(query);

            //ps.setString(1, title);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String t = rs.getString("TITLE");
                String g = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                String copies = rs.getString("COPIES");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                Movie m = new Movie(id, t, g, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                moviesByTitle.add(m);

            }
        } catch (SQLException e) {
            throw new DaoException("findMovieByTitle " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findMovieByTitle" + e.getMessage());
            }
        }
        return moviesByTitle; // u may be null 
    }

    public List<Movie> findMovieByDirector(String director) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> moviesByDirector = new ArrayList<Movie>();

        try {
            con = this.getConnection();

            String query = "SELECT * FROM movies WHERE director =? ";
            ps = con.prepareStatement(query);

            ps.setString(1, director);

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String t = rs.getString("TITLE");
                String g = rs.getString("GENRE");
                String d = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                String copies = rs.getString("COPIES");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                Movie m = new Movie(id, t, g, d, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                moviesByDirector.add(m);
            }
        } catch (SQLException e) {
            throw new DaoException("findMovieByGenre " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findMovieByGenre" + e.getMessage());
            }
        }

        return moviesByDirector;     // u may be null 
    }

    @Override
    public void addMovie(String title, String genre, String director) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        Movie m = null;
        try {
            con = this.getConnection();

            String query = "INSERT INTO movies (title, genre, director) VALUES (?,?,?) ";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setString(3, director);

            ps.executeUpdate();
            //         System.out.println("It worked");
        } catch (SQLException e) {
            throw new DaoException("addMovie " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("addMovie" + e.getMessage());
            }
        }
    }

    public void deleteMovie(String title) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //    Movie m = null;
        try {
            con = this.getConnection();

            String query = "DELETE FROM movies WHERE title = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);

            ps.executeUpdate();
            //    System.out.println("Deleted!");

        } catch (SQLException e) {
            throw new DaoException("deleteMovie " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("deleteMovie" + e.getMessage());
            }
        }

    }

    public void displayListFormat(List<Movie> x) throws DaoException {

//        System.out.format("%2s%55s%65s%65s", "\nMovieID", "Title", "Genre", "Director\n");
//        System.out.format("%2s%55s%65s%65s", "------", "----", "----", "------\n");
//        for (Movie i : x) {
//            System.out.format("%4s%60s%70s%60s", i.getId(), i.getTitle(), i.getGenre(), i.getDirector() + "\n");
//        }

                
        try {
            createJson(x);
        } catch (JSONException ex) {
            System.out.println("JSON EXCEPTION "+ ex.getMessage());
        }
        
        

    }

    public void displayObjectFormat(Movie x) throws DaoException {

        System.out.format("%2s%35s%50s%40s", "\nMovieID", "Title", "Genre", "Director\n");
        System.out.format("%2s%35s%50s%40s", "------", "----", "----", "------\n");

        System.out.format("%4s%40s%50s%40s", x.getId(), x.getTitle(), x.getGenre(), x.getDirector() + "\n");

    }

    public void updateMovieByTitle(String old_title, String new_title) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = this.getConnection();

            String query = "UPDATE movies SET title=? WHERE title = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, new_title);
            ps.setString(2, old_title);

            ps.executeUpdate();
            //              System.out.println("It worked");
        } catch (SQLException e) {
            throw new DaoException("updateMovieByTitle " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("updateMovieByTitle" + e.getMessage());
            }
        }
    }

    public List<Movie> findMovieByGenre(String genre) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> moviesByGenre = new ArrayList<Movie>();

        try {
            con = this.getConnection();

            String query = "SELECT * FROM movies WHERE genre LIKE ? ";
            ps = con.prepareStatement(query);

            ps.setString(1, genre);

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String t = rs.getString("TITLE");
                String g = rs.getString("GENRE");
                String d = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                String copies = rs.getString("COPIES");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                Movie m = new Movie(id, t, g, d, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                moviesByGenre.add(m);
            }
        } catch (SQLException e) {
            throw new DaoException("findMovieByGenre " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findMovieByGenre" + e.getMessage());
            }
        }
        return moviesByGenre;     // u may be null 
    }

    public void recordMovie(String title) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        List<Movie> moviesWatched = new ArrayList<Movie>();
        try {
            con = this.getConnection();

            String query = "SELECT * FROM movies WHERE title LIKE  '%" + title + "%'";
            ps = con.prepareStatement(query);

            //ps.setString(1, title);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String t = rs.getString("TITLE");
                String g = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                String copies = rs.getString("COPIES");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                Movie m = new Movie(id, t, g, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                moviesWatched.add(m);

                for (Movie i : moviesWatched) {
                    //    System.out.println("Movie id : " + i.getId() + "Title :" + i.getTitle());
                    //    String mv = i.getTitle();
                    //          System.out.println("Movie Title: " +mv);
                    String query1 = "INSERT INTO history (title) VALUES (example)";
                    //     ps1.setString(1,i.getTitle());
                    ps1 = con.prepareStatement(query1);
                    ps1.executeUpdate();
                }

            }
        } catch (SQLException e) {
            throw new DaoException("recordMovie " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("recordMovie" + e.getMessage());
            }
        }
    }

    public void createJson(List<Movie> movies) throws JSONException {
        JSONObject json = new JSONObject();

        for (Movie m : movies) {
            JSONObject jsonMovie = new JSONObject();
            jsonMovie.put("id", m.getId());
            jsonMovie.put("title", m.getTitle());
            jsonMovie.put("genre", m.getGenre());
            jsonMovie.put("director", m.getDirector());

            //userArray.put(jsonUser);
            json.append("movies", jsonMovie);
        }

        System.out.println(json.toString(1));
        System.out.println("");

        //deconstructJson(json.toString());

    }
    
    public void deconstructJson(String jsonString) throws JSONException{
        
        JSONObject json = new JSONObject(jsonString);
        
        List<Movie> movies = new ArrayList<>();
        
        JSONArray movieArray = json.getJSONArray("movies");
        
        for(int i=0; i<movieArray.length(); i++){
            JSONObject jsonMovie = (JSONObject) movieArray.get(i);
            int id = jsonMovie.getInt("id");
            String title = jsonMovie.getString("title");
            String genre = jsonMovie.getString("genre");
            String director = jsonMovie.getString("director");
            Movie m = new Movie(id, title, genre, director);
            movies.add(m);
        }
        
         for(Movie m : movies){
             System.out.println(m.toString());
         }
    }
    
}
    


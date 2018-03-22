package DAO;

import DTO.Movie;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class MySQLMovieDao extends MySQLDao implements MovieDaoInterface {

    //im going to leave this method here for testing queries
    public void displayAll() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM movies limit 10";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                int custID = rs.getInt("id");
                String fname = rs.getString("title");

                System.out.print(custID + ", ");
                System.out.print(fname + ", \n");

            }
        } catch (SQLException e) {
            throw new DaoException("findAllMovies() " + e.getMessage());
        }

    }

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
            //displayAllList(movies);
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
    public Movie findMovieByTitle(String title) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM movies WHERE title LIKE  '%" + title + "%'";
            ps = con.prepareStatement(query);

            //ps.setString(1, title);
            rs = ps.executeQuery();
            if (rs.next()) {
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
                m = new Movie(id, t, g, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
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
        return m; // u may be null 
    }

    public Movie findMovieByTitleAndGenre(String title, String genre) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM movies WHERE title =? AND genre =?";
            ps = con.prepareStatement(query);

            ps.setString(1, title);
            ps.setString(2, genre);
            rs = ps.executeQuery();
            if (rs.next()) {
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
                m = new Movie(id, t, g, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
            }
        } catch (SQLException e) {
            throw new DaoException("findMovieByTitleAndGenre " + e.getMessage());
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
                throw new DaoException("findMovieByTitleAndGenre " + e.getMessage());
            }
        }
        return m; // u may be null 
    }

    public List<Movie> findMovieByGenre(String genre) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> moviesByGenre = new ArrayList<Movie>();

        try {
            con = this.getConnection();

            String query = "SELECT * FROM movies WHERE genre =? ";
            ps = con.prepareStatement(query);

            ps.setString(1, genre);

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
            System.out.println("It worked");
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
            System.out.println("Deleted!");

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

}
//    @Override
//    public List<Movie> findAllMovies() throws DaoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    public void displayAllList(List<Movie> x)
//    {
//        
//        System.out.println("\n---------All Movies in Database------------");
//        System.out.format("%8s%30s%15s%24s", "MovieID", "Title", "Genre", "Director\n");
//        System.out.format("%8s%30s%15s%24s", "------", "----", "----", "------\n");
//        for(Movie i : x)
//        {
//            System.out.format("%8s%30s%15s%24s" , i.getId() , i.getTitle(), i.getGenre(), i.getDirector() + "\n");
//        }
//        
//    }


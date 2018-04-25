/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Movie;
import Exceptions.DaoException;
import java.util.List;
import org.json.JSONException;

/**
 *
 * @author ycphu
 */
public interface MovieDaoInterface {



    //Lists
    public List<Movie> findAllMovies() throws DaoException;
    public List<Movie> findMovieByDirector(String director) throws DaoException;
    public List<Movie> findMovieByTitle(String title) throws DaoException;
    public List<Movie> findMovieByGenre(String genre) throws DaoException;

    //Database
    public void addMovie(String title, String genre, String director) throws DaoException;
    public void updateMovieByTitle(String old_title, String new_title) throws DaoException;
    public void updateMovieByDirector(String old_title, String new_title) throws DaoException ;
    public void updateMovieByGenre(String old_title, String new_title) throws DaoException ;
    public void deleteMovie(String title) throws DaoException;

    //Watched
    public Movie watchMovie(String title) throws DaoException ;

    



}

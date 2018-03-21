/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Movie;
import Exceptions.DaoException;
import java.util.List;
/**
 *
 * @author ycphu
 */
public interface MovieDaoInterface {
    
    
    
    public void displayAll()  throws DaoException;
    
    //Lists
    public List<Movie> findAllMovies() throws DaoException;
    public List<Movie> findMovieByGenre(String genre) throws DaoException;
    
    
    //Objects
    public Movie findMovieByTitle(String title) throws DaoException;
    public Movie findMovieByTitleAndGenre(String title, String genre) throws DaoException;
    
    
    
    
   
}

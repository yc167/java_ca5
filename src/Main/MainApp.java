package Main;

import java.sql.*;
import java.sql.Connection;

import DTO.Movie;
import DAO.MySQLMovieDao;
import DAO.MovieDaoInterface;
import Exceptions.DaoException;
import DAO.MySQLDao;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Cormac Callan D00182306
 */
public class MainApp {

    MovieDaoInterface movieDao = new MySQLMovieDao();

    public static void main(String[] args) throws DaoException {

        new MainApp().start();
        System.out.println("Cormac GITHUB TEST");
    }

    public void start() throws DaoException {

        printMainMenu();

    }

    public void printMainMenu() throws DaoException {
        System.out.println("\n---------------Movie Main Menu---------------");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Display All Movies");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Director");
        System.out.println("4. Add Movie");
        System.out.println("5. Delete Movie");
        System.out.println("6. Update Movie");
        System.out.println("7. Recommend Movie (by genre)");
        System.out.println("8. Record Movie watched");

        performMainMenu();
    }

    public void performMainMenu() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        int option = keyboard.nextInt();

        while (option != 0) {
            if (option == 1) {
                displayAllMovies();
            }
            if (option == 2) {
                searchByTitle();
            }
            if (option == 3) {
                searchByDirector();
            }
            if (option == 4) {
                addMovie();
            }
            if (option == 5) {
                deleteMovie();
            }
            if (option == 6) {
                updateMovieByTitle();
            }
            if (option == 7) {
                searchByGenre();
            } 
            if(option == 8){
                recordMovie();
            }else {
                System.out.println("Select available option!");
                printMainMenu();
            }
        }
    }

    public void displayAllMovies() throws DaoException {
        List<Movie> allMovies = new ArrayList<Movie>();
        allMovies = movieDao.findAllMovies();

        movieDao.displayListFormat(allMovies);

        printMainMenu();

    }

    public void searchByTitle() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter \nTitle : ");
        String title = keyboard.nextLine();

        List<Movie> findByTitle = movieDao.findMovieByTitle(title);
        movieDao.displayListFormat(findByTitle);

        printMainMenu();

    }

    public void searchByDirector() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter \nDirector : ");
        String director = keyboard.nextLine();

        List<Movie> moviesByDirector = movieDao.findMovieByDirector(director);
//        for (Movie i : moviesByDirector) {
//            System.out.println(i);
//        }
        movieDao.displayListFormat(moviesByDirector);

        printMainMenu();

    }

    public void addMovie() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter \nTitle: ");
        String title = keyboard.nextLine();
        System.out.println("Enter \nGenre : ");
        String genre = keyboard.nextLine();
        System.out.println("Enter \nDirector : ");
        String director = keyboard.nextLine();

        movieDao.addMovie(title, genre, director);
        System.out.println("Adding movie to database.....");
        System.out.println("Movie added!");

        printMainMenu();

    }

    public void deleteMovie() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter \nTitle to be deleted: ");
        String title = keyboard.nextLine();

        movieDao.deleteMovie(title);
        System.out.println("Deleting movie from database.....");
        System.out.println("Movie deleted!");

        printMainMenu();

    }

    public void updateMovieByTitle() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter \nTitle to be update: ");
        String old_title = keyboard.nextLine();

        System.out.println("Enter \nNew title: ");
        String new_title = keyboard.nextLine();

        movieDao.updateMovieByTitle(old_title, new_title);

        System.out.println("Updating movie .....");
        System.out.println("Movie updated!");

        printMainMenu();
    }

    public void searchByGenre() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What type of movies do you like?  ");
        String genre = keyboard.nextLine();
        String bind_genre = "%" + genre + "%";

        List<Movie> findByGenre = movieDao.findMovieByGenre(bind_genre);
        System.out.println("We recommend these movies :");
        movieDao.displayListFormat(findByGenre);

        printMainMenu();

    }
    
     public void recordMovie() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter \nMovie watched : ");
         String title = keyboard.nextLine();


        movieDao.recordMovie(title);
        System.out.println("Recording movie to database.....");
        System.out.println("Movie added!");

        printMainMenu();

    }

}

//    public void searchByTitleAndGenre() throws DaoException {
//        Scanner keyboard = new Scanner(System.in);
//        System.out.println("Enter \nTitle : ");
//        String title = keyboard.nextLine();
//
//        System.out.println("Enter \nGenre : ");
//        String genre = keyboard.nextLine();
//
//        // eg Title : Flashback
//        // eg Genre : Action
//        Movie findByTitleAndGenre = movieDao.findMovieByTitleAndGenre(title, genre);
//        movieDao.displayObjectFormat(findByTitleAndGenre);
//
//        printMainMenu();
//
//    }

package cormaccallanca5;

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

    }

    public void start() throws DaoException {

        printMainMenu();

    }

    public void printMainMenu() throws DaoException {
        System.out.println("\n---------------Movie Main Menu---------------");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Display All Movies");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Genre");
        System.out.println("4. Search by Title And Genre (Do we need this?)");
        System.out.println("5. Add Movie to Database");
        System.out.println("6. Delete Movie from Database");

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
                searchByGenre();
            }
            if (option == 4) {
                searchByTitleAndGenre();
            }
            if (option == 5) {
                addMovie();
            }
            if (option == 6) {
                deleteMovie();
            } else {
                System.out.println("Select available option!");
                printMainMenu();
            }
        }
    }

    public void displayAllMovies() throws DaoException {
        List<Movie> allMovies = new ArrayList<Movie>();
        allMovies = movieDao.findAllMovies();

        for (Movie i : allMovies) {
            System.out.println(i);
        }

        printMainMenu();

    }

    public void searchByTitle() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter \nTitle : ");
        String title = keyboard.nextLine();

        // eg Title : Flashback
        Movie findByTitle = movieDao.findMovieByTitle(title);
        System.out.println(findByTitle);

        printMainMenu();

    }

    public void searchByGenre() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter \nGenre : ");
        String genre = keyboard.nextLine();

        // eg Genre : Action
        List<Movie> moviesByGenre = movieDao.findMovieByGenre(genre);
        for (Movie i : moviesByGenre) {
            System.out.println(i);
        }

        printMainMenu();

    }

    public void searchByTitleAndGenre() throws DaoException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter \nTitle : ");
        String title = keyboard.nextLine();

        System.out.println("Enter \nGenre : ");
        String genre = keyboard.nextLine();

        // eg Title : Flashback
        // eg Genre : Action
        Movie findByTitleAndGenre = movieDao.findMovieByTitleAndGenre(title, genre);
        System.out.println(findByTitleAndGenre);

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
        System.out.println("Enter \nTitle: ");
        String title = keyboard.nextLine();

        movieDao.deleteMovie(title);
        System.out.println("Deleting movie from database.....");
        System.out.println("Movie deleted!");

        printMainMenu();

    }
}

/** 
 * This POJO (Plain Old Java Object) is called the Data Transfer Object (DTO)
 * (or the Model Object or the Value Object).
 * It is used to transfer data between the DAO and the Business Objects.
 * Here, it represents a row of data from the User database table.
 * 
 */
package DTO;

/**
 *
 * @author Cormac
 */
public class Movie {
    
    int id;
    String title;
    String genre;
    String director;
    String runtime;
    String plot;
    String location;
    String poster;
    String rating;
    String format;
    String year;
    String starring;
    String copies;
    String barcode;
    String user_rating;

    public Movie(int id, String title, String genre, String director) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", genre=" + genre + ", director=" + director + '}';
    }
    
    

    
    
    


    
    
    
}

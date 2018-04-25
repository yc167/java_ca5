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
    
   public  int id;
    public String title;
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

    //Constructors
    
    public Movie() {
    }

    public Movie(int id) {
        this.id = id;
    }

    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Movie(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }
    
    public Movie(int id, String title, String genre, String director) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
    }

    public Movie(int id, String title, String genre, String director, String runtime) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
    }

    public Movie(int id, String title, String genre, String director, String runtime, String plot) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
    }

    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
    }

    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location, String poster) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
    }

    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location, String poster, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
    }

    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location, String poster, String rating, String format) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
    }

    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location, String poster, String rating, String format, String year) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
        this.year = year;
    }

    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location, String poster, String rating, String format, String year, String starring) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
    }

    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location, String poster, String rating, String format, String year, String starring, String copies) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
        this.copies = copies;
    }

    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location, String poster, String rating, String format, String year, String starring, String copies, String barcode) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
        this.copies = copies;
        this.barcode = barcode;
    }
    
    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location, String poster, String rating, String format, String year, String starring, String copies, String barcode, String user_rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
        this.copies = copies;
        this.barcode = barcode;
        this.user_rating = user_rating;
    }



  
    
    //Getters and setters
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

    public String getRuntime() {
        return runtime;
    }

    public String getPlot() {
        return plot;
    }

    public String getLocation() {
        return location;
    }

    public String getPoster() {
        return poster;
    }

    public String getRating() {
        return rating;
    }

    public String getFormat() {
        return format;
    }

    public String getYear() {
        return year;
    }

    public String getStarring() {
        return starring;
    }

    public String getCopies() {
        return copies;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getUser_rating() {
        return user_rating;
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

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public void setCopies(String copies) {
        this.copies = copies;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", genre=" + genre + ", director=" + director + ", runtime=" + runtime + ", plot=" + plot + ", location=" + location + ", poster=" + poster + ", rating=" + rating + ", format=" + format + ", year=" + year + ", starring=" + starring + ", copies=" + copies + ", barcode=" + barcode + ", user_rating=" + user_rating + '}';
    }
    

    
    

    
    
    


    
    
    
}

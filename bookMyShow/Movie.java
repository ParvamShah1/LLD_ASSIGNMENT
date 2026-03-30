package bookMyShow;

public class Movie {

    private final String movieId;
    private final String title;
    private final String language;

    public Movie(String movieId, String title, String language) {
        this.movieId = movieId;
        this.title = title;
        this.language = language;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return title + " [" + language + "]";
    }
}

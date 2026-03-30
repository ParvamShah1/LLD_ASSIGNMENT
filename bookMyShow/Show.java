package bookMyShow;

import java.util.List;

public class Show {

    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final long showTimeMillis;
    private final List<ShowSeat> showSeats;

    public Show(String showId, Movie movie, Screen screen, long showTimeMillis, List<ShowSeat> showSeats) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.showTimeMillis = showTimeMillis;
        this.showSeats = showSeats;
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public long getShowTimeMillis() {
        return showTimeMillis;
    }

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }

    @Override
    public String toString() {
        return movie.getTitle() + " on " + screen.getScreenName();
    }
}

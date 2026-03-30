package bookMyShow;

import java.util.ArrayList;
import java.util.List;

public class Theater {

    private final String theaterId;
    private final String theaterName;
    private final String location;
    private final List<Screen> screens;

    public Theater(String theaterId, String theaterName, String location) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.location = location;
        this.screens = new ArrayList<>();
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public String getTheaterId() {
        return theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public String getLocation() {
        return location;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    @Override
    public String toString() {
        return theaterName + ", " + location;
    }
}

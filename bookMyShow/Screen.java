package bookMyShow;

public class Screen {

    private final String screenId;
    private final String screenName;
    private final Theater theater;

    public Screen(String screenId, String screenName, Theater theater) {
        this.screenId = screenId;
        this.screenName = screenName;
        this.theater = theater;
    }

    public String getScreenId() {
        return screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public Theater getTheater() {
        return theater;
    }

    @Override
    public String toString() {
        return screenName + " @ " + theater.getTheaterName();
    }
}

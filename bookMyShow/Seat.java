package bookMyShow;

public class Seat {

    private final String seatId;
    private final int rowNum;
    private final int colNum;
    private final SeatCategory category;

    public Seat(String seatId, int rowNum, int colNum, SeatCategory category) {
        this.seatId = seatId;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.category = category;
    }

    public String getSeatId() {
        return seatId;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public SeatCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return seatId + " (Row " + rowNum + ", Col " + colNum + ", " + category + ")";
    }
}

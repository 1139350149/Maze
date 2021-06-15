package mapEntity;

public class Road implements MapSite {
    private Coordinate coordinate;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean enter(int orientation) {
        return true;
    }

    @Override
    public String toString() {
        return "Road";
    }
}

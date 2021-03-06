package mapEntity;

public class Wall implements MapSite {
    private Coordinate coordinate;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean enter(int orientation) {
        return false;
    }

    @Override
    public String toString() {
        return "Wall";
    }
}

package mapEntity;

import itemEntity.Item;

public class Room implements MapSite {
    private Coordinate coordinate;
    private MapSite[] sides;// up, down, left, right
    private Item item;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Room() {
        sides = new MapSite[4];
    }

    public void addDoor(Map map) {
        System.out.println(coordinate.getX() + "   " + coordinate.getY());
        if (coordinate.getX() == 0 || coordinate.getX() == map.getRow() - 1 || coordinate.getY() == 0 || coordinate.getY() == map.getCol() - 1) {
            return;
        }
        if (map.getSide(new Coordinate(coordinate.getX(), coordinate.getY() - 1)) instanceof Wall) {
            sides[0] = map.getSide(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
        } else if (map.getSide(new Coordinate(coordinate.getX(), coordinate.getY() - 1)) instanceof Road) {
            sides[0] = new Door();
        }
        if (map.getSide(new Coordinate(coordinate.getX(), coordinate.getY() + 1)) instanceof Wall) {
            sides[1] = map.getSide(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
        } else if (map.getSide(new Coordinate(coordinate.getX(), coordinate.getY() + 1)) instanceof Road) {
            sides[1] = new Door();
        }
        if (map.getSide(new Coordinate(coordinate.getX() - 1, coordinate.getY())) instanceof Wall) {
            sides[2] = map.getSide(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
        } else if (map.getSide(new Coordinate(coordinate.getX() - 1, coordinate.getY())) instanceof Road) {
            sides[2] = new Door();
        }
        if (map.getSide(new Coordinate(coordinate.getX() + 1, coordinate.getY())) instanceof Wall) {
            sides[3] = map.getSide(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
        } else if (map.getSide(new Coordinate(coordinate.getX() + 1, coordinate.getY())) instanceof Road) {
            sides[3] = new Door();
        }
    }

    public MapSite[] getSide() {
        return sides;
    }

    /**
     * @param side        null -> no wall
     * @param orientation 0 up 1 right 2 down 3 left
     */
    public void setSide(MapSite side, int orientation) {
        sides[orientation] = side;
    }

    @Override
    public boolean enter(int orientation) {
        switch (orientation) {
            case 0: {
                return sides[1].enter(orientation);
            }
            case 1: {
                return sides[0].enter(orientation);
            }
            case 2: {
                return sides[3].enter(orientation);
            }
            case 3: {
                return sides[2].enter(orientation);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Room";
    }
}

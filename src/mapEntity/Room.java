package mapEntity;

import itemEntity.Item;

public class Room extends MapSite {
    private MapSite[] sides;
    private int roomNum;
    private Item item;

    public Room() {

    }

    public Room(int roomNum){
        this.roomNum= roomNum;
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
    public void enter() {
        System.out.println("You enter the room.");
    }
}

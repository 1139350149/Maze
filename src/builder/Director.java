package builder;

import mapEntity.Map;

public class Director {
    private int capacity;
    private int roomCount;

    public Director(){
        capacity = 30;
        roomCount = 3;
    }

    public Map Build(Builder builder){
        builder.buildMap(capacity);
        builder.buildRoom(roomCount);
        builder.buildEnemy();
        return builder.getProduct();
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

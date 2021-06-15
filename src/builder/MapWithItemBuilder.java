package builder;

import factory.AppleFactory;
import factory.BananaFactory;
import factory.HealthFactory;
import factory.ItemFactory;
import itemEntity.*;
import mapEntity.*;
import mapEntity.Map;

import java.util.*;

public class MapWithItemBuilder implements Builder {
    Map map;

    @Override
    public void buildMap(int quantity) {
        map = new Map();
        if (quantity % 2 == 0) {
            quantity += 1;
        }
        map.setCol(quantity);
        map.setRow(quantity);
        map.setMap(new MapSite[quantity][quantity]);
        Random random = new Random();
        int mode = random.nextInt(4);
        int startPos = random.nextInt(quantity - 1);
        if (startPos % 2 == 0) {
            startPos += 1;
        }
        switch (mode) {
            case 0: {
                map.setStart(new Coordinate(1, startPos));
                break;
            }
            case 1: {
                System.out.println(quantity - 2 + " " + startPos);
                map.setStart(new Coordinate(quantity - 2, startPos));
                break;
            }
            case 2: {
                map.setStart(new Coordinate(startPos, 1));
                break;
            }
            case 3: {
                map.setStart(new Coordinate(startPos, quantity - 2));
                break;
            }
        }

        for (int i = 1; i < quantity; ) {
            for (int j = 1; j < quantity; ) {
                map.setSide(new Coordinate(i, j), new Road());
                j += 2;
            }
            i += 2;
        }

        Stack<Coordinate> traceStack = new Stack<>();
        traceStack.push(map.getStart());
        ArrayList<Integer> choices = map.detectDestination(map.getStart());
        mode = random.nextInt(choices.size());
        Coordinate next = Map.getNextRoad(map.getStart(), choices.get(mode));
        map.setNextRoad(map.getStart(), choices.get(mode));
        traceStack.push(next);

        while (!traceStack.empty()) {
            Coordinate now = traceStack.peek();
            choices = map.detectDestination(now);
            if (choices.size() > 0) {
                mode = random.nextInt(choices.size());
                next = Map.getNextRoad(now, choices.get(mode));
                map.setNextRoad(now, choices.get(mode));
                traceStack.push(next);
            } else {
                traceStack.pop();
            }
        }

        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < quantity; j++) {
                if (map.getSide(new Coordinate(i, j)) == null) {
                    map.setSide(new Coordinate(i, j), new Wall());
                }
            }
        }
        map.openEntryAndExit();
    }

    @Override
    public void buildRoom(int roomCount) {
        map.setRoomCount(roomCount);
        map.setRooms(new Room[roomCount]);
        boolean flag;
        for (int i = 0; i < map.getRoomCount(); i++) {
            flag = false;
            while (!flag) {
                Random random = new Random();
                int x = random.nextInt(map.getCol());
                int y = random.nextInt(map.getRow());
                Item item = null;
                ItemFactory factory = null;
                if (map.getSide(new Coordinate(x, y)) instanceof Road) {
                    flag = true;
                    switch (random.nextInt(3)) {
                        case 0: {
                            factory = new AppleFactory();
                            break;
                        }
                        case 1: {
                            factory = new BananaFactory();
                            break;
                        }
                        case 2: {
                            factory = new HealthFactory();
                            break;
                        }
                    }
                    item = factory.getItem();
                    Room room = new Room();
                    room.setCoordinate(new Coordinate(x, y));
                    room.addDoor(map);
                    room.setItem(item);
                    Room[] rooms = map.getRooms();
                    rooms[i] = room;
                    map.setRooms(rooms);
                    map.setSide(new Coordinate(x, y), room);
                }
            }
        }

    }

    @Override
    public void buildEnemy() {

    }

    @Override
    public Map getProduct() {
        return map;
    }

}

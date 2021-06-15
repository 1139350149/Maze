package mapEntity;

import observer.ConcreteObserver;
import observer.ScoreObserver;
import playerEntity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Map extends JPanel {
    private int row, col, roomCount;
    private long startTime;
    private Coordinate start, end;
    private MapSite[][] map;
    private Room[] rooms;
    private Player player;
    private ArrayList<Coordinate> way;

    private boolean hint;
    private Map mapObject;

    public Map() {
        this.startTime = new Date().getTime();
        this.mapObject = this;
        this.hint = false;
        this.setFocusable(true);
        this.requestFocus();
        this.player = Player.getPlayer();
        ConcreteObserver observer = new ConcreteObserver();
        ScoreObserver observer1 = new ScoreObserver();
        observer1.setMap(this);
        observer.setMap(this);
        this.player.add(observer);
        this.player.add(observer1);
        this.addKeyListener(new MyListener());
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSide(Coordinate coordinate, MapSite side) {
        map[coordinate.getX()][coordinate.getY()] = side;
    }

    public MapSite getSide(Coordinate coordinate) {
        return map[coordinate.getX()][coordinate.getY()];
    }

    /**
     * @param coordinate  position now
     * @param orientation 0 up,1 down, 2 left, 3 right
     * @return access or not
     */
    public boolean accessable(Coordinate coordinate, int orientation) {
        if (map[coordinate.getX()][coordinate.getY()] instanceof Road) {
            return true;
        } else if (map[coordinate.getX()][coordinate.getY()] instanceof Wall) {
            return false;
        } else if (map[coordinate.getX()][coordinate.getY()] instanceof Room) {
            return (map[coordinate.getX()][coordinate.getY()]).enter(orientation);
        }
        return false;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public MapSite[][] getMap() {
        return map;
    }

    public void setMap(MapSite[][] map) {
        this.map = map;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public Coordinate getStart() {
        return start;
    }

    public void setStart(Coordinate start) {
        this.start = start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public void setEnd(Coordinate end) {
        this.end = end;
    }

    public ArrayList<Coordinate> getWay() {
        return way;
    }

    public void setWay(ArrayList<Coordinate> way) {
        this.way = way;
    }

    public boolean isHint() {
        return hint;
    }

    public void setHint(boolean hint) {
        this.hint = hint;
    }

    public ArrayList<Integer> detectDestination(Coordinate now) {
        ArrayList<Integer> temp = new ArrayList<>();
        // 0上，1下，2左，3右
        if (now.getX() - 2 > 0 && getSide(new Coordinate(now.getX() - 1, now.getY())) == null) {
            if (detectConnection(new Coordinate(now.getX() - 2, now.getY()))) {
                temp.add(0);
            }
        }
        if (now.getX() + 2 < row && getSide(new Coordinate(now.getX() + 1, now.getY())) == null) {
            if (detectConnection(new Coordinate(now.getX() + 2, now.getY()))) {
                temp.add(1);
            }
        }
        if (now.getY() - 2 > 0 && getSide(new Coordinate(now.getX(), now.getY() - 1)) == null) {
            if (detectConnection(new Coordinate(now.getX(), now.getY() - 2))) {
                temp.add(2);
            }
        }
        if (now.getY() + 2 < col && getSide(new Coordinate(now.getX(), now.getY() + 1)) == null) {
            if (detectConnection(new Coordinate(now.getX(), now.getY() + 2))) {
                temp.add(3);
            }
        }
        return temp;
    }

    public boolean detectConnection(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return (map[x - 1][y] == null && map[x + 1][y] == null && map[x][y - 1] == null && map[x][y + 1] == null);
    }

    public void setNextRoad(Coordinate coordinate, int mode) {
        switch (mode) {
            case 0: {
                setSide(new Coordinate(coordinate.getX() - 1, coordinate.getY()), new Road());
                break;
            }
            case 1: {
                setSide(new Coordinate(coordinate.getX() + 1, coordinate.getY()), new Road());
                break;
            }
            case 2: {
                setSide(new Coordinate(coordinate.getX(), coordinate.getY() - 1), new Road());
                break;
            }
            case 3: {
                setSide(new Coordinate(coordinate.getX(), coordinate.getY() + 1), new Road());
                break;
            }
        }
    }

    //0 上，1 下， 2 左， 3 右
    public static Coordinate getNextRoad(Coordinate coordinate, int mode) {
        switch (mode) {
            case 0: {
                return new Coordinate(coordinate.getX() - 2, coordinate.getY());
            }
            case 1: {
                return new Coordinate(coordinate.getX() + 2, coordinate.getY());
            }
            case 2: {
                return new Coordinate(coordinate.getX(), coordinate.getY() - 2);
            }
            case 3: {
                return new Coordinate(coordinate.getX(), coordinate.getY() + 2);
            }
        }
        return null;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintMap(g);
    }

    public void paintMap(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY);

        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getCol(); j++) {
                if (map[i][j] instanceof Wall) {
                    g2d.fillRect(10 * (i + 3), 10 * (j + 3), 10, 10);
                }
            }
        }

        for (int i = 0; i < roomCount; i++) {
            Room room = rooms[i];

            if (!room.getItem().isPicked()) {
                g2d.fillRect(10 * (room.getCoordinate().getX() + 3) + 2, 10 * (room.getCoordinate().getY() + 3) + 2, 6, 6);
            }

            MapSite[] doors = room.getSide();
            for (int j = 0; j < doors.length; j++) {
                if (doors[j] != null && doors[j] instanceof Door) {
                    if (!doors[j].enter(j)) {
                        switch (j) {
                            case 0: {
                                g2d.drawLine(10 * (room.getCoordinate().getX() + 3), 10 * (room.getCoordinate().getY() + 3), 10 * (room.getCoordinate().getX() + 4), 10 * (room.getCoordinate().getY() + 3));
                                break;
                            }
                            case 1: {
                                g2d.drawLine(10 * (room.getCoordinate().getX() + 3), 10 * (room.getCoordinate().getY() + 4), 10 * (room.getCoordinate().getX() + 4), 10 * (room.getCoordinate().getY() + 4));
                                break;
                            }
                            case 2: {
                                g2d.drawLine(10 * (room.getCoordinate().getX() + 3), 10 * (room.getCoordinate().getY() + 3), 10 * (room.getCoordinate().getX() + 3), 10 * (room.getCoordinate().getY() + 4));
                                break;
                            }
                            case 3: {
                                g2d.drawLine(10 * (room.getCoordinate().getX() + 4), 10 * (room.getCoordinate().getY() + 3), 10 * (room.getCoordinate().getX() + 4), 10 * (room.getCoordinate().getY() + 4));
                                break;
                            }

                        }
                    }
                }
            }
        }

        g2d.setColor(Color.GREEN);

        if (hint) {
            for (Coordinate o : way) {
                g2d.fillRect(10 * (o.getX() + 3), 10 * (o.getY() + 3), 10, 10);
            }
        }

        g2d.setColor(Color.orange);
        g2d.fillOval(10 * (player.getPosition().getX() + 3), 10 * (player.getPosition().getY() + 3), 9, 9);
        // 2. 填充一个矩形
//        g2d.fillRect(140, 20, 80, 100);
        g2d.dispose();
    }

    public void openEntryAndExit() {
        map[0][1] = new Road();
        map[col - 1][row - 2] = new Road();
    }


    public class MyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int x = player.getPosition().getX();
            int y = player.getPosition().getY();
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                player.setFace(1);
                System.out.println("向下");
                if (accessable(new Coordinate(x, y), 0)) {
                    if (accessable(new Coordinate(x, y + 1), 1)) {
                        player.down();
                    }
                }
                hint = false;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                player.setFace(3);
                System.out.println("向右");
                if (accessable(new Coordinate(x, y), 2)) {
                    if (accessable(new Coordinate(x + 1, y), 3)) {
                        player.right();
                    }
                }
                hint = false;
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                player.setFace(2);
                System.out.println("向左");
                if (accessable(new Coordinate(x, y), 3)) {
                    if (accessable(new Coordinate(x - 1, y), 2)) {
                        player.left();
                    }
                }
                hint = false;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                player.setFace(0);
                System.out.println("向上");
                if (accessable(new Coordinate(x, y), 1)) {
                    if (accessable(new Coordinate(x, y - 1), 0)) {
                        player.up();
                    }
                }
                hint = false;
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                hint = false;
                System.out.println("开门");
                switch (player.getFace()) {
                    case 0: {
                        if (map[x][y] instanceof Room) {
                            Room temp = (Room) map[x][y];
                            MapSite[] mapsites = temp.getSide();
                            if (mapsites[0] instanceof Door) {
                                Door door = (Door) mapsites[0];
                                door.toggle();
                                temp.setSide(door, 0);
                                map[x][y] = temp;
                            }
                        } else {
                            if (map[x][y - 1] instanceof Room) {
                                Room temp = (Room) map[x][y - 1];
                                MapSite[] mapsites = temp.getSide();
                                if (mapsites[1] instanceof Door) {
                                    Door door = (Door) mapsites[1];
                                    door.toggle();
                                    temp.setSide(door, 1);
                                    map[x][y - 1] = temp;
                                }
                            }
                        }
                        break;
                    }
                    case 1: {
                        if (map[x][y] instanceof Room) {
                            Room temp = (Room) map[x][y];
                            MapSite[] mapsites = temp.getSide();
                            if (mapsites[1] instanceof Door) {
                                Door door = (Door) mapsites[1];
                                door.toggle();
                                temp.setSide(door, 1);
                                map[x][y] = temp;
                            }
                        } else {
                            if (map[x][y + 1] instanceof Room) {
                                Room temp = (Room) map[x][y + 1];
                                MapSite[] mapsites = temp.getSide();
                                if (mapsites[0] instanceof Door) {
                                    Door door = (Door) mapsites[0];
                                    door.toggle();
                                    temp.setSide(door, 0);
                                    map[x][y + 1] = temp;
                                }
                            }
                        }
                        break;
                    }
                    case 2: {
                        if (map[x][y] instanceof Room) {
                            Room temp = (Room) map[x][y];
                            MapSite[] mapsites = temp.getSide();
                            if (mapsites[2] instanceof Door) {
                                Door door = (Door) mapsites[2];
                                door.toggle();
                                temp.setSide(door, 2);
                                map[x][y] = temp;
                            }
                        } else {
                            if (map[x - 1][y] instanceof Room) {
                                Room temp = (Room) map[x - 1][y];
                                MapSite[] mapsites = temp.getSide();
                                if (mapsites[3] instanceof Door) {
                                    Door door = (Door) mapsites[3];
                                    door.toggle();
                                    temp.setSide(door, 3);
                                    map[x - 1][y] = temp;
                                }
                            }
                        }
                        break;
                    }
                    case 3: {
                        if (map[x][y] instanceof Room) {
                            Room temp = (Room) map[x][y];
                            MapSite[] mapsites = temp.getSide();
                            if (mapsites[3] instanceof Door) {
                                Door door = (Door) mapsites[3];
                                door.toggle();
                                temp.setSide(door, 3);
                                map[x][y] = temp;
                            }
                        } else {
                            if (map[x + 1][y] instanceof Room) {
                                Room temp = (Room) map[x + 1][y];
                                MapSite[] mapsites = temp.getSide();
                                if (mapsites[2] instanceof Door) {
                                    Door door = (Door) mapsites[2];
                                    door.toggle();
                                    temp.setSide(door, 2);
                                    map[x + 1][y] = temp;
                                }
                            }
                        }
                        break;
                    }
                }
            } else if (e.getKeyCode() == KeyEvent.VK_Q) {
                if (map[x][y] instanceof Room) {
                    Room room = (Room) map[x][y];
                    if (!room.getItem().isPicked()){
                        room.getItem().getItem(player);
                        JOptionPane.showMessageDialog(mapObject, "你获得了一个" + room.getItem().toString());
                    }
                }
                hint = false;
            } else if (e.getKeyCode() == KeyEvent.VK_H) {
                hint = true;
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public void findWay() {
        way = new ArrayList<>();
        Stack<Coordinate> stack = new Stack<>();

        int[][] visited = new int[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                visited[i][j] = 0;
            }
        }

        stack.push(player.getPosition());
        visited[player.getPosition().getX()][player.getPosition().getY()] = 1;

        while (!stack.empty()) {
            Coordinate now = stack.peek();
            int x = now.getX(), y = now.getY();
            if (x - 1 > 0 && x - 1 < col - 1 && !(map[x - 1][y] instanceof Wall) && visited[x - 1][y] == 0) {
                visited[x - 1][y] = 1;
                stack.push(new Coordinate(x - 1, y));
            } else if (y + 1 > 0 && y + 1 < row - 1 && !(map[x][y + 1] instanceof Wall) && visited[x][y + 1] == 0) {
                visited[x][y + 1] = 1;
                stack.push(new Coordinate(x, y + 1));
            } else if (x + 1 > 0 && x + 1 < col - 1 && !(map[x + 1][y] instanceof Wall) && visited[x + 1][y] == 0) {
                visited[x + 1][y] = 1;
                stack.push(new Coordinate(x + 1, y));
            } else if (y - 1 > 0 && y - 1 < row - 1 && !(map[x][y - 1] instanceof Wall) && visited[x][y - 1] == 0) {
                visited[x][y - 1] = 1;
                stack.push(new Coordinate(x, y - 1));
            } else {
                stack.pop();
            }
            if (stack.peek().getX() == col - 2) {
                if (stack.peek().getY() == col - 2) {
                    break;
                }
            }
        }
        while (!stack.empty()) {
            way.add(stack.pop());
        }
    }
}

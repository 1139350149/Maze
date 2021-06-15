package playerEntity;

import observer.ConcreteObserver;
import observer.Observer;
import mapEntity.Coordinate;

import java.util.ArrayList;

public class Player extends Subject {

    private static volatile Player player = null;
    private int mark;
    private int health;
    private Coordinate position;
    private int face; // 0 up 1 down 2 left 3 right

    private Player() {
        this.mark = 0;
        this.health = 3;
        this.position = new Coordinate(1, 1);
        this.face = 3;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public static synchronized Player getPlayer() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public void down(){
        position = new Coordinate(position.getX(), position.getY() + 1);
        notifyObserver();
    }

    public void up(){
        position = new Coordinate(position.getX(), position.getY() - 1);
        notifyObserver();
    }

    public void left(){
        position = new Coordinate(position.getX() - 1, position.getY());
        notifyObserver();
    }

    public void right(){
        position = new Coordinate(position.getX() + 1, position.getY());
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        for (Observer observer: observers){
            observer.response();
        }
    }
}

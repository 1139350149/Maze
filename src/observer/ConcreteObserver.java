package observer;

import mapEntity.Coordinate;
import mapEntity.Map;

import java.util.ArrayList;
import java.util.Stack;

public class ConcreteObserver implements Observer {
    private Map map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public void response() {
        map.findWay();
    }
}

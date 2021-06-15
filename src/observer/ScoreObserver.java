package observer;

import mapEntity.Map;

import javax.swing.*;
import java.util.Date;

public class ScoreObserver implements Observer {
    private Map map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public void response() {
        long endTime = new Date().getTime();
        long duration = (endTime - map.getStartTime()) / 1000;
        long score = (6 * map.getCol() - duration) > 0 ? (6 * map.getCol() - duration) : 0;
        if (map.getPlayer().getPosition().getX() == map.getRow() - 2 && map.getPlayer().getPosition().getY() == map.getCol() - 2) {
            JOptionPane.showMessageDialog(map, "你的得分是" + (score + map.getPlayer().getHealth() * 50 + map.getPlayer().getMark()));
        }
    }
}

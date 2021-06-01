package itemEntity;

import playerEntity.Player;

public class Apple implements Item {
    public int mark = 10;
    @Override
    public void getItem(Player player) {
        player.setMark(player.getMark() + this.mark);
    }
}

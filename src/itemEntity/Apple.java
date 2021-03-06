package itemEntity;

import playerEntity.Player;

public class Apple implements Item {
    private int mark = 10;
    private boolean picked = false;

    @Override
    public void getItem(Player player) {
        if (!picked) {
            player.setMark(player.getMark() + this.mark);
            picked = true;
        }
    }

    @Override
    public boolean isPicked() {
        return picked;
    }

    @Override
    public String toString() {
        return "苹果";
    }
}

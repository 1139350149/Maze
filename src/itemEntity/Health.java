package itemEntity;

import playerEntity.Player;

public class Health implements Item {
    private int mark = 1;
    private boolean picked = false;

    @Override
    public void getItem(Player player) {
        if (!picked) {
            player.setHealth(player.getHealth() + this.mark);
            picked = true;
        }
    }

    @Override
    public boolean isPicked() {
        return picked;
    }

    @Override
    public String toString() {
        return "红心";

    }
}

package itemEntity;

import playerEntity.Player;

public interface Item {
    public void getItem(Player player);
    public boolean isPicked();
}

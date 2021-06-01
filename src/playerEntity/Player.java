package playerEntity;

public class Player {

    private static volatile Player player = null;
    private int mark;

    private Player() {
        this.mark = 0;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public static synchronized Player getPlayer() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }
}

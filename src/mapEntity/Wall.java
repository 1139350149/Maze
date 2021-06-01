package mapEntity;

public class Wall extends MapSite {
    @Override
    public void enter() {
        System.out.println("This is a wall. You can't enter.");
    }
}

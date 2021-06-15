package mapEntity;

public class Door implements MapSite {
    private boolean isOpen;

    @Override
    public boolean enter(int orientation) {
        return this.isOpen;
    }

    public Door(){
        this.isOpen = false;
    }

    public void toggle(){
        this.isOpen = !this.isOpen;
    }

    public void open(){
        this.isOpen = true;
    }

    public void close(){
        this.isOpen = false;
    }
}

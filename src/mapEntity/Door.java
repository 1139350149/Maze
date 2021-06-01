package mapEntity;

public class Door extends MapSite {
    private boolean isOpen;

    @Override
    public void enter() {
        if (this.isOpen) {
            System.out.println("You enter the door");
        }
        else{
            System.out.println("The door is closed, you can't enter it.");
        }
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

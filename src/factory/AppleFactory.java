package factory;


import itemEntity.*;

public class AppleFactory implements ItemFactory {
    @Override
    public Item getItem() {
        return new Apple();
    }
}

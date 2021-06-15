package factory;

import itemEntity.*;

public class BananaFactory implements ItemFactory {
    @Override
    public Item getItem() {
        return new Banana();
    }
}

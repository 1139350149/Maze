package factory;

import itemEntity.*;

public class HealthFactory implements ItemFactory {
    @Override
    public Item getItem() {
        return new Health();
    }
}

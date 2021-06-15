package builder;

import mapEntity.Map;

public interface Builder {
    void buildMap(int quantity);
    void buildRoom(int roomCount);
    void buildEnemy();

    public Map getProduct();
}

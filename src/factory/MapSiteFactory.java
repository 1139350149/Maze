package factory;

import mapEntity.*;

public class MapSiteFactory {
    public static MapSite getMapSite(String choice){
        switch (choice) {
            case "ROOM":{
                return new Room();
            }
            case "WALL":{
                return new Wall();
            }
            case "DOOR":{
                return new Door();
            }
        }
        return null;
    }
}

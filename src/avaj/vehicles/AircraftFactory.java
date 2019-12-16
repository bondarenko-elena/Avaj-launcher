package avaj.vehicles;

import avaj.utils.CustomException;
import avaj.weather.Coordinates;

public class AircraftFactory {
    public Flyable newAircraft(
            String type,
            String name,
            int longitude,
            int latitude,
            int height
    ) throws CustomException { /* ??? */
        Coordinates coordinates = new Coordinates( longitude, latitude, height );
        Flyable toReturn;
        if ( type.toLowerCase().equals( "helicopter" ) ) {
            toReturn = new Helicopter( name, coordinates );
        } else if ( type.toLowerCase().equals( "jetplane" ) ) {
            toReturn = new JetPlane( name, coordinates );
        } else if ( type.toLowerCase().equals( "baloon" ) ) {
            toReturn = new Baloon( name, coordinates );
        } else {
//            throw new IllegalArgumentException( "Wrong Flyable type:" + type );
            throw new CustomException( "Wrong Flyable type:" + type );
        }
        return toReturn;
    }
}

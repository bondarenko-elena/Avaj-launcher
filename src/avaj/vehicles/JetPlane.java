package avaj.vehicles;

import avaj.utils.Parser;
import avaj.weather.Coordinates;
import avaj.weather.WeatherTower;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane( String name, Coordinates coordinates ) {
        super( name, coordinates );
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather( this.coordinates );
        HashMap<String, String> messages = new HashMap<>();
        messages.put( "SUN", "This is hot." );
        messages.put( "RAIN", "It's raining. Better watch out for lightings." );
        messages.put( "FOG", "Oh no! I can't see anything." );
        messages.put( "SNOW", "OMG! Winter is coming!" );

        if ( weather.toLowerCase().equals( "sun" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 10,
                    coordinates.getHeight() + 2
            );
        } else if ( weather.toLowerCase().equals( "rain" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 5,
                    coordinates.getHeight() + 0
            );
        } else if ( weather.toLowerCase().equals( "fog" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 1,
                    coordinates.getHeight() + 0
            );
        } else if ( weather.toLowerCase().equals( "snow" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 7
            );
        }
        checkBoundaryValuesOfHeight( this.coordinates );
        writeResult( weather, weatherTower, messages, this.name, this.id, this, "JetPlane" );
    }

    @Override
    public void registerTower( WeatherTower weatherTower ) {
        this.weatherTower = weatherTower;
        this.weatherTower.register( this );
        Parser.writer.println( "Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower." );
    }
}

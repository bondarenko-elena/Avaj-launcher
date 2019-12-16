package avaj.vehicles;

import avaj.utils.Parser;
import avaj.weather.Coordinates;
import avaj.weather.WeatherTower;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter( String name, Coordinates coordinates ) {
        super( name, coordinates );
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather( this.coordinates );
        HashMap<String, String> messages = new HashMap<>();
        messages.put( "SUN", "This is hot." );
        messages.put( "RAIN", "Damn you rain! You messed up my baloon." );
        messages.put( "FOG", "Oh no! I can't see anything." );
        messages.put( "SNOW", "My rotor is going to freeze!" );

        if ( weather.toLowerCase().equals( "sun" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 10,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 2
            );
        } else if ( weather.toLowerCase().equals( "rain" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 5,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 0
            );
        } else if ( weather.toLowerCase().equals( "fog" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 1,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 0
            );
        } else if ( weather.toLowerCase().equals( "snow" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 12
            );
        }

        if ( this.coordinates.getHeight() < 0 )
            this.coordinates.setHeight(0);
        else if (this.coordinates.getHeight() > 100 )
            this.coordinates.setHeight(100);

        Parser.writer.println( "Helicopter#" + this.name + "(" + this.id + "): " + messages.get(
                weather.toUpperCase() ) );
        if ( this.coordinates.getHeight() == 0 ) {
            Parser.writer.println( "Helicopter#" + this.name + "(" + this.id + "): landing." );
            this.weatherTower.unregister( this );
            Parser.writer.println( "Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower." );
        }
    }

    public void registerTower( WeatherTower weatherTower ) {
        this.weatherTower = weatherTower;
        this.weatherTower.register( this );
        Parser.writer.println( "Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower." );
    }
}

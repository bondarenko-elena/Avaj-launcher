package avaj.vehicles;

import avaj.Main;
import avaj.weather.Coordinates;
import avaj.weather.WeatherTower;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane( String name, Coordinates coordinates ) {
        super( name, coordinates );
    }

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

        if ( this.coordinates.getHeight() < 0 )
            this.coordinates.setHeight(0);
        else if (this.coordinates.getHeight() > 100 )
            this.coordinates.setHeight(100);

        Main.writer.println( "JetPlane#" + this.name + "(" + this.id + "): " + messages.get(
                weather.toUpperCase() ) );
        if ( this.coordinates.getHeight() == 0 ) {
            Main.writer.println( "JetPlane#" + this.name + "(" + this.id + "): landing." );
            this.weatherTower.unregister( this );
            Main.writer.println( "Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower." );
        }
    }

    public void registerTower( WeatherTower weatherTower ) {
        this.weatherTower = weatherTower;
        this.weatherTower.register( this );
        Main.writer.println( "Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower." );
    }
}

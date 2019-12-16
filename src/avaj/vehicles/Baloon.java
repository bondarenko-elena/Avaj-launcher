package avaj.vehicles;

import avaj.Main;
import avaj.weather.Coordinates;
import avaj.weather.WeatherTower;

import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon( String name, Coordinates coordinates ) {
        super( name, coordinates );
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather( this.coordinates );
        HashMap<String, String> messages = new HashMap<>();
        messages.put( "SUN", "Let's enjoy the good weather and take some pics." );
        messages.put( "RAIN", "Damn you rain! You messed up my baloon." );
        messages.put( "FOG", "Oh no! I can't see anything." );
        messages.put( "SNOW", "It's snowing. We're gonna crash." );

        if ( weather.toLowerCase().equals( "sun" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 2,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 4
            );
        } else if ( weather.toLowerCase().equals( "rain" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 5
            );
        } else if ( weather.toLowerCase().equals( "fog" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 3
            );
        } else if ( weather.toLowerCase().equals( "snow" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 15
            );
        }

        if ( this.coordinates.getHeight() < 0 )
            this.coordinates.setHeight(0);
        else if (this.coordinates.getHeight() > 100 )
            this.coordinates.setHeight(100);

        Main.writer.println( "Baloon#" + this.name + "(" + this.id + "): " + messages.get(
                weather.toUpperCase() ) );
        if ( this.coordinates.getHeight() == 0 ) {
            Main.writer.println( "Baloon#" + this.name + "(" + this.id + "): landing." );
            this.weatherTower.unregister( this );
            Main.writer.println( "Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower." );
        }
    }

    public void registerTower( WeatherTower weatherTower ) {
        this.weatherTower = weatherTower;
        this.weatherTower.register( this );
        Main.writer.println( "Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower." );
    }
}

package avaj.vehicles;

import avaj.utils.Parser;
import avaj.weather.Coordinates;
import avaj.weather.WeatherProvider;
import avaj.weather.WeatherTower;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter( String name, Coordinates coordinates ) {
        super( name, coordinates );
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather( this.coordinates );
        HashMap<String, String> messages = WeatherProvider.getWeatherMessages();

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
        checkBoundaryValuesOfHeight( this.coordinates );
        writeResult( weather, weatherTower, messages, this.name, this.id, this, "Helicopter" );
    }

    @Override
    public void registerTower( WeatherTower weatherTower ) {
        this.weatherTower = weatherTower;
        this.weatherTower.register( this );
        Parser.writer.println( "Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower." );
    }
}

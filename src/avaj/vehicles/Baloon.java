package avaj.vehicles;

import avaj.utils.Parser;
import avaj.weather.Coordinates;
import avaj.weather.WeatherProvider;
import avaj.weather.WeatherTower;

import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon( String name, Coordinates coordinates ) {
        super( name, coordinates );
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather( this.coordinates );
        HashMap<String, String> messages =  WeatherProvider.getWeatherMessages();

        if ( weather.toLowerCase().equals( "sun" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 2,
                    coordinates.getLatitude(),
                    coordinates.getHeight() + 4
            );
        } else if ( weather.toLowerCase().equals( "rain" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                    coordinates.getHeight() - 5
            );
        } else if ( weather.toLowerCase().equals( "fog" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                    coordinates.getHeight() - 3
            );
        } else if ( weather.toLowerCase().equals( "snow" ) ) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                    coordinates.getHeight() - 15
            );
        }
        checkBoundaryValuesOfHeight( this.coordinates );
        writeResult( weather, weatherTower, messages, this.name, this.id, this, "Baloon" );
    }

    @Override
    public void registerTower( WeatherTower weatherTower ) {
        this.weatherTower = weatherTower;
        this.weatherTower.register( this );
        Parser.writer.println( "Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower." );
    }
}

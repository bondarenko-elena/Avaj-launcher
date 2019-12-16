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
        HashMap<String, String> messages = WeatherProvider.getWeatherMessages();
        switch ( weather.toLowerCase() ) {
            case "sun":
                this.coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4
                );
                break;
            case "rain":
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5
                );
                break;
            case "fog":
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3
                );
                break;
            case "snow":
                this.coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 15
                );
                break;
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

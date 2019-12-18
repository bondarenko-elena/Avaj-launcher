package avaj.weather;

import java.util.HashMap;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {
            "RAIN",
            "FOG",
            "SUN",
            "SNOW"
    };

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        WeatherProvider toReturn = WeatherProvider.weatherProvider;
        return toReturn;
    }

    public String getCurrentWeather( Coordinates coordinates ) {
        String toReturn = weather[( coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() ) % 4];
        return toReturn;
    }

    public static HashMap<String, String> getWeatherMessages() {
        HashMap<String, String> toReturn = new HashMap<>();
        toReturn.put( "SUN", "It is sunny." );
        toReturn.put( "RAIN", "It is rainy." );
        toReturn.put( "FOG", "It is foggy." );
        toReturn.put( "SNOW", "It is snowy." );
        return toReturn;
    }
}

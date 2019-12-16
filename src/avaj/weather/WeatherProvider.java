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
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather( Coordinates coordinates ) {
        return weather[( coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() ) % 4];
    }

    public static HashMap<String, String> getWeatherMessages() {
        HashMap<String, String> messages = new HashMap<>();
        messages.put( "SUN", "Let's enjoy the good weather and take some pics." );
        messages.put( "RAIN", "It's raining. Better watch out for lightings." );
        messages.put( "FOG", "Oh no! I can't see anything." );
        messages.put( "SNOW", "It's snowing. We're gonna crash." );
        return messages;
    }
}

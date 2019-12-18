package avaj.weather;

public class WeatherTower extends Tower {

    public String getWeather( Coordinates coordinates ) {
        String toReturn = WeatherProvider.getProvider().getCurrentWeather( coordinates );
        return toReturn;
    }

    public void changeWeather() {
        this.conditionChanged();
    }
}

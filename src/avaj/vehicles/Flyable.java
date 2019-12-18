package avaj.vehicles;

import avaj.weather.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerrTower( WeatherTower weatherTower);
}

package avaj.weather;

import avaj.vehicles.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {

    private List<Flyable> observers = new ArrayList<>();

    public void register( Flyable flyable ) {
        if ( observers.contains( flyable ) ) {
            return;
        }
        observers.add( flyable );
    }

    public void unregister( Flyable flyable ) {
        observers.remove( flyable );
    }

    protected void conditionChanged() {
        for ( int i = 0; i < observers.size(); i++ ) {
            observers.get( i ).updateConditions();
        }
    }
}

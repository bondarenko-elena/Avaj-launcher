package avaj.weather;

import avaj.vehicles.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {

    private List<Flyable> flyables = new ArrayList<>();

    public void register( Flyable flyable ) {
        if ( flyables.contains( flyable ) ) {
            return;
        }
        flyables.add( flyable );
    }

    public void unregister( Flyable flyable ) {
        flyables.remove( flyable );
    }

    protected void conditionChanged() {
        for ( int i = 0; i < flyables.size(); i++ ) {
            flyables.get( i ).updateConditions();
        }
    }
}

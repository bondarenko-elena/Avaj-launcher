package avaj.vehicles;

import avaj.utils.Parser;
import avaj.weather.Coordinates;
import avaj.weather.WeatherTower;

import java.util.HashMap;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter;

    protected Aircraft( String name, Coordinates coordinates ) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId() {
        idCounter++;
        return idCounter;
    }

    public void writeResult(
            String weather,
            WeatherTower weatherTower,
            HashMap<String, String> messages,
            String name,
            long id,
            Flyable vehicle,
            String vehicleType
    ) {
        String nameSharp = "";
        if ( vehicleType.toLowerCase().equals( "helicopter" ) ) {
            nameSharp = "Helicopter#";
        } else if ( vehicleType.toLowerCase().equals( "jetplane" ) ) {
            nameSharp = "JetPlane#";
        } else if ( vehicleType.toLowerCase().equals( "baloon" ) ) {
            nameSharp = "Baloon#";
        }
        Parser.writer.println( nameSharp + name + "(" + id + "): " + messages.get(
                weather.toUpperCase() ) );
        if ( coordinates.getHeight() == 0 ) {
            Parser.writer.println( nameSharp + name + "(" + id + "): landing." );
            weatherTower.unregister( vehicle );
            Parser.writer.println( "Tower says: " + nameSharp + name + "(" + id + ")" + " unregistered from weather tower." );
        }
    }

    public void checkBoundaryValuesOfHeight( Coordinates coordinates ) {
        if ( coordinates.getHeight() < 0 ) {
            coordinates.setHeight( 0 );
        } else if ( coordinates.getHeight() > 100 ) {
            coordinates.setHeight( 100 );
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public static long getIdCounter() {
        return idCounter;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setCoordinates( Coordinates coordinates ) {
        this.coordinates = coordinates;
    }

    public static void setIdCounter( long idCounter ) {
        Aircraft.idCounter = idCounter;
    }
}

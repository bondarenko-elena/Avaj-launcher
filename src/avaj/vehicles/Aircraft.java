package avaj.vehicles;

import avaj.weather.Coordinates;

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

package avaj.weather;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates( int longitude, int latitude, int height ) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight( int height ) {
        this.height = height;
    }
}

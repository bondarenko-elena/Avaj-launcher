package avaj.utils;

import avaj.vehicles.AircraftFactory;
import avaj.weather.WeatherTower;

import java.io.*;

public class Parser {
    public static PrintWriter writer;
    public static int cycle;
    private String fileName;
    File resultFile;
    AircraftFactory aircraftFactory = new AircraftFactory();
    WeatherTower weatherTower = new WeatherTower();

    public Parser( String fileName ) {
        this.fileName = fileName;
    }

    public void createResultFile() {
        try {
            resultFile = new File( "sources.txt" );
            writer = new PrintWriter( resultFile );
        } catch ( Exception ex ) {
            System.out.println( "Error: " + ex.getMessage() );
            return;
        }
        if ( resultFile.exists() && !resultFile.isDirectory() ) {
            writer.print( "" );
        }
    }

    public void parseScenario() {
        try {
            FileInputStream fis = new FileInputStream( fileName );
            BufferedReader br = new BufferedReader( new InputStreamReader( fis ) );
            String strLine;
            int line = 1;
            String[] strSplitted;

            while ( ( strLine = br.readLine() ) != null ) {
                if ( line == 1 ) {
                    try {
                        cycle = Integer.parseInt( strLine );
                        if ( cycle < 0 ) {
                            throw new CustomException( "Error in" + fileName + " : first line of scenario file must be > 0." );
                        }
                    } catch ( Exception ex ) {
                        throw new CustomException( "Error: first line in " + fileName + " file must be an integer." );
                    }
                } else {
                    strSplitted = strLine.split( " " );
                    if ( strSplitted.length == 1 && strSplitted[0].isEmpty() ) {
                        continue;
                    }
                    if ( strSplitted.length != 5 ) {
                        throw new CustomException( "Error: in " + line + " line: there must be 5 parameters." );
                    }
                    try {
                        aircraftFactory.newAircraft(
                                strSplitted[0],
                                strSplitted[1],
                                Integer.parseInt( strSplitted[2] ),
                                Integer.parseInt( strSplitted[3] ),
                                Integer.parseInt( strSplitted[4] )
                        ).registerTower( weatherTower );
                    } catch ( Exception ex ) {
                        System.out.println( "Error: " + ex.getMessage() );
                        return;
                    }
                }
                line++;
            }
            br.close();
        } catch ( Exception ex ) {
            System.out.println( "Error: " + ex.getMessage() );
            return;
        }
        while ( cycle > 0 ) {
            weatherTower.changeWeather();
            cycle--;
        }
        writer.close();
        System.out.println( "The simulator worked out pretty good. Check " + resultFile.getName() + "." );
    }
}

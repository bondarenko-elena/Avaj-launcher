package avaj.utils;

import avaj.vehicles.AircraftFactory;
import avaj.weather.WeatherTower;

import java.io.*;

public class Parser {
    public static PrintWriter writer;
    public static int cycles;
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
            FileInputStream fstream = new FileInputStream( fileName );
            BufferedReader br = new BufferedReader( new InputStreamReader( fstream ) );
            String strLine;
            int line = 1;
            String[] splitted;

            while ( ( strLine = br.readLine() ) != null ) {
                // Checking the first line that must be only 1 parameter as an integer.
                if ( line == 1 ) {
                    try {
                        cycles = Integer.parseInt( strLine );
                        if ( cycles < 0 ) {
                            System.out.println(
                                    "Error: first line of scenario file must be a POSITIVE integer." );
                            return;
                        }
                    } catch ( NumberFormatException e ) {
                        System.out.println( "Error: first line of scenario file must be an integer." );
                        return;
                    }
                }
                // For the other lines, we check for the format <String> <String> <Int> <Int> <Int>
                else {
                    splitted = strLine.split( " " );
                    if ( splitted.length == 1 && splitted[0].isEmpty() ) // Do not consider empty lines
                    {
                        continue;
                    }
                    if ( splitted.length != 5 ) // Check if there are 5 parameters
                    {
                        throw new CustomException( "Error: line " + line + ": there must be 5 parameters." );
                    }

                    try {
                        aircraftFactory.newAircraft(
                                splitted[0],
                                splitted[1],
                                Integer.parseInt( splitted[2] ),
                                Integer.parseInt( splitted[3] ),
                                Integer.parseInt( splitted[4] )
                        ).registerTower( weatherTower );
                    } catch ( NumberFormatException nfe ) {
                        System.out.println( "Error: line " + line + ": parameter from 3th to 5th must be integers." );
                        return;
                    } catch ( Exception e ) {
                        System.out.println( "Error: " + e.getMessage() );
                        return;
                    }
                }
                line++;
            }

            br.close();
        } catch ( Exception e ) {
            System.out.println( "Error: " + e.getMessage() );
            return;
        }

        while ( cycles > 0 ) {
            weatherTower.changeWeather();
            cycles--;
        }

        writer.close();
    }
}

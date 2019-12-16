package avaj;

import avaj.exception.CustomException;
import avaj.vehicles.AircraftFactory;
import avaj.weather.WeatherTower;

import java.io.*;

public class Main {
    public static PrintWriter writer;
    public static int cycles;

    public static void main( String[] args ) throws CustomException {
        if ( args.length < 1 ) {
            throw new CustomException( "Error: Looks like you forget about argument." );
        }
        String filename = args[0];

        File simulationFile = new File( "sources.txt" );
        try {
            writer = new PrintWriter( simulationFile );
        } catch ( FileNotFoundException e ) {
            System.out.println( "Error: " + e.getMessage() );
            return;
        }
        if ( simulationFile.exists() && !simulationFile.isDirectory() ) {
            writer.print( "" );
        }

        AircraftFactory aircraftFactory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();
        try {
            FileInputStream fstream = new FileInputStream( filename );
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

package avaj.utils;

import avaj.vehicles.AircraftFactory;
import avaj.weather.WeatherTower;

import java.io.*;

public class Parser {
    public static PrintWriter writer;
    public static int loop;
    private String fileName;
    File resultFile;
    private AircraftFactory aircraftFactory = new AircraftFactory();
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

    public void parseScenario( BufferedReader br ) {
        int countLine = 1;
        String strLine;
        String[] strSplitted;
        try {
            while ( ( strLine = br.readLine() ) != null ) {
                if ( countLine == 1 ) {
                    try {
                        if ( ( loop = Integer.parseInt( strLine ) ) < 0 ) {
                            throw new CustomException( "Error: in" + fileName + " file the first line must be a positive integer." );
                        }
                    } catch ( Exception ex ) {
                        System.out.println( "Error: first line in " + fileName + " file must be an integer." );
                        return;
                    }
                } else {
                    strSplitted = strLine.split( " " );
                    if ( strSplitted[0].isEmpty() && ( strSplitted.length == 1 ) ) {
                        continue;
                    }
                    if ( strSplitted.length != 5 ) {
                        System.out.println( "Error: in " + countLine + " line: must be only 5 parameters in " + fileName + " file." );
                        return;
                    }
                    try {
                        aircraftFactory.newAircraft(
                                strSplitted[0],
                                strSplitted[1],
                                Integer.parseInt( strSplitted[2] ),
                                Integer.parseInt( strSplitted[3] ),
                                Integer.parseInt( strSplitted[4] )
                        ).registerrTower( weatherTower );
                    } catch ( Exception ex ) {
                        System.out.println( "Error: " + ex.getMessage() );
                        return;
                    }
                }
                countLine++;
            }
        } catch ( Exception ex ) {
            System.out.println( ex.getMessage() );
        }
    }

    public void changeWeather(WeatherTower weatherTower, int loop) {
        while ( loop > 0 ) {
            weatherTower.changeWeather();
            loop--;
        }
    }

    public void readScenario() {
        FileInputStream fis;
        try {
            try {
                fis = new FileInputStream( fileName );
            } catch ( FileNotFoundException ex ) {
                System.out.println( "Error: There is no file with name " + fileName + "." );
                return;
            }
            BufferedReader br = new BufferedReader( new InputStreamReader( fis ) );
            parseScenario( br );
            br.close();
        } catch ( Exception ex ) {
            System.out.println( "Error: " + ex.getMessage() );
            return;
        }
        changeWeather( weatherTower, loop );
        writer.close();
        System.out.println( "The simulator worked out pretty good. Check " + resultFile.getName() + "." );
    }
}

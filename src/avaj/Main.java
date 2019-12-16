package avaj;

import avaj.utils.CustomException;
import avaj.utils.Parser;

public class Main {
    public static void main( String[] args ) throws CustomException {
        if ( args.length < 1 ) {
            throw new CustomException( "Error: Looks like you forget about argument." );
        } else if ( args.length > 1 ) {
            throw new CustomException(
                    "Error: Looks like you too many arguments. You need just one." );
        }
        Parser parser = new Parser( args[0] );
        parser.createResultFile();
        parser.parseScenario();
    }
}

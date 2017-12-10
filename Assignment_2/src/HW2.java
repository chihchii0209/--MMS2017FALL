import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class HW2 {

	public static void main(String[] args) throws LineUnavailableException {
		// TODO Auto-generated method stub
		byte[] buf = new byte[ 1 ];
		
		float t = 2000 ;
		float f = 880 ;
		
	    AudioFormat af = new AudioFormat( t , 8, 1, true, false );
	    SourceDataLine sdl = AudioSystem.getSourceDataLine( af );
	    sdl.open();
	    sdl.start();
	    
	    
	    for( int i = 0 ; i < 1000 * t  / 1000 ; i++ ) {
	    	
	        double angle = i / ( t  / f ) * 2.0 * Math.PI;
	        buf[ 0 ] = (byte )( Math.sin( angle ) * 100 );
	        
	        sdl.write( buf, 0, 1 );
	    }
	    
	    
	    sdl.drain();
	    sdl.stop();
	}

}

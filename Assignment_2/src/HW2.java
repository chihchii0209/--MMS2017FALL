import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class HW2 {

	public static void main(String[] args) throws LineUnavailableException, IOException {
		// TODO Auto-generated method stub
		byte[] buf = new byte[ 1 ];
		
		float FS = 2000 ;
		
		float T = 5 ;
		
		float[] f4 = {392, 330, 330, 0,
					349, 294, 294, 0, 
					262, 294, 330, 349,
					392, 392, 392} ;
		
		float[] f2 = {98, 82, 82, 0,
				87, 73, 73, 0,
				65, 73, 82, 87,
				98, 98, 98} ;
		
	    AudioFormat af = new AudioFormat( FS , 8, 1, true, false );
	    SourceDataLine sdl = AudioSystem.getSourceDataLine( af );
	    
	    File dstFile = new File("C:/Users/chih chi/Desktop/sample.wav");
		FileOutputStream out = new FileOutputStream(dstFile);
		
	    
	    sdl.open();
	    sdl.start();
	    
	    
	    for(int j =0 ; j<15 ; j++){
	    	for( int i = 0 ; i < T * FS /10 ; i++ ) {
	    	
	    		double angle = (i / FS)  * (f2[j] + f4[j])/2  * 2.0 * Math.PI ;
	    		

	    		buf[ 0 ] = (byte )( Math.sin(angle) * 100 );
	    	

	    		sdl.write( buf, 0, 1 );
	    		out.write(buf, 0, 1);
	    	}
	    }
	    
	    sdl.drain();
	    sdl.stop();
	}

}

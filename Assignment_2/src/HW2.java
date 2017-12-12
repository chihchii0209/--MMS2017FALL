import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class HW2 {

	public static void main(String[] args) throws LineUnavailableException, IOException {
		// TODO Auto-generated method stub
		
		String input ;
		Scanner scan = new Scanner(System.in);
		input = scan.nextLine() ;
		
		byte[] buf = new byte[ 1 ];
		
		float FS = 2000 ;
		
		float[] f4 = {262, 294, 330, 
					349, 392, 440, 494} ;
		
		float[] f2 = {65, 73, 82,
					87, 98, 110, 124} ;
		
		float[] sample = new float[50];
		float[] sample2 = new float[50] ;
		float T = 5 ;
		
		for(int i=0 ; i<input.length() ; i++){
			if(input.charAt(i)==' '){
				sample[i] = 0 ;
				sample2[i] = 0 ;
			}
			else{
				int index = input.charAt(i)-'0' ;
				sample[i] = f4[index-1] ;
				sample2[i] = f2[index-1] ;
				
			}
		}

		
	    AudioFormat af = new AudioFormat( FS , 8, 1, true, false );
	    SourceDataLine sdl = AudioSystem.getSourceDataLine( af );
	    
	    File dstFile = new File("C:/Users/chih chi/Desktop/sample.mp3");
		FileOutputStream out = new FileOutputStream(dstFile);
		
	    
	    sdl.open();
	    sdl.start();
	    
	    
	    for(int j =0 ; j<input.length() ; j++){
	    	for( int i = 0 ; i < T * FS /10 ; i++ ) {
	    	
	    		double angle = (i / FS)  * (sample[j]+sample2[j])/2  * 2.0 * Math.PI ;

	    		buf[ 0 ] = (byte )((Math.sin(angle)*(Math.sin(angle)) * 100 ));
	    	

	    		sdl.write( buf, 0, 1 );
	    		out.write(buf, 0, 1);
	    	}
	    }
	    
	    sdl.drain();
	    sdl.stop();
	    out.close();
	    
	}

}
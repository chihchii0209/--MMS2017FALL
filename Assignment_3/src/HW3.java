import java.io.IOException;
 import org.opencv.imgcodecs.*;
 import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
 import org.opencv.imgproc.Imgproc;
 
 
 public class HW3 extends SS_2D{
	 
	
 
	public static void main(String[] args) {
        
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
	
	Mat Rimg = Imgcodecs.imread("i1.pgm"); //Reference
   	Mat Timg = Imgcodecs.imread("i2.pgm"); //Target
   	Mat SS_img = Timg.clone() ;
   	Mat TwoD_img = Timg.clone() ;
   	
    SS(Rimg,Timg,SS_img);


	}
 }
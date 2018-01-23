import java.io.IOException;
 import org.opencv.imgcodecs.*;
 import org.opencv.core.Core;
 import org.opencv.core.Mat;
 import org.opencv.imgproc.Imgproc;
 
 
 public class HW3 {
 	
 	
     public static void main(String[] args) throws IOException {
         
     	System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
     	
         Mat img = Imgcodecs.imread("C:\\Users\\chih chi\\Desktop\\1.jpg") ;
 
         Imgcodecs.imwrite( "C:\\Users\\chih chi\\Desktop\\11.jpg", img);
        
         org.opencv.core.Size img_size = img.size();
         
         
     Imgcodecs.imwrite( "C:\\Users\\chih chi\\Desktop\\12.jpg", img);
     
     }
     
     
 }
import java.io.IOException;
 import org.opencv.imgcodecs.*;
 import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
 import org.opencv.imgproc.Imgproc;

 
 
 public class HW3 {
	 
	static final int N = 16;
	static final int p = 15; 
	
	public static void main(String[] args) throws IOException {
        
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
	
    Mat Rimg = Imgcodecs.imread("i1.jpg"); //Reference
   	Mat Timg = Imgcodecs.imread("i2.jpg"); //Target
   	Mat Clone_Rimg = Rimg.clone() ;
 
    	for(int mX = 0; mX < 240; mX=mX+N) {
            for(int mY = 0; mY < 320; mY=mY+N) { 
                double min_MAD = Double.MAX_VALUE;
                int u = 0, v = 0;

                for(int i = -p; i <= p; i++) {
                    for(int j = -p; j <= p; j++) {
                       
                        if(mX+i<0 || mX+i+N>240 || mY+j<0 || mY+j+N>320){
                            continue;
                        }
                        double cur_MAD = MAD(mX, mY, i, j, Rimg, Timg);
                        if(cur_MAD < min_MAD) {
                            min_MAD = cur_MAD;
                            u = i;
                            v = j;
                        }
                    }
                }

                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                    	Clone_Rimg.put(mX+i,mY+j, Rimg.get(mX+i+u, mY+j+v));
                    }
                }
            }
        }
    	
    	Imgcodecs.imwrite( "C:\\Users\\chih chi\\Desktop\\Res.jpg", Clone_Rimg);
    	 
    	 
    	 
     }
	
    static double MAD(int x, int y, int i, int j, Mat Rimg, Mat Timg) {
        double temp = 0.0f;
        for(int k = 0; k < N; k++) {
            for(int l = 0; l < N; l++) {
                temp += Math.abs(Timg.get(x+k, y+l)[0] - Rimg.get(x+i+k, y+j+l)[0]);
            }
        }
        return temp/(double)(N*N);
    }
    
    

 }
 
 
/* public class HW3 {
 	
 	
     public static void main(String[] args) throws IOException {
         
     	System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
     	
         Mat img = Imgcodecs.imread("C:\\Users\\chih chi\\Desktop\\1.jpg") ;
 
         Imgcodecs.imwrite( "C:\\Users\\chih chi\\Desktop\\11.jpg", img);
        
         org.opencv.core.Size img_size = img.size();
         
         
     Imgcodecs.imwrite( "C:\\Users\\chih chi\\Desktop\\12.jpg", img);
     
     }
     
     
 }*/
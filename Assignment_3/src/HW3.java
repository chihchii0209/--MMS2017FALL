import java.io.IOException;
 import org.opencv.imgcodecs.*;
 import org.opencv.core.Core;
 import org.opencv.core.Mat;
 import org.opencv.imgproc.Imgproc;


 public class HW3 {
	 
	 static Mat Rimg = Imgcodecs.imread("i1.pgm");
	 static Mat Timg = Imgcodecs.imread("i2.pgm"); 
	 
	 org.opencv.core.Size img_size = Rimg.size();
	 
    public static void main(String[] args) {
	 
	 org.opencv.core.Size img_size = Rimg.size();
	 
    	int p = 16,u,v ;
    	double min_MAD=9999 ;
    	
    	for(int w=0 ; w<img_size.width ; w=w+(2*p)+1)
    		for(int h=0 ; h<img_size.height ; h=h+(2*p)+1)
    			
    	 for(int i=-p ; i<p ; i++)
    		 for(int j=-p ; j<p ; j++){
    			 double cur_MAD = MAD(i,j) ;
    			 if(cur_MAD < min_MAD){
    				 min_MAD = cur_MAD ;
    				 u=i ;
    				 v=j ;
    			 }
    		 }
    	 
    	 
    	 
     }
	
	public static double MAD(int i, int j){
		int N=16 ;
		
		for(int k=0 ; k<N-1 ; k++){
			for(int l=0 ; l<N-1 ; l++){
				
				double[] Rpix = Rimg.get(x, y) ;
				double[] Tpix = Timg.get(x, y) ;
				
				
			}
		}
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
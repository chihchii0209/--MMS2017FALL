
import java.io.IOException;
import org.opencv.imgcodecs.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class HW1 {
	
	
    public static void main(String[] args) throws IOException {
        
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
    	
        Mat img = Imgcodecs.imread("C:\\Users\\chih chi\\Desktop\\1.jpg") ;
        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2GRAY);

        Imgcodecs.imwrite( "C:\\Users\\chih chi\\Desktop\\GRAY_1.jpg", img);
       
        org.opencv.core.Size img_size = img.size();
        
        int[][] D = {{0, 8, 2, 10}, 
        			{12, 4, 14, 6},
        			{3, 11, 1, 9},
        			{15, 7, 13, 5}} ;
        
    for (int i = 0; i < img_size.height; i++)
        for (int j = 0; j < img_size.width; j++) {
            double[] data = img.get(i, j);
            
            int x = i % 4 ;
            int y = j % 4 ;
            
            if((data[0]/16)>D[x][y])
            	data[0] = 255 ;
            else
            	data[0] = 0 ;
            
            img.put(i, j, data);
        }

    Imgcodecs.imwrite( "C:\\Users\\chih chi\\Desktop\\Res.jpg", img);
    
    }
    
    
}

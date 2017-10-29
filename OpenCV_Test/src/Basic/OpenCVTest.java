package Basic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class OpenCVTest {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat m  = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("m = " + m.dump());
        
        File input = new File("Dog.jpg");
        BufferedImage image = ImageIO.read(input);
        Mat img = imread(image,0) ;
        
        Scalar intensity = img.at<uchar>(y, x);  
        
        Imgproc.cvtColor(source mat, destination mat1, Imgproc.COLOR_RGB2GRAY);
        
    }

	private static Mat imread(BufferedImage image, int i) {
		// TODO Auto-generated method stub
		return null;
	}


}

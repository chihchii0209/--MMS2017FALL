import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


public class SS_2D {
	
	final static int N = 16;
	final static int P = 15; 
	
	public static void SS(Mat Rimg, Mat Timg, Mat SS_Outimg){
		
		long SSStart = System.nanoTime();
		
		for(int mX = 0; mX < 240; mX=mX+N) {
            for(int mY = 0; mY < 320; mY=mY+N) { 
                double min_MAD = Double.MAX_VALUE;
                int u = 0, v = 0;

                for(int i = -P; i <= P; i++) {
                    for(int j = -P; j <= P; j++) {
                       
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
                    	SS_Outimg.put(mX+i,mY+j, Rimg.get(mX+i+u, mY+j+v));
                    }
                }
            }
        }
		 long SSEnd = System.nanoTime();
		 
    	Imgcodecs.imwrite( "C:\\Users\\chih chi\\Desktop\\SS_i2.jpg", SS_Outimg);
    	
    	System.out.println("SNR of SS : "+SNR(Timg,SS_Outimg)+"\nTime of SS : "+ (SSEnd-SSStart)/1000000000.0+"\n") ;
    	
     }
	
    static double MAD(int x, int y, int i, int j, Mat Rimg, Mat Timg) {
        double temp = 0.0f;
        for(int k = 0; k < N; k++) {
            for(int l = 0; l < N; l++) {
                temp += Math.abs(Timg.get(x+k, y+l)[0] - Rimg.get(x+i+k, y+j+l)[0]);
            }
        }
        return temp/(double)(N * N);
    }
    
    public static void TwoD(Mat Rimg,Mat Timg, Mat TwoD_Outimg){
    	
    	long TwoDStart = System.nanoTime();
    	
    	for(int imgX = 0; imgX < 240; imgX=imgX+N) {
            for(int imgY = 0; imgY < 320; imgY=imgY+N) {
                int offset = (int)Math.ceil(P/2.0);
                boolean check = false;
                int u = 0;
                int v = 0;
                while(!check) {

                    ArrayList<Map<String, Integer>> temp = getBlocks(imgX+u, imgY+v, offset);
                    double min_MAD = Double.MAX_VALUE;

                    int du=0,dv=0;

                    for (Map<String, Integer> map : temp) {
                        int x = map.get("x");
                        int y = map.get("y");
                        double cur_MAD = MAD(imgX, imgY, u+x, v+y, Rimg, Timg);
                        if(cur_MAD < min_MAD) {
                            min_MAD = cur_MAD;
                            du = x;
                            dv = y;
                        }
                    }

                    u = u + du;
                    v = v + dv;
                    
                    if(offset == 1)
                        check = true;
                    
                    offset = (int)Math.ceil(offset/2.0);
                }

                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                    	TwoD_Outimg.put(imgX+i,imgY+j, Rimg.get(imgX+i+u, imgY+j+v));
                    }
                }
            }
        }
    	
    	long TwoDEnd = System.nanoTime();
    	
    	Imgcodecs.imwrite( "C:\\Users\\chih chi\\Desktop\\2D_i2.jpg", TwoD_Outimg);
    	
    	System.out.println("SNR of 2D : "+SNR(Timg,TwoD_Outimg)+"\nTime of 2D : "+ (TwoDEnd-TwoDStart)/1000000000.0);  
    }

    private static ArrayList<Map<String, Integer>> getBlocks(int x, int y, int offset) {
        ArrayList<Map<String, Integer>> temp = new ArrayList<>();
        int w = 320;
        int h = 240;
        for(int i = -offset; i <= offset; i=i+offset) {
            for(int j = -offset; j <= offset; j=j+offset) {
                if(x+i<0 || x+i+N>h || y+j<0 || y+j+N>w) { 
                	continue;
                }
                Map<String, Integer> m = new HashMap<>();
                m.put("x", i);
                m.put("y", j);
                temp.add(m);
            }
        }
        return temp;
    }

    static double SNR(Mat Timg, Mat Outimg) {
    	double SNR = 0.0f;
    	
        double num = 0.0f;
        double den = 0.0f;
        for(int i=0;i<240;i++) {
            for(int j=0;j<320;j++) {
                num=num+Math.pow(Outimg.get(i,j)[0],2.0f);
                den=den+Math.pow(Timg.get(i,j)[0]-Outimg.get(i,j)[0],2.0f);
            }
        }
        SNR = num/den;
        
        
        return SNR ;
    }
   
}

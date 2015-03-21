import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class InitialPoints {
	int K;
	
	public InitialPoints(int K){
		this.K = K;
	}
	
	protected void generate(){
		Random numberGenerater = new Random();
		try {
	          File file = new File("InitialPoints.txt");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          for(int i = 0; i < K; i++){
	        	int x = numberGenerater.nextInt(10001);
	        	int y = numberGenerater.nextInt(10001);
	        	output.write(x+","+y+"\n");
	          }
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	    }
	}
}

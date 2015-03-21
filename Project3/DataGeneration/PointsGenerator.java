import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class PointsGenerator {
	int number;
	public PointsGenerator(int number){
		this.number = number;
	}
	
	protected void generate(){
		Random numberGenerater = new Random();
		try {
	          File file = new File("Points.txt");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          for(int i = 0; i < number; i++){
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

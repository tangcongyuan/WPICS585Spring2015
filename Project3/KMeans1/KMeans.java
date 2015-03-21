package org.eric;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.lib.*;
import org.apache.hadoop.util.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class KMeans {

	private static void generatePoints(int K){
		Random numberGenerater = new Random();
		try {
	          File file = new File("/home/ubuntu/Workspace/project3/oldCentroids.txt");
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
	
	public static void confJob(JobConf conf, String arg0, String arg1, int i){
		
		Path inputPaths = new Path(arg0);
		Path outputPaths = new Path(arg1+Integer.toString(i));
		
		conf.setJobName("KMeans");
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		conf.setMapperClass(Mapper1.class);
		conf.setCombinerClass(Combiner1.class);
		conf.setReducerClass(Reducer1.class);
		conf.setNumReduceTasks(1);
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		FileInputFormat.setInputPaths(conf, inputPaths);
		FileOutputFormat.setOutputPath(conf, outputPaths);
		
	}
	
	public static boolean compareCentroids(){
		File oldFile = new File("/home/ubuntu/Workspace/project3/oldCentroids.txt");
		File newFile = new File("/home/ubuntu/Workspace/project3/newCentroids.txt");
		String[] oldCentroids = new String[100]; // Assume there will only be no more than 50 centroid points.
		String[] newCentroids = new String[100]; // Assume there will only be no more than 50 centroid points.
		int index1 = 0;
		int index2 = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(oldFile))){
			String line = null;
			while ((line = br.readLine()) != null){
				String[] Centroid = line.split(","); // All K centroids.
				oldCentroids[index1++] = Centroid[0];
				oldCentroids[index1++] = Centroid[1];
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(BufferedReader br = new BufferedReader(new FileReader(newFile))){
			String line = null;
			while ((line = br.readLine()) != null){
				String[] Centroid = line.split(","); // All K centroids.
				newCentroids[index2++] = Centroid[0];
				newCentroids[index2++] = Centroid[1];
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Check 
		if(Arrays.equals(oldCentroids, newCentroids)){
			return true;
		} else {
			try {
	          File file = new File("/home/ubuntu/Workspace/project3/oldCentroids.txt");
	          BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	          for(int i = 0; i < index1;i+=2){
	          	bw.write(newCentroids[i]+","+newCentroids[i+1] + "\n");
	          }
	          bw.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	    	}
		}
		// Clear the newCentroid.txt file, because we're using appending mode in Reducer.
		try {
	          File file = new File("/home/ubuntu/Workspace/project3/newCentroids.txt");
	          BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	          bw.write("");
	          bw.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	    	}
	    return false;
	}
	
	public static int iterate(String args0, String args1){
		for(int i = 0; i < 6; i++){
    		JobConf conf = new JobConf(KMeans.class);
    		confJob(conf, args0, args1, i);
    		try{
    			JobClient.runJob(conf);
    		} catch ( IOException e ) {
	           e.printStackTrace();
	    	}
    		if(compareCentroids()) return -1;
    	}
    	return 0;
	}

    public static void main(String[] args) throws Exception {
    
    	// Turn this to true if you want to regenerate the centroid points.
    	boolean regenerate_the_data = true;
    
    	int K = Integer.parseInt(args[2]);
    	if(regenerate_the_data)	generatePoints(K);
    	
    	iterate(args[0], args[1]);
    	
		
    }
}

package org.eric;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* This is the mapper function for customer dataset. */
public class Mapper1 extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
	private Text word = new Text();
	private Text outWord = new Text();

	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

		String line1 = value.toString();
		String[] Point = line1.split(","); // All data points.
		String Centroid_x = null;
		String Centroid_y = null;

		File file = new File("/home/ubuntu/Workspace/project3/oldCentroids.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line2 = null;
			double distance = Double.MAX_VALUE;
			while ((line2 = br.readLine()) != null){
				String[] Centroid = line2.split(","); // All K centroids.
				double temp = Math.sqrt(Math.pow((Double.parseDouble(Centroid[0])-Double.parseDouble(Point[0])), 2) + Math.pow((Double.parseDouble(Centroid[1])-Double.parseDouble(Point[1])), 2));
				if(distance > temp){
					distance = temp;
					Centroid_x = Centroid[0];
					Centroid_y = Centroid[1];
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	    word.set(Centroid_x + "," + Centroid_y);
		outWord.set(Point[0] + "," + Point[1]);
	   	output.collect(word, outWord);
	}
}

package org.eric;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* This is the only reducer function. */
public class Reducer1 extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
	
	private Text newCentroid = new Text();

	public void reduce(Text Centroid, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException 
	{
		double total_x = 0;
		double total_y = 0;
		int count = 0;
		while(values.hasNext()){
			String[] value = values.next().toString().split(",");
			total_x += Double.parseDouble(value[0]);
			total_y += Double.parseDouble(value[1]);
			count += Integer.parseInt(value[2]);
	    }
		double newCentroid_x = 0;
		double newCentroid_y = 0;
		newCentroid_x = total_x/count;
		newCentroid_y = total_y/count;
		try {
	          File file = new File("/home/ubuntu/Workspace/project3/newCentroids.txt");
	          BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
	          bw.write(newCentroid_x+","+newCentroid_y+"\n");
	          bw.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	    }
		String s = "" + newCentroid_x + "," + newCentroid_y;
	    newCentroid.set(s);
	    output.collect(Centroid, newCentroid);
	}
}

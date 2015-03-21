package org.eric;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;


public class Combiner1 extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
	
	private Text outWord = new Text();

	public void reduce(Text Centroid, Iterator<Text> points, OutputCollector<Text, Text> output, Reporter reporter) throws IOException
	{
		double total_x = 0;
		double total_y = 0;
		int count = 0;
		while(points.hasNext()){
			String[] point = points.next().toString().split(",");
			total_x += Double.parseDouble(point[0]);
			total_y += Double.parseDouble(point[1]);
			count++;
	    }
		
		
		String s = "" + total_x + "," + total_y + "," + count;
	    outWord.set(s);
	    output.collect(Centroid, outWord);
	}
}

##After generating the data points(name it "Points.txt", and put it under /home/ubuntu/Workspace/project3) from DataGenerator2.java file, please run the following command in order. Also, make sure there is no file called "oldCentroid.txt" and "newCentroid.txt" file in your folder, we'll generate them on the fly.

start-all.sh

hadoop fs -put /home/ubuntu/Workspace/project3/Points.txt /home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/project3/Points

javac -classpath /home/ubuntu/Workspace/hadoop-1.1.0/hadoop-core-1.1.0.jar -d /home/ubuntu/Workspace/project3/KMeans/KMeans_class /home/ubuntu/Workspace/project3/*.java

jar -cvf /home/ubuntu/Workspace/project3/KMeans.jar -C /home/ubuntu/Workspace/project3/KMeans/KMeans_class/ .

## This command takes three arguments: 1st to be the whole data points file in HDFS; 2nd to be the output file path(The actual output will be 6 files named from "finalResult0" to "finalResult5", and the final output is "finalResult5". We exported those files as txt, which is also included in our zip file); 3rd to be K. We assume K is no larger than 50.

hadoop jar /home/ubuntu/Workspace/project3/KMeans.jar org.eric.KMeans /home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/Points /home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/project3/finalResult/finalResult 5

stop-all.sh



#PS:
##There are also some sample output from our code. In those output, we used 1000 data points instead of 10,000,000 points. We also include the output of our Mapper function, combiner function, and reducer function, they are called "sampleMapperResult1000.txt", "sampleCombinerResult1000.txt", and "sampleReducerResult1000.txt" respectively.




















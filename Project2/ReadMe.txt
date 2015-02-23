To execute the file for the second query:
	1. Start Hadoop and Pig: > start-all.sh
	2. Clear output folder for query's result: > hadoop fs -rmr /home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/Query2-temp
											   > hadoop fs -rmr /home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/Query2-final
	3. Be sure to change the path of files (customer and transaction dataset) in the query2.pig file.
	4. Run script in batch mode: > pig PATH/query2.pig
	5. The final result will be in folder /home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/Query2-final
	

To execute the file for the sixth query:
	1. 
	2. Clear output folder for query's result: > hadoop fs -rmr /home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/query6
	3. Go to hadoop local directory.
	4. Make sure our Mapper and Reducer function is excutable.
	5. Specify the number of reducer to be 1 and run: > bin/hadoop jar contrib/streaming/hadoop-streaming-1.1.0.jar -D mapred.reduce.tasks=1 -file /home/ubuntu/Desktop/PMapper.py -mapper /home/ubuntu/Desktop/PMapper.py -file /home/ubuntu/Desktop/PReducer.py -reducer /home/ubuntu/Desktop/PReducer.py -input /home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/Transaction -output /home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/query6


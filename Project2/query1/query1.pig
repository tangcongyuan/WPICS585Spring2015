SET DEFAULT_PARALLEL 20;
trans = LOAD '/home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/Transaction' USING PigStorage(',') as (transid:int,custid:int, trxamt:float,noitem:int,desc:chararray);
A = group trans by custid;
B = foreach A generate group, COUNT(trans) as NumTransactions, SUM(trans.trxamt) as TotalSum;
STORE B INTO '/home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/project2/query1' USING PigStorage(','); 

%declare dataSource1 '/home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/Customer'
%declare dataSource2 '/home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/Transaction'
%declare tempOutputFile '/home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/Query4-temp'
%declare finalOutputFile '/home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/Query4-final'

raw1 = LOAD '$dataSource1' USING PigStorage(',') AS (CID1, CName, Age, CountryCode, Salary);

raw2 = LOAD '$dataSource2' USING PigStorage(',') AS (TID, CID2, TransTotal, TransNumItem:INT, TransDesc);

sub_raw1 = FOREACH raw1 GENERATE CID1, CName, Salary;

joinedTable = JOIN raw2 BY CID2, sub_raw1 BY CID1 USING 'replicated';

groupedTable = GROUP joinedTable BY CID1;

temp = FOREACH groupedTable GENERATE group, COUNT(joinedTable.CID1) AS NumOfTrans, SUM(joinedTable.TransTotal) AS TotalSum, MIN(joinedTable.TransNumItem) AS MinItems;

outputTable = JOIN temp BY group, sub_raw1 BY CID1 USING 'replicated';

STORE outputTable INTO '$tempOutputFile' USING PigStorage(',');

raw3 = LOAD '$tempOutputFile' USING PigStorage(',') AS (CID, NumOfTrans, TotalSum, MinItems, CID1, CName, Salary);

outputTable2 = FOREACH raw3 GENERATE CID, CName, Salary, NumOfTrans, TotalSum, MinItems;

STORE outputTable2 INTO '$finalOutputFile' USING PigStorage(',');

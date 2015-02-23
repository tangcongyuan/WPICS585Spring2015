#!/usr/bin/env python
 
import sys
 
# input comes from STDIN (standard input)
for line in sys.stdin:
    try: #sometimes bad data can cause errors use this how you like to deal with lint and bad data
         
        custID = "-1" #default sorted as first
        custName = "-1" #default sorted as first
        cc = "-1" #default sorted as first
        transID = "-1" #default sorted as first
         
        # remove leading and trailing whitespace
        line = line.strip()
         
        splits = line.split(",")
         
        if len(splits[1]) > 6: #customer DATA
            custID = splits[0]
            custName = splits[1]
            cc = splits[3] 	    
        else: #trx data
            transID = splits[0] 
	    custID = splits[1]           
         
        print '%s,%s,%s,%s' % (custID,custName,cc,transID)
    except: #errors are going to make your job fail which you may or may not want
        pass

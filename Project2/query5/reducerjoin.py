#!/usr/bin/env python

import sys

#initial value of current customer & count
currentCount = 0
currentCust = "-1"

# input comes from STDIN
for line in sys.stdin:
    # remove leading and trailing whitespace
	line = line.strip()
     
	try:
		# parse the input we got from mapperjoin.py
		custID,custName,cc,transID  = line.split(',')
	 
		#group based on cust ID starts here => check if cust ID match with current cust, if still the same, then add counter => this is possible bcoz input from mapper's already sorted.
		if(currentCust == custID):       	
			currentCount += 1
			if (transID == "-1" and cc == "5"):
				print '%s,%s,%s' % (custID,custName,currentCount)  #only print if country code = 5 and if the line is from customer data 
		else:
			currentCust = custID #set new customer ID
			currentCount= 0
		
	except:
		pass
 


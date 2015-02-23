#!/usr/bin/env python

import sys

for record in sys.stdin:
	transactions = record.split(',')
	transactions = [info.strip() for info in transactions]
	print '%s,%s' % (transactions[1], transactions[2])

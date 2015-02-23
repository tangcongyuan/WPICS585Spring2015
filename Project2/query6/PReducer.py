#!/usr/bin/env python

import sys

current_customer = None
current_amount = 0
current_count = 0
for record in sys.stdin:
	transactions = record.split(',')
	transactions = [info.strip() for info in transactions]
	customer = transactions[0]
	amount = float(transactions[1])
	if current_customer == customer:
		current_amount += amount
		current_count += 1
	else:
		if current_customer:
			print '%s,%s,%s' % (current_customer, current_count, current_amount)
		current_customer = customer
		current_amount = amount
		current_count = 1
if current_customer == customer:
	print '%s,%s,%s' % (customer, current_count, current_amount)

library(rmr2)

customercount =
	function(input, output = NULL,pattern = ","){
	
	cc.map = function(., lines) {
		customers.list = strsplit(x = lines, split = pattern)
		##get the country code as the key which is the fourth element of list from the splitted line
		keyval(unlist(sapply(customers.list,function(x) x[4])),'1')
	}

	cc.reduce = function(countrycode, counts ) {
		keyval(countrycode, 
			toString(sum(as.integer(counts)))) 
	}

	mapreduce(
	input = input,
	output = output,
	map = cc.map,
	reduce = cc.reduce,
	combine = TRUE)
}

##specify the input file and the output directory
inputPath = '/home/ubuntu/Workspace/hadoop-1.1.0/hadoop-data/customerinput'
outputPath = '/home/outputProject3'

customercount(inputPath, outputPath, pattern = ",")

results <- from.dfs(outputPath)
results

x <- results$key
y <- as.integer(results$val)

##create barplot
barplot(y, main="Customer Frequency", xlab="Country Code", ylab="Freq", names.arg=x)

##ordering the list based on customer count
o <-order(y)
#merge the ordered value with the country code
orderedResult <- rbind(x[o], y[o])
orderedResult

##create ordered barplot
barplot(y[o], main="Sorted Customer Frequency", xlab="Country Code", ylab="Freq", names.arg=x[o], col="green")

package org.codefiesta.hadoop.examples.twitter.mappers;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class InitPageRankMapper extends MapReduceBase implements Mapper<Text, Text, Text, Text> {

	public void map(Text key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		
		output.collect(key, new Text("1.00000 " + value.toString()));

	}

}
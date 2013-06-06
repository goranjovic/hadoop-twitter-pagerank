package org.codefiesta.hadoop.examples.twitter.mappers;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class UpdatePageRankMapper extends MapReduceBase implements Mapper<Text, Text, Text, Text> {

	private static final DecimalFormat df = new DecimalFormat("#.00000");
	
	public void map(Text key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {

		String[] val = value.toString().split(" ");
		
		Text targetKey = new Text();
		Text targetValue = new Text();

		if (val.length > 1) {

			double pr = Double.parseDouble(val[0]);
			String[] targets = val[1].split(",");

			if (targets.length > 0) {
				double piece = pr / targets.length;
				for (String target : targets) {
					targetKey.set(target);
					targetValue.set(df.format(piece));
					output.collect(targetKey, targetValue);
				}
			}
			targetKey.set(key);
			targetValue.set(df.format(0.0)  + " " + val[1]);
			output.collect(targetKey, targetValue);
			
		}
		

	}

}
package org.codefiesta.hadoop.examples.twitter.reducers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class AggregatePageRankReducer extends MapReduceBase implements
		Reducer<Text, Text, Text, Text> {

	private static final DecimalFormat df = new DecimalFormat("#.00000");

	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output,
			Reporter reporter) throws IOException {

		String targets = null;
		double sum = 0.0;
		while (values.hasNext()) {
			String[] value = values.next().toString().split(" ");
			double pr = Double.parseDouble(value[0]);
			sum += pr;
			if (value.length > 1 && value[1] != null) {
				targets = value[1];
			}
		}
		output.collect(key, new Text(df.format(sum) + " " + targets));

	}

}
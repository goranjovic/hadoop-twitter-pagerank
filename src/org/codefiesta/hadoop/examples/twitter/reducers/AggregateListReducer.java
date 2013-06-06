package org.codefiesta.hadoop.examples.twitter.reducers;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class AggregateListReducer extends MapReduceBase implements
		Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output,
			Reporter reporter) throws IOException {

		StringBuilder sb = new StringBuilder();
		boolean empty = true;
		while (values.hasNext()) {
			if (!empty) {
				sb.append(",");
			}
			empty = false;
			sb.append(values.next().toString());
		}
		output.collect(key, new Text(sb.toString()));

	}

}
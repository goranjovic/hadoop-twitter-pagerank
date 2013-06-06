package org.codefiesta.hadoop.examples.twitter.drivers;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.codefiesta.hadoop.examples.twitter.mappers.UpdatePageRankMapper;
import org.codefiesta.hadoop.examples.twitter.reducers.AggregatePageRankReducer;


public class UpdatePageRank extends Configured implements Tool {

	public int run(String[] args) throws Exception {

		Configuration conf = getConf();
		JobConf job = new JobConf(conf, this.getClass());
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		FileInputFormat.setInputPaths(job, in);
		FileOutputFormat.setOutputPath(job, out);
		job.setJobName(this.getClass().getName());
		job.setMapperClass(UpdatePageRankMapper.class);
		//job.setCombinerClass(AggregatePageRankReducer.class);
		job.setReducerClass(AggregatePageRankReducer.class);
		job.setInputFormat(KeyValueTextInputFormat.class);
		job.setOutputFormat(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		JobClient.runJob(job);
		return 0;

	}

}

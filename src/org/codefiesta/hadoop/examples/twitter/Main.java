package org.codefiesta.hadoop.examples.twitter;


import org.apache.commons.lang.time.StopWatch;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.ToolRunner;
import org.codefiesta.hadoop.examples.twitter.drivers.InitPageRank;
import org.codefiesta.hadoop.examples.twitter.drivers.PrepareTwitterData;
import org.codefiesta.hadoop.examples.twitter.drivers.UpdatePageRank;


public class Main {

	public static void main(String[] args) throws Exception {
		
		StopWatch timer = new StopWatch();
		timer.start();

		String dir = "hdfs://localhost:54310/user/goran/twitter/";
		
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://localhost:54310");
		FileSystem fs = FileSystem.get(conf);

		String[] prepareOpts = { dir + "large.txt", dir + "prepared.out" };
		ToolRunner.run(new Configuration(), new PrepareTwitterData(), prepareOpts);

		String[] initOpts = { dir + "prepared.out", dir + "pr-0.out" };
		ToolRunner.run(new Configuration(), new InitPageRank(), initOpts);
		
		fs.delete(new Path(dir + "prepared.out"), true);
		
		for(int i = 1; i < 10; i++){
			String previous = dir + "pr-" + (i - 1) + ".out";
			String current = dir + "pr-" + i + ".out";
			String[] opts = {previous, current};
			ToolRunner.run(new Configuration(), new UpdatePageRank(), opts);
			fs.delete(new Path(previous), true);
		}
		
		
		timer.stop();
		System.out.println("Elapsed " + timer.toString());

	}

}

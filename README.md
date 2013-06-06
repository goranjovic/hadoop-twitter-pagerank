hadoop-twitter-pagerank
=======================

Hadoop Example - A naive PageRank implementation for Twitter dataset

# Description

I created this project as a Hadoop MapReduce example which is more complicated than counting words, yet simple enough so that it may 
be easily undestood by a beginner. It is not intended as an actual production-ready Page Rank calculator.

Some features:
- Map Reduce framework - core functionality
- Multiple consecutive jobs - each depending on the previous results 
- FileSystem API - for cleaning up intermediate result files

This project is a work in progess, so stay tuned - more stuff is coming up!

# Installation and usage 


1. Install Hadoop, see [this page](http://www.michael-noll.com/tutorials/running-hadoop-on-ubuntu-linux-single-node-cluster) for Ubuntu or equivalent for your environment. 
2. (Optional) Install Hadoop Eclipse plugin, see [this page](http://wiki.apache.org/hadoop/EclipsePlugIn).
3. Download the Twitter dataset from [here](http://an.kaist.ac.kr/traces/WWW2010.html). The archive is several GB large, so this may take a while depending on the situation.
4. Create a smaller subset. If you are trying this out on a single machine you probably don't have the time to wait for the whole 26GB to be processed. 
You can create a file consisting of only first N lines with `head` command line utility, e.g. `head -n 10000000 twitter_rv.net > small.txt`
5. Change the file paths to correspond to your environment.
6. Run the example, either from Eclipse or by generating the jar and submitting it via command line.
7. On my laptop it takes about 20 min per each 10 million lines (or ~150 MB), so just wait a little and your results should be there

# License

Copyright (C) 2013 Goran Jovic

Distributed under the Eclipse Public License.
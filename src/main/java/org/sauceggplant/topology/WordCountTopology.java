package org.sauceggplant.topology;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;

//import backtype.storm.Config;
//import backtype.storm.StormSubmitter;
//import backtype.storm.topology.TopologyBuilder;

import org.sauceggplant.bolt.LogBolt;
import org.sauceggplant.bolt.WordCollectBolt;
import org.sauceggplant.bolt.WordCountBolt;
import org.sauceggplant.spout.WordSpout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jacob
 * @version 0.0.1.0
 */
public class WordCountTopology {

    private static Logger logger = LoggerFactory.getLogger(WordCountTopology.class);

    public static void main(String args[]) {
        try{
            TopologyBuilder topologyBuilder = new TopologyBuilder();
            topologyBuilder.setSpout("WordSpout",new WordSpout(),1);
            topologyBuilder.setBolt("WordCollectBolt",new WordCollectBolt(),1)
                    //.shuffleGrouping("WordSpout");
                    .setNumTasks(1)
                    .shuffleGrouping("WordSpout");
            topologyBuilder.setBolt("WordCountBolt",new WordCountBolt(),2)
                    //.shuffleGrouping("WordCollectBolt");
                    .setNumTasks(1)
                    .shuffleGrouping("WordCollectBolt");
            topologyBuilder.setBolt("LogBolt",new LogBolt(),1)
                    //.shuffleGrouping("WordCountBolt");
                    .setNumTasks(1)
                    .shuffleGrouping("WordCountBolt");

            Config config = new Config();
            config.setDebug(true);
            config.setNumWorkers(2);
            StormSubmitter.submitTopology("WordCountTopology",
                    config,
                    topologyBuilder.createTopology());
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}

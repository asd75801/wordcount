package org.sauceggplant.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jacob
 * @version 0.0.1.0
 */
public class LogBolt implements IRichBolt {

    /**
     * logger
     * */
    private static Logger logger = LoggerFactory.getLogger(LogBolt.class);

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

    }

    public void execute(Tuple input) {
        Map map = (Map)input.getValueByField("wordCountMap");
        Iterator iterator = map.keySet().iterator();
        logger.info("*****begin*****");
        while(iterator.hasNext()){
            String name = String.valueOf(iterator.next());
            logger.info(name+"\t"+((ArrayList<String>)map.get(name)).size()) ;
//            for(String time : (ArrayList<String>)map.get(name)){
//                logger.info(name+"\t"+time);
//            }
//            logger.info("**********");
        }
        logger.info("*****end*****");
    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}

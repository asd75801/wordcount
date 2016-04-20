package org.sauceggplant.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * tuple filed:name will be collected.each name and current system time build a map.
 * emit map to next tuple.
 *
 * @author jacob
 * @version 0.0.1.0
 */
public class WordCollectBolt implements IRichBolt {

    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {
        String name = String.valueOf(input.getValueByField("name"));
        Map map = new HashMap();
        map.put(name,System.currentTimeMillis());
        if(name!=null){
            collector.emit(new Values(map));
        }
    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("name-map"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}

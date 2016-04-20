package org.sauceggplant.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.*;

/**
 * @author jacob
 * @version 0.0.1.0
 */
public class WordCountBolt implements IRichBolt {

    private OutputCollector collector;

    private static Map<String, ArrayList<String>> wordCountMap = new HashMap<String, ArrayList<String>>();

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {
        Map map = (Map) input.getValueByField("name-map");
        addMap2WordCountMap(map);
        collector.emit(new Values(wordCountMap));
    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("wordCountMap"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    private void addMap2WordCountMap(Map map) {
        String name = null;
        boolean isExist = false;
        if (map.keySet().iterator().hasNext()) {
            name = String.valueOf(map.keySet().iterator().next());
            synchronized (wordCountMap) {
                Iterator iterator = wordCountMap.keySet().iterator();
                while (iterator.hasNext()) {
                    if (String.valueOf(iterator.next()).equals(name)) {
                        wordCountMap.get(name).add(String.valueOf(map.get(name)));
                        isExist=true;
                    }
                }
                if(!isExist){
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(String.valueOf(map.get(name)));
                    wordCountMap.put(name,list);
                }
            }
        }
    }
}

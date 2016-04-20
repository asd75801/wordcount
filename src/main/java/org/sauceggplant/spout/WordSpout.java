package org.sauceggplant.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;

import java.util.Map;

/**
 * @author jacob
 * @version 0.0.1.0
 */
public class WordSpout implements IRichSpout {

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {

    }

    public void close() {

    }

    public void activate() {

    }

    public void deactivate() {

    }

    public void nextTuple() {

    }

    public void ack(Object msgId) {

    }

    public void fail(Object msgId) {

    }
}

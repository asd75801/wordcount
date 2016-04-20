package org.sauceggplant.spout;


import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;

/**
 *
 * names as datasource,each 200ms create a new name,emit by nextTuple method.
 *
 * @author jacob
 * @version 0.0.1.0
 */
public class WordSpout implements IRichSpout {

    private SpoutOutputCollector collector;

    private Random random = new Random();

    private static String[] names={"zhangsan","lisi","wangwu","maliu","sunqi"};

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("name"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    public void close() {

    }

    public void activate() {

    }

    public void deactivate() {

    }

    public void nextTuple() {
        try {
            collector.emit(new Values(names[random.nextInt(names.length)]));
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ack(Object msgId) {

    }

    public void fail(Object msgId) {

    }
}

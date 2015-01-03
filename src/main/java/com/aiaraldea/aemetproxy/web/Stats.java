package com.aiaraldea.aemetproxy.web;

import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author inaki
 */
@XmlRootElement
public class Stats {

    private Map<Integer, Integer> counter;

    public Stats() {
    }

    public Stats(Map<Integer, Integer> counter) {
        this.counter = counter;
    }

    @XmlJavaTypeAdapter(MapAdapter.class)
    @XmlElement(name = "stats")
    public Map<Integer, Integer> getCounter() {
        return counter;
    }

    public void setCounter(Map<Integer, Integer> counter) {
        this.counter = counter;
    }

}

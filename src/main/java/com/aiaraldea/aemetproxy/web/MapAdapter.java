package com.aiaraldea.aemetproxy.web;

import java.util.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter extends XmlAdapter<MapAdapter.AdaptedMap, Map<Integer, Integer>> {

    public static class AdaptedMap {
        public List<Entry> entry = new ArrayList<>();
    }

    public static class Entry {
        public Integer key;
        public Integer value;
    }

    @Override
    public Map<Integer, Integer> unmarshal(AdaptedMap adaptedMap) throws Exception {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Entry entry : adaptedMap.entry) {
            map.put(entry.key, entry.value);
        }
        return map;
    }

    @Override
    public AdaptedMap marshal(Map<Integer, Integer> map) throws Exception {
        AdaptedMap adaptedMap = new AdaptedMap();
        for (Map.Entry<Integer, Integer> mapEntry : map.entrySet()) {
            Entry entry = new Entry();
            entry.key = mapEntry.getKey();
            entry.value = mapEntry.getValue();
            adaptedMap.entry.add(entry);
        }
        return adaptedMap;
    }
}

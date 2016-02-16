package org.sergez.twsearch.api;

/**
 * Created by simpumind on 2/16/16.
 */
import java.util.HashMap;
import java.util.Map;
public class Location {

    private String name;
    private Integer woeid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The woeid
     */
    public Integer getWoeid() {
        return woeid;
    }

    /**
     *
     * @param woeid
     * The woeid
     */
    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
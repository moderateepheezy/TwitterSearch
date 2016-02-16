package org.sergez.twsearch.api;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by simpumind on 2/16/16.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TrendList {

    private String asOf;
    private String createdAt;
    private List<Location> locations = new ArrayList<>();
    public List<Trend> trends = new ArrayList<>();
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     *
     * @return
     * The asOf
     */
    public String getAsOf() {
        return asOf;
    }

    /**
     *
     * @param asOf
     * The as_of
     */
    public void setAsOf(String asOf) {
        this.asOf = asOf;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     *
     * @param locations
     * The locations
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    /**
     *
     * @return
     * The trends
     */
    public List<Trend> getTrends() {
        return trends;
    }

    /**
     *
     * @param trends
     * The trends
     */
    public void setTrends(List<Trend> trends) {
        this.trends = trends;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


package it.reloia.tecnoutils.httpserver.data;

import java.io.Serializable;
import java.util.Map;

public class MarkersDataSet implements Serializable {
    private String label;
    private final Boolean toggleable = true;
    
    private Map<String, Marker> markers;

    public MarkersDataSet(String label, Map<String, Marker> markers) {
        this.label = label;
        this.markers = markers;
    }
}

package it.reloia.tecnoutils.httpserver.data;

import java.io.Serializable;
import java.util.List;

public class Marker implements Serializable {
    // TODO: maybe make subclass of Marker for other types
    private final String type = "shape";
    private String label;
    private String detail;
    private final int lineWidth = 2;
    private MarkerColor lineColor;
    private MarkerColor fillColor;
    
    private final float minDistance = 0.0f;
    private final float maxDistance = 1.0E7f;
    
    private List<MarkerShapeVertex> shape;
    
    private MarkerPosition position;
    
    private final boolean listed = true;

    public Marker(String label, MarkerColor lineColor, MarkerColor fillColor, List<MarkerShapeVertex> shape, MarkerPosition position) {
        this.label = label;
        this.detail = label;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        this.shape = shape;
        this.position = position;
    }
}

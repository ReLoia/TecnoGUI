package it.reloia.tecnoutils.httpserver.data;

import java.io.Serializable;

public class MarkerColor implements Serializable {
    private int r;
    private int g;
    private int b;
    private float a;

    public MarkerColor(int r, int g, int b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}

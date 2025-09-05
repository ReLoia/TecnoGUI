package it.reloia.tecnoutils.httpserver.data;

import java.io.Serializable;

public class MarkerPosition implements Serializable {
    private int x;
    private int y;
    private int z;

    public MarkerPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

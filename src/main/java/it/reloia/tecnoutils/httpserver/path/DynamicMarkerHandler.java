package it.reloia.tecnoutils.httpserver.path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.reloia.tecnoutils.httpserver.StaticMarkerSets;
import it.reloia.tecnoutils.httpserver.data.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class DynamicMarkerHandler implements HttpHandler {
    StaticMarkerSets staticMarkerSets = new StaticMarkerSets();
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                Map<String, MarkersDataSet> markerDataSets = getMarkerDataSets();

                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(markerDataSets);
                
                
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, json.getBytes().length);
                
                OutputStream os = exchange.getResponseBody();
                os.write(json.getBytes());
                os.close();
            } catch (Exception e) {
                String errorResponse = "{\"error\": \"Failed to retrieve the dynamic markers data\"}";
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(500, errorResponse.getBytes().length);
                
                OutputStream os = exchange.getResponseBody();
                os.write(errorResponse.getBytes());
                os.close();
            }
        }
        else {
            String response = "Method Not Allowed";
            exchange.sendResponseHeaders(405, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    private Map<String, MarkersDataSet> getMarkerDataSets() {
        Map<String, MarkersDataSet> markerData = new HashMap<>();

        markerData.put("public-structures-marker-set", staticMarkerSets.getPublicStructures());
//        markerData.put("private-structures-marker-set", staticMarkerSets.getPrivateStructures());
        
        return markerData;
    }
}

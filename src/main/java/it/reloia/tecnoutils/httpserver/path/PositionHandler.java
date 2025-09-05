package it.reloia.tecnoutils.httpserver.path;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.reloia.tecnoutils.dataparsing.TecnoData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

public class PositionHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                MinecraftClient client = MinecraftClient.getInstance();
                assert client.player != null;
                
                if (!TecnoData.INSTANCE.isInTecnoRoleplay) {
                     throw new Exception("Not in TecnoRoleplay");
                }
                
                Vec3d playerPos = client.player.getPos();
                
                // Locale is used to ensure the decimal separator is a dot
                String response = String.format(Locale.US, """
                        {
                          "players": [{
                            "uuid": "%s",
                            "name": "%s",
                            "foreign": false,
                            "position": {"x": %f, "y": %f, "z": %f},
                            "rotation": {"pitch": %d, "yaw": %d, "roll": %d}
                          }]
                        }""", client.player.getUuid(), client.player.getName().getString(), playerPos.getX(), playerPos.getY(), playerPos.getZ(), 0, 0, 0);
                
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                String errorResponse = "{\"error\": \"Failed to retrieve player position\"}";
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(500, errorResponse.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(errorResponse.getBytes());
                os.close();
            }
        } else {
            String response = "Method Not Allowed";
            exchange.sendResponseHeaders(405, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}

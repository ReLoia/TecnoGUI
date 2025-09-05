package it.reloia.tecnoutils.httpserver.path;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class AnyHandler implements HttpHandler { 
    private final String basePath;
    
    public AnyHandler() {
        basePath = "resources/tecnoutils/web";
    }
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestPath = exchange.getRequestURI().getPath();
        if (requestPath.equals("/")) {
            requestPath = "/index.html";
        }

        MinecraftClient mc = MinecraftClient.getInstance();
        
        assert mc != null;

        ResourceManager resourceManager = mc.getResourceManager();
        
        Path path = Paths.get(basePath, requestPath).normalize();
        
        if (!path.startsWith(Paths.get(basePath).normalize())) {
            exchange.sendResponseHeaders(403, -1);
            return;
        }

        if (requestPath.equals("/icon.png")) {
            requestPath = "/../icon.png";
        }
        
        Identifier id = new Identifier("tecnoutils", "web" + requestPath);

        Optional<Resource> optionalResource = resourceManager.getResource(id);
        
        if (optionalResource.isPresent()) {
            Resource resource = optionalResource.get();
            try (InputStream inputStream = resource.getInputStream()) {
                byte[] bytes = inputStream.readAllBytes();
                
                String contentType = guessContentType(requestPath);
                
                exchange.getResponseHeaders().add("Content-Type", contentType);
                exchange.sendResponseHeaders(200, bytes.length);
                
                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        exchange.sendResponseHeaders(404, -1);
    }


    private String guessContentType(String filename) {
        if (filename.endsWith(".html")) return "text/html";
        if (filename.endsWith(".js")) return "application/javascript";
        if (filename.endsWith(".css")) return "text/css";
        if (filename.endsWith(".png")) return "image/png";
        return "application/octet-stream";
    }
}

package enginer;

import java.util.HashMap;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Enginer {
    private final HashMap<String, Callable> routes = new HashMap();

    public void registerRouter(String name, Callable callback) {
        this.routes.put(name, callback);
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket client = serverSocket.accept();
                new Thread(() -> {
                    try {
                        Request request = new Request(client.getInputStream());
                        Response response = new Response(client.getOutputStream());
                        if (routes.containsKey(request.getUrl())) {
                            routes.get(request.getUrl()).callback(request, response);
                        } else {
                            response.setStatus(404).send(request.getUrl() + " not found");
                        }
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                }).start();
            }
        } catch(IOException error) {
            error.printStackTrace();
        }
    }
}

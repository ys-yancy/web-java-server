package enginer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Engineer {
    private final HashMap<String, Callable> routes = new HashMap();

    public void registerRouter(String name, Callable callback) {
        this.routes.put(name, callback);
    }

    public String getRouteName(String url) {
        String routeName = "";
        for (String baseRoute: routes.keySet()) {
            if (url.startsWith(baseRoute)) {
                routeName = baseRoute;
                break;
            }
        }

        return routeName;
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
                        String baseRouteName = this.getRouteName(request.getUrl());
                        if (baseRouteName.length() > 0) {
                            routes.get(baseRouteName).callback(request, response);
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

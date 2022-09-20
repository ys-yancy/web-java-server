import enginer.*;
import controller.*;

public class App {
    public Engineer register(Engineer engineer) {
        engineer.registerRouter(Main.baseRoute, (request, response) -> {
            new Main(request, response);
        });

        return engineer;
    }

    private void run() {
        this.register(new Engineer()).start(80);
    }

    public void start() {
        this.run();
    }

    public  static void  main(String[] args) {
        System.out.println("Server Run...");
        App server = new App();
        server.start();
    }
}
import enginer.*;

public class App {
    public Enginer register(Enginer enginer) {
        enginer.registerRouter("/test", (request, response) -> {
            response.setStatus(200);
            response.setHeaders("Content-Type", "application/json");
            response.send("{name: Http Server}");
        });

        return enginer;
    }

    private void run() {
        this.register(new Enginer()).start(80);
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
import enginer.*;

import java.lang.reflect.Array;

public class App {
    public Enginer register(Enginer enginer) {
        enginer.registerRouter("/test", (request, response) -> {
            // 生成参数
            Parameter parameter = new Parameter();
            parameter.setValue("name", 1);
            parameter.setValue("test", 2);
            parameter.setValue("value", 3);

            // response
            response.setStatus(200);
            response.setHeaders("Content-Type", "application/json");
            response.setHeaders("Access-Control-Allow-Origin", "*");
            response.send(parameter.toJsValue());
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
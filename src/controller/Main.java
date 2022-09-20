package controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import enginer.*;

public class Main extends Base {
    public static String baseRoute = "/main";

    public Main(Request request, Response response) {
        super(request, response);
    }

    @Override
    public void run (Request request, Response response) {
        String methodName = this.parseName(
                request.getUrl(),
                this.getBaseName()
        );
        methodName = new base.BaseString(methodName).firstToUpperCase();
        try {
            Class cls = this.getClass();
            Method callback = cls.getMethod(
                    "process" + methodName,
                    new Class[]{Request.class, Response.class}
            );
            Object[] args = {request, response};
            callback.invoke(this, args);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException error) {
            throw new RuntimeException(error);
        }
    }

    @Override
    public String getBaseName() {
        return Main.baseRoute;
    }

    private Message getParameter() {
        HashMap<String, Object> content = new HashMap();
        content.put("items", this.getItems());

        Message message = new Message(200, content);
        System.out.println(message.code);
        return message;
    }

    private int[] getItems() {
        int[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        return items;
    }

    public void processList(Request request, Response response) {
        // 生成参数
        Message parameter = this.getParameter();
        System.out.println(parameter.toJSON());
        // response
        response.setStatus(200);
        response.send(parameter.toJSON());
    }
}

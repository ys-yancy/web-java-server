package controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Vector;

import enginer.*;
import commodities.*;

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

    private HashMap<String, Object> getOaBasicInfo() {
        HashMap<String, Object> basicInfo = new HashMap();
        basicInfo.put("name", "OA系统");
        basicInfo.put("terminal", "mobile");
        return basicInfo;
    }

    public void processOaBasicInfo(Request request, Response response) {
        HashMap<String, Object> content = this.getOaBasicInfo();
        Message message = new Message(200, content);

        // response
        response.setStatus(200);
        response.send(message.toJSON());
    }

    /**
     * desc 购物商品
     * @param {Request} request
     * @param {Response} response
     */
    public void processCommodities(Request request, Response response) {
        HashMap query = request.getQueryMap();

        Vector<Object> commodities = new Meals("蛋糕").buildCommodities(
                Integer.parseInt((String) query.get("count"))
        );
        Message message = new Message(200, commodities);

        // response
        response.setStatus(200);
        response.send(message.toJSON());
    }
}

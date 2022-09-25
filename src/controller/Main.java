package controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Vector;

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

    private HashMap<String, Object> getOaBasicInfo() {
        HashMap<String, Object> basicInfo = new HashMap();
        basicInfo.put("name", "OA系统");
        basicInfo.put("terminal", "mobile");
        return basicInfo;
    }

    private HashMap<String, Object> createCommodities(int index) {
        HashMap<String, Object> item = new HashMap<>();

        // 之后改为类
        item.put("order", index);
        item.put("name", "吃货");
        item.put("price", "10.21");
        item.put("arrivalTime", "10: 21");
        item.put("storeName", "回龙观餐馆");
        item.put("storeLocation", "回龙观");

        return item;
    }

    private Vector<Object> getCommodities(int count) {
        // Object[] commodities = new Object[10];
        Vector<Object> commodities = new Vector<Object>(10, 5);
        for (int i = 1; i <= count; i++) {
            commodities.add(this.createCommodities(i));
        }

        return commodities;
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
        Vector<Object> commodities = this.getCommodities(
                Integer.parseInt((String) query.get("count"))
        );
        Message message = new Message(200, commodities);

        // response
        response.setStatus(200);
        response.send(message.toJSON());
    }
}

package commodities;

import java.util.Vector;
import java.util.HashMap;

public class Base<T> {
    private int index;

    private String name;

    public Base(String name) {
        System.out.print("commodities base init");
        this.index = 0;
        this.name = name;
    }

    public Base(String name, int index) {
        System.out.print("commodities base init");
        this.name = name;
        this.index = index;
    }

    protected HashMap<String, Object> buildCommodity() {
        HashMap<String, Object> commodity = new HashMap<>();
        commodity.put("name", this.name);
        commodity.put("order", this.index);
        commodity.put("arrivalTime", "预计30分钟内送达");
        commodity.put("image", new Image().getImage());
        commodity.put("storeName", new Merchantor().getMerchant());
        commodity.put("storeLocation", new Location().getLocation());
        commodity.put("price", String.format("%.2f", (float) (Math.random() * 50)));
        return commodity;
    }

    protected HashMap<String, Object> buildCommodity(String name, int index) {
        HashMap<String, Object> commodity = new HashMap<>();
        commodity.put("name", name);
        commodity.put("order", index);
        commodity.put("arrivalTime", "预计30分钟内送达");
        commodity.put("image", new Image().getImage());
        commodity.put("storeName", new Merchantor().getMerchant());
        commodity.put("storeLocation", new Location().getLocation());
        commodity.put("price", String.format("%.2f", (float) (Math.random() * 50)));
        return commodity;
    }

    public HashMap<String, Object> getCommodity() {
       return this.buildCommodity();
    }

    public Vector<Object> buildCommodities(int count) {
        Vector<Object> commodities = new Vector<>(10, 5);
        for (int index = 0; index < count; index++) {
            commodities.add(this.buildCommodity(this.name, index));
        }

        return commodities;
    }
}

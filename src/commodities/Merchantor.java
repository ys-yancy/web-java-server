package commodities;

public class Merchantor {
    private String[] merchants = {
        "小食谭记饭店",
        "蟹蟹餐饮店",
        "紫豪都饭店",
        "雅仕居饭店",
        "网鸿餐厅",
        "原粮粗菜饭店",
        "真火炭烤鱼",
        "溢香苑",
    };

    public String getMerchant() {
        int position = Math.min((int) (Math.random() * 10), 7);
        return this.merchants[position];
    }
}

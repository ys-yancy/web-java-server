package commodities;

public class Location {
    private String[] locationCollection = {
            "回龙观店",
            "上地店",
            "上地西里店",
            "西三旗总店"
    };

    public String getLocation() {
        int position = Math.min((int) (Math.random() * 10), 3);
        return this.locationCollection[position];
    }
}

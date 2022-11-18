package commodities;

import java.util.Vector;

public class Meals extends Base {
    public Meals(String name) {
        super(name);
    }

    public Meals(String name, int order) {
        super(name, order);
    }

    public Vector<Object> getMeals(int count) {
        return this.buildCommodities(count);
    }
}

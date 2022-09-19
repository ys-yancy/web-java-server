package enginer;

import java.util.HashMap;

public class Parameter {
    private final HashMap<String, Object> value = new HashMap();

    public HashMap<String, Object> setValue(String name, Object value) {
        this.value.put(name, value);
        return this.value;
    }

    public HashMap<String, Object> deleteValue(String name) {
        this.value.remove(name);
        return this.value;
    }

    public HashMap<String, Object> updateValue(String name, Object value) {
        this.value.put(name, value);
        return this.value;
    }

    public String toJsValue() {
        StringBuilder stringValue = new StringBuilder();
        stringValue.append("{");
        for (String name: this.value.keySet()) {
            stringValue.append("\"").append(name).append("\"").append(":").append(
                  this.value.get(name).toString()
            ).append(",");
        }

        int length = stringValue.length();

        // replace [,] -> [}];
        stringValue.replace(length - 1, length, "}");

        return stringValue.toString();
    }
}

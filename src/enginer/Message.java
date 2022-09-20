package enginer;

import com.alibaba.fastjson2.*;

public class Message {
    public int code;
    public Object content;

    public String message;

    public Message(int code, Object content) {
        System.out.println(code);
        this.code = code;
        this.content = content;
        this.message = "success";
    }

    public String toJSON() {
        String json = JSON.toJSONString(this);

        if (json == null) {
            System.out.println("json is null");
            return "{}";
        }

        return json;
    }
}

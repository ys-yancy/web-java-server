package enginer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Request {
    private String url;
    private String query;
    private String method;

    public Request(InputStream inputStream) {
        try {
            String requestStringValue = new BufferedReader(
                new InputStreamReader(inputStream)
            ).readLine();

            // empty request
            if (requestStringValue == null || requestStringValue == "") {
                this.setUrl("");
                this.setQuery("");
                this.setMethod("");
                return;
            }

            String[] requestLineValues = requestStringValue.split(" ");
            if (requestLineValues.length == 3 && requestLineValues[2].startsWith("HTTP/")) {
                String requestUrl = requestLineValues[1];
                if (requestUrl.contains("?")) {
                    this.setUrl(requestUrl.substring(0, requestUrl.indexOf("?")));
                    this.setQuery(requestUrl.substring(0, requestUrl.indexOf("?")) + 1);
                } else {
                    this.setUrl(requestUrl);
                    this.setQuery("");
                }
                this.setMethod(requestLineValues[0]);
            }

        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public String getQuery() {
        return query;
    }

    public String getMethod() {
        return method;
    }
}

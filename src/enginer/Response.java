package enginer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class Response {
    private final String httpVersion = "HTTP/1.1 ";
    private final String responseSeparator = "\n";
    private int status;
    private OutputStream outputStream;
    private HashMap<String, String> headers = new HashMap();

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public Response setHeaders(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    public Response setStatus(int status) {
        this.status = status;
        return this;
    }

    private StringBuilder responseBuilder() {
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append(
                this.httpVersion
        ).append(
                this.status
        ).append(this.responseSeparator);
        return dataBuilder;
    }

    public void send(String data) {
        try {
            StringBuilder dataBuilder = this.responseBuilder();
            for (String name: this.headers.keySet()) {
                dataBuilder.append(name).append(": ").append(
                        this.headers.get(name)
                ).append(this.responseSeparator);
            }
            dataBuilder.append(this.responseSeparator).append(data);
            outputStream.write(dataBuilder.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}

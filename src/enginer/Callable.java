package enginer;

@FunctionalInterface
public interface Callable {
    void callback(Request request, Response response);
}

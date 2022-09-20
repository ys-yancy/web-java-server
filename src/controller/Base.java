package controller;

import java.lang.reflect.*;
import java.lang.Class;
import enginer.Request;
import enginer.Response;

public abstract class Base {
    public Base(Request request, Response response) {
        this.run(request, response);
    }

    protected String parseName(String url, String base) {
        return url.replace(base + "/", "");
    }

    public abstract String getBaseName();

    public abstract void run (Request request, Response response);
}

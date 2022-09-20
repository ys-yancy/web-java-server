package base;

public class BaseString {
    private String value;

    public BaseString(String value) {
        this.value = value;
    }

    public String firstToUpperCase() {
        String firstStr = this.value.substring(0, 1);
        String restStr = this.value.substring(1);
        return  firstStr.toUpperCase() + restStr;
    }
}

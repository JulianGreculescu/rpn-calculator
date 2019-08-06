package au.com.gritmed.rpn.output;

public class ConsoleDisplay implements Display {
    @Override
    public void show(String data) {
        System.out.println(data);
    }
}

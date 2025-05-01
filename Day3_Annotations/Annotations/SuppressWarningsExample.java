package Annotations;
import java.util.ArrayList;

public class SuppressWarningsExample {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ArrayList list = new ArrayList(); // No generics used
        list.add("Java");
        list.add(42);
        System.out.println(list);
    }
}
package sdf.revision;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

// parameterized type, generics, template (C++)
public class Cart<T> {
    private final List<T> content = new LinkedList<>();

    public void add(T o) {
        content.add(o);
    }

    public Optional<T> get(int i) {
        if (i > content.size())
            return Optional.empty();
        return Optional.of(content.get(i));
    }

    public static void main(String[] args) {
        Cart<String> c = new Cart<>();
        c.add("apple");
        c.add("orange");
        c.add("1.5f");

        Cart<Float> intCart = new Cart<>();
        intCart.add(1.5f);

        Optional<String> opt = c.get(1);

        System.out.printf(">>>> %s\n", opt.orElse("grape"));

        opt = c.get(10);
        System.out.printf(">>>> 10: %s\n", opt.orElse("grapes"));

        opt = c.get(2);
        System.out.printf(">>>> 10: %s\n", opt.orElse("grapes"));
    }
    
}

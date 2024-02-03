package exercise;

import exercise.annotation.Inspect;
import exercise.model.Address;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (var method : address.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                var type = method.getReturnType().toString();
                System.out.printf(
                        "Method %s returns a value of type %s\n"
                        , method.getName()
                        , type.contains("String") ? "String" : type
                );
            }
        }
        // END
    }
}

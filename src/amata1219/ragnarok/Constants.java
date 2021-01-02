package amata1219.ragnarok;

import org.bukkit.NamespacedKey;

import java.util.function.Consumer;

public class Constants {

    static NamespacedKey executable_item_id;

    private final static Consumer<?> NO_OPERATION = it -> {};

    public static NamespacedKey executable_item_id() {
        return executable_item_id;
    }

    public static <T> Consumer<T> noOperation() {
        return (Consumer<T>) NO_OPERATION;
    }

}

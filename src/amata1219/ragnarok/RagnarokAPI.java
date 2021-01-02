package amata1219.ragnarok;

import amata1219.ragnarok.dsl.ExecutableItem;

public interface RagnarokAPI {

    void register(ExecutableItem item);

    void unregister(ExecutableItem item);

    boolean isRegistered(ExecutableItem item);

}

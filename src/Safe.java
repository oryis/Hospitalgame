import java.util.HashMap;

public class Safe extends Place {
    private HashMap<String, Item> safeItems; // Items inside the safe
    private boolean isLocked;

    // Constructor
    public Safe(String desc, boolean locked) {
        super(desc);
        this.safeItems = new HashMap<>();
        this.isLocked = locked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unlock() {
        isLocked = false;
    }

    public void lock() {
        isLocked = true;
    }

    public void putItem(Item item) {
        if (item != null) {
            safeItems.put(item.getName(), item); // Add item to the safe
        }
    }

    public Item getItem(String name) {
        return safeItems.get(name); // Retrieve item from the safe
    }

    public void removeItem(String name) {
        safeItems.remove(name); // Remove item from the safe
    }
}


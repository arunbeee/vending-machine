package models;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory<T> {
    private Map<T, Integer> inventory = new ConcurrentHashMap<T, Integer>();

    public int getQuantity(T item) {
        Integer value = inventory.get(item);
        return value == null ? 0 : value;
    }

    public void add(T item) {
        int count = getQuantity(item);
        put(item, count + 1);
    }

    public void deduct(T item) {
        if (hasItem(item)) {
            int count = inventory.get(item);
            put(item, count - 1);
        }
    }

    public boolean hasItem(T item) {
        return getQuantity(item) > 0;
    }

    public void clear() {
        inventory.clear();
    }

    public void put(T item, int quantity) {
        inventory.put(item, quantity);
    }

    public void removeAndAdd(T oldItem, T newItem) {
        int count = getQuantity(oldItem);
        inventory.remove(oldItem);
        put(newItem, count);
    }
}

package ca.shubbar.sorting;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mustafa <codingbox@outlook.com>
 * Created at 2021-01-17
 */
public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new HashMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        // Basic validation
        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            // already in the basket?
            int inBasket = list.getOrDefault(item, 0);
            int newQuantity = inBasket - quantity;
            if (newQuantity > 0) {
                list.put(item, newQuantity);
                return quantity;
            } else if (newQuantity == 0) {
                list.remove(item);
                return quantity;
            }
        }
        return 0;
    }

    public void clearBasket() {
        this.list.clear();
    }

    public Map<StockItem, Integer> items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping Basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + " -> " + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost: " + totalCost;
    }
}

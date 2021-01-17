package ca.shubbar.sorting;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mustafa <codingbox@outlook.com>
 * Created at 2021-01-17
 */
public class StocklList {
    private final Map<String, StockItem> list;


    public StocklList() {
        this.list = new HashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // check if there are already stocks from this item, adjust the quantity
            if (inStock != item) {
                // if they are not the same -> this item is already exists in the map, so we
                // adjust and pass the original quantity
                item.adjustStock(inStock.getQuantityStock());
            }
            // Otherwise, the item is new, no adjustment needed.

            // The list will not duplicate entries, so if its already exists nothing added.
            list.put(item.getName(), item);
            return item.getQuantityStock();
        }
        return 0;
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        if ((inStock != null) && (inStock.getQuantityStock() >= quantity) && (inStock.getQuantityStock() > 0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, StockItem> items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock List:\n";
        double totalCost = 0.0;
        for(Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();
            double itemValue = stockItem.getPrice() * stockItem.getQuantityStock();

            s = s + stockItem + "There are " + stockItem.getQuantityStock() + " in stock. Value of items: ";
            s = s + itemValue + "\n";
            totalCost += itemValue;
        }
        return s + "Total stock value: " + totalCost;
    }
}

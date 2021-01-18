package ca.shubbar.sorting;

import java.util.Map;

public class Main {
    private static StocklList stocklList = new StocklList();

    public static void main(String[] args) {

        // Testing

        System.out.println();
        StockItem temp = new StockItem("Sock", 2.99, 50);
        stocklList.addStock(temp);

        temp = new StockItem("Glove", 3.46, 10);
        stocklList.addStock(temp);

        temp = new StockItem("Water bottle", 7.00, 10);
        stocklList.addStock(temp);

        temp = new StockItem("Ruler", 1.45, 8);
        stocklList.addStock(temp);

        // Trying to invoke the adjust method
        temp = new StockItem("Ruler", 1.90, 20);
        stocklList.addStock(temp);


        System.out.println(stocklList);

        System.out.println("\n==================\nItems in stock:");
        for (String s : stocklList.items().keySet()) {
            System.out.println(s);
        }

        Basket customerBasket = new Basket("Customer 1");

        // Gloves now 10 -2 = 8;
        sellItem(customerBasket, "Glove", 2);
        System.out.println(customerBasket);

        // Rulers now 28 - 1 = 27
        sellItem(customerBasket, "Ruler", 1);
        System.out.println(customerBasket);

        // Trying to sell an item with a quantity more than available in stock
        // Trying to sell item not found in stock
        sellItem(customerBasket, "Water bottle", 50);
        sellItem(customerBasket, "Computer", 1);
        System.out.println(customerBasket);

        // Adding a new basket
        Basket newBasket = new Basket("Customer 2");
        sellItem(newBasket, "Glove", 100);
        sellItem(newBasket, "Ruler", 5);
        sellItem(newBasket, "Computer", 1);
        System.out.println(newBasket);

        removeItem(customerBasket, "Glove", 1);
        removeItem(newBasket, "Ruler", 1);

        System.out.println(customerBasket);
        System.out.println(newBasket);

        System.out.println("Printing stock items before and after checkout");
        System.out.println(stocklList);
        checkOut(newBasket);
        checkOut(customerBasket);
        System.out.println(stocklList);

        // now print the updated stockList
        System.out.println(stocklList);


        // This will cause an error because it's an unmodifiable map
        // Try to modify the list other way
//        temp = new StockItem("iPhone4", 680);
//        stocklList.items().put(temp.getName(), temp);

        // This will work because the collection itself is not modifiable, not the object
//        stocklList.items().get("Sock").adjustStock(5000);
//        stocklList.get("Sock").adjustStock(5000);
//        System.out.println(stocklList);


        for (Map.Entry<String, Double> price : stocklList.priceList().entrySet()) {
            System.out.println(price.getKey() + " costs " + price.getValue());
        }


    }

    public static int sellItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list
        StockItem stockItem = stocklList.get(item);
        if (stockItem == null) {
            System.out.println("Item not in stock, " + item);
            return 0;
        }
        if (stocklList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list
        StockItem stockItem = stocklList.get(item);
        if (stockItem == null) {
            System.out.println("Item not in stock, " + item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stocklList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkOut(Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.items().entrySet()) {
            stocklList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}


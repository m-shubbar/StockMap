package ca.shubbar.sorting;

public class Main {
    private static StocklList stocklList = new StocklList();

    public static void main(String[] args) {

        StockItem temp = new StockItem("Sock", 2.99, 50);
        stocklList.addStock(temp);

        temp = new StockItem("Glove", 3.46, 10);
        stocklList.addStock(temp);

        temp = new StockItem("Water bottle", 7.00, 10);
        stocklList.addStock(temp);

        temp = new StockItem("Ruler", 1.45, 8);
        stocklList.addStock(temp);

        temp = new StockItem("Picture Frame", 10.99, 25);
        stocklList.addStock(temp);

        temp = new StockItem("Shower mirror", 12.99, 20);
        stocklList.addStock(temp);

        temp = new StockItem("Alarm radio", 25.50, 5);
        stocklList.addStock(temp);

        temp = new StockItem("Book smart cover", 17.99, 10);
        stocklList.addStock(temp);


        System.out.println(stocklList);









    }
}


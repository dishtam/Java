package TradeMaster.DataStore;

import java.util.ArrayList;
import java.util.List;



public class StockStore {
    public static class Stock{
        public String symbol;
        public int price;

        Stock(String symbol,int price){
            this.symbol = symbol;
            this.price = price;
        }

        @Override
        public String toString() {
            return symbol + " " + price;
        }
    }
    public List<Stock> stocks = new ArrayList<>(List.of(
            new Stock("AAPL",100),
            new Stock("GOGL",200),
            new Stock("META",300)));
}

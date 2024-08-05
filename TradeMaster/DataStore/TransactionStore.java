package TradeMaster.DataStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionStore {
    public static class Transaction{
        public String username;
        public String symbol;
        public boolean type;
        public int quantity;

        public Transaction(String username, String symbol, boolean type, int quantity){
            this.username = username;
            this.symbol = symbol;
            this.type = type;
            this.quantity = quantity;
        }

        @Override
        public String toString(){
            return (username + " has did a " + (type ? "buy" : "sell ") + "of " + symbol + " for " + quantity + " shares");
        }
    }

    public static Map<String, Integer> buyOrders = new HashMap<>();
    public static Map<String, Integer> sellOrders = new HashMap<>();
    public static Map<String, List<String>> history = new HashMap<>(); // Corrected initialization
}

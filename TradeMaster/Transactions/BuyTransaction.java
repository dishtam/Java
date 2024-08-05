package TradeMaster.Transactions;

import TradeMaster.DataStore.StockStore;
import TradeMaster.DataStore.TransactionStore;
import TradeMaster.DataStore.UserStore;

import java.util.*;

public class BuyTransaction {

    // Method to find stock price by symbol
    private static int getStockPrice(String symbol) {
        // Access the static instance of StockStore
        StockStore stockStore = new StockStore();
        return stockStore.stocks.stream()
                .filter(stock -> stock.symbol.equals(symbol))
                .map(stock -> stock.price)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Stock symbol not found"));
    }

    public static boolean isPossible(String name, String sym, int count) {
        // Find the user by username
        Optional<UserStore.User> userOpt = UserStore.users.stream()
                .filter(user -> user.username.equals(name))
                .findFirst();

        if (userOpt.isEmpty()) {
            System.out.println("User not found.");
            return false;
        }

        UserStore.User user = userOpt.get();

        // Get the stock price
        int pricePerStock = getStockPrice(sym);

        // Check if user has sufficient balance for the transaction
        if (user.balance < pricePerStock * count) {
            System.out.println("Insufficient balance.");
            return false;
        }

        // Create a new transaction
        TransactionStore.Transaction ts = new TransactionStore.Transaction(name, sym, true, count);

        // Update the buyOrders map
        TransactionStore.buyOrders.put(sym, TransactionStore.buyOrders.getOrDefault(sym, 0) + count);

        // Deduct balance
        user.balance -= pricePerStock * count;

        // Print transaction details
        System.out.println(ts);

        // Update transaction history
        String date = new Date().toString();
        String historyEntry = "On " + date + " buy order was placed for " + sym + " for " + count + " shares";
        TransactionStore.history.computeIfAbsent(name, k -> new ArrayList<>()).add(historyEntry);

        return true;
    }

    public static void buy(String name, String sym, int count) {
        boolean success = isPossible(name, sym, count);
        if (success) {
            System.out.println("Buy transaction successful.");
        } else {
            System.out.println("Buy transaction failed.");
        }

        // Print the updated buyOrders map and user details
//        System.out.println("Buy Orders: " + TransactionStore.buyOrders);
//        UserStore.users.forEach(user -> System.out.println("User: " + user.username + ", Balance: " + user.balance));
//
//        // Print the transaction history
//        System.out.println("Transaction History: " + TransactionStore.history);
    }

}

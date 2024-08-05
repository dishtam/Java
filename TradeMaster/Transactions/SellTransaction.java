package TradeMaster.Transactions;

import TradeMaster.DataStore.StockStore;
import TradeMaster.DataStore.TransactionStore;
import TradeMaster.DataStore.UserStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class SellTransaction {

    // Method to get stock price by symbol
    private static int getStockPrice(String symbol) {
        // Access the static instance of StockStore
        StockStore stockStore = new StockStore();
        return stockStore.stocks.stream()
                .filter(stock -> stock.symbol.equals(symbol))
                .map(stock -> stock.price)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Stock symbol not found"));
    }

    // Method to check if the sell transaction is possible
    private static boolean isPossible(String name, String sym, int count) {
        // Find the user by username
        Optional<UserStore.User> userOpt = UserStore.users.stream()
                .filter(user -> user.username.equals(name))
                .findFirst();

        if (userOpt.isEmpty()) {
            System.out.println("User not found.");
            return false;
        }

        // Check if the user has enough quantity to sell
        if (TransactionStore.buyOrders.containsKey(sym)) {
            int availableQuantity = TransactionStore.buyOrders.get(sym);
            if (availableQuantity >= count) {
                return true;
            } else {
                System.out.println("Insufficient quantity available to sell.");
                return false;
            }
        } else {
            System.out.println("Symbol not found in buyOrders.");
            return false;
        }
    }

    public static void sell(String name, String sym, int count) {
        boolean success = isPossible(name, sym, count);
        if (success) {
            // Find the user by username
            Optional<UserStore.User> userOpt = UserStore.users.stream()
                    .filter(user -> user.username.equals(name))
                    .findFirst();

            if (userOpt.isEmpty()) {
                System.out.println("User not found.");
                return;
            }

            UserStore.User user = userOpt.get();

            // Proceed with the sell transaction
            int stockPrice = getStockPrice(sym);
            int totalAmount = stockPrice * count;

            // Update buyOrders and sellOrders maps
            int availableQuantity = TransactionStore.buyOrders.get(sym);
            TransactionStore.sellOrders.put(sym, TransactionStore.sellOrders.getOrDefault(sym, 0) + count);

            if (availableQuantity == count) {
                TransactionStore.buyOrders.remove(sym);
            } else {
                TransactionStore.buyOrders.put(sym, availableQuantity - count);
            }

            // Update user balance
            user.balance += totalAmount;

            // Print transaction details
            System.out.println("Sell transaction successful. Sold " + count + " shares of " + sym + " at " + stockPrice + " each for a total of " + totalAmount + ".");
            System.out.println("User " + name + " new balance: " + user.balance);

            // Update transaction history
            String date = new Date().toString();
            String historyEntry = "On " + date + " sell order was placed for " + sym + " for " + count + " shares";
            TransactionStore.history.computeIfAbsent(name, k -> new ArrayList<>()).add(historyEntry);

        } else {
            System.out.println("Sell transaction failed.");
        }

//        // Print the updated maps
//        System.out.println("Buy Orders: " + TransactionStore.buyOrders);
//        System.out.println("Sell Orders: " + TransactionStore.sellOrders);
//
//        // Print the transaction history
//        System.out.println("Transaction History: " + TransactionStore.history);
    }
}

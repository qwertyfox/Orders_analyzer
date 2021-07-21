package sample.poi.classification;

import java.util.*;

public class BreadType {

    public static final BreadType breadType = new BreadType();

    private final List<String> breadNames = new LinkedList<>();
    private final Map<String, TreeMap<String, Integer>> finalMapOfProducts = new TreeMap<>();
    // Map<BreadName, ProductName, NumberOfOrders>

    public static BreadType getInstance() {
        return breadType;
    }

    public Map<String, TreeMap<String, Integer>> getFinalMapOfProducts() {
        return Collections.unmodifiableMap(finalMapOfProducts);
    }

    private boolean checkBreadType (String breadName) {
        return breadNames.contains(breadName);
    }

    public void addToFinalList (String breadName, String productName, int orderNumber) {
        if(checkBreadType(breadName)){
            // if BreadType already exists
            TreeMap<String, Integer> existingProduct = finalMapOfProducts.get(breadName);

            if(existingProduct.containsKey(productName)){
                // updating product the previous numbers
                int oldValue = existingProduct.get(productName);
                existingProduct.put(productName, oldValue + orderNumber);
            }else {
                // Adding new product name using same bread type
                existingProduct.put(productName, orderNumber);
            }

        }else {
            TreeMap<String, Integer> newProduct = new TreeMap<>();
            newProduct.put(productName, orderNumber);
            finalMapOfProducts.put(breadName, newProduct);
            breadNames.add(breadName);
        }
    }

    public void printFinalList() {
        for(String breadType : finalMapOfProducts.keySet()){
            Map<String, Integer> value = finalMapOfProducts.get(breadType);

            System.out.println(breadType);
            for(String key : value.keySet()){

                if(key.contains("Special")){
                    String [] array = key.split("=");
                    String name = array [0];
                    String client = array [1];
                    String productName = name.replace("Special", "");
                    System.out.println("\t" +productName+ client + " - " + value.get(key));
                }else {
                    System.out.println("\t" +key+ " - " + value.get(key));
                }
            }
        }
    }


}

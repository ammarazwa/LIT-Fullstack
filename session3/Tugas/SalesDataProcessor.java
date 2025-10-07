import java.io.*;
import java.util.*;

public class SalesDataProcessor {
    public static void main(String[] args) {
        String inputFile = "sales.csv";
        String outputFile = "sales_report.txt";
        List<Map<String, String>> salesData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; 
                    continue;
                }
                String[] values = line.split(",");
                if (values.length != 3) {
                    continue;
                }
                Map<String, String> sale = new HashMap<>();
                sale.put("Product", values[0].trim());
                sale.put("Price", values[1].trim());
                sale.put("Quantity", values[2].trim());
                salesData.add(sale);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Double> productSales = new HashMap<>();
        for (Map<String, String> sale : salesData) {
            String product = sale.get("Product");
            double price = Double.parseDouble(sale.get("Price"));
            int quantity = Integer.parseInt(sale.get("Quantity"));
            double total = price * quantity;
            productSales.put(product, productSales.getOrDefault(product, 0.0) + total);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("Product Sales Report\n");
            writer.write("--------------------\n");
            for (Map.Entry<String, Double> entry : productSales.entrySet()) {
                writer.write(entry.getKey() + ": $" + String.format("%.2f", entry.getValue()) + "\n");
            }
            System.out.println("Sales report generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

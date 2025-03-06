import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Currency_converter {

    // API key for ExchangeRate-API (You need to get a free API key from their website)
    private static final String API_KEY = "YOUR_API_KEY";  // Replace with your API key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Allow the user to choose base currency
        System.out.print("Enter the base currency (e.g., USD, EUR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        // Step 2: Allow the user to choose target currency
        System.out.print("Enter the target currency (e.g., USD, EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Step 3: Input the amount they want to convert
        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        // Step 4: Fetch the exchange rate from API
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

        if (exchangeRate == -1) {
            System.out.println("Could not fetch the exchange rate. Please check the currencies and try again.");
        } else {
            // Step 5: Convert the amount using the exchange rate
            double convertedAmount = amount * exchangeRate;

            // Display the result
            System.out.printf("%.2f %s is equal to %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
        }

        scanner.close();
    }

    /**
     * Fetches the exchange rate from the API for the given base and target currency.
     *
     * @param baseCurrency the base currency (e.g., USD)
     * @param targetCurrency the target currency (e.g., EUR)
     * @return the exchange rate or -1 if the API request fails
     */
    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            // Build the URL for the API request
            String apiUrl = API_URL + baseCurrency;

            // Make a GET request to fetch the exchange rates
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.setReadTimeout(5000); // 5 seconds timeout
            connection.connect();

            // Read the response
            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Convert the response to a string
            String responseStr = response.toString();

            // Step 1: Check if the response contains the result key "success"
            if (responseStr.contains("\"result\":\"success\"")) {
                // Step 2: Find the exchange rate for the target currency
                String rateKey = "\"conversion_rates\":{";
                int startIndex = responseStr.indexOf(rateKey) + rateKey.length();
                int endIndex = responseStr.indexOf("}", startIndex);

                // Extract the conversion rates block
                String ratesBlock = responseStr.substring(startIndex, endIndex);

                // Step 3: Extract the exchange rate for the target currency
                String targetKey = "\"" + targetCurrency + "\":";
                int targetStartIndex = ratesBlock.indexOf(targetKey) + targetKey.length();
                int targetEndIndex = ratesBlock.indexOf(",", targetStartIndex);

                // If it's the last key in the block, handle it differently
                if (targetEndIndex == -1) {
                    targetEndIndex = ratesBlock.length();
                }

                // Extract the exchange rate value
                String exchangeRateStr = ratesBlock.substring(targetStartIndex, targetEndIndex).trim();

                // Convert it to a double
                return Double.parseDouble(exchangeRateStr);
            } else {
                System.out.println("Error fetching exchange rates: " + responseStr);
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

  
 
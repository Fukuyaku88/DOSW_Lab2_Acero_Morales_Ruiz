package edu.eci.dosw.reto4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * ROL EN BRIDGE: Client.
 *
 * El cliente solo conoce la Abstraction (CurrencyExchanger /
 * MultiTargetExchanger). Es responsable de:
 *  1) Elegir e inyectar la Concrete Implementation (RateProvider)
 *     que se va a usar.
 *  2) Pedir al usuario los datos de cada transacción.
 *  3) Mostrar los resultados individuales.
 *  4) Agrupar y acumular totales por moneda destino usando Streams.
 */
public class TheCurrencyExchangeScamC4 {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        // 1) El cliente arma el "puente": conecta la abstraccion
        //    con una implementacion concreta de RateProvider.
        RateProvider rateProvider = new InMemoryRateProvider();
        MultiTargetExchanger exchanger = new MultiTargetExchanger(rateProvider);

        List<Transaction> transactions = new ArrayList<>();

        boolean continueLoop = true;
        while (continueLoop) {
            Transaction transaction = readTransaction(scanner, exchanger);
            transactions.add(transaction);
            printTransaction(transaction);

            System.out.print("\nWould you like to register another transaction? (y/n): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            continueLoop = answer.equals("y") || answer.equals("yes");
        }

        printGroupedTotals(transactions);
    }

    /**
     * Lee desde consola los datos de una transacción: monto, moneda
     * origen y una o más monedas destino, y ejecuta la conversión.
     */
    private static Transaction readTransaction(Scanner scanner, MultiTargetExchanger exchanger) {
        BigDecimal amount = readAmount(scanner);
        Currency source = readCurrency(scanner, "Enter the source currency (USD, EUR, JPY, COP): ");
        List<Currency> targets = readTargetCurrencies(scanner, source);

        List<ConversionResult> results = exchanger.convertToMultiple(amount, source, targets);
        return new Transaction(amount, source, results);
    }

    private static BigDecimal readAmount(Scanner scanner) {
        while (true) {
            System.out.print("Enter the amount to convert: ");
            String input = scanner.nextLine().trim();
            try {
                BigDecimal amount = new BigDecimal(input);
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("The amount must be greater than zero.");
                    continue;
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount, please try again.");
            }
        }
    }

    private static Currency readCurrency(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Currency.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Unsupported currency. Options: " + Arrays.toString(Currency.values()));
            }
        }
    }

    private static List<Currency> readTargetCurrencies(Scanner scanner, Currency source) {
        while (true) {
            System.out.print("Enter the target currency(ies) separated by comma (e.g. EUR,JPY): ");
            String input = scanner.nextLine().trim().toUpperCase();
            String[] tokens = input.split(",");

            List<Currency> targets = new ArrayList<>();
            boolean valid = true;
            for (String token : tokens) {
                try {
                    Currency currency = Currency.valueOf(token.trim());
                    if (currency == source) {
                        System.out.println("The target currency cannot be the same as the source currency: " + currency);
                        valid = false;
                        break;
                    }
                    targets.add(currency);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid target currency: " + token.trim());
                    valid = false;
                    break;
                }
            }

            if (valid && !targets.isEmpty()) {
                return targets;
            }
        }
    }

    private static void printTransaction(Transaction transaction) {
        System.out.println("\n--- Transaction result ---");
        for (ConversionResult result : transaction.getResults()) {
            System.out.printf("Original amount: %s %s  ->  Converted amount: %s %s%n",
                    result.getOriginalAmount().stripTrailingZeros().toPlainString(),
                    result.getSourceCurrency(),
                    result.getConvertedAmount().setScale(2, RoundingMode.HALF_UP),
                    result.getTargetCurrency());
        }
    }

    /**
     * Requisito: "Use Java Streams when totals from multiple
     * transactions must be grouped or accumulated."
     *
     * Agrupa TODOS los ConversionResult de TODAS las transacciones
     * por moneda destino, y suma los montos convertidos de cada grupo.
     */
    private static void printGroupedTotals(List<Transaction> transactions) {
        List<ConversionResult> allResults = transactions.stream()
                .flatMap(t -> t.getResults().stream())
                .collect(Collectors.toList());

        Map<Currency, BigDecimal> totalsByTargetCurrency = allResults.stream()
                .collect(Collectors.groupingBy(
                        ConversionResult::getTargetCurrency,
                        Collectors.reducing(BigDecimal.ZERO,
                                ConversionResult::getConvertedAmount,
                                BigDecimal::add)));

        System.out.println("\n=== Accumulated totals by target currency ===");
        totalsByTargetCurrency.forEach((currency, total) ->
                System.out.printf("%s: %s%n", currency, total.setScale(2, RoundingMode.HALF_UP)));
    }
}
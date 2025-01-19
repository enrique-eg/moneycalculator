package software.ulpgc.moneycalculator.mock;

import software.ulpgc.moneycalculator.Currency;
import software.ulpgc.moneycalculator.CurrencyLoader;

import java.util.List;

public class MockCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        return List.of(new Currency("USD", "American Dollar"),
                new Currency("GBP", "British Pound"),
                new Currency("DKK", "Danish Krone"),
                new Currency("BRL", "Brazilian Real"),
                new Currency("EUR", "Euro"));
    }
}

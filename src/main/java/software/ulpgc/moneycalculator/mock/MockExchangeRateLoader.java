package software.ulpgc.moneycalculator.mock;

import software.ulpgc.moneycalculator.Currency;
import software.ulpgc.moneycalculator.ExchangeRate;
import software.ulpgc.moneycalculator.ExchangeRateLoader;

import java.time.LocalDate;

public class MockExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {return new ExchangeRate(1.23, LocalDate.now(), from, to);}
}

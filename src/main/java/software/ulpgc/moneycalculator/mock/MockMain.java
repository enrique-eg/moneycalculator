package software.ulpgc.moneycalculator.mock;

import software.ulpgc.moneycalculator.Currency;
import software.ulpgc.moneycalculator.CurrencyDialog;
import software.ulpgc.moneycalculator.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.MoneyDialog;

import java.util.List;

public class MockMain {
    public static void main(String[] args) {
        List<Currency> currencies = new MockCurrencyLoader().load();
        MoneyDialog moneyDialog = new MockMoneyDialog().define(currencies);
        CurrencyDialog currencyDialog = new MockCurrencyDialog().define(currencies);
        MockMoneyDisplay moneyDisplay = new MockMoneyDisplay();
        MockExchangeRateLoader exchangeRateLoader = new MockExchangeRateLoader();
        ExchangeMoneyCommand command = new ExchangeMoneyCommand(moneyDialog, currencyDialog, exchangeRateLoader, moneyDisplay);
        command.execute();
    }
}

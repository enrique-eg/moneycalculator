package software.ulpgc.moneycalculator;

import java.time.LocalDate;

public record ExchangeRate(double rate, LocalDate date, Currency from, Currency to) {
}

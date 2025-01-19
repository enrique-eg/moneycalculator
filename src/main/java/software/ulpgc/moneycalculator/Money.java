package software.ulpgc.moneycalculator;

public record Money(double amount, Currency currency) {
    @Override
    public String toString() {
        return Math.round(amount * 100.0)/100.0 + " - " + currency;
    }
}

package software.ulpgc.moneycalculator.fixers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.Currency;
import software.ulpgc.moneycalculator.ExchangeRate;
import software.ulpgc.moneycalculator.ExchangeRateLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;

public class FixerExchangeRateLoader implements ExchangeRateLoader {
    private Currency from;
    private Currency to;

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        this.from = from;
        this.to = to;

        try {
            return toExchangeRate(loadJson());
        } catch (IOException e) {
            return null;
        }
    }

    private ExchangeRate toExchangeRate(String json) {
        return new ExchangeRate(calculateRate(json), LocalDate.now(), from, to);
    }

    private double calculateRate(String json) {
        Map<String, JsonElement> ratesMap = new Gson()
                .fromJson(json, JsonObject.class)
                .get("rates")
                .getAsJsonObject()
                .asMap();
        return ratesMap.get(to.code()).getAsDouble() / ratesMap.get(from.code()).getAsDouble();
    }

    private String loadJson() throws IOException {
        URL url = new URL("http://data.fixer.io/api/latest?access_key=57c454472ad2ec5a881f628597e49137");
        try (InputStream inputStream = url.openStream()){
            return new String(inputStream.readAllBytes());
        }
    }
}

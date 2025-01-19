package software.ulpgc.moneycalculator.fixers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.Currency;
import software.ulpgc.moneycalculator.CurrencyLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class FixerCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        try {
            return toList(loadJson());
        } catch (IOException e) {
            return emptyList();
        }
    }

    private List<Currency> toList(String json) {
        List<Currency> result = new ArrayList<>();
        Map<String, JsonElement> codes = new Gson()
                .fromJson(json, JsonObject.class)
                .get("symbols")
                .getAsJsonObject()
                .asMap();

        for (String code : codes.keySet()) {
            result.add(new Currency(code, codes.get(code).getAsString()));
        }
        return result;
    }

    private String loadJson() throws IOException {
        URL url = new URL("http://data.fixer.io/api/symbols?access_key=31547d8fcf138f06d26b4f66076050d1");
        try (InputStream inputStream = url.openStream()) {
            return new String(inputStream.readAllBytes());
        }
    }
}

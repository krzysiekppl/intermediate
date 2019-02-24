package pl.sda.intermediate;

import lombok.Getter;

@Getter
public enum Countries {

    USA("Stany Zjednoczone","US"),
    CZECH_REPUBLIC("Czechy", "CZ"),
    FRANCE("Francja","FR"),
    GERMANY("Niemcy","D"),
    POLAND("Polska","PL");

    private String plName;
    private String symbol;

    Countries(String plName, String symbol) {
        this.plName = plName;
        this.symbol = symbol;
    }
}

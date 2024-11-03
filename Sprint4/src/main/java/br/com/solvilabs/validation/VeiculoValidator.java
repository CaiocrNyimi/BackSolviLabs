package br.com.solvilabs.validation;

import java.util.regex.Pattern;

public class VeiculoValidator {
    private static final Pattern PLACA_ANTIGA = Pattern.compile("^[A-Z]{3}[0-9]{4}$");
    private static final Pattern PLACA_MERCOSUL = Pattern.compile("^[A-Z]{3}[0-9][A-Z][0-9]{2}$");
    private static final Pattern MARCA = Pattern.compile("^[A-Za-z ]+$");
    private static final int ANO_MINIMO = 1980;
    private static final int ANO_ATUAL = java.time.Year.now().getValue();

    public static boolean isPlacaValida(String placa) {
        return PLACA_ANTIGA.matcher(placa).matches() || PLACA_MERCOSUL.matcher(placa).matches();
    }

    public static boolean isMarcaValida(String marca) {
        return MARCA.matcher(marca).matches();
    }

    public static boolean isAnoValido(int ano) {
        return ano >= ANO_MINIMO && ano <= ANO_ATUAL;
    }
}

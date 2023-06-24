package org.rakoto.bybit.ichimoku.models.api;

/**
 * Represents interval that we can specify in the ByBIT api (see <a
 * href="https://bybit-exchange.github.io/docs/v5/enum#interval">
 * https://bybit-exchange.github.io/docs/v5/enum#interval</a>).
 */
public enum Interval {
    I_1("1"),
    I_3("3"),
    I_5("5"),
    I_15("15"),
    I_30("30"),
    I_60("60"),
    I_120("120"),
    I_240("240"),
    I_360("360"),
    I_720("720"),
    I_D("D"),
    I_W("W"),
    I_M("M");

    // Variables.

    private final String key;

    // Constructors.

    Interval(String key) {
        this.key = key;
    }

    // Getters.

    public String getKey() {
        return key;
    }
}

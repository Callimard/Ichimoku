package org.rakoto.bybit.ichimoku.models.api;

/**
 * Represents categories that we can specify in the ByBIT api (see <a
 * href="https://bybit-exchange.github.io/docs/v5/enum#category">
 * https://bybit-exchange.github.io/docs/v5/enum#category</a>).
 */
public enum Category {
    spot,
    linear,
    inverse,
    option
}

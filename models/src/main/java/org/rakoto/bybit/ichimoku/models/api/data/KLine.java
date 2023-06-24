package org.rakoto.bybit.ichimoku.models.api.data;

import java.time.Instant;

/**
 * Represent KLine in ByBIT API. A KLine is a equivalent of candle or candle
 * stick.
 */
public record KLine(
        Instant startTime,
        String openPrice,
        String closePrice,
        String highestPrice,
        String lowestPrice,
        String tradeVolume,
        String turnover
) {
}

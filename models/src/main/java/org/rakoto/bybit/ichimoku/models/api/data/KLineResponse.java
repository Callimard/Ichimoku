package org.rakoto.bybit.ichimoku.models.api.data;

import org.rakoto.bybit.ichimoku.models.api.Category;
import org.rakoto.bybit.ichimoku.models.api.Symbol;

import java.time.Instant;
import java.util.List;

public record KLineResponse(
        Category category,
        Symbol symbol,
        List<List<String>> list
) {

    // Constants.

    private static final int START_TIME_INDEX = 0;
    private static final int OPEN_PRICE_INDEX = 1;
    private static final int CLOSE_PRICE_INDEX = 4;
    private static final int HIGH_PRICE_INDEX = 2;
    private static final int LOW_PRICE_INDEX = 3;
    private static final int VOLUME_INDEX = 5;
    private static final int TURNOVER_INDEX = 6;

    // Methods.

    public List<KLine> getKLines() {
        return list.stream().map(this::convertToKline).toList();
    }

    private KLine convertToKline(List<String> kline) {
        Instant startTime = Instant.ofEpochMilli(Long.parseLong(kline.get(START_TIME_INDEX)));
        String openPrice = kline.get(OPEN_PRICE_INDEX);
        String closePrice = kline.get(CLOSE_PRICE_INDEX);
        String highPrice = kline.get(HIGH_PRICE_INDEX);
        String lowPrice = kline.get(LOW_PRICE_INDEX);
        String volume = null;
        String turnover = null;
        if (kline.size() > TURNOVER_INDEX) {
            volume = kline.get(VOLUME_INDEX);
            turnover = kline.get(TURNOVER_INDEX);
        }

        return new KLine(
                startTime,
                openPrice,
                closePrice,
                highPrice,
                lowPrice,
                volume,
                turnover
        );
    }

}

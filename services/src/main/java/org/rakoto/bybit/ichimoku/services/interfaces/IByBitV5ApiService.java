package org.rakoto.bybit.ichimoku.services.interfaces;

import jakarta.validation.constraints.NotNull;
import org.rakoto.bybit.ichimoku.models.api.Category;
import org.rakoto.bybit.ichimoku.models.api.Interval;
import org.rakoto.bybit.ichimoku.models.api.Symbol;
import org.rakoto.bybit.ichimoku.models.api.data.KLine;

import java.time.Instant;
import java.util.List;

/**
 * Service which manages calls to the api V5 of ByBIT (see <a href="https://bybit-exchange.github.io/docs/v5/intro">
 * https://bybit-exchange.github.io/docs/v5/intro</a>)
 */
public interface IByBitV5ApiService {

    // API URL.

    String V5 = "/v5";

    String MARKET_URL = V5.concat("/market");
    String MARKET_KLINE_URL = MARKET_URL.concat("/kline");

    // Headers keys.

    String X_BAPI_TIMESTAMP = "X-BAPI-TIMESTAMP";
    String X_BAPI_API_KEY = "X-BAPI-API-KEY";
    String X_BAPI_SIGN = "X-BAPI-SIGN";
    String X_BAPI_RECV_WINDOW = "X-BAPI-RECV-WINDOW";

    // Params keys.

    String CATEGORY_PARAM_KEY = "category";
    String SYMBOL_PARAM_KEY = "symbol";
    String INTERVAL_PARAM_KEY = "interval";
    String START_PARAM_KEY = "start";
    String END_PARAM_KEY = "end";
    String LIMIT_PARAM_KEY = "limit";

    // Methods.

    List<KLine> getKLine(@NotNull Category category, @NotNull Symbol symbol, @NotNull Interval interval, Instant start,
                         Instant end, Integer limit);

}

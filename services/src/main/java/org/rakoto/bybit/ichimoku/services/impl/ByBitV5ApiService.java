package org.rakoto.bybit.ichimoku.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rakoto.bybit.ichimoku.models.api.Category;
import org.rakoto.bybit.ichimoku.models.api.Interval;
import org.rakoto.bybit.ichimoku.models.api.Symbol;
import org.rakoto.bybit.ichimoku.models.api.data.ApiResponse;
import org.rakoto.bybit.ichimoku.models.api.data.KLine;
import org.rakoto.bybit.ichimoku.models.api.data.KLineResponse;
import org.rakoto.bybit.ichimoku.services.data.Account;
import org.rakoto.bybit.ichimoku.services.impl.exception.ByBitApiRequestFailException;
import org.rakoto.bybit.ichimoku.services.interfaces.IByBitV5ApiService;
import org.rakoto.bybit.ichimoku.utils.http.HttpUtils;
import org.rakoto.bybit.ichimoku.utils.logs.LoggingMarkers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class ByBitV5ApiService implements IByBitV5ApiService {

    // Constants.

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        JavaTimeModule module = new JavaTimeModule();
        OBJECT_MAPPER.registerModule(module);
    }

    // Variables.

    private final HttpClient httpClient = HttpClient.newBuilder().version(
            HttpClient.Version.HTTP_2).build();

    private final String apiBaseUrl;
    private final Account account;

    // Methods.

    @Override
    public List<KLine> getKLine(Category category, Symbol symbol, Interval interval,
                                Instant start, Instant end, Integer limit) {

        URI uri = createKLineURI(category, symbol, interval, start, end, limit);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        return sendByBitApiRequest(request, KLineResponse.class).getKLines();
    }

    private URI createKLineURI(Category category, Symbol symbol,
                               Interval interval,
                               Instant start, Instant end, Integer limit) {
        ApiURIBuilder apiURIBuilder = new ApiURIBuilder(MARKET_KLINE_URL)
                .param(CATEGORY_PARAM_KEY, category)
                .param(SYMBOL_PARAM_KEY, symbol)
                .param(INTERVAL_PARAM_KEY, interval.getKey());

        if (start != null) {
            apiURIBuilder.param(START_PARAM_KEY, start.toEpochMilli());
        }

        if (end != null) {
            apiURIBuilder.param(END_PARAM_KEY, end.toEpochMilli());
        }

        if (limit != null) {
            if (limit >= 1 && limit <= 200) {
                apiURIBuilder.param(LIMIT_PARAM_KEY, limit);
            } else {
                throw new IllegalArgumentException("Limit must be between 1 and 200");
            }
        }

        return apiURIBuilder.build();
    }

    private <T> T sendByBitApiRequest(HttpRequest request, Class<T> resClass) {
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ApiResponse apiResponse = OBJECT_MAPPER.readValue(response.body(), ApiResponse.class);
            return OBJECT_MAPPER.convertValue(apiResponse.result(), resClass);
        } catch (IOException | InterruptedException e) {
            log.error(LoggingMarkers.TECHNICAL, "Fail to send ByBIT api request", e);
            Thread.currentThread().interrupt();
            throw new ByBitApiRequestFailException(e);
        }
    }

    // Inner classes.

    private class ApiURIBuilder {

        // Variables.

        private final String path;

        private final Map<String, Object> params = Maps.newHashMap();

        // Constructors.

        private ApiURIBuilder(String path) {
            this.path = path;
        }

        // Methods.

        public ApiURIBuilder param(String key, Object value) {
            params.put(key, value);
            return this;
        }

        public URI build() {
            URI baseURI = URI.create(apiBaseUrl + path);
            return HttpUtils.addParameters(baseURI, params);
        }

    }

}

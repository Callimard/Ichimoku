package org.rakoto.bybit.ichimoku.utils.http;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
@DisplayName("HttpUtils test")
public class HttpUtilsTest {

    // Constants.

    private static final String BASE_URI = "https://api-testnet.bybit.com";

    // Tests.

    @Nested
    @DisplayName("AddParameters tests")
    class AddParameters {

        // Tests.

        @Test
        @DisplayName("addParameters() return uri with new parameters with " +
                "correct separators if the uri does not previously contains " +
                "param")
        void testWithUriWithoutPreviousParams() {
            URI uri = URI.create(BASE_URI);

            String p1 = "p1";
            String v1 = "v1";
            String p2 = "p2";
            String v2 = "v2";

            String param1 = p1.concat("=").concat(v1);
            String param2 = p2.concat("=").concat(v2);

            Map<String, Object> params = Maps.newHashMap();
            params.put(p1, v1);
            params.put(p2, v2);
            URI res = HttpUtils.addParameters(uri, params);

            assertThat(res.toString()).contains(p1);
            assertThat(res.toString()).contains(v1);
            assertThat(res.toString()).contains(p2);
            assertThat(res.toString()).contains(v2);
            assertThat(res).hasToString(BASE_URI.concat("?")
                                                .concat(param1)
                                                .concat("&")
                                                .concat(param2));
        }

        @Test
        @DisplayName("addParameters() return uri with new parameters with " +
                "correct separators if the uri previously contains param")
        void testWithUriWithParams() {
            String previousParam = "p=v";
            URI uri = URI.create(BASE_URI + "?" + previousParam);

            String p1 = "p1";
            String v1 = "v1";
            String p2 = "p2";
            String v2 = "v2";

            String param1 = p1.concat("=").concat(v1);
            String param2 = p2.concat("=").concat(v2);

            Map<String, Object> params = Maps.newHashMap();
            params.put(p1, v1);
            params.put(p2, v2);
            URI res = HttpUtils.addParameters(uri, params);

            assertThat(res.toString()).contains(p1);
            assertThat(res.toString()).contains(v1);
            assertThat(res.toString()).contains(p2);
            assertThat(res.toString()).contains(v2);
            assertThat(res).hasToString(BASE_URI.concat("?")
                                                .concat(previousParam)
                                                .concat("&")
                                                .concat(param1)
                                                .concat("&")
                                                .concat(param2));
        }
    }

    @Nested
    @DisplayName("AddParameter tests")
    class AddParameter {

        // Tests.

        @Test
        @DisplayName("addParameter() returns the uri with the new param " +
                "separate by a '?' if the uri does not previously contains " +
                "params")
        void testWithUriWithoutPreviousParams() {
            URI uri = URI.create(BASE_URI);

            String paramKey = "myParam";
            String paramValue = "myValue";
            URI res = HttpUtils.addParameter(uri, paramKey, paramValue);

            assertThat(res.toString()).contains("?");
            assertThat(res.toString()).contains(paramKey);
            assertThat(res.toString()).contains(paramValue);
            assertThat(res).hasToString(BASE_URI.concat("?")
                                                         .concat(paramKey)
                                                         .concat("=")
                                                         .concat(paramValue));
        }

        @Test
        @DisplayName("addParameter() returns the uri with the new param " +
                "separate by a '&' if the uri already contains params")
        void testWithUriWithParams() {
            String firstParam = "p1=v1";
            URI uri = URI.create(BASE_URI + "?" + firstParam);

            String paramKey = "myParam";
            String paramValue = "myValue";
            URI res = HttpUtils.addParameter(uri, paramKey, paramValue);

            assertThat(res.toString()).contains("&");
            assertThat(res.toString()).contains(paramKey);
            assertThat(res.toString()).contains(paramValue);
            assertThat(res).hasToString(BASE_URI.concat("?")
                                                .concat(firstParam)
                                                .concat("&")
                                                .concat(paramKey)
                                                .concat("=")
                                                .concat(paramValue));
        }
    }
}

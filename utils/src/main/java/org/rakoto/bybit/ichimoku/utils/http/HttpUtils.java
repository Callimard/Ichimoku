package org.rakoto.bybit.ichimoku.utils.http;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtils {

    // Methods.

    public static URI addParameters(URI uri, Map<String, Object> parameters) {
        URI res = uri;
        for (Map.Entry<String, Object> param : parameters.entrySet()) {
            res = addParameter(res, param.getKey(), param.getValue());
        }

        return res;
    }

    public static URI addParameter(URI uri, String parameterKey,
                                   Object parameterValue) {
        String params;
        if (hasAtLeastOneParameter(uri)) {
            params = uri.getPath() + "?".concat(uri.getQuery())
                    .concat("&")
                    .concat(parameterKey)
                    .concat("=")
                    .concat(parameterValue.toString());

        } else {
            params = uri.getPath() + "?"
                    .concat(parameterKey)
                    .concat("=")
                    .concat(parameterValue.toString());
        }

        return uri.resolve(params);
    }

    private static boolean hasAtLeastOneParameter(URI uri) {
        return uri.getQuery() != null;
    }

}

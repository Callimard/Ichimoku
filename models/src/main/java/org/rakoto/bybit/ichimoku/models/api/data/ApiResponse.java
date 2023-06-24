package org.rakoto.bybit.ichimoku.models.api.data;

import org.rakoto.bybit.ichimoku.models.api.ResponseCode;

import java.time.Instant;

public record ApiResponse(
        Integer retCode,
        ResponseCode retMsg,
        Object result,
        Object retExtInfo,
        Instant time
) {
}

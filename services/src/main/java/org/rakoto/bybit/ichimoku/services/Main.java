package org.rakoto.bybit.ichimoku.services;

import lombok.extern.slf4j.Slf4j;
import org.rakoto.bybit.ichimoku.models.api.Category;
import org.rakoto.bybit.ichimoku.models.api.Interval;
import org.rakoto.bybit.ichimoku.models.api.Symbol;
import org.rakoto.bybit.ichimoku.models.api.data.KLine;
import org.rakoto.bybit.ichimoku.services.data.Account;
import org.rakoto.bybit.ichimoku.services.impl.ByBitV5ApiService;
import org.rakoto.bybit.ichimoku.services.interfaces.IByBitV5ApiService;
import org.rakoto.bybit.ichimoku.utils.logs.LoggingMarkers;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) throws RuntimeException {

        Account callimardAccount = new Account(
                "2LyZPt72EJcRjNYMYN",
                "hPNjunZISEXigUAFIY1jOzl09PxJz6qxvju1"
        );
        IByBitV5ApiService byBitV5ApiService =
                new ByBitV5ApiService("https://api-testnet.bybit.com",
                                      callimardAccount);

            List<KLine> res = byBitV5ApiService
                    .getKLine(Category.linear,
                              Symbol.BTCUSDT,
                              Interval.I_D,
                              Instant.ofEpochMilli(1669852800000L),
                              Instant.ofEpochMilli(1671062400000L),
                              20);
            log.info("Res of the KLine request {}", res);
    }
}

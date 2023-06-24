package org.rakoto.bybit.ichimoku.utils.logs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggingMarkers {

    // Constants

    private static final IMarkerFactory MARKER_FACTORY = new BasicMarkerFactory();

    public static final Marker TECHNICAL = MARKER_FACTORY.getMarker("TECHNICAL");
}

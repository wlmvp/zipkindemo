package com.wl.springtest1.logUtils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

import java.util.Map;

public class TestFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        Map<String, String> map = event.getMDCPropertyMap();
        String tranceId = event.getMDCPropertyMap().get("traceId");
        if (tranceId != null) {
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }
}

package com.malog.common;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public final class KeyGenerator {
    private static final String ZONE_ID = "Asia/Seoul";
    private static final String DATE_FORMAT_PATTERN = "yyyyMMddHHmmssSSS";
    private static final long ORIGIN = 1000000;
    private static final long BOUND = 9999999;

    private KeyGenerator() {}

    public static String generate() {
        final var bounded = ThreadLocalRandom.current().nextLong(ORIGIN, BOUND);
        final var now = ZonedDateTime.now(ZoneId.of(ZONE_ID));
        final var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        return formatter.format(now) + bounded;
    }

    public static String generate(String prefix) {
        return prefix + generate();
    }
}
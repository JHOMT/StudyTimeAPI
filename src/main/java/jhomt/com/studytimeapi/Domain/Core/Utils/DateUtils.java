package jhomt.com.studytimeapi.Domain.Core.Utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Timer;

public class DateUtils {

    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yy")
                    .withZone(ZoneId.systemDefault());

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a", new Locale("es", "ES"))
                    .withZone(ZoneId.systemDefault());

    // formato para horas en 12 horas cuando solo hay horas y minutos
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("hh:mm a", new Locale("es", "ES"))
                    .withZone(ZoneId.systemDefault());


    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    public static String formatDate(LocalDate date) {
        if (date == null) return null;
        return date.format(LOCAL_DATE_FORMATTER);
    }

    public static String formatDate(Instant instant) {
        if (instant == null) return null;
        return DATE_FORMATTER.format(instant);
    }

    public static String formatDateTime(Instant instant) {
        if (instant == null) return null;
        return DATE_TIME_FORMATTER.format(instant);
    }

    public static String formatDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        ZonedDateTime zonedDateTime = localDateTime.atZone(DEFAULT_ZONE);
        return DATE_TIME_FORMATTER.format(zonedDateTime);
    }

    public static String formatTime(LocalTime localTime) {
        if (localTime == null) return null;
        ZonedDateTime zonedDateTime = localTime.atDate(LocalDate.now()).atZone(DEFAULT_ZONE);
        return TIME_FORMATTER.format(zonedDateTime);
    }
}
package lambdasinaction.chap12;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate date = LocalDate.of( 2014,3,18);
		System.out.println("LocalDate: " + date);
		System.out.println("LocalDate year: " + date.getYear());
		System.out.println("LocalDate month: " + date.getMonth());
		System.out.println("LocalDate day: " + date.getDayOfMonth());
		System.out.println("LocalDate day of year: " + date.getDayOfYear());
		System.out.println("LocalDate legth of month: " + date.lengthOfMonth());
		System.out.println("LocalDate era: " + date.getEra());
		
		LocalDate today = LocalDate.now();
		System.out.println("today: " + today);
		System.out.println("year today: " + today.get(ChronoField.YEAR));
		System.out.println("month today: " + today.get(ChronoField.MONTH_OF_YEAR));
		System.out.println("day today: " + today.get(ChronoField.DAY_OF_MONTH));
		System.out.println("day today: " + today.get(ChronoField.DAY_OF_WEEK));
		
		
		LocalTime timeNow = LocalTime.now();
		System.out.println("hour: " + timeNow.getHour());
		System.out.println("minute: " + timeNow.getMinute());
		System.out.println("second: " + timeNow.getSecond());
		System.out.println("nano second: " + timeNow.getNano());
		
		LocalDate dateParse = LocalDate.parse("2014-03-18");
		LocalTime timeParse = LocalTime.parse("13:45:20");
		System.out.println("dateParse: " + dateParse);
		System.out.println("timeParse: " + timeParse);
		
		LocalDateTime localDateTimeNow = LocalDateTime.now();
		LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
		LocalDateTime dt2 = LocalDateTime.of(date, timeNow);
		LocalDateTime dt3 = date.atTime(13, 45, 20);
		LocalDateTime dt4 = date.atTime(timeNow);
		LocalDateTime dt5 = timeNow.atDate(date);
		
		System.out.println("dt1 toLocalDate(): " + dt1.toLocalDate());
		System.out.println("dt1 toLocalTime(): " + dt1.toLocalTime());
		System.out.println("Instant.now(): " + Instant.now());
		
		System.out.println("Duration since java 8 was released: " + Duration.between(dt1, localDateTimeNow));
		System.out.println("Period of time since java 8 was released: " + Period.between(dateParse, today));

		Duration threeMinutes = Duration.ofMinutes(3);
		Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
		
		Period tenDays = Period.ofDays(10);
		Period threeWeks = Period.ofWeeks(3);
		Period threeYearsSixMonthsOneDay = Period.of(3, 6, 1);
		
		LocalDate date1 = LocalDate.of(2014, 3, 18);
		System.out.println(date1);
		LocalDate date2 = date1.withYear(2011);
		System.out.println("withYear: " + date2);
		LocalDate date3 = date2.withDayOfMonth(25);
		System.out.println("withDayOfMonth: " + date3);
		LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
		System.out.println("with ChronoField.MONTH_OF_YEAR: " + date4);
		
		LocalDate date5 = date1.plusWeeks(1);
		System.out.println(date5);
		LocalDate date6 = date5.minusYears(3);
		System.out.println(date6);
		LocalDate date7 = date6.plus(9, ChronoUnit.YEARS);
		System.out.println(date7);
		
		System.out.println("----------------- Temporal adjusters ----------------");
		LocalDate javaDate = LocalDate.of(2014, 3, 18);
		LocalDate javaDate2 = javaDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		System.out.println("javaDate2: " + javaDate2);
		LocalDate javaDate3 = javaDate2.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println("javaDate3: " + javaDate3);
		LocalDate javaDate4 = javaDate.with(TemporalAdjusters.dayOfWeekInMonth(0, DayOfWeek.FRIDAY));
		System.out.println("javaDate4: " + javaDate4);
		System.out.println("----------------- Temporal adjusters ----------------");
		LocalDate nextDayToWork = javaDate.with(new NextWorkingDay());
		System.out.println("nextDayToWork: " + nextDayToWork);
		LocalDate nextDayToWorkLambda = javaDate.with(temporal -> {
			DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			int dayToAdd = 1;
			if (dow == DayOfWeek.FRIDAY) {
				dayToAdd = 3;
			} else if (dow == DayOfWeek.SATURDAY) {
				dayToAdd = 2;
			}			
			return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		});
		System.out.println("nextDayToWorkLambda: " + nextDayToWorkLambda);
		TemporalAdjuster nextDayToWorkLambdaAdjusters = TemporalAdjusters.ofDateAdjuster(
				temporal -> {
					DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
					int dayToAdd = 1;
					if (dow == DayOfWeek.FRIDAY) {
						dayToAdd = 3;
					} else if (dow == DayOfWeek.SATURDAY) {
						dayToAdd = 2;
					}			
					return temporal.plus(dayToAdd, ChronoUnit.DAYS);
				});
		LocalDate nextDayToWotkAdjust = javaDate.with(nextDayToWorkLambdaAdjusters);
		System.out.println("nextDayToWotkAdjust: " + nextDayToWotkAdjust);
		System.out.println("---------------- Date formats -----------------");
		String s1 = javaDate.format(DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("s1: " + s1);
		String s2 = javaDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println("s2: " + s2);
		System.out.println(" -------- Creating a DateTimeFormatter from a pattern ---------");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.of(2014, 3, 18);
		String formattedDate = localDate.format(formatter);
		System.out.println("formattedDate: " + formattedDate);
		LocalDate localDate2 = LocalDate.parse(formattedDate, formatter);
		System.out.println("localDate2: " + localDate2);
		
		DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
		String formattedDateItalian = javaDate.format(italianFormatter);
		System.out.println("formattedDateItalian: " + formattedDateItalian);
		LocalDate italianDate = LocalDate.parse(formattedDateItalian, italianFormatter);
		System.out.println("italianDate: " + italianDate);
		System.out.println("---------- Building a DateTimeFormatter ------------");
		DateTimeFormatter italianFormatter2 = new DateTimeFormatterBuilder()
				.appendText(ChronoField.DAY_OF_MONTH)
				.appendLiteral(". ")
				.appendText(ChronoField.MONTH_OF_YEAR)
				.appendLiteral(" ")
				.appendText(ChronoField.YEAR)
				.parseCaseInsensitive()
				.toFormatter(Locale.ITALIAN);
		
		System.out.println("javaDate.format(italianFormatter): " + javaDate.format(italianFormatter2));
		System.out.println("------- Working with different time zones and calendars--------");
		ZoneId romeZone = ZoneId.of("Europe/Rome");
		System.out.println(romeZone.getId()); 
		ZoneId ecZone = TimeZone.getDefault().toZoneId();
		System.out.println(ecZone);
		System.out.println("----------- Applying a time zone to a point in time --------");
		LocalDate date0 = LocalDate.of(2014, Month.MARCH, 18);
		ZonedDateTime zdt1 = date0.atStartOfDay(ecZone);
		System.out.println("zdt1: " + zdt1);
		LocalDateTime date01 = LocalDateTime.of(2014, Month.MARCH, 18,13,45);
		ZonedDateTime zdt2 = date01.atZone(romeZone);
		System.out.println("zdt2: " + zdt2);
		Instant instant = Instant.now();
		ZonedDateTime zdt3 = instant.atZone(romeZone);
		System.out.println("zdt3: " + zdt3);
		LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
		//Instant instantFromDateTime = dateTime.toInstant(romeZone);
		LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, romeZone);
	}

}

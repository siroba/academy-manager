package PL53.SI2020_PL53;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Random;

public class Date extends java.util.Date {
	// Auto-generated serial ID
	private static final long serialVersionUID = -6185333649323730247L;

	public static final DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

	protected int day, month, year;

	/**
	 * Default constructor.
	 * 
	 * @param day
	 * @param month
	 * @param year
	 */
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
	 * Copy constructor
	 * 
	 * @param other
	 */
	public Date(Date other) {
		this.day = other.getDay();
		this.month = other.getMonth();
		this.year = other.getYear();
	}

	/**
	 * Generates a random date (unbounded)
	 */
	public Date() {
		this.setRandom();
	}

	/**
	 * Parses from the sql.Timestamp class (discards the time)
	 * 
	 * @param datetime
	 * @return Date
	 */
	public static Date parse(java.sql.Timestamp datetime) {
		return fromMillis(datetime.getTime());
	}

	/**
	 * Parses from the sql.Date class (discards the time)
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date parse(java.sql.Date date) {
		LocalDate d = date.toLocalDate();

		return new Date(d.getDayOfMonth(), d.getMonthValue(), d.getYear());
	}

	/**
	 * Calculates the Date from the milliseconds passed since 1/1/1990 00:00:00.0
	 * 
	 * @param millis
	 * @return Date
	 */
	public static Date fromMillis(long millis) {
		// long total = (millis+(offset*3600000L))/86400000L + 25569L;

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);

		return new Date(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
	}

	/**
	 * Generates a sql.Date object
	 * 
	 * @return sql.Date
	 * @throws ParseException
	 */
	public java.sql.Date toSQL() throws ParseException {
		java.util.Date date;

		date = dateformat.parse(Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day));

		return new java.sql.Date(date.getTime());
	}

	/**
	 * Parses this object to an LocalDateTime object (assumes the time to be 00:00)
	 * 
	 * @return LocalDate
	 */
	public LocalDateTime toLocalDateTime() {
		return LocalDateTime.of(year, month, day, 0, 0);
	}

	/**
	 * Uses the function {@link #toLocalDateTime()} and the constructor
	 * {@link Timestamp#valueOf(LocalDateTime)} to generate the Timestamp
	 * 
	 * @return
	 */
	public Timestamp toTimestamp() {
		return Timestamp.valueOf(toLocalDateTime());
	}

	/**
	 * Assumes we are in UTC+1.<br/>
	 * Uses the function {@link #toLocalDateTime()} to get
	 * the epoch seconds with the function
	 * {@link LocalDateTime#toEpochSecond(ZoneOffset)} and then, the seconds are
	 * multiplied by 1000
	 * 
	 * @return
	 */
	public long toMillis() {
		return toLocalDateTime().toEpochSecond(ZoneOffset.ofHours(1)) * 1000L;
	}

	public void setDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public void setRandom() {
		RandomDate rd = new RandomDate();
		this.setDate(rd.nextDate());
	}

	public void setRandom(int min_year, int max_year) {
		RandomDate rd = new RandomDate(min_year, max_year);
		this.setDate(rd.nextDate());
	}

	/**
	 * Gives the days passed between the two dates
	 * Uses the function {@link #daysSince(Date, Date)} and assumes the other date to be today (uses the function {@link #now()})
	 * 
	 * @param date
	 * @return days passed
	 */
	public static int daysSince(Date d) {
		return daysSince(Date.now(), d);
	}

	/**
	 * Gives the days passed between the two given dates
	 * 
	 * @param date1
	 * @param date2
	 * @return days passed
	 */
	public static int daysSince(Date date1, Date date2) {
		long difference = date1.toMillis() - date2.toMillis();
		int daysBetween = Math.round(difference / (1000.0f * 60.0f * 60.0f * 24.0f));

		return daysBetween;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Copies the parameters from the given date
	 * 
	 * @param other
	 */
	public void setDate(Date d) {
		this.day = d.getDay();
		this.month = d.getMonth();
		this.year = d.getYear();
	}

	/**
	 * The returned string is formatted according to Spanish standards (dd/MM/yy)
	 */
	@Override
	public String toString() {
		return this.day + "/" + this.month + "/" + this.year;
	}
	
	public static Date parseString(String date) {
		String tmp [] = date.split("-");
		
		return new Date(Integer.parseInt(tmp[2]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[0]));
	}

	/**
	 * Generates a random date with a bounded year. <br/>
	 * Uses the constructor {@link RandomDate#RandomDate(int, int)}
	 * 
	 * @param min_year
	 * @param max_year
	 * @return
	 */
	public static Date random(int min_year, int max_year) {
		RandomDate rd = new RandomDate(min_year, max_year);
		return rd.nextDate();
	}

	/**
	 * Generates a random date (the year can be between 2000 and 2050). <br/>
	 * 
	 * @return
	 */
	public static Date random() {
		RandomDate rd = new RandomDate();
		return rd.nextDate();
	}

	/**
	 * Gets today's date using {@link LocalDate#now()} and then parsing it to a {@link Date} object.
	 * 
	 * @return
	 */
	public static Date now() {
		LocalDate d = LocalDate.now();

		return new Date(d.getDayOfMonth(), d.getMonthValue(), d.getYear());
	}

	/**
	 * @author marcos
	 *
	 * Random date generator.
	 */
	public static class RandomDate {
		private final LocalDate minDate;
		private final LocalDate maxDate;
		private final Random random;

		public RandomDate(LocalDate minDate, LocalDate maxDate) {
			this.minDate = minDate;
			this.maxDate = maxDate;
			this.random = new Random();
		}

		public RandomDate() {
			this.minDate = LocalDate.of(2000, 1, 1);
			this.maxDate = LocalDate.of(2050, 1, 1);
			this.random = new Random();
		}

		public RandomDate(int min_year, int max_year) {
			this.minDate = LocalDate.of(min_year, 1, 1);
			this.maxDate = LocalDate.of(max_year, 1, 1);
			this.random = new Random();
		}

		public Date nextDate() {
			int minDay = (int) minDate.toEpochDay();
			int maxDay = (int) maxDate.toEpochDay();
			long randomDay = minDay + random.nextInt(maxDay - minDay);
			LocalDate ld = LocalDate.ofEpochDay(randomDay);

			return new Date(ld.getDayOfMonth(), ld.getMonth().getValue(), ld.getYear());
		}

		@Override
		public String toString() {
			return "RandomDate{" + "maxDate=" + maxDate + ", minDate=" + minDate + '}';
		}
	}

}

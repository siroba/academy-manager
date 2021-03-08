package PL53.SI2020_PL53;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;

public class DateTime extends Date {
	// Auto-generated serial ID
	private static final long serialVersionUID = 2169788639882609776L;

	/**
	 * {@link DateFormat} variable to format the dates
	 */
	public static final DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private int hour, minute;

	/**
	 * Default constructor
	 *
	 * @param minute
	 * @param hour
	 * @param day
	 * @param month
	 * @param year
	 */
	public DateTime(int minute, int hour, int day, int month, int year) {
		super(day, month, year);

		this.hour = hour;
		this.minute = minute;
	}

	/**
	 * Copies the date from the given object
	 *
	 * @param minute
	 * @param hour
	 * @param date
	 */
	public DateTime(int minute, int hour, Date d) {
		super(d);

		this.hour = hour;
		this.minute = minute;
	}

	/**
	 * Copy constructor
	 *
	 * @param other
	 */
	public DateTime(DateTime other) {
		super(other.getDay(), other.getMonth(), other.getYear());

		this.hour = other.getHour();
		this.minute = other.getMinute();
	}

	/**
	 * This constructor takes a Date and assumes the time to be 00:00
	 *
	 * @param other
	 */
	public DateTime(Date other) {
		super(other.getDay(), other.getMonth(), other.getYear());

		this.hour = 0;
		this.minute = 0;
	}



	/**
	 * Returns a {@link java.sql.Timestamp} object. The {@link Date#toSQL()} returns
	 * a {@link java.sql.Date} object, which ignores the time
	 *
	 * @return Timestamp
	 */
	public java.sql.Timestamp toTimestamp() {
		return new java.sql.Timestamp(this.toMillis());
	}

	/**
	 * Parses a String containing the date and time to an object The input date must
	 * be in format "yyyy-MM-dd HH:mm"
	 *
	 * @param datetime
	 * @return
	 * @throws ParseException
	 */
	public static DateTime parseString(String datetime) throws ParseException {
		java.util.Date d = dateformat.parse(datetime);

		return DateTime.fromMillis(d.getTime());
	}

	/**
	 * Parses a {@link java.sql.Timestamp} to a {@link DateTime} object by using
	 * {@link java.sql.Timestamp#getTime()}.
	 *
	 * @param datetime
	 * @return DateTime
	 */
	public static DateTime parse(java.sql.Timestamp datetime) {
		return fromMillis(datetime.getTime());
	}

	/**
	 * Uses the {@link #toLocalDateTime()} function combined with the
	 * {@link LocalDateTime#toEpochSecond(ZoneOffset)} (assumes UTC+1) * 1000L
	 */
	@Override
	public long toMillis() {
		return this.toLocalDateTime().toEpochSecond(ZoneOffset.ofHours(1)) * 1000l;
	}

	/**
	 * Parses milliseconds to a {@link DateTime} object. <br/>
	 * Uses the {@link Calendar#setTimeInMillis(long)} to then parse it with the
	 * {@link DateTime#DateTime(int, int, int, int, int)} constructor.
	 *
	 * @param millis
	 * @return
	 */
	public static DateTime fromMillis(long millis) {
		LocalDateTime ldt = LocalDateTime.ofEpochSecond(millis / 1000L, 0, ZoneOffset.ofHours(1));

		return new DateTime(ldt.getMinute(), ldt.getHour(), ldt.getDayOfMonth(), ldt.getMonthValue(), ldt.getYear());
	}

	/**
	 * Parses this object to an LocalDateTime object
	 *
	 * @return LocalDate
	 */
	public LocalDateTime toLocalDateTime() {
		return LocalDateTime.of(year, month, day, hour, minute);
	}

	/**
	 * Uses {@link LocalDateTime#now()} to generate the values
	 *
	 * @return
	 */
	public static DateTime now() {
		LocalDateTime d = LocalDateTime.now();

		return new DateTime(d.getMinute(), d.getHour(), d.getDayOfMonth(), d.getMonthValue(), d.getYear());
	}

	/**
	 * Same as {@link Date#daysSince(Date)}, but with minutes
	 *
	 * @param d
	 * @return
	 */
	public static int minutesSince(DateTime d) {
		return minutesSince(d, DateTime.now());
	}

	/**
	 * Same as {@link Date#daysSince(Date, Date)}, but with minutes
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int minutesSince(DateTime date1, DateTime date2) {
		long difference = date1.toMillis() - date2.toMillis();
		int hBetween = Math.round(difference / (1000.0f * 60.0f));

		return hBetween;
	}

	/**
	 * Same as {@link Date#daysSince(Date)}, but with minutes
	 *
	 * @param d
	 * @return
	 */
	public static int minutesSince(DateTime d) {
		return minutesSince(d, DateTime.now());
	}

	/**
	 * Same as {@link Date#daysSince(Date, Date)}, but with minutes
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int minutesSince(DateTime date1, DateTime date2) {
		long difference = date1.toMillis() - date2.toMillis();
		int hBetween = Math.round(difference / (1000.0f * 60.0f));

		return hBetween;
	}

	/**
	 * The returned string is formatted according to Spanish standards (dd/MM/yy
	 * HH:MM)
	 */
	@Override
	public String toString() {
		String h = (this.hour < 10 ? "0" : "") + this.hour;
		String m = (this.minute < 10 ? "0" : "") + this.minute;
		return this.day + "/" + this.month + "/" + this.year + " " + h + ":" + m;
	}

	public void setTime(int minute, int hour) {
		this.minute = minute;
		this.hour = hour;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

}

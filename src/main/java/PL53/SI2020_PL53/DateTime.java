package PL53.SI2020_PL53;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DateTime extends Date {
	// Auto-generated serial ID
	private static final long serialVersionUID = 2169788639882609776L;

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
	 * Returns a {@link java.sql.Timestamp} object.
	 * The {@link Date#toSQL()} returns a {@link java.sql.Date} object, which ignores the time
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
	 */
	public static DateTime parseString(String datetime) {
		System.out.println(datetime);
		String tmp[] = datetime.split(" ");

		String time[] = tmp[1].split(":");
		String date[] = tmp[0].split("-");

		System.out.println("Hour: " + time[0]);

		return new DateTime(Integer.parseInt(time[1]), Integer.parseInt(time[0]), Integer.parseInt(date[2]),
				Integer.parseInt(date[1]), Integer.parseInt(date[0]));
	}

	/**
	 * Parses a String containing the date and time to an object. It takes care of
	 * the formatting.
	 *
	 * @param datetime
	 * @return
	 */
	public static DateTime parseUnformattedString(String datetime) throws ParseException {
		String tmp[] = dateformat.format(datetime).split(" ");

		String time[] = tmp[0].split(":");
		String date[] = tmp[1].split("-");

		return new DateTime(Integer.parseInt(time[1]), Integer.parseInt(time[0]), Integer.parseInt(date[2]),
				Integer.parseInt(date[1]), Integer.parseInt(date[0]));
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
	 * Parses milliseconds to a {@link DateTime} object. <br/>
	 * Uses the {@link Calendar#setTimeInMillis(long)} to then parse it with the
	 * {@link DateTime#DateTime(int, int, int, int, int)} constructor.
	 *
	 * @param millis
	 * @return
	 */
	public static DateTime fromMillis(long millis) {
		// long total = (millis+(offset*3600000L))/86400000L + 25569L;

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);

		return new DateTime(calendar.get(Calendar.MINUTE), calendar.get(Calendar.HOUR),
				calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
	}

	/**
	 * Parses this object to an LocalDateTime object
	 *
	 * @return LocalDate
	 */
	public LocalDateTime toLocalDateTime() {
		return LocalDateTime.of(year, month, day, hour, minute);
	}

	public static DateTime now() {
		LocalDateTime d = LocalDateTime.now();

		return new DateTime(d.getMinute(), d.getHour(), d.getDayOfMonth(), d.getMonthValue(), d.getYear());
	}

	/**
	 * The returned string is formatted according to Spanish standards (dd/MM/yy HH:MM)
	 */
	@Override
	public String toString() {
		String h = (this.hour<10?"0":"") + this.hour;
		String m = (this.minute<10?"0":"") + this.minute;
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

package PL53.SI2020_PL53;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTime extends Date {
	// Auto-generated serial ID
	private static final long serialVersionUID = 2169788639882609776L;

	private int hour, minute;

	public DateTime(int minute, int hour, int day, int month, int year) {
		super(day, month, year);

		this.hour = hour;
		this.minute = minute;
	}

	public DateTime(int minute, int hour, Date d) {
		super(d);

		this.hour = hour;
		this.minute = minute;
	}

	public DateTime(DateTime other) {
		super(other.getDay(), other.getMonth(), other.getYear());

		this.hour = other.getHour();
		this.minute = other.getMinute();
	}

	/**
	 * The input date must be in format "yyyy-MM-dd HH:mm
	 * "
	 * @param datetime
	 * @return
	 */
	public static DateTime parseString(String datetime) {
		String tmp[] = datetime.split(" ");

		String time[] = tmp[0].split(":");
		String date[] = tmp[1].split("-");

		return new DateTime(Integer.parseInt(time[1]), Integer.parseInt(time[0]), Integer.parseInt(date[2]),
				Integer.parseInt(date[1]), Integer.parseInt(date[0]));
	}

	@Override
	public Instant toInstant() {
		String parsed = this.year + "-" + this.month + "-" + this.day + " " + this.hour + ":" + this.minute + ":00";

		return LocalDateTime.parse(parsed).atZone(ZoneId.of("Europe/Madrid")).toInstant();
	}

	@Override
	public Timestamp toTimestamp() {
		return Timestamp.from(toInstant());
	}

	@Override
	public String toString() {
		return this.year + "-" + this.month + "-" + this.day + " " + this.hour + ":" + this.minute;
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

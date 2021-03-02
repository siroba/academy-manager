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
	
	@Override
	public Instant toInstant() {
		String parsed = this.year + "-" + this.month + "-" + this.day + " " + this.hour + ":" + this.minute + ":00";
		
		return LocalDateTime.parse(parsed).atZone(ZoneId.of( "Europe/Madrid" )).toInstant();
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

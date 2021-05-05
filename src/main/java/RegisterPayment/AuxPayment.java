package RegisterPayment;

import PL53.util.Date;
import StatusOfFormativeAction.Payment;

public class AuxPayment extends Payment{
	
	String sender , receiver;

	public AuxPayment(Date date, float amount , String sender , String receiver) {
		super(date, amount);
		this.sender=sender;
		this.receiver=receiver;
		
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

}

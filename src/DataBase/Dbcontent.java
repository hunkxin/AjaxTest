package DataBase;

public class Dbcontent {
	public Dbcontent() {
		
	}
	
	public Dbcontent(String caller_id_name, String destination_number,
			String start_stamp, String answer_stamp, String end_stamp, int duration,
			int billsec, String hangup_cause) {
		this.caller_id_name = caller_id_name;
		this.destination_number = destination_number;
		this.start_stamp = start_stamp;
		this.answer_stamp = answer_stamp;
		this.end_stamp = end_stamp;
		this.duration = duration;
		this.billsec = billsec;
		this.hangup_cause = hangup_cause;
	}
	private String caller_id_name;
	private String destination_number;
	private String start_stamp;
	private String answer_stamp;
	private String end_stamp;
	private int duration;
	private int billsec;
	private String hangup_cause;
	public String getCaller_id_name() {
		return caller_id_name;
	}
	public void setCaller_id_name(String caller_id_name) {
		this.caller_id_name = caller_id_name;
	}
	public String getDestination_number() {
		return destination_number;
	}
	public void setDestination_number(String destination_number) {
		this.destination_number = destination_number;
	}
	public String getStart_stamp() {
		return start_stamp;
	}
	public void setStart_stamp(String start_stamp) {
		this.start_stamp = start_stamp;
	}
	public String getAnswer_stamp() {
		return answer_stamp;
	}
	public void setAnswer_stamp(String answer_stamp) {
		this.answer_stamp = answer_stamp;
	}
	public String getEnd_stamp() {
		return end_stamp;
	}
	public void setEnd_stamp(String end_stamp) {
		this.end_stamp = end_stamp;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getBillsec() {
		return billsec;
	}
	public void setBillsec(int billsec) {
		this.billsec = billsec;
	}
	public String getHangup_cause() {
		return hangup_cause;
	}
	public void setHangup_cause(String hangup_cause) {
		this.hangup_cause = hangup_cause;
	}
}

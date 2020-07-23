package modules;

import java.io.Serializable;
import java.util.Date;

public class Inspection implements Serializable {
	private static final long serialVersionUID = 466280847402391218L;
	
	private int id;
	private Result result;
	private int[] violations;
	private Date date;
	
	public enum Result {
		PASS,
		OUTOFBUSINESS,
		FAIL,
		NOTREADY,
		PASSWCONDITIONS,
		BUSINESSNOTLOCATED,
		NOENTRY;
		
		public static Result parse(String risk) {
			return Result.valueOf(risk.toUpperCase().replace(" ", "").replace("/", ""));
		}
	}

	public Inspection(int id, int[] violations, Result res, Date date) {
		this.id = id;
		this.result = res;
		this.violations = violations;
		this.date = date;
	}

	public int getId() {
		return this.id;
	}

	public int[] getViolations() {
		return this.violations;
	}

	public Result getResult() {
		return this.result;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String toString() {
		return this.id + "\t" + this.date.toString().replaceAll(" (\\d{2}:){2}\\d{2} .{3}", "") + "\t" + this.result;
	}
	
	/**
	 * finds the risk factor weight of the result of the inspection
	*/
	public int weightOfResult(){
		switch (this.result) {
		case PASS:
			return 0;
		case OUTOFBUSINESS:
			return 100;
		case FAIL:
			return 100;
		case NOTREADY:
			return 100;
		case PASSWCONDITIONS:
			return 50;
		case BUSINESSNOTLOCATED:
			return 50;
		case NOENTRY:
			return 100;
		default:
			break;
		}
		return 100;		
	}
	
    /**
	 * finds the risk factor weight of the violations violated by a facility during an inspection
	*/
	public double weightOfViolations(){
		int countCritical = 0;
		int countSerious = 0;
		int countMinor = 0;
		int numViolations = 14;
		for (int i = 0; i < this.violations.length; i++) {
			if (this.violations[i] <= 14)
				countCritical++;
			else if (this.violations[i] <= 29)
				countSerious++;
			else 
				countMinor++;
		}
		return (0.6*countCritical/numViolations + 0.3*countSerious/numViolations + 0.1*countMinor/numViolations);
	}
}
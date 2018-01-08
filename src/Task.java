
/**
 * A Task object contains a String (for task description), a long value (converted from date),
 * a String representing the priority level and int params for year, month and day (for date formating/
 * display functionality)
 * 
 * @author Sabirah Shuaybi
 * @author Tseki Lhamo
 */
public class Task implements Comparable<Task> {
	private String taskName;
	private long dateNum;
	private int priorityLevel;
	private int year;
	private int month;
	private int day;
		
	public Task(String taskName, long dateNum, String priority, int year, int month, int day) {
		this.taskName = taskName;
		this.dateNum = dateNum;
		this.year = year;
		//must add 1 to get correct month due to a strange bug in JDatePicker package
		this.month = month + 1; 
		this.day = day;
		
		//convert priority string into a number value for more efficient comparisons 
		if(priority.equals("High")) {
			priorityLevel = 1;
		}
		else if(priority.equals("Medium")) {
			priorityLevel = 2;
		}
		else {
			priorityLevel = 3;
		}
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @return String description of the task
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @return the seconds representing the date
	 */
	public long getDateNum() {
		return dateNum;
	}

	/**
	 * @return number representing the priority level of task
	 */
	public int getPriorityLevel() {
		return priorityLevel;
	}
	
	/**
	 * Formats the individual components of a data into a well formatted
	 * date string object
	 * 
	 * @return A String consisting of the date in a nice readable format
	 */
	public String getFormattedDate() {
		return "" + getMonth() + "/" + getDay() + "/" + getYear();
	}

	@Override
	public int compareTo(Task o) {
		if (o.getDateNum() > getDateNum()) {
			return -1;
		}
		//If two dates are equal (that is the seconds are equal), compare priority levels
		else if(o.getDateNum() == getDateNum()) {
			if(o.getPriorityLevel() == getPriorityLevel()) {
				//if two tasks have identical due dates AND priority level, then
					//it does not matter which one is displayed first (logically, they are of
						//equal weight in terms of which to complete first
				return 0;
			}
			else if (o.getPriorityLevel() > getPriorityLevel()) {
				return -1;
			}
			else {
				return 1;
			}
		}
		return 0; 
	}

	
	
	

}

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

/**
 * Graphical User Interface for the Task Manager Application
 * 
 * @author Sabirah Shuaybi
 * @author Tseki Lhamo
 */
public class TaskManagerGUI extends JPanel implements ActionListener{
	
	private static final Color BRIGHT_BLUE = new Color (49, 154, 196);
	private static final Color LIGHT_GREEN = new Color (89, 214, 125);
	private static final Color BACKGROUND_COLOR = new Color (155, 202, 232);
	private static final int NUM_COLS = 25;  //number of characters allowed in text field
	private static final float DATE_FONT_SIZE = 35.0f;
	private static final Border border = BorderFactory.createLineBorder(BRIGHT_BLUE, 15);
	
	private TaskManager taskManager = new TaskManager();
	
	private JButton addButton = new JButton("Add Task");
	private JButton completedButton = new JButton("Task Completed");
	private JButton allTasks = new JButton("View All Tasks");
	private JTextField taskStr = new JTextField(NUM_COLS);
	private JComboBox<String> difficultyLevel = new JComboBox<>();
	private JDatePicker datePicker;
	
	private JPanel mainPanel = new JPanel(new GridLayout(1, 2));
	private JLabel progressLabel = new JLabel("Total Number of Tasks Completed: ");
	private JLabel futureLabel = new JLabel("Total Number of Tasks Remaining: ");
	private JLabel taskDetails;
	private JLabel instructions;
	private JLabel upcomingTaskLabel;
	private JLabel enter;
	private JLabel instructions2;
	
	private int completedNum;
	private int remainingNum;
	
	public TaskManagerGUI() {
		super(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4)); 
		buildDatePanel();
		buildInputPanel();
		buildDisplayPanel();
		setUpButtons();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			//If any of the fields are null, display pop-up message 
			if (taskStr.getText().equals("")
					|| difficultyLevel.getSelectedItem() == null
					|| datePicker.getModel().getValue() == null) {
				// if all info is not filled, show this message pop up
				JOptionPane.showMessageDialog(new JFrame(),
						"Please Fill All Fields!");
			} else {
				addTask();
			}
		}
		if (e.getSource() == completedButton) {
			removeTask();
		}
		if (e.getSource() == allTasks) {
			showAllTasks();
		}
	}
	
	
	/**
	 * Conversion of inputed date into a long, 
	 * Date Validation,
	 * Extraction of data from all input fields (including dataPicker)
	 * Construction of a task object with user input,
	 * Addition of task to the TaskManager
	 * Update Status (num of completed and remaining)
	 * Clear input fields now that previous input has been handled
	 * Call DisplayNextTask (since the insertion of this task could have changed priorities)
	 */
	private void addTask() {
		Date date = (Date)datePicker.getModel().getValue();
		long inputDateNum = date.getTime();  //inputed date translated into seconds
		long currentDateNum = new java.util.Date().getTime(); //current date translated into seconds
		
		//If data entered is smaller than current date, present a validation message
		if(inputDateNum < currentDateNum) {
			JOptionPane.showMessageDialog(new JFrame(), "Please Enter a Valid Date!");
		}
		else {
			int year = datePicker.getModel().getYear();
			int month = datePicker.getModel().getMonth();
			int day = datePicker.getModel().getDay();
			
			//Construct a new task object and pass in all required params from the inputs
			Task newTask = new Task (taskStr.getText(), inputDateNum, difficultyLevel.getSelectedItem().toString(), 
					year, month, day);
			taskManager.addTask(newTask); 
			
			remainingNum++;
			updateLabels();
			clearAllInputs();
			displayNextTask();
		}
	}
	
	/**
	 * Handles the removal of the next task.
	 */
	private void removeTask() {
		//Check if taskManager is already empty
		if (taskManager.getNextTask() == null) {
			return; //if no tasks exist, ignore the button click
		}
		taskManager.removeNextTask();
		if (taskManager.getNextTask() != null) {
			completedNum++;
			remainingNum--;
		}
		displayNextTask();
		//must check if taskManager is empty again (since we have removed the top task)
		if (taskManager.getNextTask() == null) { 
			//TaskManager is empty so all tasks have been completed 
			taskDetails.setText("Finished All Remaining Tasks!");
			completedNum++;
			remainingNum--;
		}
		updateLabels();
	}

	/**
	 * Constructs, fills and formats a JTable consisting
	 * of all task objects currently in TaskManager.
	 */
	public void showAllTasks() {
		Comparable[] tasks = taskManager.getTasks();
		Object[][] data = new Object[tasks.length][2];
		//This for loop runs through taskManager and fills data array with
			//tasks's name for the first column and task's date for the second col
		for (int i=0; i<taskManager.getSize(); i++) {
			data[i][0] = ((Task)tasks[i]).getTaskName();
			data[i][1] = ((Task)tasks[i]).getFormattedDate();
		}
		//headers of the table
		Object[] columnNames = {"Task Name", "Due Date"};
		final JTable table = new JTable(data, columnNames); 

		table.setRowHeight(55);
		table.setBackground(LIGHT_GREEN);
		
		//Make the JTable scrollable, add it to a JPanel and 
			//place the JPanel onto a JDialog 
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout());
		JScrollPane sp = new JScrollPane(table);
		jPanel.add(sp);
		JDialog jdialog = new JDialog();
		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jdialog.setContentPane(jPanel);

		jdialog.setLocation(200,150); 
		jdialog.pack();
		jdialog.setVisible(true);
		jdialog.setTitle("My Tasks");
	}
	
	/**
	 * Displays the next task on the GUI (the one calculated to be of the highest
	 * priority). The string description of the task as well as a the date it is due
	 * is displayed. 
	 */
	private void displayNextTask() {
		if(taskManager.getNextTask() != null) {
			taskDetails.setText(taskManager.getNextTask().getTaskName() +  "           " +
					taskManager.getNextTask().getFormattedDate());
		}
	}
	
	/**
	 * Clears all inputs on the interface
	 * (After a task has been added, to ensure that the 
	 * input slate is blank for the next task input)
	 */
	private void clearAllInputs() {
		taskStr.setText(null);
		difficultyLevel.setSelectedIndex(-1);
		datePicker.getModel().setValue(null);
	}
	
	/**
	 * Updates the JLabels for number of remaining tasks and 
	 * number of completed tasks
	 */
	private void updateLabels() {
		progressLabel.setText("Total Number of Completed Tasks: " + completedNum);
		futureLabel.setText("Total Number of Tasks Remaining: " + remainingNum);
	}
	
	/**
	 * Builds, designs and adds functionality to the date panel (which is in charge of
	 * displaying the current date at the top of the window)
	 */
	public void buildDatePanel() {
		JPanel datePanel = new JPanel();
		datePanel.setBorder(BorderFactory.createLineBorder(new Color(7, 150, 71), 4)); 
		datePanel.setBackground(LIGHT_GREEN);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		
		JLabel date = new JLabel("Today's Date:  " + dtf.format(localDate)); 
		date.setHorizontalAlignment(date.CENTER);
		date.setBorder(new EmptyBorder(25, 25, 25, 25));
		date.setFont (date.getFont().deriveFont(DATE_FONT_SIZE));
		datePanel.add(date);
		add(datePanel, BorderLayout.NORTH);
	}
	
	/**
	 * Builds the left side of the application, namely the input portion
	 */
	public void buildInputPanel() {
		JPanel inputPanel = new JPanel(new GridLayout(7,1));
		inputPanel.setBorder(new CompoundBorder(border, new EmptyBorder(15, 15, 15, 15)));
		inputPanel.setBackground(BACKGROUND_COLOR);
		enter = new JLabel("Enter a Description of a Task:");
		enter.setHorizontalAlignment(enter.CENTER);
		inputPanel.add(enter);
		inputPanel.add(taskStr);
		
		difficultyLevel.addItem("Low");
	    difficultyLevel.addItem("Medium");
	    difficultyLevel.addItem("High");
	    //prevents first item in list from getting selected
	    difficultyLevel.setSelectedIndex(-1);
		instructions = new JLabel("Select the Priority Level of the Task");
		instructions.setHorizontalAlignment(instructions.CENTER);
	    inputPanel.add(instructions);
		inputPanel.add(difficultyLevel);
		
		UtilDateModel model = new UtilDateModel();
		datePicker = new JDatePicker(model);

		java.util.Date selectedDate = (java.util.Date)datePicker.getModel().getValue();
		instructions2 = new JLabel("Enter the Task's Due Date");
		instructions2.setHorizontalAlignment(instructions2.CENTER);
		inputPanel.add(instructions2);
		inputPanel.add(datePicker);
		inputPanel.add(addButton);
		mainPanel.add(inputPanel);
	}
	
	/**
	 * Builds the right side of the application, namely the display portion 
	 */
	public void buildDisplayPanel() {
		JPanel displayPanel = new JPanel(new GridLayout(8, 1));
		displayPanel.setBorder(new CompoundBorder(border, new EmptyBorder(15, 15, 15, 15)));
		displayPanel.setBackground(BACKGROUND_COLOR);
		
		upcomingTaskLabel = new JLabel("My Next Task:");
		upcomingTaskLabel.setHorizontalAlignment(upcomingTaskLabel.CENTER);
	
		displayPanel.add(upcomingTaskLabel);
		
		taskDetails = new JLabel("You have no tasks");
		taskDetails.setHorizontalAlignment(taskDetails.CENTER);
		taskDetails.setOpaque(true);
		taskDetails.setBackground(LIGHT_GREEN);
		displayPanel.add(taskDetails); 
		JPanel filler = new JPanel();
		filler.setBackground(BACKGROUND_COLOR);
		displayPanel.add(filler);
		displayPanel.add(completedButton);
		displayPanel.add(progressLabel);
		displayPanel.add(futureLabel);
		displayPanel.add(allTasks);
		
		mainPanel.add(displayPanel);
		add(mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Adds actionListeners to JButtons as well as handling the 
	 * look of the buttons
	 */
	private void setUpButtons() {
		addButton.addActionListener(this);
		completedButton.addActionListener(this);
		allTasks.addActionListener(this);
		
        addButton.setFocusPainted(false);
        
        completedButton.setFocusPainted(false);
		
		colorButton(addButton, LIGHT_GREEN);
		colorButton(completedButton, LIGHT_GREEN);
		colorButton(allTasks, LIGHT_GREEN);
	}
	
	/**
     * A generic method that colors any JButton passed in with
     * the color passed in.
     *
     * @param button: the JButton to be colored
     * @param color: the color of the flavor, which will be
     *             the color of the JButton
     */
    private void colorButton(JButton button, Color color) {
        button.setBackground(color);
        button.setOpaque(true);
    }

}

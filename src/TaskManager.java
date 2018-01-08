/**
 * A TaskManager object contains a collection of Tasks (organized in a PQ)
 * Contains functionality of adding new tasks, removing the next task
 * 
 * @author Sabirah Shuaybi
 * @author Tseki Lhamo
 */
public class TaskManager {
	
	private PriorityQueue<Task> taskQueue = new PriorityQueueBH<>(10);
	
	/**
	 * Adds a task to the PQ
	 * 
	 * @param task to be added
	 */
	public void addTask(Task task) {
		taskQueue.insert(task);
	}
	
	/**
	 * Removes the next task 
	 * (aka the min task/task of highest priority)
	 */
	public void removeNextTask() {
		taskQueue.extractMinimum();
	}
	
	/**
	 * @return the task of the highest priority
	 */
	public Task getNextTask() {
		return (Task)taskQueue.minimum();
	}
	
	/**
	 * @return true if queue is empty
	 */
	public boolean isEmpty() {
		return taskQueue.isEmpty();
	}
	
	/**
	 * @return size of PQ
	 */
	public int getSize() {
		return taskQueue.getSize(); 
	}
	
	/**
	 * @param i
	 * @return the task object at specified index
	 */
	public Comparable getTaskAt(int i) {
		return taskQueue.getElementAt(i);
	}
	
	/**
	 * @return array of all Task objects in PQ
	 */
	public Comparable[] getTasks() {
		return taskQueue.getInternalArray();
	}
}

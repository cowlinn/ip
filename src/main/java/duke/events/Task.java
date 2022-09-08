package duke.events;

import java.io.Serializable;
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Helper method to generate status icon based on boolean value of isDone
     * @returns 2 types of indicators, if task is marked as done: "X"
     * else, returns " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        isDone = true;
    }

    public void removeDone() {
        isDone = false;
    }

    public String fullStatusIcon() {
        return "[" + getStatusIcon() + "]";
    }

    /**
     * Default method when comparing whether a date clashes with the above
     * To be overriden by tasks that are bound by date (e.g: Event/Deadline)
     * Returns True if the task occurs on the same date as given
     * @param date Unformatted date
     * @return
     */
    public boolean compareDate(String date) {
        return false;
    }


    public static Task readTask(String[] values) {
        boolean isDone = values[1].equals("0");
        String description = values[2];
        return new Task(isDone, description);

    }

    /**
     * Converts the task into a string, deliminated by "//" that is savable.
     * @returns a string that will be saved into text file, representing a single task
     */
    public String savableString() {
        String savedString = "";
        savedString += isDone ? "//0" : "//1"; //isDone is represented by 0 (done) or 1 (undone)
        savedString += "//" + description;
        return savedString;

    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return fullStatusIcon() + " " + description;
    }
}

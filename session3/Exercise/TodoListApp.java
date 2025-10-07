import java.io.*;
import java.util.*;

public class TodoListApp {

    static class Task {
        String description;
        boolean isComplete;

        public Task(String description) {
            this.description = description;
            this.isComplete = false;
        }

        public void markComplete() {
            this.isComplete = true;
        }

        public void markIncomplete() {
            this.isComplete = false;
        }

        @Override
        public String toString() {
            return (isComplete ? "[Completed] " : "[Pending] ") + description;
        }
    }

    static class TodoList {
        private ArrayList<Task> tasks = new ArrayList<>();

        public void addTask(String taskDescription) {
            tasks.add(new Task(taskDescription));
        }

        public void removeTask(int taskIndex) {
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.remove(taskIndex);
            } else {
                System.out.println("Invalid index.");
            }
        }

        public void displayTasks() {
            if (tasks.isEmpty()) {
                System.out.println("No tasks to display.");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }
        }

        public void markTaskComplete(int taskIndex) {
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markComplete();
            } else {
                System.out.println("Invalid index.");
            }
        }

        public void saveTasksToFile() {
            try (FileWriter writer = new FileWriter("tasks.txt")) {
                for (Task task : tasks) {
                    writer.write(task.description + "\n");
                }
                System.out.println("Tasks saved to file.");
            } catch (IOException e) {
                System.out.println("Error saving tasks to file.");
            }
        }

        public void loadTasksFromFile() {
            try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    tasks.add(new Task(line));
                }
                System.out.println("Tasks loaded from file.");
            } catch (IOException e) {
                System.out.println("Error loading tasks from file.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TodoList todoList = new TodoList();

        // Load tasks from file if available
        todoList.loadTasksFromFile();

        while (true) {
            System.out.println("\nTo-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Display Tasks");
            System.out.println("4. Mark Task Complete");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task to add: ");
                    String task = scanner.nextLine();
                    todoList.addTask(task);
                    break;
                case 2:
                    System.out.print("Enter task index to remove: ");
                    int index = scanner.nextInt();
                    todoList.removeTask(index - 1); // Adjust index to match human-readable format
                    break;
                case 3:
                    todoList.displayTasks();
                    break;
                case 4:
                    System.out.print("Enter task index to mark as complete: ");
                    int taskIndex = scanner.nextInt();
                    todoList.markTaskComplete(taskIndex - 1); // Adjust index to match human-readable format
                    break;
                case 5:
                    // Save tasks to file before exiting
                    todoList.saveTasksToFile();
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}

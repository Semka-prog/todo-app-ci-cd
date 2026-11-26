package org.example;

import java.util.List;
import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Todo App");
        System.out.println("Commands: add <text>, list, done <index>, clear, search <text>, help, exit");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            int separator = input.indexOf(' ');
            String command = (separator >= 0 ? input.substring(0, separator) : input).toLowerCase();
            String argument = separator >= 0 ? input.substring(separator + 1).trim() : "";

            try {
                switch (command) {
                    case "add":
                        if (argument.isEmpty()) {
                            throw new IllegalArgumentException("Usage: add <task text>");
                        }
                        service.addTask(argument);
                        System.out.println("Task added.");
                        break;
                    case "list":
                        printTasks(service.getTasks());
                        break;
                    case "done":
                        int index = Integer.parseInt(argument);
                        if (index < 0 || index >= service.getTaskCount()) {
                            throw new IllegalArgumentException("Invalid task index.");
                        }
                        service.completeTask(index);
                        System.out.println("Task marked as done.");
                        break;
                    case "clear":
                        service.clearTasks();
                        System.out.println("All tasks cleared.");
                        break;
                    case "search":
                        if (argument.isEmpty()) {
                            throw new IllegalArgumentException("Usage: search <text>");
                        }
                        printTasks(service.searchTasks(argument));
                        break;
                    case "help":
                        System.out.println("Commands: add <text>, list, done <index>, clear, search <text>, exit");
                        break;
                    case "exit":
                        System.out.println("Goodbye.");
                        return;
                    default:
                        System.out.println("Unknown command. Type 'help' for commands.");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Task index must be a number.");
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i));
        }
    }
}
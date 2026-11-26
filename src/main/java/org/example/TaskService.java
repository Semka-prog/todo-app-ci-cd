package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        tasks.add(new Task(title));
    }

    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markCompleted();
        }
    }

    public void clearTasks() {
        tasks.clear();
    }

    public List<Task> searchTasks(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be empty");
        }
        String normalizedQuery = query.trim().toLowerCase();
        List<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(normalizedQuery)) {
                matches.add(task);
            }
        }
        return matches;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public int getTaskCount() {
        return tasks.size();
    }
}
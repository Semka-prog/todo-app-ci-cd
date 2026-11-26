package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void shouldAddTaskAndIncreaseCount() {
        taskService.addTask("Test task 1");
        taskService.addTask("Test task 2");
        assertEquals(2, taskService.getTaskCount());
    }

    @Test
    void shouldThrowExceptionWhenAddingEmptyTask() {
        assertThrows(IllegalArgumentException.class, () -> taskService.addTask("   "));
    }

    @Test
    void shouldMarkTaskAsCompleted() {
        taskService.addTask("Complete me");
        taskService.completeTask(0);
        assertTrue(taskService.getTasks().get(0).isCompleted());
    }

    @Test
    void shouldClearAllTasks() {
        taskService.addTask("First");
        taskService.addTask("Second");
        taskService.clearTasks();
        assertEquals(0, taskService.getTaskCount());
    }

    @Test
    void shouldSearchTasksCaseInsensitively() {
        taskService.addTask("Learn CI/CD");
        taskService.addTask("Write unit tests");
        List<Task> matches = taskService.searchTasks("CI");
        assertEquals(1, matches.size());
        assertEquals("Learn CI/CD", matches.get(0).getTitle());
    }

    @Test
    void shouldRejectEmptySearchQuery() {
        assertThrows(IllegalArgumentException.class, () -> taskService.searchTasks(" "));
    }

    @Test
    void shouldReturnIndependentTaskCopy() {
        taskService.addTask("Original");
        List<Task> copy = taskService.getTasks();
        copy.clear();
        assertEquals(1, taskService.getTaskCount());
    }
}

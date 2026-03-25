package model;

import java.time.LocalDate;

public class Task {

    // fields
    private long id;
    private User user;
    private String title, description;
    private LocalDate createdAt, dueDate;
    private TaskStatus status; 
    private TaskPriority priority;

    public Task(){
        super();
    };

    public Task(long id, User user, String title, String description,
            LocalDate createdAt, LocalDate dueDate,
            TaskStatus status, TaskPriority priority) {

        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }


}

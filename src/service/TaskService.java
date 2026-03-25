package service;

import dao.TaskDaoImplementation;
import java.util.List;

import model.Task;

public class TaskService {
    
    private final TaskDaoImplementation taskDao;

    public TaskService(TaskDaoImplementation taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getAllTasks() {
        return taskDao.findAll();
    }

    public Long createTask(Task task) {
        return taskDao.insertTask(task);
    }
}

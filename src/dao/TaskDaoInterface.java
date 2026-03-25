package dao;

import java.util.List;
import model.Task;

public interface TaskDaoInterface {
    List<Task> findAll();
    long insertTask(Task task);
    void updateTask(Task task);
    void deleteTask(Task task);
}

// package dao;

// import java.time.LocalDate;
// import java.util.List;

// import model.Task;
// import model.TaskPriority;
// import model.TaskStatus;
// import model.User;

// import java.util.ArrayList;
// import java.util.Collections;

// public class TaskDaoImplementation implements TaskDaoInterface{

//     private final List<Task> taskList = new ArrayList<Task>();
//     public long nextID = 1;

//     public TaskDaoImplementation() {
//         seedTasks();
//     }

//     private void seedTasks() {
//         User user = new User();
//         user.setId(1);

//         Task t1 = new Task(
//             0l, user,
//             "Drop off kids", 
//             "", 
//             LocalDate.now(), 
//             LocalDate.now().plusDays(1), 
//             TaskStatus.OPEN, 
//             TaskPriority.HIGH);

//         Task t2 = new Task(
//             0l, user,
//             "Pick up kids", 
//             "", 
//             LocalDate.now(), 
//             LocalDate.now().plusDays(1), 
//             TaskStatus.OPEN, 
//             TaskPriority.HIGH);

//         taskList.add(t1);
//         taskList.add(t2);
//     }

//     @Override
//     public List<Task> findAll() {
//         // Return an unmodifiedable copy to prevent others from mutating our 
//         // internal task list
//         return Collections.unmodifiableList(new ArrayList<Task>(taskList));
//     }

//     @Override
//     public long insertTask(Task task) {
//         long assignedId = nextID++;
//         task.setId(assignedId);

//         taskList.add(task);
//         return task.getId();
//     }

//     @Override
//     public void updateTask(Task task) {
//         return;
//     }

//     @Override
//     public void deleteTask(Task task) {
//         return;
//     }
// }

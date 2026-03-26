// import service.UserService;
// import service.TaskService;

// import java.time.LocalDate;
// import java.util.List;

// import dao.TaskDaoImplementation;
// import dao.UserDaoImplementation;
// import model.Task;
// import model.TaskPriority;
// import model.TaskStatus;
// import model.User;

// public class App {
//     public static void main(String[] args) throws Exception {
        
//         UserDaoImplementation userDao = new UserDaoImplementation();
//         UserService userService = new UserService(userDao);

//         User newUser = userService.registerUser("Nicholas Wise");

//         // System.out.println(newUser.getName());

//         TaskDaoImplementation taskDao = new TaskDaoImplementation();
//         TaskService taskService = new TaskService(taskDao);

//         // add a new task
//         Task newTask = new Task(
//             0l, 
//             newUser,
//             "Drop off kids", 
//             "", 
//             LocalDate.now(), 
//             LocalDate.now().plusDays(1), 
//             TaskStatus.OPEN, 
//             TaskPriority.HIGH);

//         taskService.createTask(newTask);
//         // PROMPT: display the values of the tasks.
//         List<Task> tasks = taskService.getAllTasks();

//         System.out.println(tasks);

//         for (int i = 0; i < tasks.size(); i++) {
//             System.out.println("ID:" + tasks.get(i).getId());
//             System.out.println("Title:" + tasks.get(i).getTitle());
//             System.out.println("Title:" + tasks.get(i).getTitle());
//             System.out.println("Created At:" + tasks.get(i).getCreatedAt());
//             System.out.println("Due Date:" + tasks.get(i).getDueDate());
//             System.out.println("Priority:" + tasks.get(i).getPriority());
//             System.out.println("User:" + tasks.get(i).getUser() + "\n");
//         }
//     }
// }

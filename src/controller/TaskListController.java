package controller;

import view.AddUpdateTaskView;
import view.TaskListView;

public class TaskListController {
    TaskListView taskListView;
    AddUpdateTaskView addUpdateTaskView;

    public TaskListController(TaskListView taskListView) {
        this.taskListView = taskListView;
        this.addUpdateTaskView = new AddUpdateTaskView(taskListView);
    }

    public void onAddButtonClick() {
        addUpdateTaskView.setVisible(true);
    }
}

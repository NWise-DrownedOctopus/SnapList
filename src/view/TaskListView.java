package view;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.TaskListController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;


public class TaskListView extends JFrame{

    private JLabel userLabel = new JLabel("Hello xx");
    private JComboBox<String> cmbStatus = new JComboBox<String>(new String[] {"Any", "OPEN", "COMPLETED", "IN PROGRESS"});
    private JComboBox<String> cmbPriority = new JComboBox<String>(new String[] {"Any", "LOW", "MEDIUM", "HIGH"});
    private JTextField txtKeywordSearch = new JTextField(20);
    private JButton btnApplyFilter = new JButton("Apply Filter");
    private JButton btnClear = new JButton("Clear Filter");

    private JTable tblTasks;

    private JButton btnNewTask = new JButton("NewTask");
    private JButton btnEditTask = new JButton("Edit Task");
    private JButton btnMarkComplete = new JButton("Mark Complete");
    private JButton btnMarkInProgress = new JButton("Mark In Progress");
    private JButton btnDelete = new JButton("Delete");

    private TaskListController controller;
    
    public TaskListView() {

        controller = new TaskListController(this);

        setSize(500, 500);
        
        setTitle("Task Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cmbPriority.setSelectedItem("Any");
        cmbStatus.setSelectedItem("Any");

        // Build an empty table model for the JTable
        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[] {"ID", "Title", "Status", "Priority", "Due Date"}, 0);     
            
        tblTasks = new JTable(tableModel);

        buildUI();
        pack();
        addActionListeners();
        setLocationRelativeTo(null);
    }

    private void buildUI() {
        setLayout(new BorderLayout());

        //Top
        JPanel topJPanel = new JPanel(new BorderLayout());

        JPanel labelJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelJPanel.add(userLabel);
        topJPanel.add(labelJPanel, BorderLayout.WEST);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Status"));
        filterPanel.add(cmbStatus);
        filterPanel.add(Box.createHorizontalStrut(12));

        filterPanel.add(new JLabel("Priority"));
        filterPanel.add(cmbPriority);
        filterPanel.add(Box.createHorizontalStrut(12));

        filterPanel.add(new JLabel("Keyword"));
        filterPanel.add(txtKeywordSearch);
        filterPanel.add(Box.createHorizontalStrut(12));

        filterPanel.add(btnApplyFilter);
        filterPanel.add(btnClear);

        topJPanel.add(filterPanel, BorderLayout.CENTER);
        add(topJPanel, BorderLayout.NORTH);
        // add(filterPanel);

        // CENTER
        add(new JScrollPane(tblTasks), BorderLayout.CENTER);

        // BOTTOM
        JPanel bottomPanel = new JPanel();

        bottomPanel.add(btnNewTask);
        bottomPanel.add(btnEditTask);
        bottomPanel.add(btnMarkComplete);
        bottomPanel.add(btnMarkInProgress);
        bottomPanel.add(btnDelete);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addActionListeners() {
        btnNewTask.addActionListener(e -> controller.onAddButtonClick());
    }
}

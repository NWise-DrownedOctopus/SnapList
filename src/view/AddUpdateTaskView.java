package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddUpdateTaskView extends JDialog {
    private JTextField txtTitle = new JTextField(35);
    private JTextArea txtDescription = new JTextArea(6, 35);
    private JTextField txtDueDate = new JTextField(35);
    private JComboBox<String> cmbPriority = new JComboBox<String>(new String[] {"LOW", "MEDIUM", "HIGH"});
    private JButton btnSave = new JButton("Save");
    
    public AddUpdateTaskView(Frame parent) {

        super(parent, true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // setAlwaysOnTop(true);
        // cmbPriority.setSelectedItem("MEDIUM");
        setLayout(new BorderLayout());
        // setSize(100, 100);

        setLocationRelativeTo(parent);
        
        buildUI();
    }

    private void buildUI() {
        cmbPriority.setSelectedItem("MEDIUM");
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);

        Dimension labelSize = new Dimension(200, 25);

        // NORTH
        JPanel titleRow = new JPanel(new BorderLayout(12, 0));
        JLabel lblTitle = new JLabel("Title");
        lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTitle.setPreferredSize(labelSize);
        titleRow.add(lblTitle, BorderLayout.WEST);
        titleRow.add(txtTitle, BorderLayout.CENTER);

        // CENTER
        JPanel descRow = new JPanel(new BorderLayout(12, 0));
        JLabel lblDesc = new JLabel("Description");
        lblDesc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDesc.setPreferredSize(labelSize);
        descRow.add(lblDesc, BorderLayout.WEST);
        descRow.add(txtDescription, BorderLayout.CENTER);

        // SOUTH
        JPanel bottomFields = new JPanel(new GridLayout(3, 1, 0, 10));

        JPanel dueDateRow = new JPanel(new BorderLayout(12, 0));
        JLabel lblDueDate = new JLabel("Due Date (YYYY-MM-DD)");
        lblDueDate.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDueDate.setPreferredSize(labelSize);
        JPanel dueWrap = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dueWrap.add(txtDueDate);
        dueDateRow.add(lblDueDate, BorderLayout.WEST);
        dueDateRow.add(dueWrap, BorderLayout.CENTER);

        JPanel cmbJPanel = new JPanel(new GridLayout(1, 2, 0, 10));
        JLabel lblPriority = new JLabel("Priority");
        lblPriority.setHorizontalAlignment(SwingConstants.RIGHT);
        cmbJPanel.add(lblPriority);
        cmbJPanel.add(cmbPriority);

        bottomFields.add(dueDateRow);
        bottomFields.add(cmbJPanel);
        bottomFields.add(btnSave);

        add(titleRow, BorderLayout.NORTH);
        add(descRow, BorderLayout.CENTER);
        add(bottomFields, BorderLayout.SOUTH);

        pack();
        setResizable(false);
    }
}

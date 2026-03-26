package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class AddUpdateCardView extends JDialog {
    private JComboBox<String> cmbSet = new JComboBox<String>(new String[] { "Any", "Lorwyn", "Final Fantasy", "Edge of Eternities"});

    private JTextField txtName = new JTextField(35);

    private JComboBox<String> cmbLanguage = new JComboBox<String>(new String[] { "Any", "English", "Japanese", "Korean", "Spanish", "Russian", "German", "French" });

    private JRadioButton btnNM = new JRadioButton("NM");
    private JRadioButton btnLP = new JRadioButton("LP");
    private JRadioButton btnMP = new JRadioButton("MP");
    private JRadioButton btnHP = new JRadioButton("HP");
    private JRadioButton btnDG = new JRadioButton("DG");

    private JTextField txtQuantity = new JTextField(10);

    private JButton btnSave = new JButton("Save");

    public AddUpdateCardView(Frame parent) {

        super(parent, true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Add New Card");
        setLayout(new BorderLayout());

        setLocationRelativeTo(parent);

        buildUI();
    }

    private void buildUI() {
        JPanel addCardPane = new JPanel();
        addCardPane.setLayout(new BoxLayout(addCardPane, BoxLayout.Y_AXIS));
        cmbSet.setSelectedItem("Any");

        Dimension labelSize = new Dimension(60, 25);

        // Set Drop Down
        JPanel setRow = new JPanel(new BorderLayout(12, 0));
        JLabel lblSet = new JLabel("Set"); 
        lblSet.setPreferredSize(labelSize);       
        
        setRow.add(lblSet, BorderLayout.WEST);
        setRow.add(cmbSet, BorderLayout.CENTER);

        // Card Name Entry
        JPanel nameRow = new JPanel(new BorderLayout(12, 0));
        JLabel lblName = new JLabel("Name");
        lblName.setPreferredSize(labelSize);

        nameRow.add(lblName, BorderLayout.WEST);
        nameRow.add(txtName, BorderLayout.CENTER);

        // Condtion Radio Button
        JPanel conditionRow = new JPanel();
        ButtonGroup group = new ButtonGroup();
        group.add(btnNM);
        group.add(btnLP);
        group.add(btnMP);
        group.add(btnHP);
        group.add(btnDG);
        conditionRow.add(btnNM);
        conditionRow.add(btnLP);
        conditionRow.add(btnMP);
        conditionRow.add(btnHP);
        conditionRow.add(btnDG);

        // Language Drop Down
        JPanel LanguageRow = new JPanel(new BorderLayout(12, 0));
        JLabel lblLanguage = new JLabel("Language");

        lblLanguage.setHorizontalAlignment(SwingConstants.LEFT);
        lblLanguage.setPreferredSize(labelSize);
        LanguageRow.add(lblLanguage, BorderLayout.WEST);
        LanguageRow.add(cmbLanguage, BorderLayout.CENTER);

        // Quantity text field
        JPanel quantityRow = new JPanel(new BorderLayout(12, 0));
        JLabel lblQuantity = new JLabel("Quantity");        
        lblQuantity.setPreferredSize(labelSize);
        quantityRow.add(lblQuantity,  BorderLayout.WEST);
        quantityRow.add(txtQuantity, BorderLayout.CENTER);

        // Save Button
        JPanel saveRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveRow.add(btnSave);
        
        addCardPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        addCardPane.setPreferredSize(new Dimension(400, 210));

        addCardPane.add(setRow);
        addCardPane.add(Box.createVerticalStrut(5));

        addCardPane.add(nameRow);
        addCardPane.add(Box.createVerticalStrut(5));

        addCardPane.add(conditionRow);
        addCardPane.add(Box.createVerticalStrut(5));

        addCardPane.add(LanguageRow);
        addCardPane.add(Box.createVerticalStrut(5));

        addCardPane.add(quantityRow);
        addCardPane.add(Box.createVerticalStrut(5));

        addCardPane.add(saveRow);
        addCardPane.add(Box.createVerticalStrut(5));

        setRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        nameRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        conditionRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        LanguageRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        quantityRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        add(addCardPane, BorderLayout.CENTER);
        txtName.requestFocusInWindow();
        getRootPane().setDefaultButton(btnSave);
        btnNM.setSelected(true);

        pack();
        setResizable(false);       
    }
}

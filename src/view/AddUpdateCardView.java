package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Condition;
import model.Language;
import model.Card;

public class AddUpdateCardView extends JDialog {
    private JComboBox<String> cmbSet = new JComboBox<>(new String[] { "Lorwyn Eclipsed", "Final Fantasy", "Edge of Eternities" });
    private JTextField txtName = new JTextField(30);
    private JComboBox<Language> cmbLanguage = new JComboBox<>(Language.values());

    private JRadioButton btnNM = new JRadioButton("NM");
    private JRadioButton btnLP = new JRadioButton("LP");
    private JRadioButton btnMP = new JRadioButton("MP");
    private JRadioButton btnHP = new JRadioButton("HP");
    private JRadioButton btnDG = new JRadioButton("DG");

    private JTextField txtQuantity = new JTextField(10);
    private JButton btnSave = new JButton("Save");

    public AddUpdateCardView(Frame parent) {
        super(parent, true);
        setTitle("Add New Card");
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        buildUI();
        pack();
        setResizable(false);
    }

    private void buildUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        Dimension labelSize = new Dimension(70, 25);

        // Set row
        JPanel setRow = new JPanel(new BorderLayout(10, 0));
        JLabel lblSet = new JLabel("Set");
        lblSet.setPreferredSize(labelSize);
        setRow.add(lblSet, BorderLayout.WEST);
        setRow.add(cmbSet, BorderLayout.CENTER);

        // Name row
        JPanel nameRow = new JPanel(new BorderLayout(10, 0));
        JLabel lblName = new JLabel("Name");
        lblName.setPreferredSize(labelSize);
        nameRow.add(lblName, BorderLayout.WEST);
        nameRow.add(txtName, BorderLayout.CENTER);

        // Condition row
        JPanel conditionRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        btnNM.setSelected(true);

        // Language row
        JPanel languageRow = new JPanel(new BorderLayout(10, 0));
        JLabel lblLanguage = new JLabel("Language");
        lblLanguage.setPreferredSize(labelSize);
        languageRow.add(lblLanguage, BorderLayout.WEST);
        languageRow.add(cmbLanguage, BorderLayout.CENTER);

        // Quantity row
        JPanel quantityRow = new JPanel(new BorderLayout(10, 0));
        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setPreferredSize(labelSize);
        quantityRow.add(lblQuantity, BorderLayout.WEST);
        quantityRow.add(txtQuantity, BorderLayout.CENTER);

        // Save button row
        JPanel saveRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveRow.add(btnSave);

        // Add rows to main panel
        panel.add(setRow);
        panel.add(Box.createVerticalStrut(5));
        panel.add(nameRow);
        panel.add(Box.createVerticalStrut(5));
        panel.add(conditionRow);
        panel.add(Box.createVerticalStrut(5));
        panel.add(languageRow);
        panel.add(Box.createVerticalStrut(5));
        panel.add(quantityRow);
        panel.add(Box.createVerticalStrut(10));
        panel.add(saveRow);

        add(panel, BorderLayout.CENTER);

        txtName.requestFocusInWindow();
        getRootPane().setDefaultButton(btnSave);
    }

    // ---- Getters for controller ----
    public String getCardName() { return txtName.getText().trim(); }
    public String getSelectedSet() { return (String) cmbSet.getSelectedItem(); }
    
    public int getQuantity() {
        try {
            return Integer.parseInt(txtQuantity.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Condition getSelectedCondition() {
        if (btnNM.isSelected()) return Condition.NM;
        if (btnLP.isSelected()) return Condition.LP;
        if (btnMP.isSelected()) return Condition.MP;
        if (btnHP.isSelected()) return Condition.HP;
        if (btnDG.isSelected()) return Condition.DG;
        return Condition.NM;
    }

    public Language getSelectedLanguage() {
        return (Language) cmbLanguage.getSelectedItem();
    }

    public void populateFields(Card card) {
        txtName.setText(card.getName());
        cmbSet.setSelectedItem(card.getSet());
        cmbLanguage.setSelectedItem(card.getLanguage());
        switch (card.getCondition()) {
            case NM -> btnNM.setSelected(true);
            case LP -> btnLP.setSelected(true);
            case MP -> btnMP.setSelected(true);
            case HP -> btnHP.setSelected(true);
            case DG -> btnDG.setSelected(true);
        }
    }

    public JButton getBtnSave() { return btnSave; }
}
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import controller.CardListController;

public class CardListView extends JFrame {
    private JComboBox<String> cmbGame = new JComboBox<>(
            new String[] { "Any", "MTG", "Pokemon", "UnionArena", "Lorcana", "One Piece" });
    private JComboBox<String> cmbLanguage = new JComboBox<>(
            new String[] { "Any", "English", "Japanese", "Korean", "Spanish", "Russian", "German", "French" });
    private JButton btnNM = new JButton("NM");
    private JButton btnLP = new JButton("LP");
    private JButton btnMP = new JButton("MP");
    private JButton btnHP = new JButton("HP");
    private JButton btnDG = new JButton("DG");

    private JTable tblTasks;

    private JButton btnAdd = new JButton("Add");
    private JButton btnEdit = new JButton("Edit");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnSubmit = new JButton("Submit");

    // Optional: dynamic labels for offers
    private JLabel lblQuantity = new JLabel("Quantity: 0");
    private JLabel lblMarketValue = new JLabel("Market Value: $0.00");
    private JLabel lblStoreCredit = new JLabel("Store Credit Offer: $0.00");
    private JLabel lblCash = new JLabel("Cash Offer: $0.00");

    private CardListController controller;

    public CardListView() {
        controller = new CardListController(this);

        setTitle("Snap List - Untitled Project");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cmbLanguage.setSelectedItem("Any");
        cmbGame.setSelectedItem("Any");

        // Table model
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[] { "#", "Shop Qtry", "Game", "Product Name", "Set", "Condition", "Printing", "Lang.",
                        "Qty.", "Market Price" },
                0);
        tblTasks = new JTable(tableModel);

        // Adjust column widths
        tblTasks.getColumnModel().getColumn(0).setPreferredWidth(30); // #
        tblTasks.getColumnModel().getColumn(1).setPreferredWidth(60); // Shop Qtry
        tblTasks.getColumnModel().getColumn(2).setPreferredWidth(100); // Game
        tblTasks.getColumnModel().getColumn(3).setPreferredWidth(250); // Product Name 
        tblTasks.getColumnModel().getColumn(4).setPreferredWidth(200); // Set 
        tblTasks.getColumnModel().getColumn(5).setPreferredWidth(60); // Condition
        tblTasks.getColumnModel().getColumn(6).setPreferredWidth(80); // Printing
        tblTasks.getColumnModel().getColumn(7).setPreferredWidth(80); // Lang
        tblTasks.getColumnModel().getColumn(8).setPreferredWidth(50); // Qty. 
        tblTasks.getColumnModel().getColumn(9).setPreferredWidth(100); // Market Price

        buildUI();
        addActionListeners();

        setPreferredSize(new Dimension(1200, 700));
        setResizable(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void buildUI() {
        setLayout(new BorderLayout(10, 10));

        // ===== Top Filter Panel =====
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Game"));
        filterPanel.add(cmbGame);
        filterPanel.add(Box.createHorizontalStrut(12));

        filterPanel.add(new JLabel("Language"));
        filterPanel.add(cmbLanguage);
        filterPanel.add(Box.createHorizontalStrut(12));

        filterPanel.add(btnNM);
        filterPanel.add(btnLP);
        filterPanel.add(btnMP);
        filterPanel.add(btnHP);
        filterPanel.add(btnDG);

        add(filterPanel, BorderLayout.NORTH);

        // ===== Center Table =====
        JScrollPane tableScroll = new JScrollPane(tblTasks);
        tableScroll.setPreferredSize(new Dimension(600, 300));
        add(tableScroll, BorderLayout.CENTER);

        // ===== Bottom Panels =====
        // Left: action buttons
        JPanel actionPanel = new JPanel();
        actionPanel.add(btnAdd);
        actionPanel.add(Box.createHorizontalStrut(10));
        actionPanel.add(btnEdit);
        actionPanel.add(Box.createHorizontalStrut(10));
        actionPanel.add(btnDelete);

        // Right: offer info
        JPanel offerPanel = new JPanel();
        offerPanel.setLayout(new BoxLayout(offerPanel, BoxLayout.Y_AXIS));
        offerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        offerPanel.add(lblQuantity);
        offerPanel.add(Box.createVerticalStrut(5));
        offerPanel.add(lblMarketValue);
        offerPanel.add(Box.createVerticalStrut(5));
        offerPanel.add(lblStoreCredit);
        offerPanel.add(Box.createVerticalStrut(5));
        offerPanel.add(lblCash);
        offerPanel.add(Box.createVerticalStrut(5));
        offerPanel.add(btnSubmit);

        // Combine into one bottom container
        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        bottomContainer.add(actionPanel, BorderLayout.WEST);
        bottomContainer.add(offerPanel, BorderLayout.EAST);

        add(bottomContainer, BorderLayout.SOUTH);
    }

    private void addActionListeners() {
        btnAdd.addActionListener(e -> controller.onAddButtonClick());
        btnEdit.addActionListener(e -> controller.onEditButtonClick());
        btnDelete.addActionListener(e -> controller.onDeleteButtonClick());
        btnSubmit.addActionListener(e -> controller.onSubmitButtonClick());
    }

    // Optional: methods to update dynamic offer labels
    public void updateOfferLabels(int quantity, double market, double storeCredit, double cash) {
        lblQuantity.setText("Quantity: " + quantity);
        lblMarketValue.setText(String.format("Market Value: $%.2f", market));
        lblStoreCredit.setText(String.format("Store Credit Offer: $%.2f", storeCredit));
        lblCash.setText(String.format("Cash Offer: $%.2f", cash));
    }
}
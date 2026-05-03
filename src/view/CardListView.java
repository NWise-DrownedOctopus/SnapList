package view;

import model.Card;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controller.AddUpdateCardController;
import controller.CardListController;

public class CardListView extends JFrame {
    private JComboBox<String> cmbGame = new JComboBox<>(
            new String[] { "Any", "MTG", "Pokemon", "UnionArena", "Lorcana", "One Piece" });
    private JComboBox<String> cmbLanguage = new JComboBox<>(
            new String[] { "Any", "English", "Japanese", "Korean", "Spanish", "Russian", "German", "French" });

    private JToggleButton btnNM = new JToggleButton("NM");
    private JToggleButton btnLP = new JToggleButton("LP");
    private JToggleButton btnMP = new JToggleButton("MP");
    private JToggleButton btnHP = new JToggleButton("HP");
    private JToggleButton btnDG = new JToggleButton("DG");

    private JTable tblTasks;
    private java.util.List<Card> currentCards = new java.util.ArrayList<>();
    private java.util.List<Card> filteredCards = new java.util.ArrayList<>(); // tracks what's visible in the table

    private JButton btnAdd = new JButton("Add");
    private JButton btnEdit = new JButton("Edit");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnSubmit = new JButton("Submit");
    private JButton btnLogout = new JButton("Logout");

    private JLabel lblQuantity = new JLabel("Quantity: 0");
    private JLabel lblMarketValue = new JLabel("Market Value: $0.00");
    private JLabel lblStoreCredit = new JLabel("Store Credit Offer: $0.00");
    private JLabel lblCash = new JLabel("Cash Offer: $0.00");
    private JLabel lblLoading = new JLabel("Loading cards...");

    private CardListController controller;

    public CardListView(CardListController controller) {
        this.controller = controller;

        setTitle("Snap List - Untitled Project");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cmbLanguage.setSelectedItem("Any");
        cmbGame.setSelectedItem("Any");

        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[] { "#", "Shop Qtry", "Game", "Product Name", "Set", "Condition", "Printing", "Lang.",
                        "Qty.", "Market Price" },
                0);
        tblTasks = new JTable(tableModel);

        tblTasks.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblTasks.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblTasks.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblTasks.getColumnModel().getColumn(3).setPreferredWidth(250);
        tblTasks.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblTasks.getColumnModel().getColumn(5).setPreferredWidth(60);
        tblTasks.getColumnModel().getColumn(6).setPreferredWidth(80);
        tblTasks.getColumnModel().getColumn(7).setPreferredWidth(80);
        tblTasks.getColumnModel().getColumn(8).setPreferredWidth(50);
        tblTasks.getColumnModel().getColumn(9).setPreferredWidth(100);

        buildUI();
        addActionListeners();

        setPreferredSize(new Dimension(1200, 700));
        setResizable(true);
        pack();
        setLocationRelativeTo(null);

        refreshTable();
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

        // Logout button pinned to the right of the top bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.add(filterPanel, BorderLayout.CENTER);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(btnLogout);
        topBar.add(logoutPanel, BorderLayout.EAST);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(topBar, BorderLayout.NORTH);
        topPanel.add(lblLoading, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // ===== Center Table =====
        JScrollPane tableScroll = new JScrollPane(tblTasks);
        tableScroll.setPreferredSize(new Dimension(600, 300));
        add(tableScroll, BorderLayout.CENTER);

        // ===== Bottom Panels =====
        JPanel actionPanel = new JPanel();
        actionPanel.add(btnAdd);
        actionPanel.add(Box.createHorizontalStrut(10));
        actionPanel.add(btnEdit);
        actionPanel.add(Box.createHorizontalStrut(10));
        actionPanel.add(btnDelete);

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

        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        bottomContainer.add(actionPanel, BorderLayout.WEST);
        bottomContainer.add(offerPanel, BorderLayout.EAST);

        add(bottomContainer, BorderLayout.SOUTH);
    }

    private void addActionListeners() {

        // ── Filters ──────────────────────────────────────────────
        cmbGame.addActionListener(e -> applyFilters());
        cmbLanguage.addActionListener(e -> applyFilters());

        btnNM.addActionListener(e -> applyFilters());
        btnLP.addActionListener(e -> applyFilters());
        btnMP.addActionListener(e -> applyFilters());
        btnHP.addActionListener(e -> applyFilters());
        btnDG.addActionListener(e -> applyFilters());

        // ── CRUD buttons ─────────────────────────────────────────
        btnAdd.addActionListener(e -> {
            controller.openAddDialog();
            refreshTable();
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = tblTasks.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a card entry to edit");
                return;
            }
            // Use filteredCards so the correct card is selected even when filters are active
            Card selectedCard = filteredCards.get(selectedRow);
            controller.openEditDialog(selectedCard);
            refreshTable();
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = tblTasks.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a card entry to delete");
                return;
            }
            // Use filteredCards so the correct card is deleted even when filters are active
            Card selectedCard = filteredCards.get(selectedRow);
            controller.deleteCard(selectedCard.getId());
            refreshTable();
        });

        btnSubmit.addActionListener(e -> controller.onSubmitButtonClick());

        // ── Logout ───────────────────────────────────────────────
        btnLogout.addActionListener(e -> controller.logout());
    }

    public void updateOfferLabels(int quantity, double market, double storeCredit, double cash) {
        lblQuantity.setText("Quantity: " + quantity);
        lblMarketValue.setText(String.format("Market Value: $%.2f", market));
        lblStoreCredit.setText(String.format("Store Credit Offer: $%.2f", storeCredit));
        lblCash.setText(String.format("Cash Offer: $%.2f", cash));
    }

    public void refreshTable() {
        lblLoading.setVisible(true);
        tblTasks.setEnabled(false);

        SwingWorker<java.util.List<Card>, Void> worker = new SwingWorker<>() {

            @Override
            protected java.util.List<Card> doInBackground() throws Exception {
                Thread.sleep(1500);
                return controller.getAllCards();
            }

            @Override
            protected void done() {
                try {
                    currentCards = get();
                    applyFilters(); // applyFilters updates filteredCards and rebuilds the table

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            CardListView.this,
                            "Failed to load cards: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } finally {
                    lblLoading.setVisible(false);
                    tblTasks.setEnabled(true);
                }
            }
        };

        worker.execute();
    }

    private void applyFilters() {
        String selectedGame = (String) cmbGame.getSelectedItem();
        String selectedLanguage = (String) cmbLanguage.getSelectedItem();
        String selectedCondition = getSelectedCondition();

        // Build filtered list and store it in filteredCards
        filteredCards = currentCards.stream()
                .filter(card -> {
                    if (!"Any".equals(selectedGame)) {
                        if (!card.getGame().name().equalsIgnoreCase(selectedGame))
                            return false;
                    }
                    if (!"Any".equals(selectedLanguage)) {
                        if (!card.getLanguage().name().equalsIgnoreCase(selectedLanguage))
                            return false;
                    }
                    if (selectedCondition != null) {
                        if (!card.getCondition().name().equals(selectedCondition))
                            return false;
                    }
                    return true;
                })
                .collect(java.util.stream.Collectors.toList());

        // Rebuild table from filteredCards
        DefaultTableModel model = (DefaultTableModel) tblTasks.getModel();
        model.setRowCount(0);

        int counter = 1;
        for (Card card : filteredCards) {
            model.addRow(new Object[] {
                    counter++,
                    0,
                    card.getGame(),
                    card.getName(),
                    card.getSet(),
                    card.getCondition(),
                    "Normal",
                    card.getLanguage(),
                    card.getQuantity(), // real quantity from model
                    0.0
            });
        }
    }

    private String getSelectedCondition() {
        if (btnNM.isSelected()) return "NM";
        if (btnLP.isSelected()) return "LP";
        if (btnMP.isSelected()) return "MP";
        if (btnHP.isSelected()) return "HP";
        if (btnDG.isSelected()) return "DG";
        return null;
    }
}
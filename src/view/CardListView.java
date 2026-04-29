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
    // Change these four lines at the top of the class
    private JToggleButton btnNM = new JToggleButton("NM");
    private JToggleButton btnLP = new JToggleButton("LP");
    private JToggleButton btnMP = new JToggleButton("MP");
    private JToggleButton btnHP = new JToggleButton("HP");
    private JToggleButton btnDG = new JToggleButton("DG");

    private JTable tblTasks;
    private java.util.List<Card> currentCards = new java.util.ArrayList<>();

    private JButton btnAdd = new JButton("Add");
    private JButton btnEdit = new JButton("Edit");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnSubmit = new JButton("Submit");

    // Optional: dynamic labels for offers
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

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(filterPanel, BorderLayout.NORTH);
        topPanel.add(lblLoading, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

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
    // TICKET:: Shop quantity would need to hook up to some sort of internal storage
    // system that would allow the user to manage their card inventory
    // For now I have it at 0, but ideally I could set up an inventory system, and
    // then allow this to pull data from that to manage that state.

    // TICKET:: Printing is still not fully implemented, because it will relly on
    // the db to pull print information that is avalible for a card, for now
    // it has a default (normal) print set as a placeholder

    // TICKET:: Market price should be updated and stored locally, but the process
    // of pulling that data from online and chaching isn't implemented, so $0 is a
    // placeholder for now

    // TICKET:: QTY should be updated as the user adds entries to the table, they
    // should be able to specify how many of the card should be added to the list,
    // but without having multiple entries
    // in the table

    // Updates the view of the table to reflect the data from the dao
    // public void refreshTable() {
    // DefaultTableModel model = (DefaultTableModel) tblTasks.getModel();
    // model.setRowCount(0); // clear current rows

    // // Stores acurate list of cards that are stored in the DAO
    // currentCards = controller.getAllCards();

    // int counter = 1;
    // for (Card card : currentCards) {
    // model.addRow(new Object[] {
    // counter++,
    // 0, // placeholder shop quantity
    // card.getGame(),
    // card.getName(),
    // card.getSet(),
    // card.getCondition(),
    // "Normal", // placeholder printing
    // card.getLanguage(),
    // 1, // placeholder quantity
    // 0.0 // placeholder market price
    // });
    // }
    // }

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
            Card selectedCard = currentCards.get(selectedRow);
            controller.openEditDialog(selectedCard);
            refreshTable();
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = tblTasks.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a card entry to delete");
                return;
            }
            Card selectedCard = currentCards.get(selectedRow);
            controller.deleteCard(selectedCard.getId());
            refreshTable();
        });

        btnSubmit.addActionListener(e -> controller.onSubmitButtonClick());
    }

    // Optional: methods to update dynamic offer labels
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
                // DB call happens on background thread
                Thread.sleep(1500);
                return controller.getAllCards();
            }

            @Override
            protected void done() {
                try {
                    currentCards = get();

                    DefaultTableModel model = (DefaultTableModel) tblTasks.getModel();
                    model.setRowCount(0);

                    int counter = 1;
                    for (Card card : currentCards) {
                        model.addRow(new Object[] {
                                counter++,
                                0,
                                card.getGame(),
                                card.getName(),
                                card.getSet(),
                                card.getCondition(),
                                "Normal",
                                card.getLanguage(),
                                1,
                                0.0
                        });
                    }

                    applyFilters();

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

        // Filter the current card list
        java.util.List<Card> filtered = currentCards.stream()
                .filter(card -> {
                    // Game filter
                    if (!"Any".equals(selectedGame)) {
                        if (!card.getGame().name().equalsIgnoreCase(selectedGame))
                            return false;
                    }
                    // Language filter
                    if (!"Any".equals(selectedLanguage)) {
                        if (!card.getLanguage().name().equalsIgnoreCase(selectedLanguage))
                            return false;
                    }
                    // Condition filter
                    if (selectedCondition != null) {
                        if (!card.getCondition().name().equals(selectedCondition))
                            return false;
                    }
                    return true;
                })
                .collect(java.util.stream.Collectors.toList());

        // Rebuild the table with filtered results
        DefaultTableModel model = (DefaultTableModel) tblTasks.getModel();
        model.setRowCount(0);

        int counter = 1;
        for (Card card : filtered) {
            model.addRow(new Object[] {
                    counter++,
                    0,
                    card.getGame(),
                    card.getName(),
                    card.getSet(),
                    card.getCondition(),
                    "Normal",
                    card.getLanguage(),
                    1,
                    0.0
            });
        }
    }

    private String getSelectedCondition() {
        // Check which condition button is toggled
        // We track this with a selected state on the buttons
        if (btnNM.isSelected())
            return "NM";
        if (btnLP.isSelected())
            return "LP";
        if (btnMP.isSelected())
            return "MP";
        if (btnHP.isSelected())
            return "HP";
        if (btnDG.isSelected())
            return "DG";
        return null; // no condition filter active
    }
}
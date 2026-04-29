package controller;

import service.CardService;
import model.Card;
import model.Condition;
import model.Language;
import view.AddUpdateCardView;

import javax.swing.*;

public class AddUpdateCardController {
    private AddUpdateCardView view;
    private CardService service;
    private Card editingCard;
    JComboBox<Language> cmbLanguage = new JComboBox<>(Language.values());

    public AddUpdateCardController(AddUpdateCardView view, CardService service, Card card) {
        this.view = view;
        this.service = service;
        this.editingCard = card;

        if (card != null) {
            view.populateFields(card);
            view.setTitle("Edit Card");
        } else {
            view.setTitle("Add New Card");
        }
        initController();
    }

    private void initController() {
        view.getBtnSave().addActionListener(e -> onSave());
    }

    private void onSave() {
        try {
            String name = view.getCardName();
            String set = view.getSelectedSet();
            Language language = view.getSelectedLanguage();
            Condition condition = view.getSelectedCondition();
            int quantity = view.getQuantity();

            long userId = model.CurrentUser.get().getId();

            if (editingCard != null) {
                service.updateCard(editingCard, name, set, language, condition, quantity);
                JOptionPane.showMessageDialog(view, "Card updated successfully!");
            } else {
                service.addCard(name, set, language, condition, quantity, userId);
                JOptionPane.showMessageDialog(view, "Card added successfully!");
            }

            view.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
package controller;

import dao.CardDaoImplementation;
import model.Card;
import model.Condition;
import model.Game;
import model.Mtg_Card;
import model.Language;
import model.MTG_Printing;
import view.AddUpdateCardView;

import javax.swing.*;

public class AddUpdateCardController {
    private AddUpdateCardView view;
    private CardDaoImplementation dao;

    public AddUpdateCardController(AddUpdateCardView view, CardDaoImplementation dao) {
        this.view = view;
        this.dao = dao;
        initController();
    }

    private void initController() {
        view.getBtnSave().addActionListener(e -> onSave());
    }

    private void onSave() {
        try {
            String name = view.getCardName();
            if (name.isEmpty())
                throw new Exception("Name cannot be empty");

            String set = view.getSelectedSet();
            String langStr = view.getSelectedLanguage();
            Language language = Language.valueOf(langStr.toUpperCase());

            Condition condition = view.getSelectedCondition();

            int quantity;
            try {
                quantity = Integer.parseInt(view.getQuantity());
                if (quantity < 1)
                    throw new Exception("Quantity must be at least 1");
            } catch (NumberFormatException e) {
                throw new Exception("Quantity must be a number");
            }

            
            // TICKET:: I want name to be an auto complete field. Once you type the name of the card, it will display multiple
            //          entries with the same name, but with appended printing modifiers, once one is selected, it will
            //          allow me to specify the printing there. I don't want to have an adiditonal drop down or field for printing
            //          for now card bellow is getting default (normal) printing

            // Create new card (example MTG card)
            Card newCard = new Mtg_Card(null, name, Game.MTG, set, language, condition, MTG_Printing.normal);

            dao.insertCard(newCard);

            JOptionPane.showMessageDialog(view, "Card added successfully!");
            view.dispose(); // close dialog
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
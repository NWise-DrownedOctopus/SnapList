package controller;

import model.Card;
import service.CardService;
import view.AddUpdateCardView;
import view.CardListView;

import java.util.List;

public class CardListController {
    private CardService service;
    private CardListView view;

    public CardListController(CardService service) {
        this.service = service;
    }

    public void setView(CardListView view) {
        this.view = view;
    }

    public List<Card> getAllCards() {
        return service.getAllCards();
    }

    public void deleteCard(long id) {
        service.deleteCard(id);
        if (view != null)
            view.refreshTable();
    }

    public void openAddDialog() {
        AddUpdateCardView dialog = new AddUpdateCardView(view);
        new AddUpdateCardController(dialog, service, null);
        dialog.setVisible(true);
        if (view != null)
            view.refreshTable();
    }

    public void openEditDialog(Card card) {
        AddUpdateCardView dialog = new AddUpdateCardView(view);
        new AddUpdateCardController(dialog, service, card);
        dialog.setVisible(true);
        if (view != null)
            view.refreshTable();
    }

    public void onSubmitButtonClick() {
        // placeholder logic
    }
}
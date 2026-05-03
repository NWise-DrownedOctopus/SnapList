package controller;

import model.Card;
import model.CurrentUser;
import service.CardService;
import service.UserService;
import view.AddUpdateCardView;
import view.CardListView;
import view.LoginView;

import java.util.List;

public class CardListController {
    private CardService cardService;
    private UserService userService;
    private CardListView view;

    public CardListController(CardService cardService, UserService userService) {
        this.cardService = cardService;
        this.userService = userService;
    }

    public void setView(CardListView view) {
        this.view = view;
    }

    public List<Card> getAllCards() {
        if (model.CurrentUser.isAdmin()) {
            return cardService.getAllCards();
        } else {
            return cardService.getAllCardsByUser(model.CurrentUser.get().getId());
        }
    }

    public void deleteCard(long id) {
        cardService.deleteCard(id);
        if (view != null)
            view.refreshTable();
    }

    public void openAddDialog() {
        AddUpdateCardView dialog = new AddUpdateCardView(view);
        new AddUpdateCardController(dialog, cardService, null);
        dialog.setVisible(true);
        if (view != null)
            view.refreshTable();
    }

    public void openEditDialog(Card card) {
        AddUpdateCardView dialog = new AddUpdateCardView(view);
        new AddUpdateCardController(dialog, cardService, card);
        dialog.setVisible(true);
        if (view != null)
            view.refreshTable();
    }

    public void logout() {
        CurrentUser.clear();
        LoginView loginView = new LoginView(userService);
        loginView.setVisible(true);
        view.dispose();
    }

    public void onSubmitButtonClick() {
        // placeholder logic
    }
}
package controller;

import view.AddUpdateCardView;
import view.CardListView;

public class CardListController {
    CardListView cardListView;
    AddUpdateCardView addUpdateCardView;

    public CardListController(CardListView cardListView) {
        this.cardListView = cardListView;
        this.addUpdateCardView = new AddUpdateCardView(cardListView);
    }

    public void onAddButtonClick() {
        addUpdateCardView.setVisible(true);
    }

    public void onEditButtonClick() {
    }

    public void onDeleteButtonClick() {
    }
    
    public void onSubmitButtonClick() {
    }
}

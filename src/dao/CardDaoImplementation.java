package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Card;
import model.Game;
import model.Language;
import model.Condition;
import model.Mtg_Card;
import model.MTG_Printing;

public class CardDaoImplementation {
    private final List<Card> cardList = new ArrayList<>();
    private long nextID = 1;

    public CardDaoImplementation() {
        seedCards();
    }

    private void seedCards() {
        Card card1 = new Mtg_Card(null, "Harmonized Crescendo", Game.MTG, "Lorwyn Eclipsed", Language.ENGLISH,
                Condition.LP, MTG_Printing.extended_art);
        insertCard(card1);
    }

    public List<Card> findAll() {
        return Collections.unmodifiableList(cardList);
    }

    public synchronized long insertCard(Card card) {
        long assignedId = nextID++;
        card.setId(assignedId);
        cardList.add(card);
        return assignedId;
    }

    public Card findById(long id) {
        return cardList.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public boolean delete(long id) {
        return cardList.removeIf(c -> c.getId() == id);
    }

    public boolean update(Card updatedCard) {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getId() == updatedCard.getId()) {
                cardList.set(i, updatedCard);
                return true;
            }
        }
        return false;
    }
}
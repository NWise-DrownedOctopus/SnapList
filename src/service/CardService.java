package service;

import dao.CardDaoImplementation;
import model.Card;
import model.Condition;
import model.Game;
import model.Language;
import model.Mtg_Card;
import model.MTG_Printing;

import java.util.List;

public class CardService {
    private CardDaoImplementation dao;

    public CardService(CardDaoImplementation dao) {
        this.dao = dao;
    }

    public List<Card> getAllCards() {
        return dao.findAll();
    }

    public List<Card> getAllCardsByUser(long userId) {
        return dao.findAllByUser(userId);
    }

    public void addCard(String name, String set, Language language, Condition condition, int quantity, long userId) {
        validateCard(name, quantity);

        Mtg_Card newCard = new Mtg_Card(null, name, Game.MTG, set, language, condition, MTG_Printing.normal, userId);
        newCard.setUserId(userId);

        dao.insertCard(newCard);
    }

    public void updateCard(Card card, String name, String set, Language language, Condition condition, int quantity) {
        validateCard(name, quantity);

        card.setName(name);
        card.setSet(set);
        card.setLanguage(language);
        card.setCondition(condition);

        dao.update(card);
    }

    public boolean deleteCard(long id) {
        return dao.delete(id);
    }

    private void validateCard(String name, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
    }
}
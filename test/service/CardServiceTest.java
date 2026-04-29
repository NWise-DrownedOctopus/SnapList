package service;

import dao.CardDaoImplementation;
import model.Card;
import model.Condition;
import model.Language;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardServiceTest {

    // ── In-memory stub DAO — no DB connection needed ──────────
    private static class StubCardDao extends CardDaoImplementation {
        private final List<Card> cards = new ArrayList<>();
        private long nextId = 1;

        @Override
        public List<Card> findAll() {
            return Collections.unmodifiableList(cards);
        }

        @Override
        public synchronized long insertCard(Card card) {
            card.setId(nextId++);
            cards.add(card);
            return card.getId();
        }

        @Override
        public boolean delete(long id) {
            return cards.removeIf(c -> c.getId() == id);
        }

        @Override
        public boolean update(Card updated) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getId() == updated.getId()) {
                    cards.set(i, updated);
                    return true;
                }
            }
            return false;
        }
    }

    private CardService service;

    @BeforeEach
    void setUp() {
        service = new CardService(new StubCardDao());
    }

    // ── Test 1: empty name should throw ───────────────────────
    @Test
    void addCard_emptyName_throwsException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> service.addCard("", "Lorwyn Eclipsed", Language.ENGLISH, Condition.NM, 1, 1L)
        );
        assertEquals("Name cannot be empty", ex.getMessage());
    }

    // ── Test 2: quantity below 1 should throw ─────────────────
    @Test
    void addCard_zeroQuantity_throwsException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> service.addCard("Black Lotus", "Lorwyn Eclipsed", Language.ENGLISH, Condition.NM, 0, 1L)
        );
        assertEquals("Quantity must be at least 1", ex.getMessage());
    }

    // ── Test 3: valid card should insert without error ─────────
    @Test
    void addCard_validInput_insertsSuccessfully() {
        assertDoesNotThrow(
            () -> service.addCard("Black Lotus", "Lorwyn Eclipsed", Language.ENGLISH, Condition.NM, 1, 1L)
        );
        assertEquals(1, service.getAllCards().size());
    }
}
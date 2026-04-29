package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class CardDaoImplementation {

    public List<Card> findAll() {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM cards";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cards.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch cards: " + e.getMessage(), e);
        }

        return cards;
    }

    public List<Card> findAllByUser(long userId) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT c.* FROM cards c JOIN users u ON c.user_id = u.id " +
                     "WHERE c.user_id = ? ORDER BY c.`condition`";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cards.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch cards for user: " + e.getMessage(), e);
        }

        return cards;
    }

    public synchronized long insertCard(Card card) {
        String sql = "INSERT INTO cards (user_id, name, game, set_name, language, `condition`, quantity) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, card.getUserId());
            ps.setString(2, card.getName());
            ps.setString(3, card.getGame().name());
            ps.setString(4, card.getSet());
            ps.setString(5, card.getLanguage().name());
            ps.setString(6, card.getCondition().name());
            ps.setInt(7, 1);

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long assignedId = keys.getLong(1);
                    card.setId(assignedId);
                    return assignedId;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert card: " + e.getMessage(), e);
        }

        throw new RuntimeException("Insert failed — no ID returned");
    }

    public Card findById(long id) {
        String sql = "SELECT * FROM cards WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find card: " + e.getMessage(), e);
        }

        return null;
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM cards WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete card: " + e.getMessage(), e);
        }
    }

    public boolean update(Card card) {
        String sql = "UPDATE cards SET name = ?, set_name = ?, language = ?, " +
                     "`condition` = ?, quantity = ? WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, card.getName());
            ps.setString(2, card.getSet());
            ps.setString(3, card.getLanguage().name());
            ps.setString(4, card.getCondition().name());
            ps.setInt(5, 1);
            ps.setLong(6, card.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update card: " + e.getMessage(), e);
        }
    }

    private Card mapRow(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        long userId = rs.getLong("user_id");
        String name = rs.getString("name");
        Game game = Game.valueOf(rs.getString("game"));
        String set = rs.getString("set_name");
        Language language = Language.valueOf(rs.getString("language"));
        Condition condition = Condition.valueOf(rs.getString("condition"));

        Mtg_Card card = new Mtg_Card(id, name, game, set, language, condition, MTG_Printing.normal, userId);
        card.setUserId(userId);
        return card;
    }
}
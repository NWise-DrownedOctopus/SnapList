INSERT INTO users (username, password_hash) VALUES ('testuser', 'testhash');
INSERT INTO cards (user_id, name, game) VALUES (1, 'Test Card', 'MTG');
SELECT * FROM cards;
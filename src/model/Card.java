package model;

enum Game {
    MTG, POKEMON, LORCANA, RIFTBOUND, ONEPIECE, YUGIOH, UNIONARENA, GUNADAM
}

enum Condition {
    NM, LP, MP, HP, DG
}

abstract class Card {
    // fields
    private String name;
    private Game game;
    private String set;
    private String language;
    private Condition condition;

    public Card(String name, Game game, String set, String language, Condition condition) {
        this.name = name;
        this.game = game;
        this.set = set;
        this.language = language;
        this.condition = condition;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Game getGame() {
        return game;
    }

    public String getSet() {
        return set;
    }

    public String getLanguage() {
        return language;
    }

    public Condition getCondition() {
        return condition;
    }
}

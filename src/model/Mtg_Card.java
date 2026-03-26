package model;

enum Printing {
    normal, extended_art, borderless, showcase
}

public class Mtg_Card extends Card {
    private Printing printing;

    public Mtg_Card(String name, Game game, String set, String language, Condition condition, Printing printing) {
        super(name, game, set, language, condition);
        this.printing = printing;
    }

    public Printing getPrinting() {
        return printing;
    }
}




package model;

enum Printing {
    normal, holo_foil, reverse_foil, full_art
}

public class Pokemon_Card extends Card {
    private Printing printing;

    public Pokemon_Card(String name, Game game, String set, String language, Condition condition, Printing printing) {
        super(name, game, set, language, condition);
        this.printing = printing;
    }

    public Printing getPrinting() {
        return printing;
    }
}

package model;

enum Printing {
    normal, holo_foil, reverse_foil, full_art
}

public class Pokemon_Card extends Card {
    private Printing printing;

    public Pokemon_Card(Long id, String name, Game game, String set, Language language, Condition condition, Printing printing) {
        super(id, name, game, set, language, condition);
        this.printing = printing;
    }

    public Printing getPrinting() {
        return printing;
    }
}

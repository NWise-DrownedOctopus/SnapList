package model;

enum Printing {
    normal, holo_foil, reverse_foil, full_art
}

public class Pokemon_Card extends Card {
    private Printing printing;

    public Pokemon_Card(Long id, String name, Game game, String set, Language language, Condition condition, Printing printing, Long userId, int quantity) {
        super(id, name, game, set, language, condition, userId, quantity);
        this.printing = printing;
    }

    public Printing getPrinting() {
        return printing;
    }
}
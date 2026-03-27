package model;

public class Mtg_Card extends Card {
    private MTG_Printing printing;

    public Mtg_Card(Long id, String name, Game game, String set, Language language, Condition condition, MTG_Printing printing) {
        super(id, name, game, set, language, condition);
        this.printing = printing;
    }

    public MTG_Printing getPrinting() {
        return printing;
    }
}




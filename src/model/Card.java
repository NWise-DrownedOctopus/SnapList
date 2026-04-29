package model;

public abstract class Card {
    // fields
    private Long id;
    private String name;
    private Game game;
    private String set;
    private Language language;
    private Condition condition;
    private Long userId;

    public Card(Long id, String name, Game game, String set, Language language, Condition condition, Long userId) {
        this.id = id;
        this.name = name;
        this.game = game;
        this.set = set;
        this.language = language;
        this.condition = condition;
        this.userId = userId;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Game getGame() {
        return game;
    }

    public String getSet() {
        return set;
    }

    public Language getLanguage() {
        return language;
    }

    public Condition getCondition() {
        return condition;
    }

    public Long getUserId() {
        return userId;
    }

    // Setters

    public void setId(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("ID must be non-null and non-negative");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.trim();
    }

    public void setGame(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game must be selected");
        }
        this.game = game;
    }

    public void setSet(String set) {
        if (set == null || set.trim().isEmpty()) {
            throw new IllegalArgumentException("Set cannot be empty");
        }
        this.set = set.trim();
    }

    public void setLanguage(Language language) {
        if (language == null) {
            throw new IllegalArgumentException("Language must be selected");
        }
        this.language = language;
    }

    public void setCondition(Condition condition) {
        if (condition == null) {
            throw new IllegalArgumentException("Condition must be selected");
        }
        this.condition = condition;
    }

    public void setUserId(Long userId) {
        if(userId == null) {
            throw new IllegalArgumentException("User Id must be selected");
        }
        this.userId = userId;
    }
}

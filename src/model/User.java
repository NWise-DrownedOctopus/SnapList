package model;

public class User {
    private long id;
    private String username;
    private String passwordHash;
    private boolean isAdmin;

    public User() {}

    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public boolean isAdmin() { return isAdmin; }

    public void setId(long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
}
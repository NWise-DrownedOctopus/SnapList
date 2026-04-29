package model;

public class CurrentUser {
    private static User user;

    public static void set(User u) {
        user = u;
    }

    public static User get() {
        return user;
    }

    public static boolean isLoggedIn() {
        return user != null;
    }

    public static boolean isAdmin() {
        return user != null && user.isAdmin();
    }

    public static void clear() {
        user = null;
    }
}
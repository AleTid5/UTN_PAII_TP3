package src.Services;

import android.preference.PreferenceManager;

import src.Models.User;

public abstract class SessionService {
    private static User user = null;

    public static void setUser(User user) {
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putInt("userId", user.getId()).apply();
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putString("name", user.getName()).apply();
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putString("email", user.getEmail()).apply();
    }

    public static User getUser() {
        if (user != null) {
            return user;
        }

        try {
            user = new User(
                    PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                            .getInt("userId", -1),
                    PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                            .getString("name", ""),
                    PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                            .getString("email", "")
            );
        } catch (Exception ignored) {}

        return user;
    }

    public static void cleanSession() {
        user = null;
    }
}

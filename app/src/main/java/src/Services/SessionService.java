package src.Services;

import android.preference.PreferenceManager;

import src.Models.User;

public abstract class SessionService {
    private static User user = null;

    public static void setUserId(Integer userId) {
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putInt("userId", userId).apply();
    }

    public static Integer getUserId() {
        return PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .getInt("userId", -1);
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
}

package src.Services;

import android.preference.PreferenceManager;

public abstract class SessionService {
    public static void setUserId(Integer userId) {
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putInt("userId", userId).apply();
    }

    public static Integer getUserId() {
        return PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .getInt("userId", -1);
    }
}

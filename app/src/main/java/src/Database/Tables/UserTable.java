package src.Database.Tables;

import android.provider.BaseColumns;

public class UserTable {
    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "users";

        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
    }
}

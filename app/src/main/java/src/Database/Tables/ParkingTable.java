package src.Database.Tables;

import android.provider.BaseColumns;

public class ParkingTable {
    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "parkings";

        public static final String USER_ID = "user_id";
        public static final String CAR_REGISTER = "car_register";
        public static final String TIME = "time";
    }
}

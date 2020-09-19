package src.Services;

import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

import src.Database.DatabaseManager;
import src.Database.Tables.ParkingTable;
import src.Database.Tables.UserTable;
import src.Models.Parking;
import src.Models.User;

public abstract class UserService {
    public static List<Parking> getParkingList() {
        List<Parking> parkingList = new LinkedList<>(); // Linked list es más rapido para agregar y eliminar

        Cursor cursor = new DatabaseManager().find(String.format(
                "SELECT * FROM %s WHERE %s = %s",
                ParkingTable.Entry.TABLE_NAME,
                ParkingTable.Entry.USER_ID,
                SessionService.getUser().getId()
        ));

        if (cursor.moveToFirst()) {
            do {
                try {
                    parkingList.add(new Parking()
                            .setTime(cursor.getInt(cursor.getColumnIndex(ParkingTable.Entry.TIME)))
                            .setCarRegistration(cursor.getString(cursor.getColumnIndex(ParkingTable.Entry.CAR_REGISTER)))
                    );
                } catch (Exception ignored) {}
            } while (cursor.moveToNext());
        }

        return parkingList;
    }

    public static void authenticate(User user) throws Exception {
        Cursor cursor = new DatabaseManager().find(String.format(
                "SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'",
                UserTable.Entry.TABLE_NAME,
                UserTable.Entry.EMAIL,
                user.getEmail(),
                UserTable.Entry.PASSWORD,
                user.getPassword()
        ));

        if (!cursor.moveToFirst()) {
            throw new Exception("Las credenciales ingresadas son incorrectas");
        }

        user.setId(cursor.getInt(cursor.getColumnIndex(UserTable.Entry._ID)));
        user.setName(cursor.getString(cursor.getColumnIndex(UserTable.Entry.NAME)));
        user.cleanPassword(); // Preventive

        SessionService.setUser(user);
    }

    public static void saveUser(User user) {
        try {
            new DatabaseManager().save(UserTable.Entry.TABLE_NAME, user);
        } catch (Exception ignored) {}
    }
}

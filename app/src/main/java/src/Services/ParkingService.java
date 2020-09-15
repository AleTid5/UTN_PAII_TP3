package src.Services;

import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

import src.Database.DatabaseManager;
import src.Database.Tables.ParkingTable;
import src.Models.Parking;

public abstract class ParkingService {
    public static List<Parking> getParkingList() {
        List<Parking> parkingList = new LinkedList<>(); // Linked list es más rapido para agregar y eliminar

        Cursor cursor = new DatabaseManager().find(String.format(
                "SELECT * FROM %s WHERE %s = %s",
                ParkingTable.Entry.TABLE_NAME,
                ParkingTable.Entry.USER_ID,
                SessionService.getUserId()
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

    public static void saveParking(Parking parking) {
        try {
            new DatabaseManager().save(ParkingTable.Entry.TABLE_NAME, parking);
        } catch (Exception ignored) {}
    }
}

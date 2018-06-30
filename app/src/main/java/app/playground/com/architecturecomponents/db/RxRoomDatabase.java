package app.playground.com.architecturecomponents.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import app.playground.com.architecturecomponents.db.dao.UserDao;
import app.playground.com.architecturecomponents.db.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class RxRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static RxRoomDatabase instance;

    public static final String DATABASE_NAME = "rx-room-database";

    public static RxRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (RxRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            RxRoomDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return instance;
    }
}

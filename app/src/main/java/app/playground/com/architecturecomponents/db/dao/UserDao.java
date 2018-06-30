package app.playground.com.architecturecomponents.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import app.playground.com.architecturecomponents.db.entity.User;

@Dao
public interface UserDao {
    @Query("select * from user_table")
    LiveData<List<User>> getUsers();

    @Insert
    void insert(User user);

    @Query("delete from user_table")
    void deleteAll();
}

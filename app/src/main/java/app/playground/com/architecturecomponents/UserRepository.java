package app.playground.com.architecturecomponents;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.Calendar;
import java.util.List;

import app.playground.com.architecturecomponents.db.RxRoomDatabase;
import app.playground.com.architecturecomponents.db.dao.UserDao;
import app.playground.com.architecturecomponents.db.entity.User;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {
    private LiveData<List<User>> usersLiveData;
    private final UserDao userDao;

    public UserRepository(Application application) {

        userDao = RxRoomDatabase.getDatabase(application).userDao();
        usersLiveData = userDao.getUsers();
    }

    public LiveData<List<User>> getUsers() {
        return usersLiveData;
    }

    public void insert(final User user) {
        user.setLogTime(Calendar.getInstance().getTime());
        Completable.defer(() -> Completable.fromAction(() -> userDao.insert(user)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {},
                        (Throwable throwable) -> {throw new RuntimeException(throwable);}
                );
    }
}

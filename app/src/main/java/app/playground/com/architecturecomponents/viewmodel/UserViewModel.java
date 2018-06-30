package app.playground.com.architecturecomponents.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import java.util.List;

import app.playground.com.architecturecomponents.UserRepository;
import app.playground.com.architecturecomponents.db.entity.User;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private LiveData<List<User>> userListLiveData;
    private MutableLiveData<User> userLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);

        // don't load any users initially
        userListLiveData = new MutableLiveData<>();

        // show an empty user
        userLiveData = new MutableLiveData<>();
        userLiveData.setValue(new User());
    }

    public void observeUser(LifecycleOwner owner, Observer<User> observer) {
        userLiveData.observe(owner, observer);
    }

    public void observeUserList(LifecycleOwner owner, Observer<List<User>> observer) {
        userListLiveData.observe(owner, observer);
    }

    public void loadUserList() {
        userListLiveData = userRepository.getUsers();
    }

    public void insert(User user) {
        userRepository.insert(user);
    }
}


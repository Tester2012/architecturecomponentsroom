package app.playground.com.architecturecomponents;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import app.playground.com.architecturecomponents.adapter.UserListAdapter;
import app.playground.com.architecturecomponents.databinding.ActivityMainBinding;
import app.playground.com.architecturecomponents.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        mainBinding.setLifecycleOwner(this);

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        final UserListAdapter userListAdapter = new UserListAdapter(this);
        mainBinding.userList.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.userList.setAdapter(userListAdapter);

        userViewModel.observeUser(this, user -> mainBinding.setUser(user));
        userViewModel.observeUserList(this, userListAdapter::setUserList);

        mainBinding.loadButton.setOnClickListener(v -> userViewModel.insert(mainBinding.getUser()));
        mainBinding.showButton.setOnClickListener(v -> {
            userViewModel.loadUserList();
            userViewModel.observeUserList(MainActivity.this, userListAdapter::setUserList);
        });
    }
}

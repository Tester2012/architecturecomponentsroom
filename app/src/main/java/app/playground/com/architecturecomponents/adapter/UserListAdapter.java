package app.playground.com.architecturecomponents.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.playground.com.architecturecomponents.R;
import app.playground.com.architecturecomponents.db.entity.User;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<User> users = new ArrayList<>();

    public UserListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.user_row, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.firstNameView.setText(user.getFirstName());
        holder.lastNameView.setText(user.getLastName());
        holder.logTimeView.setText(toStringDate(user.getLogTime()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUserList(List<User> userList) {
        this.users = new ArrayList<>(userList);
        notifyDataSetChanged();
    }

    private String toStringDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstNameView;
        private final TextView lastNameView;
        private final TextView logTimeView;

        public UserViewHolder(View itemView) {
            super(itemView);

            firstNameView = itemView.findViewById(R.id.first_name);
            lastNameView = itemView.findViewById(R.id.last_name);
            logTimeView = itemView.findViewById(R.id.log_time);
        }
    }
}

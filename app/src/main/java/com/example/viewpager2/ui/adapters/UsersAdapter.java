package com.example.viewpager2.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpager2.R;
import com.example.viewpager2.data.models.Poost;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private ItemClickListener listener;
    private List<Poost> usersList;
    private int selectedPos = 0;

    public UsersAdapter(List<Poost> usersList) {
        this.usersList = usersList;
    }

    public void setList(List<Poost> usersList){
        this.usersList = usersList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.bind(usersList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public Poost getUserPost(int selectedPos) {
        return usersList.get(selectedPos);
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {
        private TextView txtUser;
        private Poost post;
        private int pos;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.txt_user);

            itemView.setOnClickListener(view -> {
                selectedPos = getAdapterPosition();
                notifyItemChanged(selectedPos);
                if (listener != null)
                    listener.onItemClick(getAdapterPosition());
            });
        }

        @SuppressLint("SetTextI18n")
        public void bind(Poost post, int pos){
            this.post = post;
            this.pos = pos;
            txtUser.setText("User: " + post.getUser());
        }
    }

    public void setOnClickListener (ItemClickListener listener) {this.listener = listener;}

    public interface ItemClickListener {
        void onItemClick(int pos);
    }
}

package com.example.viewpager2.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpager2.R;
import com.example.viewpager2.data.models.Poost;
import com.example.viewpager2.data.models.network.GhibliService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Poost> postList;
    private ItemClickListener listener;
    private int selectedPosition = 0;

    public PostAdapter(List<Poost> list) {
        this.postList = list;
    }

    public void setList(List<Poost> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    public void addItem(Poost post) {
        this.postList.add(post);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(postList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setItem(Poost post) {
        postList.add(post);
        notifyItemChanged(0);
    }

    public void updateData() {
        notifyDataSetChanged();
    }

    public void update(int position, Poost post) {
        postList.remove(position);
        postList.add(position, post);
        notifyDataSetChanged();
    }

    public void deleteFromBack(int position){
        GhibliService.getApiInterface().deleteId(position).enqueue(new Callback<Poost>() {
            @Override
            public void onResponse(Call<Poost> call, Response<Poost> response) {
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Poost> call, Throwable t) {

            }
        });
    }

    public void deleteItem(int position) {
        Poost post = postList.get(position);
        postList.remove(post);
        notifyItemRemoved(position);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView title, content, group, user;
        private Poost post;
        private int position;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            group = itemView.findViewById(R.id.group);
            user = itemView.findViewById(R.id.user);

            itemView.setOnClickListener(view -> {
                selectedPosition = getAdapterPosition();
                notifyItemChanged(selectedPosition);
                if (listener != null)
                    listener.onItemClick(getAdapterPosition());
            });
            itemView.setOnLongClickListener(view -> {
                listener.onLongListener(getAdapterPosition());
                return true;
            });
        }

        public void bind(Poost post, int position) {
            if (post != null) {
                this.post = post;
                this.position = position;
                title.setText("Title: " + post.getTitle());
                user.setText("User name: " + post.getUser());
                group.setText("Group: " + post.getGroup());
                content.setText("Content: " + post.getContent());
            }
        }
    }

    public void setOnClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(int position);

        void onLongListener(int position);
    }
}

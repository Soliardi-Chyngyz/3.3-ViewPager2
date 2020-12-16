package com.example.viewpager2.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpager2.R;
import com.example.viewpager2.data.models.Poost;
import com.example.viewpager2.data.network.GhibliService;
import com.example.viewpager2.databinding.FragmentUsersBinding;
import com.example.viewpager2.ui.adapters.PostAdapter;
import com.example.viewpager2.ui.adapters.UsersAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Poost> list = new ArrayList<>();
    private FragmentUsersBinding binding;
    private UsersAdapter usersAdapter;
    private PostAdapter postAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersAdapter = new UsersAdapter(list);
        postAdapter = new PostAdapter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        getBackUp();

        usersAdapter.setOnClickListener(pos -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", usersAdapter.getUserPost(pos));
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_usersFragment_to_listFragment2,bundle);
        });
    }

    private void getBackUp() {
        GhibliService.getApiInterface().getPosts().enqueue(new Callback<List<Poost>>() {
            @Override
            public void onResponse(Call<List<Poost>> call, Response<List<Poost>> response) {
                usersAdapter.setList(response.body());
                recyclerView.setAdapter(usersAdapter);
            }

            @Override
            public void onFailure(Call<List<Poost>> call, Throwable t) {
                Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.user_recycler);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.invalidate();
    }
}
package com.example.viewpager2.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpager2.R;
import com.example.viewpager2.data.models.Poost;
import com.example.viewpager2.data.network.GhibliService;
import com.example.viewpager2.ui.adapters.PostAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private static List<Poost> list = new ArrayList<>();
    private PostAdapter adapter;
    private String mParam1;
    private Bundle args;

    public ListFragment() {
    }

    public static ListFragment newInstance(Poost post) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, post);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostAdapter(list);
        args = this.getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.recycler);
        init();
        initListeners();
        setToAdapter();
        getAllPostsFromBackUp();

        adapter.setOnClickListener(new PostAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("post", adapter.getPost(position));
//                setArguments(bundle);
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_listFragment2_to_addFragment, bundle);

            }

            @Override
            public void onLongListener(int position) {
                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogCustom));
                DialogInterface.OnClickListener dialog = (dialogInterface1, i) -> {
                    switch (i) {
                        case DialogInterface.BUTTON_POSITIVE:
                            adapter.deleteFromBack(position);
                            adapter.deleteItem(position);
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure?")
                        .setPositiveButton("Yes", dialog)
                        .setNegativeButton("No", dialog).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllPostsFromBackUp();
    }

    private void getAllPostsFromBackUp() {
        GhibliService.getApiInterface().getPosts().enqueue(
                new Callback<List<Poost>>() {
                    @Override
                    public void onResponse(Call<List<Poost>> call, Response<List<Poost>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.i("TAG", "onResponse: " + response.body());
                            adapter.setList(response.body());
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Poost>> call, Throwable t) {
                        Log.e("ERR", "onFailure: " + t.getMessage());
                    }
                }
        );
    }

    private void setToAdapter() {
        Log.i("ARGUMENT", "setToAdapter: " + getArguments());
        if (getArguments() != null) {
            adapter.setItem((Poost) getArguments().getSerializable(ARG_PARAM1));
        }
    }

    private void initListeners() {
        fab.setOnClickListener(view -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_listFragment2_to_addFragment);
        });
    }

    public void init() {
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.invalidate();
    }

    // ???
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
package com.example.viewpager2.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.viewpager2.R;
import com.example.viewpager2.data.models.Poost;
import com.example.viewpager2.data.network.GhibliService;
import com.example.viewpager2.ui.adapters.PostAdapter;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    //    private TextView tvFragmentName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ed_title)
    TextInputEditText title;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ed_user)
    TextInputEditText user;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ed_group)
    TextInputEditText group;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ed_content)
    TextInputEditText content;
    @SuppressLint("NonConstantResourceId")
    private Button btn_add;
    private Unbinder unbinder;
    private String fragmentName;
    private PostAdapter postAdapter;

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance(Poost post) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, post);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            fragmentName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_add = view.findViewById(R.id.btn_add);
        getData();
        // broadcast riceaver
        btn_add.setOnClickListener(view1 -> {
            if (content != null)
                saveOnBack();
        });

    }

    private void getData() {
        if (getArguments() != null) {
            btn_add.setText("Change");
            Poost poost = (Poost) getArguments().getSerializable("post");
            Log.d("TAG", "getData: " + poost.getTitle());
            if (poost != null) {
                title.setText(poost.getTitle());
                user.setText(poost.getUser());
                group.setText(poost.getGroup());
                content.setText(poost.getContent());
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void saveOnBack() {
        String sTitle = title.getText().toString().trim();
        String sUser = user.getText().toString().trim();
        String sContent = content.getText().toString().trim();
        String sGroup = group.getText().toString().trim();

        Poost post = new Poost(sTitle, sContent, sUser, sGroup);
//        ListFragment.newInstance(post);
        if (getArguments() != null) {

            Poost poost = (Poost) getArguments().getSerializable("post");
            GhibliService.getApiInterface().changePutById(poost.getId(), post).enqueue(new Callback<Poost>() {
                @Override
                public void onResponse(Call<Poost> call, Response<Poost> response) {
                    Toast.makeText(getContext(), "Succeed changed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Poost> call, Throwable t) {

                }
            });

        } else {
            GhibliService.getApiInterface().setPost(post).enqueue(new Callback<Poost>() {
                @Override
                public void onResponse(Call<Poost> call, Response<Poost> response) {
                    Toast.makeText(getContext(), "Succeed, id: " + response.body().getId(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Poost> call, Throwable t) {

                }
            });
        }
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_addFragment_to_listFragment2);

        title.setText("");
        user.setText("");
        content.setText("");
        group.setText("");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        tvFragmentName.setText(fragmentName);
    }
}
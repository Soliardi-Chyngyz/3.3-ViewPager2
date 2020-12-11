package com.example.viewpager2.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

// FragmentAdapter отрисовывает весь фрагмент
public class FragmentAdapter extends FragmentStateAdapter {
    private List <Fragment> list;

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> list) {
        super(fragmentActivity);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // возвращаем элемент листа на той позоиции в котором находится
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

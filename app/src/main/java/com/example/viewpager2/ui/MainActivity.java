package com.example.viewpager2.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.viewpager2.R;
import com.example.viewpager2.ui.adapters.FragmentAdapter;
import com.example.viewpager2.ui.fragments.AddFragment;
import com.example.viewpager2.ui.fragments.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private static final int NUM_PAGES = 2;

    private static ViewPager2 viewPager2;
    //    private RecyclerViewPagerAdapter adapter;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.view_pager);
        fragmentAdapter = new FragmentAdapter(this, getFakeFragmentList());
        viewPager2.setAdapter(fragmentAdapter);
    }

    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }
    }

    public static void pressed() {
        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
    }

    public static void pressedBack(){
        viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
    }


    private List<String> getFakeList() {
//        return List.of("Item1", "Item2", "Item3");
        List<String> list = new ArrayList<>();
        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        return list;
    }

    private List<Fragment> getFakeFragmentList() {
//        return List.of("Item1", "Item2", "Item3");
        List<Fragment> list = new ArrayList<>();
        list.add(new ListFragment());
        list.add(new AddFragment());

        /*SampleFragment sampleFragment1 = new SampleFragment();
        SampleFragment sampleFragment2 = new SampleFragment();
        SampleFragment sampleFragment3 = new SampleFragment();

        Bundle bundle = new Bundle();
        bundle.putString("key", "name 1");

        sampleFragment1.setArguments(bundle);

        list.add(sampleFragment1);
        list.add(sampleFragment2);
        list.add(sampleFragment3);*/
        return list;
    }
}
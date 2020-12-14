package com.example.viewpager2.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.viewpager2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

//    private static final int NUM_PAGES = 2;
//    private static ViewPager2 viewPager2;
    //    private RecyclerViewPagerAdapter adapter;
//    private FragmentAdapter fragmentAdapter;

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNavController();
//        viewPager2 = findViewById(R.id.view_pager);
//        fragmentAdapter = new FragmentAdapter(this, getFakeFragmentList());
//        viewPager2.setAdapter(fragmentAdapter);
    }

    private void initNavController() {
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.listFragment2,
                R.id.weatherFragment).build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.weatherFragment ||
                    destination.getId() == R.id.addFragment) {
                getSupportActionBar().hide();
            } else {
                getSupportActionBar().show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    //    private List<Fragment> getFakeFragmentList() {
//        return List.of("Item1", "Item2", "Item3");
//        List<Fragment> list = new ArrayList<>();
//        list.add(new ListFragment());

        /*SampleFragment sampleFragment1 = new SampleFragment();
        SampleFragment sampleFragment2 = new SampleFragment();
        SampleFragment sampleFragment3 = new SampleFragment();

        Bundle bundle = new Bundle();
        bundle.putString("key", "name 1");

        sampleFragment1.setArguments(bundle);

        list.add(sampleFragment1);
        list.add(sampleFragment2);
        list.add(sampleFragment3);*/
//        return list;
//    }
}
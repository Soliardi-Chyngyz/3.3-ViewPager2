package com.example.viewpager2;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.viewpager2.ui.SecondActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SecActivityTest {

    @Rule
    public ActivityScenarioRule<SecondActivity> activityRule
            = new ActivityScenarioRule<>(SecondActivity.class);

    @Test
    public void addTest() {
        for (int i = 0; i < 4; i++) {
            onView(withId(R.id.sec_act_edit)).perform(typeText("4"));
            onView(withId(R.id.sec_act_edit_2)).perform(typeText("4"));
            onView(withId(R.id.apply)).perform(click());
        }
    }

    @Test
    public void nullTest() {
        onView(withId(R.id.sec_act_edit)).perform(typeText(""));
        onView(withId(R.id.sec_act_edit_2)).perform(typeText("2"));
        onView(withId(R.id.apply)).perform(click());
    }
}

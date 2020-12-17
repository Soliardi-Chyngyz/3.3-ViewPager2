package com.example.viewpager2.data.entities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.viewpager2.data.StudentDao;

@Database(entities = Student.class, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract StudentDao studentDao();
}

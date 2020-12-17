package com.example.viewpager2.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.viewpager2.data.entities.Student;

import java.util.List;

@Dao
public interface StudentDao {

    // если попытаемся добавить студента со схожей id, он заменит на новый
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Student> studentList);

    @Query("SELECT * FROM Student")
    List<Student> getAllStudents ();
}

package com.example.viewpager2;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.viewpager2.data.StudentDao;
import com.example.viewpager2.data.entities.AppDataBase;
import com.example.viewpager2.data.entities.Student;
import com.example.viewpager2.utils.StudentTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RoomTest {
    AppDataBase dataBase;
    StudentDao studentDao;

    @Before
    public void setUpData() {
        // Applicat..Provider = Provides ability to retrieve the current application {@link Context} in tests.
        Context context = ApplicationProvider.getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase.class).build();
        studentDao = dataBase.studentDao();
    }

    @After
    public void clearUpData() {
        dataBase.close();
    }

    @Test
    public void simpleTest() {
        List<Student> studentList = StudentTestUtil.createStudentList(1);
        studentDao.insertStudent(studentList.get(0));

        List<Student> dbList = studentDao.getAllStudents();
        // хотим убедиться что этот studentList добавился в нашу базу
        // действительно ли эти 2 студента равны, то есть равен тому что
        // сохранили в базе
        assertThat(studentList.get(0), equalTo(dbList.get(0)));
    }

    @Test
    public void countTableTest() {
        List<Student> testStudentList = StudentTestUtil.createStudentList(3);
        studentDao.insertAll(testStudentList);

        List<Student> dbStudentList = studentDao.getAllStudents();
        // проверка на то что в базу добавились всё что внесли через .createStudentList
        assertEquals(testStudentList.size(), dbStudentList.size());
    }
}

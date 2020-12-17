package com.example.viewpager2.utils;

import com.example.viewpager2.data.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentTestUtil {
    public static List<Student> createStudentList(int count) {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Student student = new Student("Student: " + i, (i + 3) * 2);
            student.setId(i + 1);
            studentList.add(student);
        }
        return studentList;
    }
}

package com.example.viewpager2.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.viewpager2.App;
import com.example.viewpager2.R;
import com.example.viewpager2.data.core.Math;
import com.example.viewpager2.data.entities.Student;

import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnApply;
    private TextView result;
    private EditText ed1, ed2;
    private Math math;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initV();
        btnApply.setOnClickListener(this);

//        testDB();
//        findViewById(R.id.apply).setOnClickListener(view -> findViewById(R.id.apply).setVisibility(View.INVISIBLE));
    }

    private void testDB() {
        Student student = new Student("Aza", 20);
        App.dataBase.studentDao().insertStudent(student);

        List<Student> students = App.dataBase.studentDao().getAllStudents();
        Log.d("TAG", "testDB: " + student.toString());
    }

    private void initV() {
        btnApply = findViewById(R.id.apply);
        ed1 = findViewById(R.id.sec_act_edit);
        ed2 = findViewById(R.id.sec_act_edit_2);
        result = findViewById(R.id.txt_result);
        math = new Math();
    }

    @Override
    public void onClick(View view) {
        if (!TextUtils.isEmpty(ed1.getText().toString())) {
            i++;
            int oper2 = Integer.parseInt(ed2.getText().toString());
            int oper1 = Integer.parseInt(ed1.getText().toString());
            if (i == 1) {
                String sum = String.valueOf(math.add(oper1, oper2));
                editTxtClean(sum);
            } else if (i == 2) {
                String sum = String.valueOf(math.sub(oper1, oper2));
                editTxtClean(sum);
            } else if (i == 3) {
                String sum = String.valueOf(math.multiply(oper1, oper2));
                editTxtClean(sum);
            } else {
                String sum = String.valueOf(math.divide(oper1, oper2));
                editTxtClean(sum);
            }
        } else {
            Toast.makeText(this, "Так делать нельзя, ай ай ай!", Toast.LENGTH_SHORT).show();
        }
    }

    private void editTxtClean(String sum) {
        result.setText(sum);
        ed1.getText().clear();
        ed2.getText().clear();
    }
}
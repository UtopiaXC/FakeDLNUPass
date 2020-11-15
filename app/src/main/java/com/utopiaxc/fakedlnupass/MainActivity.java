package com.utopiaxc.fakedlnupass;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNumber;
    private EditText editTextName;
    private EditText editTextSex;
    private EditText editTextFaculty;
    private EditText editTextClass;
    private Button buttonStartTime;
    private Button buttonEndTime;
    private Button buttonChoosePhoto;
    private Button buttonGenerate;

    private String Number="";
    private String Name="";
    private String Sex="";
    private String Faculty="";
    private String ProfessionalClass="";
    private String StartTime="";
    private String EndTime="";
    private String Photo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bind();

        Generate();
    }
    private void bind(){
        editTextNumber=findViewById(R.id.editTextNumber);
        editTextName=findViewById(R.id.editTextName);
        editTextSex=findViewById(R.id.editTextSex);
        editTextFaculty=findViewById(R.id.editTextFaculty);
        editTextClass=findViewById(R.id.editTextClass);
        buttonStartTime=findViewById(R.id.buttonStartTime);
        buttonEndTime=findViewById(R.id.buttonEndTime);
        buttonChoosePhoto=findViewById(R.id.buttonChoosePhoto);
        buttonGenerate=findViewById(R.id.buttonGenerate);
    }

    private void Generate(){
        buttonGenerate.setOnClickListener(e->{
            Number=editTextNumber.getText().toString();
            Name=editTextName.getText().toString();
            Sex=editTextSex.getText().toString();
            Faculty=editTextFaculty.getText().toString();
            ProfessionalClass=editTextClass.getText().toString();
            if (Number.equals("")||Name.equals("")||Sex.equals("")||Faculty.equals("")||ProfessionalClass.equals("")||
            StartTime.equals("")||EndTime.equals("")||Photo.equals("")){
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.warning))
                        .setMessage(getString(R.string.content_not_filled))
                        .setPositiveButton(R.string.confirm, null)
                        .create()
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getNumber() {
        return Number;
    }

    public String getName() {
        return Name;
    }

    public String getSex() {
        return Sex;
    }

    public String getFaculty() {
        return Faculty;
    }

    public String getProfessionalClass() {
        return ProfessionalClass;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public String getPhoto() {
        return Photo;
    }
}
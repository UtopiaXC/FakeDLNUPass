package com.utopiaxc.fakedlnupass;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.donkingliang.imageselector.utils.ImageSelector;

import cn.qqtheme.framework.picker.DateTimePicker;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CHOOSE = 23;
    private EditText editTextNumber;
    private EditText editTextName;
    private EditText editTextSex;
    private EditText editTextFaculty;
    private EditText editTextClass;
    private Button buttonStartTime;
    private Button buttonEndTime;
    private Button buttonChoosePhoto;
    private Button buttonGenerate;


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

        SharedPreferences sharedPreferences=getSharedPreferences("PersonalMessages",MODE_PRIVATE);
        editTextNumber=findViewById(R.id.editTextNumber);
        editTextNumber.setText(sharedPreferences.getString("Number",null));
        editTextName=findViewById(R.id.editTextName);
        editTextName.setText(sharedPreferences.getString("Name",null));
        editTextSex=findViewById(R.id.editTextSex);
        editTextSex.setText(sharedPreferences.getString("Sex",null));
        editTextFaculty=findViewById(R.id.editTextFaculty);
        editTextFaculty.setText(sharedPreferences.getString("Faculty",null));
        editTextClass=findViewById(R.id.editTextClass);
        editTextClass.setText(sharedPreferences.getString("ProfessionalClass",null));
        buttonStartTime=findViewById(R.id.buttonStartTime);
        buttonEndTime=findViewById(R.id.buttonEndTime);
        buttonChoosePhoto=findViewById(R.id.buttonChoosePhoto);
        buttonGenerate=findViewById(R.id.buttonGenerate);

        variables.Photo=sharedPreferences.getString("Photo",null);

        buttonStartTime.setOnClickListener(e->{
            DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_24);//24小时值
            picker.setDateRangeStart(2015, 1, 1);//日期起点
            picker.setDateRangeEnd(2025, 1,1);//日期终点
            picker.setTimeRangeStart(0, 0);//时间范围起点
            picker.setTimeRangeEnd(23, 59);//时间范围终点
            picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                @Override
                public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                    variables.StartTime=year+"年"+month+"月"+day+"日 "+hour+"时"+minute+"分";
                    buttonStartTime.setText("开始时间："+variables.StartTime);
                }
            });
            picker.show();
        });

        buttonEndTime.setOnClickListener(e->{
            DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_24);//24小时值
            picker.setDateRangeStart(2015, 1, 1);//日期起点
            picker.setDateRangeEnd(2025, 1,1);//日期终点
            picker.setTimeRangeStart(0, 0);//时间范围起点
            picker.setTimeRangeEnd(23, 59);//时间范围终点
            picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                @Override
                public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                    variables.EndTime=year+"年"+month+"月"+day+"日 "+hour+"时"+minute+"分";
                    buttonEndTime.setText("结束时间："+variables.EndTime);
                }
            });
            picker.show();
        });

        buttonChoosePhoto.setOnClickListener(e->{
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_CHOOSE);
            }
            else
                choose();


        });
    }

    private void choose(){
        ImageSelector.builder()
                .useCamera(true) // 设置是否使用拍照
                .setSingle(true)  //设置是否单选
                .setCrop(true)  // 设置是否使用图片剪切功能。
                .setCropRatio(1.33f) // 图片剪切的宽高比,默认1.0f。宽固定为手机屏幕的宽。
                .canPreview(true) //是否可以预览图片，默认为true
                .start(this, REQUEST_CODE_CHOOSE); // 打开相册

    }

    private void Generate(){
        buttonGenerate.setOnClickListener(e->{
            variables.Number=editTextNumber.getText().toString();
            variables.Name=editTextName.getText().toString();
            variables.Sex=editTextSex.getText().toString();
            variables.Faculty=editTextFaculty.getText().toString();
            variables.ProfessionalClass=editTextClass.getText().toString();
            if (variables.Number.equals("")||variables.Name.equals("")||variables.Sex.equals("")||variables.Faculty.equals("")||variables.ProfessionalClass.equals("")||
                    variables.StartTime.equals("")||variables.EndTime.equals("")||variables.Photo.equals("")){
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.warning))
                        .setMessage(getString(R.string.content_not_filled))
                        .setPositiveButton(R.string.confirm, null)
                        .create()
                        .show();
            }else{
                Intent intent=new Intent(MainActivity.this,ActivityPass.class);
                startActivity(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            variables.Photo = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT).get(0);
            buttonChoosePhoto.setText("选择照片（已选定）");
            System.out.println(variables.Photo);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CHOOSE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                choose();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("警告")
                        .setMessage("您必须提供访问存储的权限才能选择显示的照片")
                        .setPositiveButton("确认", null)
                        .create()
                        .show();
            }
        }
    }
}
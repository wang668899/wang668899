package com.example.myapplication002;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SmallQQ_LoginActivity extends AppCompatActivity {

    private EditText et_qqnum, et_qqpwd;
    private ImageView iv_login, et_delete_num, et_delete_pwd, et_pwd_see;
    private TextView tv_forgetpwd, tv_register;
    private String qq_numtext, qq_pwdtext;
    private boolean pwdCanSee;
    private Object EditTextUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_qq__login);

        findId();
        //QQ账号输入状态监听
        et_qqnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                qq_pwdtext = et_qqpwd.getText().toString().trim();
                qq_numtext = et_qqnum.getText().toString().trim();
                if (!TextUtils.isEmpty(qq_numtext) && !TextUtils.isEmpty(qq_pwdtext)) {
                    //如果账号和密码都不为空，打开图片响应事件，并且更换图片
                    iv_login.setEnabled(true);
                    iv_login.setImageResource(R.drawable.go_yes);
                } else {
                    iv_login.setEnabled(false);
                    iv_login.setImageResource(R.drawable.go_no);
                }
            }
        });

        //QQ密码输入状态监听
        et_qqpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                qq_pwdtext = et_qqpwd.getText().toString().trim();
                qq_numtext = et_qqnum.getText().toString().trim();
                if (!TextUtils.isEmpty(qq_numtext) && !TextUtils.isEmpty(qq_pwdtext)) {
                    //如果账号和密码都不为空，打开图片响应事件，并且更换图片
                    iv_login.setEnabled(true);
                    iv_login.setImageResource(R.drawable.go_yes);
                } else {
                    iv_login.setEnabled(false);
                    iv_login.setImageResource(R.drawable.go_no);
                }
            }
        });
        //QQ密码输入焦点监听
        et_qqpwd.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                et_pwd_see.setVisibility(View.VISIBLE);
            } else {
                et_pwd_see.setVisibility(View.INVISIBLE);
            }
        });

        //密码可见小图标
        pwdCanSee = false;//true密码可见，false密码不可见
        et_pwd_see.setOnClickListener(v -> {
            if (pwdCanSee) {
                //设置不可见
                et_qqpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                et_pwd_see.setImageResource(R.drawable.et_pwd_no);
                et_qqpwd.setSelection(et_qqpwd.getText().length());
                pwdCanSee = false;
            } else {
                //设置可见
                et_qqpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                et_pwd_see.setImageResource(R.drawable.et_pwd_yes);
                et_qqpwd.setSelection(et_qqpwd.getText().length());
                pwdCanSee = true;
            }
        });

        //删除小图标
        EditTextUtils.clearButtonListener(et_qqnum, et_delete_num);
        EditTextUtils.clearButtonListener(et_qqpwd, et_delete_pwd);

        //登录
        //iv_login.setClickable(true);
        //setOnClickListener方法会默认把控件的setClickable设置为true。
        //设置login图片无事件响应
        iv_login.setEnabled(false);
        iv_login.setOnClickListener(v -> {
            qq_numtext = et_qqnum.getText().toString().trim();
            qq_pwdtext = et_qqpwd.getText().toString().trim();
            Toast.makeText(this, "登录成功!账号:" + qq_numtext + ",密码:" + qq_pwdtext, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SmallQQ_LoginActivity.this, SmallQQ_LoginActivity.class);
            startActivity(intent);
        });

        //忘记密码
        tv_forgetpwd.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("SmallQQ.error");
            startActivity(intent);
        });
        //用户注册
        tv_register.setOnClickListener(v -> startActivity(new Intent().setAction("SmallQQ.error")));
    }

    private void findId() {
        et_qqnum = findViewById(R.id.qq_num);
        et_qqpwd = findViewById(R.id.qq_pwd);
        iv_login = findViewById(R.id.qq_login);
        tv_forgetpwd = findViewById(R.id.qq_forgetpwd);
        tv_register = findViewById(R.id.qq_register);
        et_delete_num = findViewById(R.id.iv_et_num_delete);
        et_delete_pwd = findViewById(R.id.iv_et_pwd_delete);
        et_pwd_see = findViewById(R.id.iv_et_pwd_see);
    }
}
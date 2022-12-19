package com.noob.rebirthsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.noob.rebirthsimulator.AppData.AppDatabase;
import com.noob.rebirthsimulator.AppData.User;

public class UserInformation extends AppCompatActivity {
    //实例一个userDao
    UserDao userDao;

    Button loginbutton;
    Button registerbutton;
    Button logoutbutton;
    TextView name;

    //获取用户名的方法
public String getusername(){
    name=findViewById(R.id.inputname);
    return name.getText().toString();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

//实例化控件
        userDao = AppDatabase.getInstance(this).userDao();
        loginbutton=findViewById(R.id.loginbutton);
        registerbutton=findViewById(R.id.registerbutton);
        logoutbutton=findViewById(R.id.logoutbutton);
        name=findViewById(R.id.inputname);
//登陆按钮功能
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getusername().isEmpty()){
                    Toast.makeText(UserInformation.this, "请输入用户名！",Toast.LENGTH_SHORT).show();
                }
                if (userDao.findByName(getusername())==null){
                    Toast.makeText(UserInformation.this, "不存在的用户名！",Toast.LENGTH_SHORT).show();
                }
                else {
                    //设置按钮的可见
                    logoutbutton.setVisibility(View.VISIBLE);
                    loginbutton.setVisibility(View.GONE);
                    registerbutton.setVisibility(View.GONE);
                    name.setText(getusername());
                    //登陆后不可输入用户名
                    name.setEnabled(false);
                    //登陆
                    userDao.updatelogin(true,getusername());
                    Toast.makeText(UserInformation.this, "欢迎！"+getusername()+"!",Toast.LENGTH_SHORT).show();
                }
            }
        });
//注册按钮功能
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getusername().isEmpty()) {
                    Toast.makeText(UserInformation.this, "请输入用户名！",Toast.LENGTH_SHORT).show();

                }
                else{
                    //创建一个用户，初始水晶5w 并设置登陆状态为false
                    User user = new User();
                    user.username = getusername();
                    user.water = 50000;
                    user.fragment = 0;
                    user.iflogin = false;
                    user.drawcounter=100;
                    user.giftwater=3;
                    userDao.insertAll(user);
                    Toast.makeText(UserInformation.this, "注册成功！请登陆！",Toast.LENGTH_SHORT).show();
                }

            }
        });
//注销按钮功能
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置按钮的可见
                loginbutton.setVisibility(View.VISIBLE);
                registerbutton.setVisibility(View.VISIBLE);
                logoutbutton.setVisibility(View.GONE);
                //登陆按钮可用
                name.setEnabled(true);
                //注销掉账号
                userDao.updatelogin(false,getusername());
                //设置用户名栏为空
                name.setText("");
                Toast.makeText(UserInformation.this, "注销成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }
//下次打开时界面为登陆后界面
    @Override
    protected void onResume() {
        super.onResume();
        //如果有登陆有用户，显示为登陆界面
        if (userDao.getloginuser(true)!=null){
            //设置按钮的可见
            logoutbutton.setVisibility(View.VISIBLE);
            loginbutton.setVisibility(View.GONE);
            registerbutton.setVisibility(View.GONE);
            //登陆后不可输入用户名
            name.setEnabled(false);
            //将用户名设置为登陆用户
            name.setText(userDao.getloginuser(true).username);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
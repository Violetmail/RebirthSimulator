package com.noob.rebirthsimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.noob.rebirthsimulator.AppData.AppDatabase;
import com.noob.rebirthsimulator.AppData.User;

public class setActivity extends AppCompatActivity {

    //实例一个Dao
    UserDao userDao;
    UserCardDao userCardDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        //初始化DAO
        userDao = AppDatabase.getInstance(this).userDao();
        userCardDao=AppDatabase.getInstance(this).userCardDao();
        //实例按钮
        Button clearbtn=findViewById(R.id.restart);
        Button deletebtn=findViewById(R.id.deleteaccount);

        //定义清除抽卡记录函数
//清除数据响应按钮
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userDao.findByName("Guest").iflogin){
                    User user0=userDao.getloginuser(true);
                    user0.username="Guest";
                    user0.water=40000;
                    user0.fragment=0;
                    user0.iflogin=true;
                    user0.drawcounter=100;
                    userDao.update(user0);
                    //清除抽卡记录
                    userCardDao.deletesomeone("Guest");
                    Toast.makeText(setActivity.this, "Guest账号重置数据成功！",Toast.LENGTH_SHORT).show();
                }
                else {
                    User clcuser=userDao.getloginuser(true);
                    clcuser.username=userDao.getloginuser(true).username;
                    clcuser.water=50000;
                    clcuser.fragment=0;
                    clcuser.iflogin=true;
                    clcuser.drawcounter=100;
                    userDao.update(clcuser);
                    //清除抽卡记录
                    userCardDao.deletesomeone(clcuser.username);
                    Toast.makeText(setActivity.this, "数据已经重置！",Toast.LENGTH_SHORT).show();
                }

            }
        });
//删除账号响应按钮
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userDao.findByName("Guest").iflogin)
                    Toast.makeText(setActivity.this, "无法删除游客账号！",Toast.LENGTH_SHORT).show();
                else
                {
                    User deluser = userDao.getloginuser(true);
                    deluser.username = userDao.getloginuser(true).username;
                    userDao.delete(deluser);
                    Toast.makeText(setActivity.this, "账号已经删除！如需请重新注册！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //定义清除抽卡记录函数
private void clearhistory(String name){

}

}
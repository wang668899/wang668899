package com.example.myapplication002;




import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.asyyy.androidkaifa.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class SmallQQ_MainChatActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView user_title, bar_news, bar_friend, bar_dongtai;
    private ImageView menu_add;

    private NewsFragment newsFragment;
    private FriendFragment friendFragment;
    private DongtaiFragment dongtaiFragment;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smallqq_mainchat_layout);

        user_title = findViewById(R.id.user_title);
        bar_news = findViewById(R.id.tvbar_user);
        bar_friend = findViewById(R.id.tvbar_friend);
        bar_dongtai = findViewById(R.id.tvbar_dongtai);
        menu_add  = findViewById(R.id.menu_add);

        bar_news.setOnClickListener(this);
        bar_friend.setOnClickListener(this);
        bar_dongtai.setOnClickListener(this);

        fManager = getSupportFragmentManager();

        bar_news.performClick();//模拟一次点击，既进去后选择消息界面

        menu_add.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(SmallQQ_MainChatActivity.this, menu_add);
            popupMenu.getMenuInflater().inflate(R.menu.menu_add,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                String str = "";
                switch (item.getItemId()){
                    case R.id.menu_add1:
                        str = item.getTitle() + "";
                        break;
                    case R.id.menu_add2:
                        str = item.getTitle() + "";
                        break;
                    case R.id.menu_add3:
                        str = item.getTitle() + "";
                        break;
                    case R.id.menu_add4:
                        str = item.getTitle() + "";
                        break;
                    case R.id.menu_add5:
                        str = item.getTitle() + "";
                        break;
                    case R.id.menu_add6:
                        str = item.getTitle() + "";
                        break;
                }
                Toast.makeText(SmallQQ_MainChatActivity.this,"你点击了 " + str, Toast.LENGTH_SHORT).show();
                return true;
            });
            popupMenu.show();
        });

    }

    //重置所有文本的选中状态
    private void setSelected(){
        bar_news.setSelected(false);
        bar_friend.setSelected(false);
        bar_dongtai.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction transaction){
        if(newsFragment != null) transaction.hide(newsFragment);
        if(friendFragment != null) transaction.hide(friendFragment);
        if(dongtaiFragment != null) transaction.hide(dongtaiFragment);
    }

    @Override
    public void onClick(View v) {
        //FragmentTransaction只能使用一次
        //每次使用都要调用FragmentManager的beginTransaction()方法获得FragmentTransaction事务对象
        FragmentTransaction transaction = fManager.beginTransaction();
        hideAllFragment(transaction);
        switch (v.getId()){
            case R.id.tvbar_user:
                setSelected();
                bar_news.setSelected(true);
                user_title.setText("消息");
                if(newsFragment == null){
                    newsFragment = new NewsFragment();
                    transaction.add(R.id.fragment_content, newsFragment);
                }else {
                    transaction.show(newsFragment);
                }
                break;
            case R.id.tvbar_friend:
                setSelected();
                bar_friend.setSelected(true);
                user_title.setText("联系人");
                if(friendFragment == null){
                    friendFragment = new FriendFragment();
                    transaction.add(R.id.fragment_content, friendFragment);
                }else {
                    transaction.show(friendFragment);
                }
                break;
            case R.id.tvbar_dongtai:
                setSelected();
                bar_dongtai.setSelected(true);
                user_title.setText("动态");
                if(dongtaiFragment == null){
                    dongtaiFragment = new DongtaiFragment();
                    FragmentTransaction add = transaction.add(R.id.fragment_content, dongtaiFragment);
                }else {
                  
                    transaction.show(dongtaiFragment);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        //把newsFragment添加到Activity中的指定位置,最后调用commit()或者commitAllowingStateLoss()
        transaction.commitAllowingStateLoss();
    }
}

package com.example.wushange.testrealm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    LongClickButton mWrite;
    LongClickButton mRead;
    Button mClear;
    TextView mContext;
    TextView mUserSum;
    Realm mRealm;
    RealmResults<User> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            mRealm = Realm.getDefaultInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mWrite = (LongClickButton) findViewById(R.id.lbtn_write);
        mRead = (LongClickButton) findViewById(R.id.lbtn_read);
        mClear = (Button) findViewById(R.id.btn_clear);
        mContext = (TextView) findViewById(R.id.tv_context);
        mUserSum = (TextView) findViewById(R.id.tv_usersum);


        mContext.setOnClickListener(view2 -> gotoGit());
        mWrite.setOnClickListener(view -> writeUser2Realm());
        mWrite.setLongClickRepeatListener(() -> writeUser2Realm());
        mRead.setOnClickListener(view -> readUserFromRealm());
        mClear.setOnClickListener(view1 -> clearRealm());
        readUserFromRealm();
        //监听数据库数据变化
        mUsers.addChangeListener(element -> {
            Log.e("TAG", "数据变化");
            mUserSum.setText(element.size() + "条记录");
            mContext.setText(element.toString());
        });

    }

    /**
     * 向数据库中插入一条用户信息
     */
    private void writeUser2Realm() {
        Log.e("TAG", "開始插入。。。");
        final User user = new User();
        user.userName = "wushange";
        user.userGit = "https://github.com/wushge11";
        user.userCsdn = "http://blog.csdn.net/wushge11";
        mRealm.executeTransactionAsync(
                realm -> realm.insertOrUpdate(user),
                () -> Log.e("TAG", "插入成功"),
                error -> Log.e("TAG", "插入失敗" + error.getMessage()));
    }

    /**
     * 从数据库读取全部信息
     */
    private void readUserFromRealm() {
        Log.e("TAG", "開始讀取。。。");
        mUsers = mRealm.where(User.class).findAll();
        if (mUsers != null) {
            Log.e("TAG", "查詢結果" + mUsers.size());
            Log.e("TAG", mUsers.toString());
            mUserSum.setText(mUsers.size() + "条记录");
            mContext.setText(mUsers.toString());
        } else {
            Log.e("TAG", "查詢為空");
        }
    }

    /**
     * 清空数据库
     */
    private void clearRealm() {

        mRealm.executeTransaction(realm -> realm.deleteAll());

    }


    private void gotoGit() {
        Uri uri = Uri.parse("http://blog.csdn.net/wushge11");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}

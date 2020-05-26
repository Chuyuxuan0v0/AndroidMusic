package com.example.plamusics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.plamusics.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity
{
    ActivityMainBinding Binding_play_bar;  //播放区域绑定
    ActivityMainBinding Binding_main;  //数据绑定
    ActivityMainBinding Binding_list_item; //list绑定
    private ListView musicListView;//音乐列表
    private String [] song ={"奈何桥","as","wechat","fds","As you know","foryouself","奈何桥","as","wechat","fds","As you know","foryouself"};
    private String [] singer ={"朴树","许嵩","汪苏泷","徐良","朴树","许嵩","汪苏泷","徐良","朴树","许嵩","汪苏泷","徐良"};
//  private int [] id ={1,2,3,4};
    private String [] song_time ={"4.03","4.10","5.10","4.36","4.03","4.10","5.10","4.36","4.03","4.10","5.10","4.36"};


    private String path;//歌曲路径定义
    private Intent intent;//跳转
  //  private MyCon myCon;//自定义


    private View play_bar;
    private ImageView playIv;
    private ImageView nextIv;//上一曲下一曲暂停声明
    private TextView singerTv,songTv;//歌手，歌曲的声明
    private ListView musicListLv; //列表声明





//    public class DatabaseHelper extends SQLiteOpenHelper {
//
//
//        public static final String song_id = "song_id";
//        public static final String song_tilte = "song_tilte";
//        public static final String song_artist= "song_artist";
//        public static final String song_album = "song_album";
//        public static final String DATABASE_NAME = "song_info.db";
//        public static final int VERSION = 1;
//
//        public DatabaseHelper(Context context)
//        {
//            super(context, DATABASE_NAME, null, 2);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            db.execSQL("create table " + "song_info" + "(" + song_id + " varchar(20) not null,"
//                    + song_tilte + " varchar(10) not null,"
//                    + song_artist + " varchar(10) not null,"
//                    + song_album + " varchar(10) not null" +")");
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        }






        protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


        initView();//包含控件元组的方法



       Binding_main = DataBindingUtil.setContentView(this, R.layout.activity_main);
       Binding_list_item = DataBindingUtil.setContentView(this,R.layout.list_item);//绑定list_item里面的控件，


       //setContentView(R.layout.activity_main);

       musicListView = Binding_main.list1;//

        MainAadpter mAadpter = new MainAadpter();

        musicListView.setAdapter(mAadpter);
    }



    private void initView()
    {
        Binding_play_bar = DataBindingUtil.setContentView(this,R.layout.play_bar);
        //初始化控件的方法

        play_bar = Binding_play_bar.layoutPlaybar;

    }


    class MainAadpter extends BaseAdapter//数据传递
    {
        @Override
        public int getCount()
        {
            return song.length;
        }
        @Override
        public Object getItem(int position)
        {
            return song[position];
        }
        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int positon , View convertViwew, ViewGroup parent)
        {
            Context context;
            View view =View.inflate(MainActivity.this ,R.layout.list_item, null);
            TextView mSong = view.findViewById(R.id.item_mymusic_song);
            mSong.setText(song[positon]);
            TextView mSinger = view.findViewById(R.id.item_mymusic_singer);
          mSinger.setText(singer[positon]);
            TextView mtTime = view.findViewById(R.id.item_mymusic_time);
            mtTime.setText(song_time[positon]);
            return view;
        }

    }


}

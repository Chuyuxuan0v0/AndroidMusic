package com.example.plamusics;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service
{
    private static final String TAG = "MusicSerService";
    public MediaPlayer mediaPlayer;

    class MyBinder extends Binder
    {
        public void play(String path)
        {
            try
            {
            if (mediaPlayer == null)
            {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//指定播放音频文件
                mediaPlayer.setDataSource(path);//指定播放路径
                mediaPlayer.prepare();//准备播放

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();//开始播放
                    }
                });

            }
            else
            {
                int position = getCurrentProgress();
                mediaPlayer.seekTo(position);
                try
                {
                    mediaPlayer.prepare();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        public void pause ()
        {
            if (mediaPlayer != null && mediaPlayer.isPlaying())
            {
                mediaPlayer.pause();//暂停
            }
            else if (mediaPlayer !=null && (!mediaPlayer.isPlaying()))
            {
                mediaPlayer.start();
            }
        }
    }


    public void onCreate()

    {
        super.onCreate();
    }

    public  int getCurrentProgress()//获取当前进度长度
    {
        if (mediaPlayer !=null & mediaPlayer.isPlaying())
        {
            return mediaPlayer.getCurrentPosition();
        }
        else if (mediaPlayer !=null &(!mediaPlayer.isPlaying()))
        {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void onDestroy()
    {
        if (mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent)
    {
        return new MyBinder();
    }
}

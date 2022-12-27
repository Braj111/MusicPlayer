package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class PlaySong : AppCompatActivity() {
    companion object {
        var musicList: ArrayList<Song> ?=null
        var songPosition: Int = 0
        var mediaPlayer: MediaPlayer? = null
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_song)
        val shuffle:ImageButton=findViewById(R.id.shuffle)
        val previous:FloatingActionButton=findViewById(R.id.previousBtn)
        val next:FloatingActionButton=findViewById(R.id.nextBtn)
        val seekbar:SeekBar=findViewById(R.id.seekbar)
        val ongoingTime:TextView=findViewById(R.id.ongoingTime)
        val endingTime:TextView=findViewById(R.id.endingTime)
        musicList= arrayListOf()
        val albumImage:ImageView=findViewById(R.id.albumImage)
        val playPause:FloatingActionButton=findViewById(R.id.playPause)
        if(mediaPlayer!=null){
            mediaPlayer!!.stop()
        }
        mediaPlayer= MediaPlayer()

        songPosition=intent.getIntExtra("index",0)

        when(intent.getStringExtra("class")){
            "MyAdapter"->{
                musicList!!.addAll(MainActivity.songList)
                mediaPlayer!!.reset()

                mediaPlayer!!.setDataSource(musicList!![songPosition].path)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
                endingTime.text= durationFormat(musicList!![songPosition].duration)
//                seekbar.max= mediaPlayer!!.duration
//                Timer().scheduleAtFixedRate(object:TimerTask(){
//                    override fun run() {
//                        seekbar.setProgress(mediaPlayer!!.currentPosition)
//                    }
//
//                },0,900)
//                seekbar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
//                    override fun onProgressChanged(
//                        seekBar: SeekBar?,
//                        progress: Int,
//                        fromUser: Boolean,
//                    ) {
//                        mediaPlayer!!.seekTo(progress)
//                    }
//
//                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//                    }
//
//                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//                    }
//                })


                Glide.with(this).load(musicList!![songPosition].artUri).apply(
                    RequestOptions().placeholder(R.drawable.img).centerCrop()).into(albumImage)
            }
        }

        playPause.setOnClickListener(){
            if(mediaPlayer!!.isPlaying){
                playPause.setImageResource(R.drawable.ic_play)
                mediaPlayer!!.pause()

            }
            else{
                playPause.setImageResource(R.drawable.ic_pause)
                mediaPlayer!!.start()
//                seekbar.max= mediaPlayer!!.duration
//                Timer().scheduleAtFixedRate(object:TimerTask(){
//                    override fun run() {
//                        seekbar.setProgress(mediaPlayer!!.currentPosition)
//                    }
//
//                },0,900)
//                seekbar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
//                    override fun onProgressChanged(
//                        seekBar: SeekBar?,
//                        progress: Int,
//                        fromUser: Boolean,
//                    ) {
//                        mediaPlayer!!.seekTo(progress)
//                    }
//
//                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//                    }
//
//                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//                    }
//                })
            }
        }

        next.setOnClickListener(){
            songPosition++
            if(songPosition> musicList!!.size-1){
                songPosition= 0
            }
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.setDataSource(musicList!![songPosition].path)
            mediaPlayer!!.prepare()
            Glide.with(this).load(musicList!![songPosition].artUri).apply(
                RequestOptions().placeholder(R.drawable.img).centerCrop()).into(albumImage)
            mediaPlayer!!.start()
            endingTime.text= durationFormat(musicList!![songPosition].duration)
//            seekbar.max= mediaPlayer!!.duration
//            Timer().scheduleAtFixedRate(object:TimerTask(){
//                override fun run() {
//                    seekbar.setProgress(mediaPlayer!!.currentPosition)
//                }
//
//            },0,900)
//            seekbar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
//                override fun onProgressChanged(
//                    seekBar: SeekBar?,
//                    progress: Int,
//                    fromUser: Boolean,
//                ) {
//                    mediaPlayer!!.seekTo(progress)
//                }
//
//                override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//                }
//
//                override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//                }
//            })
        }

        previous.setOnClickListener(){
            songPosition--
            if(songPosition<0){
                songPosition= musicList!!.size-1
            }
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.setDataSource(musicList!![songPosition].path)
            mediaPlayer!!.prepare()
            Glide.with(this).load(musicList!![songPosition].artUri).apply(
                RequestOptions().placeholder(R.drawable.img).centerCrop()).into(albumImage)
            mediaPlayer!!.start()
            endingTime.text= durationFormat(musicList!![songPosition].duration)
//            seekbar.max= mediaPlayer!!.duration
//            Timer().scheduleAtFixedRate(object:TimerTask(){
//                override fun run() {
//                    seekbar.setProgress(mediaPlayer!!.currentPosition)
//                }
//
//            },0,900)
//            seekbar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
//                override fun onProgressChanged(
//                    seekBar: SeekBar?,
//                    progress: Int,
//                    fromUser: Boolean,
//                ) {
//                    mediaPlayer!!.seekTo(progress)
//                }
//
//                override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//                }
//
//                override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//                }
//            })
        }
        shuffle.setOnClickListener(){
            musicList!!.shuffle()
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.setDataSource(musicList!![songPosition].path)
            mediaPlayer!!.prepare()
            Glide.with(this).load(musicList!![songPosition].artUri).apply(
                RequestOptions().placeholder(R.drawable.img).centerCrop()).into(albumImage)
            mediaPlayer!!.start()
            endingTime.text= durationFormat(musicList!![songPosition].duration)

        }




    }




}
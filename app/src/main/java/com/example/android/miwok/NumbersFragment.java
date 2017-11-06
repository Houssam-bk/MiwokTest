package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    WordAdapter adapter;
    AudioManager audioManager;
    MediaPlayer mediaPlayer;
    Word word;
    int i;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                mediaPlayer.stop();
                relaseMediaPlayer();

            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
        }
    };

    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

            relaseMediaPlayer();

        }
    };

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.word_list,container,false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> Words = new ArrayList<Word>();

        Words.add( new Word("One","lutti",R.drawable.number_one,R.raw.number_one));
        Words.add( new Word("Two","otiiko",R.drawable.number_two,R.raw.number_two));
        Words.add( new Word("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        Words.add( new Word("For","oyyisa",R.drawable.number_four,R.raw.number_four));
        Words.add( new Word("Five","massokka",R.drawable.number_five,R.raw.number_five));
        Words.add( new Word("Six","temmokka",R.drawable.number_six,R.raw.number_six));
        Words.add( new Word("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        Words.add(new Word("Eight","kawinta",R.drawable.number_eight,R.raw.number_eight) );
        Words.add( new Word("Nine","wo’e",R.drawable.number_nine,R.raw.number_nine));
        Words.add( new Word("Ten","na’aacha",R.drawable.number_ten,R.raw.number_ten));

        adapter = new WordAdapter(getActivity(), Words, R.color.category_numbers);
        ListView listView = (ListView) rootview.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i = audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(i == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    word = Words.get(position);
                    mediaPlayer = MediaPlayer.create(getActivity(),word.getAudioId());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });


        return rootview ;
    }

    void relaseMediaPlayer(){

        if(mediaPlayer != null){

            mediaPlayer.release();
            mediaPlayer = null ;

            audioManager.abandonAudioFocus(onAudioFocusChangeListener);

        }
    }



    @Override
    public void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        relaseMediaPlayer();
    }
}

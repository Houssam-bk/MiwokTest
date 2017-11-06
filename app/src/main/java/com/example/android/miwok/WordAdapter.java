package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Houssam on 10/15/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int color;
    private ArrayList<Word> wordArrayList = null;
    private Context context = null;


    public WordAdapter(Context context, ArrayList<Word> wordArrayList, int Mcolor) {

        super(context, 0, wordArrayList);

        this.wordArrayList = wordArrayList;
        this.context = context;
        this.color = ContextCompat.getColor(context, Mcolor);

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {


        View view = convertView; //existing view That we can use

        if (view == null) {

            //la makanet 7eta view sne3 lia view :)
            view = LayoutInflater.from(context).inflate(R.layout.view_proto, parent, false);


        }


        TextView mword = (TextView) view.findViewById(R.id.miwok_wordword);
        TextView eword = (TextView) view.findViewById(R.id.english_word);
        ImageView imageView = (ImageView) view.findViewById(R.id.rr);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.text_container);

        final Word word = wordArrayList.get(position);


        if (word.hasImage()) {

            imageView.setImageResource(word.getImageId());
            imageView.setVisibility(View.VISIBLE);

        } else
            imageView.setVisibility(View.GONE);

        mword.setText(word.getMiwokTranslation());
        eword.setText(word.getDefaultTranslation());
        linearLayout.setBackgroundColor(color);


        return view;
    }

}

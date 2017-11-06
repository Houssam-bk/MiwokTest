package com.example.android.miwok;

/**
 * Created by Houssam on 10/15/2017.
 */

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;

    private String miwokTranslation , defaultTranslation;

    private int imageId = NO_IMAGE_PROVIDED;
    private int audioId ;

    public Word(String mT,String dT,int iD,int aduioId){

        this.miwokTranslation = mT;
        this.defaultTranslation = dT;
        this.imageId = iD;
        this.audioId = aduioId;
    }

    public Word(String mT,String dT,int aduioId){
        this.miwokTranslation = mT;
        this.defaultTranslation = dT;
        this.audioId = aduioId;
    }


    public String getMiwokTranslation(){
        return miwokTranslation;
    }

    public String getDefaultTranslation(){
        return defaultTranslation;
    }

    public int getImageId(){
        return imageId;
    }

    public boolean hasImage(){

        return imageId != NO_IMAGE_PROVIDED;
    }
    public int getAudioId(){return audioId;}

    @Override
    public String toString() {
        return "Word{" +
                "miwokTranslation='" + miwokTranslation + '\'' +
                ", defaultTranslation='" + defaultTranslation + '\'' +
                ", imageId=" + imageId +
                ", audioId=" + audioId +
                '}';
    }
}

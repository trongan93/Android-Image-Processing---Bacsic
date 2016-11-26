package com.trongan93.imageprocessingbasicnoneopencv.ImageProcessing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by buitrongan on 26/11/2016.
 */

public class ScaleImage {
    Context mContext;
    private int mSourceWidth, mSourceHeight;
    private int mDstWidth, mDstHeight;

    public ScaleImage(Context mContext, int mDstWidth, int mDstHeight) {
        this.mContext = mContext;
        this.mDstWidth = mDstWidth;
        this.mDstHeight = mDstHeight;
    }
    public Bitmap DownScaleBitmap(Bitmap sourceBitmap){
        Bitmap destinationBitmap;
        mSourceWidth = sourceBitmap.getWidth();
        mSourceHeight = sourceBitmap.getHeight();
        float ratio = Math.min((float)mDstWidth/mSourceWidth,(float)mDstHeight/mSourceHeight);
        int width = Math.round((float)ratio * mSourceWidth);
        int height = Math.round((float)ratio * mSourceHeight);

        if(mSourceWidth > mDstWidth || mSourceHeight > mDstHeight){
            destinationBitmap = Bitmap.createScaledBitmap(sourceBitmap,width,height,false);
        }
        else
        {
            destinationBitmap = sourceBitmap;
        }
        return destinationBitmap;
    }
    public Bitmap DowScaleBitmap(Bitmap sourceBitmap, float maxImageSize, boolean filter){
        float ratio = Math.min((float) maxImageSize / sourceBitmap.getWidth(), (float) maxImageSize / sourceBitmap.getHeight());
        int width = Math.round((float) ratio * sourceBitmap.getWidth());
        int height = Math.round((float) ratio * sourceBitmap.getHeight());
        Bitmap newBitmap = Bitmap.createScaledBitmap(sourceBitmap, width, height, filter);
        return newBitmap;
    }
}

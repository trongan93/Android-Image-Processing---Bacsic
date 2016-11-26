package com.trongan93.imageprocessingbasicnoneopencv.ImageProcessing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by buitrongan on 26/11/2016.
 */

public class ScaleImage {
    Context mContext;
    private int mDstWidth, mDstHeight;

    public ScaleImage(Context mContext, int mDstWidth, int mDstHeight) {
        this.mContext = mContext;
        this.mDstWidth = mDstWidth;
        this.mDstHeight = mDstHeight;
    }

    public Bitmap DownScaleBitmap(final int SampleSize, final int ImageResId){
        final Bitmap scaledBitmap;
        final BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inDensity = SampleSize;
        bitmapOptions.inTargetDensity = 1;
        scaledBitmap = BitmapFactory.decodeResource(mContext.getResources(),ImageResId, bitmapOptions);
        scaledBitmap.setDensity(Bitmap.DENSITY_NONE);
        return scaledBitmap;
    }
}

package com.trongan93.imageprocessingbasicnoneopencv.ImageProcessing;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by buitrongan on 27/03/2017.
 */

public class ImageArray {
    int[] imageArray;
    Bitmap inputBitmap;
    int width, height;
    public ImageArray(Bitmap bitmap){
        this.inputBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        this.width = inputBitmap.getWidth();
        this.height = inputBitmap.getHeight();
        this.imageArray = new int[this.width * this.height];
    }
    private void ConvertBitMapToIntArray(){
        inputBitmap.getPixels(imageArray, 0, width, 0, 0 , width, height);
    }
    public void PrintBitMapIntArray(){
        ConvertBitMapToIntArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < imageArray.length; i++){
            stringBuilder.append(imageArray[i]);
            if(i%width == 0){
                stringBuilder.append("\n");
            }
            else{
                stringBuilder.append(" ");
            }
        }
        Log.d("Print Bitmap", "Value of Image ("+this.width+" x "+ this.height +"): " + stringBuilder.toString());

    }
}

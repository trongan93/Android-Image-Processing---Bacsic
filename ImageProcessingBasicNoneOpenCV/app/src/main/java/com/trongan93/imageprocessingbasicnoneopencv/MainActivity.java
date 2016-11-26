package com.trongan93.imageprocessingbasicnoneopencv;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static int IMG_RESULT = 1;
    String ImageDecode;
    Button btnGetGallery;
    ImageView imgSourceImage, imgDestinationImage;
    Activity mActivity;

    // Storage Permissions - from API 23+
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        btnGetGallery = (Button) this.findViewById(R.id.btnGetImage);
        imgSourceImage = (ImageView)this.findViewById(R.id.imvSourceImage);
        imgDestinationImage = (ImageView)this.findViewById(R.id.imvDestinationImage);

        //Delete Cache - when reload App
        DeleteCache(this);

        btnGetGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerifyStoragePermissions(mActivity);
                Intent photoPickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(photoPickIntent, IMG_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if(requestCode == IMG_RESULT && resultCode == RESULT_OK){
                Uri URI = data.getData();
                String[] FILE = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(URI,FILE,null,null,null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();

                imgSourceImage.setImageBitmap(BitmapFactory.decodeFile(ImageDecode));
                imgDestinationImage.setImageBitmap(BitmapFactory.decodeFile(ImageDecode));
                Log.d("trongan93","Success set Image Bitmap");
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void VerifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    /**
     * Delete caches when start Activity
     * Apply for time developer app
     * Custom by trongan93
     */
    public static void DeleteCache(Context context){
        try {
            File dir = context.getCacheDir();
            if(dir != null && dir.isDirectory()){
                DeleteDir(dir);
            }
        }
        catch(Exception e){
            Log.e("trongan93","Fail delete Cache");
        }
    }
    public static boolean DeleteDir(File dir){
        if(null != dir && dir.isDirectory()){
            String[] children = dir.list();
            for(int i = 0; i < children.length; i++){
                boolean sucess = DeleteDir(new File(dir, children[i]));
                if(!sucess)
                    return false;
            }
        }
        return dir.delete();
    }
}

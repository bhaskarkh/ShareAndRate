package com.bhaskar.shareandrateapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;



public class ShareAndRateApp {
    private static final String TAG = "ShareAndRateApp";
    Context context;

    public ShareAndRateApp(Context context) {
        this.context = context;
    }

    public void shareMyApp(int icon_image, String subject, String txtmsg)
    {

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), icon_image);
        String link = "https://play.google.com/store/apps/details?id="+context.getPackageName();

        txtmsg=txtmsg+" "+link;

        try {
            File file = new File(context.getExternalCacheDir(), "iconforshare.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //   Uri outputUri=FileProvider.getUriForFile(this, AUTHORITY, file);
            // intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file)); previous working

            Uri outputFileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
            // intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(), getPackageName() + ".fileProvider", file));
            intent.putExtra(Intent.EXTRA_STREAM, outputFileUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra("android.intent.extra.SUBJECT",subject);
            intent.putExtra("android.intent.extra.TEXT", txtmsg);


            intent.setType("image/png");
            context.startActivity(Intent.createChooser(intent, "Share with"));
        } catch (Exception e) {
            Log.d(TAG, "shareMyApp: Exception msg= "+e.getMessage());
            e.printStackTrace();

        }


    }
}

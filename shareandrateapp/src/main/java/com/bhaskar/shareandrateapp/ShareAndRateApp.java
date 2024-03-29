package com.bhaskar.shareandrateapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


public class ShareAndRateApp {
    private static final String TAG = "ShareAndRateApp";
    Context context;

    public ShareAndRateApp(Context context) {
        this.context = context;
    }

    public void shareMyApp(int icon_image, String subject, String txtmsg,String link)
    {

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), icon_image);
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

            Uri outputFileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
            intent.putExtra(Intent.EXTRA_STREAM, outputFileUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra("android.intent.extra.SUBJECT",subject);
            intent.putExtra("android.intent.extra.TEXT", txtmsg);
            intent.setType("image/png");
            Intent chooser = Intent.createChooser(intent, "Share with");
            List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;

                context.grantUriPermission(packageName, outputFileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            context.startActivity(chooser);


        } catch (Exception e) {
            Log.d(TAG, "shareMyApp: Exception msg= "+e.getMessage());
            e.printStackTrace();

        }
    }
    public void shareMyApp(int icon_image, String subject, String txtmsg)
    {
        Log.d(TAG, "shareMyApp: ");

        String link = "https://play.google.com/store/apps/details?id="+context.getPackageName();
        shareMyApp(icon_image,subject,txtmsg,link);
    }
    public void rateThisApp() {

        try
        {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName())));
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
            Toast.makeText(context, "No App Found",Toast.LENGTH_SHORT).show();
        }
    }


}

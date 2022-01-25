# ShareAndRate
share and rate app libraray for play store

Add it in your root build.gradle at the end of repositories:

**Gradle**

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

    dependencies {
          implementation 'com.github.bhaskarkh:ShareAndRate:1.0.8';
    }
  
 ## Youtube Tutorial on how to use this library
 
 [![Share and Rate Library](http://img.youtube.com/vi/AZpBPlaw41M/0.jpg)](http://www.youtube.com/watch?v=AZpBPlaw41M "How to use share and rate library")
  
  
  ## **Code**
   
```java
  There are three method
  shareMyApp(int icon_image, String subject, String txtmsg);
  shareMyApp(int icon_image, String subject, String txtmsg,String link);
  rateThisApp();
  

  //To share the app with playstore link
ShareAndRateApp shareAndRateApp=new ShareAndRateApp(this);
String txtmsg = "Bhaskar \n\n"+"Download App and Share with your Friends and enjoy\n";
int image_name=R.drawable.b2 ; // image to be attached in msg

 shareAndRateApp.shareMyApp(image_name,"Subject",txtmsg);
 
 //To share the app with any other link
 String link="abcd.com";
 shareAndRateApp.shareMyApp(image_name,"Subject",txtmsg,link);
 
 //To Rate the app
shareAndRateApp.rateThisApp();
```

## **Additional Configuration if you are getting any Error like this**
#### 
```java 
 Writing exception to parcel java.lang.SecurityException: Permission Denial: reading androidx.core.content.FileProvider uri content://com.bhaskar.myapplication.fileprovider/external_files/Android/data/com.bhaskar.myapplication/cache/iconforshare.png from pid=24902, uid=1000 requires the provider be exported, or grantUriPermission()
```

**just add below code in manifest file**
```xml
android:requestLegacyExternalStorage="true"

```
```xml
 <provider android:name="androidx.core.content.FileProvider"
		android:authorities="${applicationId}.fileprovider"
            android:exported="false"
           android:grantUriPermissions="true">
           <meta-data
			android:name="android.support.FILE_PROVIDER_PATHS"
              android:resource="@xml/provider_paths" />
        </provider>
```

### After adding above code manfiest.xml will look like this
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.bhaskar.myapplication">

    <application
        ....
       android:requestLegacyExternalStorage="true">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <provider android:name="androidx.core.content.FileProvider"
		android:authorities="${applicationId}.fileprovider"
            android:exported="false"
           android:grantUriPermissions="true">
           <meta-data
			android:name="android.support.FILE_PROVIDER_PATHS"
              android:resource="@xml/provider_paths" />
        </provider>
    </application>

</manifest>
```
## add provider_paths.xml in xml folder inside res
```xml
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <external-path name="external_files" path="."/>
</paths>

```

## Folder structure

![folder structure](https://github.com/bhaskarkh/Image/blob/main/android_folder_structure.PNG?raw=true)

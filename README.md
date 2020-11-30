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
          implementation 'com.github.bhaskarkh:ShareAndRate:1.0.5';
    }
  
 
  
  ##**Code**
   
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

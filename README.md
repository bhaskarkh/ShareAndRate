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
         compile 'com.github.hajiyevelnur92:intentanimation:1.0.4'
    }
  
 
  
  ##**Code**
   
```java
  There are two method
  shareMyApp(int,String,String);
  rateThisApp();
  

  //to share the app
ShareAndRateApp shareAndRateApp=new ShareAndRateApp(this);
String txtmsg = "Bhaskar \n\n"+"Download App and Share with your Friends and enjoy\n";
int image_name=R.drawable.b2 ; // image to be attached in msg

 shareAndRateApp.shareMyApp(image_name,"Subject",txtmsg);
 
 //to Rate the app
shareAndRateApp.rateThisApp();

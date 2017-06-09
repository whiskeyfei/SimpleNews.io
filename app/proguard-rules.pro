# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/zhangxinxin/Documents/SDK/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class com.library.event.AppExitEvent
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keep class com.kong.app.news.beans.** { *; }
-keep class com.actionbarsherlock.** { *; }
-keep class com.google.gson.** {*;}

-dontobfuscate
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn rx.**
-dontnote sun.misc.Unsafe
-dontnote android.net.http.*
-dontnote org.apache.commons.codec.**
-dontnote org.apache.http.**
-dontnote com.android.org.conscrypt.**
-dontnote org.apache.harmony.xnet.provider.jsse.**
-dontnote com.android.internal.**
-dontwarn android.support.v4.**
-dontwarn com.dsi.ant.**
-dontwarn com.samsung.**
-dontwarn com.vividsolutions.jts.awt.**
-dontwarn android.support.**
-dontwarn com.squareup.okhttp.**
-dontnote android.net.http.*
-dontnote org.apache.http.**
-dontwarn javax.annotation.**
-dontwarn java.lang.invoke.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
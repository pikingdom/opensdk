# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keep public class * extends android.app.Activity

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
    public void set*(***);
   *** get*();
}

#保护泛型
-keepattributes Signature

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep class com.inveno.se.model.BannerNews{
    public void set*(***);
   *** get*();
   }

-keep class com.inveno.se.model.FlowNewsinfo{
    public void set*(***);
   *** get*();
   }

-keep class com.inveno.se.model.Channel{
    public void set*(***);
   *** get*();
   }

-keep class com.inveno.se.model.Channels{
    public void set*(***);
   *** get*();
   }

-keep class com.inveno.se.model.uiconfig.UiConfig{
public void set*(***);
	*** parse*(***);
   *** get*();}

-keep class com.inveno.se.model.uiconfig.HomePage{
public void set*(***);
   *** get*();}

-keep class com.inveno.se.model.ZZNewsinfo{
    public void set*(***);
   *** get*();
   }

-keep class com.inveno.se.model.ZZNews{
    public void set*(***);
   *** get*();
   }

-keep class com.inveno.se.model.InitConfigModel{*;}
-keep class com.inveno.se.adapi.model.*{*;}
-keep class com.inveno.se.adapi.http.*{*;}
-keep class com.inveno.se.adapi.biz.*{*;}
-keep class com.inveno.se.adapi.config.*{*;}
-keep class com.inveno.se.adapi.model.adstyle.*{*;}
-keep class com.inveno.se.adapi.model.adresp.*{*;}
-keep class com.inveno.se.adapi.model.adreq.*{*;}
-keep class com.inveno.se.adapi.AdApiMgr{*;}
-keep class com.inveno.se.biz.ChannelBiz{*;}
-keep class com.inveno.se.config.JsonString{*;}
#-keep class com.inveno.se.model.rss.RssByType{*;}
-keep class com.inveno.se.config.KeyParam{*;}
-keep class com.inveno.se.config.Result{*;}

-keep class com.inveno.se.exception.FileAlreadyExistException{*;}
#-keep class com.inveno.se.tools.*{*;}
-keep class com.inveno.se.tools.AppConfig{*;}
-keep class com.inveno.se.tools.LogTools{*;}
-keep class com.inveno.se.tools.SdcardUtil{*;}
-keep class com.inveno.se.tools.StringTools{*;}
-keep class com.inveno.se.tools.DeviceConfig{*;}
-keep class com.inveno.se.tools.Const{*;}
-keep class com.inveno.se.tools.Tools{*;}
-keep class com.inveno.se.tools.NetWorkUtil{*;}
-keep class com.inveno.se.tools.NetWorkUtil$netType{*;}
-keep class com.inveno.se.tools.DensityUtil{*;}
#-keep class com.inveno.se.volley.**{*;}
-keepattributes Exceptions,InnerClasses,...
-keep class com.inveno.se.volley.toolbox.*{*;}
-keep class com.inveno.se.volley.toolbox.ImageLoader{ *; }
-keep class com.inveno.se.volley.toolbox.ImageLoader$ImageListener { *; }
-keep class com.inveno.se.volley.toolbox.ImageLoader.*{*;}
-keep class com.inveno.se.volley.toolbox.ImageLoader.ImageListener{*;}
-keep class com.inveno.se.volley.lrucache.LruBitmapCache{*;}
-keep class com.inveno.se.volley.lrucache.RecyclingBitmapDrawable{*;}
-keep class com.inveno.se.volley.lrucache.RecyclingImageView{*;}
-keep class com.inveno.se.volley.RequestQueue{*;}
-keep class com.inveno.se.volley.toolbox.Volley{*;}

-keep class com.inveno.se.config.KeyString{*;}
-keep class com.inveno.se.callback.DownloadCallback{*;}
-keep class com.inveno.se.callback.IFlowInfoLongClick{*;}
-keep class com.inveno.se.callback.ZZReuqestListener{*;}
-keep class com.inveno.se.callback.WebTransCallback{*;}
-keep class com.inveno.se.CustomController{*;}
-keep class com.inveno.se.NContext{*;}
#-keep class com.inveno.se.PiflowInfoManager{*;}
-keep class com.inveno.se.SearchManager{*;}
-keep class com.inveno.se.ZZSDKManager{*;}

-keep class com.inveno.se.tools.ysj.log.L{*;}

-keep class com.inveno.reportsdk.type.*{*;}
-keep class com.inveno.reportsdk.type.ContentType{*;}
-keep class com.inveno.reportsdk.type.DisplayType{*;}
-keep class com.inveno.reportsdk.type.LinkType{*;}
-keep class com.inveno.reportsdk.type.Scenario{*;}
-keep class com.inveno.reportsdk.XZReportAgent{*;}
-keep class com.inveno.reportsdk.KeyConstants{*;}
-keep class com.inveno.reportsdk.CommonParams{*;}


#-keep class com.inveno.datasdk.*{*;}
#-keep class utils.*{*;}
-keep class com.inveno.se.tools.CheckPermissions{*;}
-keep class com.inveno.se.model.**{*;}
-keep class com.inveno.se.http.*{*;}
-keep class com.inveno.se.model.MustParam{*;}
-keep class com.inveno.se.http.VolleyHttp{*;}
-keep class com.inveno.se.volley.*{*;}
-keep class com.inveno.se.DetailManager{*;}

-keep class com.inveno.se.biz.CanReleaseBiz{*;}
-keep class com.inveno.se.biz.UiConfigBiz{*;}
-keep class com.inveno.se.biz.WebviewToolsBiz{*;}
-keep class com.inveno.se.config.HostConfig{*;}
-keep class com.inveno.se.config.URLPath{*;}

-keep class com.inveno.se.biz.UidBiz{*;}
-keep class com.inveno.se.callback.UidLisener{*;}

-keep class com.inveno.se.adapi.ad.DownloadService{*;}
-keep class com.inveno.se.adapi.model.adconfig.*{*;}
-keep class com.inveno.se.adapi.model.update.*{*;}
-keep class com.inveno.se.adapi.AdsConst{*;}
-keep class utils.NetworkUtils{*;}
#-keep class com.inveno.se.biz.CanReleaseBiz{*;}

-keep class com.inveno.se.tools.GetFileMD5{*;}
-keep class com.inveno.se.volley.Response{*;}
-keep class com.inveno.se.volley.VolleyError{*;}
-keep class com.inveno.se.config.ZZAppConfig{*;}


-keep class com.inveno.opensdk.open.detail.detail.support**{*;}
-keep class com.inveno.opensdk.open.mvp.view**{*;}
-keep class com.inveno.opensdk.open.mvp.cardview**{*;}
-keep class com.inveno.opensdk.open.mvp.baseview**{*;}
-keep class com.inveno.opensdk.open.mvp.param**{*;}
# Keep JS Interaction
-keep class com.inveno.opensdk.open.detail.detail.JavaScriptMobileInfo{*;}
-keep class com.inveno.opensdk.open.detail.detail.TranscodeObject{*;}
# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**




package com.manappuram.reapp.Utils;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

public class REApplication extends Application {

    public static REApplication getAppInstance() {
        return appInstance;
    }


    public static CryptLib getCryptLib() {
        return cryptLib;
    }

    private static REApplication appInstance;
    private static CryptLib cryptLib;
    public static Boolean showAnimation = true;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        Stetho.initializeWithDefaults(this);
        initCrypto();
       // AppLockManager.getInstance().enableDefaultAppLockIfAvailable(this);
        // StaticVariableInitializer.init(this);

//        AppSignatureHelper appSignature = new AppSignatureHelper(this);
//        System.out.println(" appsignature " + appSignature.getAppSignatures());
    }


    /**
     * Initialize Encryption for App
     */
    private void initCrypto() {
        try {
            cryptLib = new CryptLib();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     *
     * @return tracker
     */
//    synchronized public Tracker getDefaultTracker() {
//        if (sTracker == null) {
//            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
//        }
//        return sTracker;
//    }
}

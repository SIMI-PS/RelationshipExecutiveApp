package com.manappuram.reapp.Utils.Navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class navigator implements BaseNavigator {

    private final WeakReference<Activity> mActivity;

    public navigator(Activity activity) {
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public void finishActivity() {
        if (mActivity.get() != null) {
            mActivity.get().finish();
        }
    }

    @Override
    public void finishActivityWithResult(int resultCode) {
        if (mActivity.get() != null) {
            mActivity.get().setResult(resultCode);
            mActivity.get().finish();
        }
    }

    @Override
    public void startActivityForResult(Class cls, int requestCode) {
        if (mActivity.get() != null) {
            Intent intent = new Intent(mActivity.get(), cls);
            mActivity.get().startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void startActivityForResultWithExtra(Class cls, int requestCode, String extraKey,
                                                String extraValue) {
        if (mActivity.get() != null) {
            Intent intent = new Intent(mActivity.get(), cls);
            intent.putExtra(extraKey, extraValue);
            mActivity.get().startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void startActivity(Class cls) {
        if (mActivity.get() != null) {
            Intent intent = new Intent(mActivity.get(), cls);
            mActivity.get().startActivity(intent);
        }
    }

    public void startActivityWithAnimation(Class cls) {
        if (mActivity.get() != null) {
            Intent intent = new Intent(mActivity.get(), cls);
            mActivity.get().startActivity(intent);
        }
    }

    public static void navigate(Context context, Class cls){
        Intent i = new Intent(context,cls);
        context.startActivity(i);
    }

    public static void navigate(Context context, Bundle bundle, Class cls){
        Intent i = new Intent(context,cls);
        i.putExtras(bundle);
        context.startActivity(i);
    }

    public void openCallActivity(Context context, String number){

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+number));
        context.startActivity(callIntent);
    }


}

package com.manappuram.reapp.Utils;

import static android.graphics.Typeface.BOLD;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.manappuram.reapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Utility {

    private static Dialog progressDialog;
    private static AlertDialog.Builder builder;
    private static AlertDialog alertDialog;

    public static String supplyDate(String dateInput) {
        try {
            String outPattern = "dd-MMM-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
            Date date = dateFormat.parse(dateInput);
            String dateOut = simpleDateFormat.format(date);
            return dateOut;
        } catch (Exception e) {
            return dateInput;
        }
    }

    public static String getFormattedDate(String dateInput) {
        try {
            String outPattern = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aaa");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
            Date date = dateFormat.parse(dateInput);
            String dateOut = simpleDateFormat.format(date);
            return dateOut;
        } catch (Exception e) {
            return "";
        }
    }


    public static String getFormattedDate2(String dateInput) {
        try {
            String outPattern = "dd-MMM-yyyy HH:mm:ss aaa";
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aaa");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
            Date date = dateFormat.parse(dateInput);
            String dateOut = simpleDateFormat.format(date);
            return dateOut;
        } catch (Exception e) {
            return "";
        }
    }


    public static String formatDate(String dateInput) {
        try {
            String outPattern = "dd-MMM-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
            Date date = dateFormat.parse(dateInput);
            String dateOut = simpleDateFormat.format(date);
            return dateOut;
        } catch (Exception e) {
            return dateInput;
        }
    }

    public static String getDate(int dayOfMonth, int month, int year) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar1.set(Calendar.MONTH, month);
        calendar1.set(Calendar.YEAR, year);

        return getDate(calendar1);
    }

    public static String getDateLeave(int dayOfMonth, int month, int year) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar1.set(Calendar.MONTH, month);
        calendar1.set(Calendar.YEAR, year);

        return getDateLeave(calendar1);
    }

    public static Calendar getDateCal(int dayOfMonth, int month, int year) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar1.set(Calendar.MONTH, month);
        calendar1.set(Calendar.YEAR, year);

        return calendar1;
    }

    public static String getDate(Calendar calendar) {
        try {
            String outPattern = "dd-MMM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
            String dateOut = simpleDateFormat.format(calendar.getTime());
            return dateOut;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getDateLeave(Calendar calendar) {
        try {
            String outPattern = "dd/MMM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
            String dateOut = simpleDateFormat.format(calendar.getTime());
            return dateOut;
        } catch (Exception e) {
            return "";
        }
    }


    public static String getLangCode(int position) {
        String langCode = null;

        switch (position) {
            case 0:
                langCode = "EN";
                break;
            case 1:
                langCode = "HI";
                break;
            case 2:
                langCode = "MA";
                break;
            case 3:
                langCode = "TA";
                break;
            case 4:
                langCode = "KA";
                break;
            case 5:
                langCode = "TE";
                break;
            case 6:
                langCode = "MR";
                break;
            case 7:
                langCode = "GU";
                break;
            case 8:
                langCode = "OR";
                break;
            case 9:
                langCode = "PU";
                break;
            case 10:
                langCode = "BE";
                break;
            case 11:
                langCode = "AS";
                break;
        }

        return langCode;
    }

//    public static String getARGB(int color){
//
//        float red=   (color >> 16) & 0xFF;
//        float green= (color >> 8) & 0xFF;
//        float blue=  (color >> 0) & 0xFF;
//        float alpha= (color >> 24) & 0xFF;
//
//    }

    public static boolean isNull(String string) {

        if (string == null)
            return true;

        if (string.equalsIgnoreCase("null"))
            return true;

        return false;
    }

    public static void showAlertDialog(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    public static void showAlertDialog(Context context, String msg, DialogInterface.OnClickListener listener) {

        builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);
        builder.setCancelable(false);

        builder.setPositiveButton(R.string.ok, listener);
        alertDialog = builder.create();
        alertDialog.show();


    }

    public static void dismissAlertDialog() {

        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }

    }

    //   public static void showAlertDialogWithRetry(View view, String msg, View.OnClickListener listener) {
//        showAlertDialogWithAction(R.string.retry, view, msg, listener);
////        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
////        snackbar.setAction(R.string.retry,new View.OnClickListener(){
////            @Override
////            public void onClick(View v) {
////                listener.onClick(v);
////                snackbar.dismiss();
////            }
//        });
//        snackbar.setActionTextColor(Color.RED);
//        snackbar.show();
    //   }


    public static void showAlertDialogWithAction(String actionMsg, View view, String msg, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.setAction(actionMsg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    public static void showAlertDialogWithAction(int actionMsg, View view, String msg, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.setAction(actionMsg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

//
//    public static void showAlertDialogWithRetry(View view, int msg, View.OnClickListener listener) {
//        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
//        snackbar.setAction(R.string.retry, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onClick(v);
//                snackbar.dismiss();
//            }
//        });
//        snackbar.setActionTextColor(Color.RED);
//        snackbar.show();
//    }

    public static String encodeString(String text) {
        try {
            String CIPHER_KEY = "8080808080808080";
            String CIPHER_IV = "8080808080808080";
            return Encrypt(text, CIPHER_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decodeString(String text) {
        try {
            return REApplication.getCryptLib().decryptCipherText(text, Constants.CIPHER_KEY, Constants.CIPHER_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void makeLinks(TextView textView, String[] links, ClickableSpan[] clickableSpans) {
        try {
            SpannableString spannableString = new SpannableString(textView.getText());
            for (int i = 0; i < links.length; i++) {
                ClickableSpan clickableSpan = clickableSpans[i];
                String link = links[i];

                int startIndexOfLink = textView.getText().toString().indexOf(link);
                spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new StyleSpan(BOLD), startIndexOfLink, startIndexOfLink + link.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            textView.setMovementMethod(LinkMovementMethod.getInstance());

            textView.setText(spannableString, TextView.BufferType.SPANNABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String millisecondsToTime(long milliseconds) {
        long minutes = (milliseconds / 1000) / 60;
        long seconds = (milliseconds / 1000) % 60;
        String secondsStr = Long.toString(seconds);
        String secs;
        if (secondsStr.length() >= 2) {
            secs = secondsStr.substring(0, 2);
        } else {
            secs = "0" + secondsStr;
        }
        return minutes + ":" + secs;
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager methodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

//    public static void showKeyboard(Activity activity) {
//        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//    }

    public static Bitmap convertLayoutToImage(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }


    public static String getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c);
    }


    public static String toCamelCase(String s) {
        String[] parts = s.split(" ");
        String camelCaseString = "";
        for (String part : parts) {
            if (part != null && part.trim().length() > 0)
                camelCaseString = camelCaseString + toProperCase(part);
            else
                camelCaseString = camelCaseString + part + "";
        }
        return camelCaseString;
    }

    static String toProperCase(String s) {
        String temp = s.trim();
        String spaces = "";
        if (temp.length() != s.length()) {
            int startCharIndex = s.charAt(temp.indexOf(0));
            spaces = s.substring(0, startCharIndex);
        }
        temp = temp.substring(0, 1).toUpperCase() +
                spaces + temp.substring(1).toLowerCase() + "";
        return temp;

    }


//    public static String encodeString(String text) {
//        String encrypted = "";
//        try {
//            encrypted = CryptLib.encrypt(text);
//            return encrypted;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    //new


    public static String Encrypt(String text, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));

        String Base64encodeString = android.util.Base64.encodeToString(results, Base64.DEFAULT);

        return Base64encodeString.substring(0, Base64encodeString.length() - 1);
    }

    public static void setProgressbar(Context context) {


        progressDialog = new Dialog(context, R.style.AlertDialogCustom);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.custom_progressbar);
        progressDialog.setCancelable(false);

        progressDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        progressDialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;
        progressDialog.getWindow().setGravity(Gravity.CENTER);

        if (!((Activity) context).isFinishing()) {
            progressDialog.show();
        }

    }

    public static void cancelProgressbar() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showSnackbar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackbar(View view, int msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static String dateChange(String time) {
        String inputPattern = "MM/dd/yyyy hh:mm:ss a";
        String outputPattern = "EEE, dd MMM, hh:mm a, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }




    public static String getDateSlashparent(int dayOfMonth, int month, int year) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar1.set(Calendar.MONTH, month);
        calendar1.set(Calendar.YEAR, year);

        return getDateslash(calendar1);
    }

    public static String getDateslash(Calendar calendar) {
        try {
            String outPattern = "dd/MMM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
            return simpleDateFormat.format(calendar.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    public static String encodecusid(String text) {
        try {
            return REApplication.getCryptLib().encryptPlainText(text, com.manappuram.reapp.Utils.Constants.CIPHER_KEY, Constants.CIPHER_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void showpushnotification(String title, String text, Context context, Intent in) {
//
//
//        Notification notification = new Notification();
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(context, "notify_001"); //send notification
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, in, 0);
//        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
//
//        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ess_icon);
//        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
//        bigText.setBigContentTitle(title);
//        //mBuilder.setStyle(style);
//        mBuilder.setContentTitle(title);
//        mBuilder.setWhen(System.currentTimeMillis());
//        mBuilder.setLargeIcon(largeIcon);
//        mBuilder.setContentIntent(pendingIntent);
//        mBuilder.setSmallIcon(R.drawable.male_icon);
//        mBuilder.setContentText(text);
//        mBuilder.setPriority(Notification.PRIORITY_MAX);
//        // mBuilder.setStyle(style);
//        mBuilder.setAutoCancel(true);
//        mBuilder.setTicker("ESS");
//        mBuilder.setOngoing(true);
//        mBuilder.setColor(context.getResources().getColor(R.color.black));
//        mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
//        mBuilder.setDefaults(notification.DEFAULT_SOUND | notification.DEFAULT_LIGHTS | notification.DEFAULT_VIBRATE);
//
//
//        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            String channelId = "notify_001";
//            NotificationChannel channel = new NotificationChannel(
//                    channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_HIGH);
//            channel.setLockscreenVisibility(notification.VISIBILITY_PUBLIC);
//            mNotificationManager.createNotificationChannel(channel);
//            mBuilder.setChannelId(channelId);
//        }
//        mNotificationManager.notify(0, mBuilder.build());
//    }

    public static String parseDateToddMMyyyy(String time) {
        String inputPattern = "MM/dd/yyyy hh:mm:ss a";
        String outputPattern = "EEE, dd MMM, hh:mm a, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getFormattedDate3(String dateInput) {
        try {
            String outPattern = "dd-MM-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aaa");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outPattern);
            Date date = dateFormat.parse(dateInput);
            String dateOut = simpleDateFormat.format(date);
            return dateOut;
        } catch (Exception e) {
            return "";
        }
    }

}








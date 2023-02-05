package us.xoventech.playertime;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;

public class AleartDialogue {
    public static void createDialogue(Context context) {
        new AlertDialog.Builder(context).setTitle(Html.fromHtml("<font color='#FF4242'><b>সতর্কতা!</b></font>")).setMessage(Html.fromHtml("<font color='#18836F'>সার্ভারের সাথে সংযুক্ত নেই। দয়া করে আপনার ডিভাইসের ইন্টারনেট সংযোগ চেক করুন এবং পুনরায় চেষ্টা করুন?</font>")).setIcon(R.drawable.warning_icon).setCancelable(false).setPositiveButton("ঠিকাছে", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }
}

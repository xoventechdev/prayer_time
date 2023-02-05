package us.xoventech.playertime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    private static final String ONESIGNAL_APP_ID = "9fc7ab61-79ef-4fa9-9add-8b948051a365";
    public static SharedPreferences.Editor editor;
    public static TextView location;
    public static SharedPreferences sharedPreferences;
    AdView adView;
    LinearLayout banner_container;
    Dialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        InternetChecker.checkInternet(this);
        this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLyout);
        final ViewPager2 viewPager2 = (ViewPager2) findViewById(R.id.viewPager);
        this.banner_container = (LinearLayout) findViewById(R.id.banner_container);
        location = (TextView) findViewById(R.id.loacation);
        this.adView = (AdView) findViewById(R.id.adView);
        SharedPreferences sharedPreferences2 = getSharedPreferences("2131820591", 0);
        sharedPreferences = sharedPreferences2;
        editor = sharedPreferences2.edit();
        if (InternetChecker.checkInternet(this)) {
           // createUpdateDialogue();
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        OneSignal.promptForPushNotifications();
        location.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (InternetChecker.checkInternet(MainActivity.this)) {
                    MainActivity.this.locationSet_Dialogue();
                } else {
                    new AlertDialog.Builder(MainActivity.this).setTitle(Html.fromHtml("<font color='#FF4242'><b>ইন্টারনেট সংযুক্ত নেই!</b></font>")).setMessage(Html.fromHtml("<font color='#18836F'>আপনার জেলা/বিভাগ পরিবর্তন করার জন্য ইন্টারনেট সংযুক্ত করুন?</font>")).setIcon(R.drawable.warning_icon).setCancelable(false).setPositiveButton("ঠিকাছে", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                }
            }
        });
        DistrictName.locationSet_data(this);
        MyViewpagerAdapter myViewpagerAdapter = new MyViewpagerAdapter(this);
        tabLayout.addTab(tabLayout.newTab().setText((CharSequence) "ওয়াক্তের সূচি"));
        tabLayout.addTab(tabLayout.newTab().setText((CharSequence) "ওয়াক্তের প্রস্তুতি"));
        viewPager2.setAdapter(myViewpagerAdapter);
        tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
            public void onTabReselected(TabLayout.Tab tab) {
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }

            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            public void onPageSelected(int i) {
                super.onPageSelected(i);
                tabLayout.getTabAt(i).select();
            }
        });
    }

    /* access modifiers changed from: private */
    public void locationSet_Dialogue() {
        Dialog dialog2 = new Dialog(this);
        this.dialog = dialog2;
        dialog2.setContentView(R.layout.location_change_lay);
        final Spinner spinner = (Spinner) this.dialog.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(this, R.array.District_List, R.layout.color_spinner_layout);
        createFromResource.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(createFromResource);
        ((TextView) this.dialog.findViewById(R.id.change_location)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String obj = spinner.getSelectedItem().toString();
                if (obj.contains("--জেলা/বিভাগ--")) {
                    Toast.makeText(MainActivity.this, "দয়া করে আপনার জেলা/বিভাগ নির্বাচন করুন?", Toast.LENGTH_SHORT).show();
                    return;
                }
                MainActivity.editor.putString("district_name", obj);
                MainActivity.editor.apply();
                DistrictName.locationSet_data(MainActivity.this);
                MostRecentFragment.getSalat_time_data(MainActivity.this);
                MainActivity.this.dialog.dismiss();
            }
        });
        ((ImageView) this.dialog.findViewById(R.id.close_dialogue)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.dialog.dismiss();
            }
        });
        this.dialog.setCancelable(true);
        this.dialog.show();
    }

    private void createUpdateDialogue() {
        FirebaseDatabase.getInstance().getReference("Dialogue").addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                String obj = dataSnapshot.child("newVersionCode").getValue().toString();
                String obj2 = dataSnapshot.child("switch_ads").getValue().toString();
                if (MainActivity.this.getCurrentVersionCode() < Integer.parseInt(obj)) {
                    MainActivity.this.show_updateDialogue();
                }
                if (obj2.contains("ON")) {
                    MainActivity.this.adView.setVisibility(View.VISIBLE);
                    MainActivity.this.adView.loadAd(new AdRequest.Builder().build());
                    return;
                }
                MainActivity.this.adView.setVisibility(View.GONE);
            }

            public void onCancelled(DatabaseError databaseError) {
                Log.d("nve", databaseError.getMessage());
            }
        });
    }

    /* access modifiers changed from: private */
    public void show_updateDialogue() {
        final Dialog dialog2 = new Dialog(this);
        dialog2.setContentView(R.layout.update_dialogue);
        ((TextView) dialog2.findViewById(R.id.update_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName()));
                try {
                    MainActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(MainActivity.this, "Google Play have not been installed.", Toast.LENGTH_LONG).show();
                }
            }
        });
        ((TextView) dialog2.findViewById(R.id.later_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog2.dismiss();
            }
        });
        dialog2.setCancelable(false);
        dialog2.show();
    }

    /* access modifiers changed from: private */
    public int getCurrentVersionCode() {
        PackageInfo packageInfo;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (Exception e) {
            Log.d("v_code", e.getMessage());
            packageInfo = null;
        }
        return packageInfo.versionCode;
    }
}
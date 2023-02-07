package us.xoventech.playertime;



import static us.xoventech.playertime.MainActivity.getFull;
import static us.xoventech.playertime.MainActivity.location;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONException;
import org.json.JSONObject;




import com.al.tobangla.utils.ProcessType;
import com.al.tobangla.views.BNTextView;


public class MostRecentFragment extends Fragment {
    public static String DAY = "";
    public static String LOCATION = "";
    public static String TIME = "";
    public static LottieAnimationView anim_by_time;
    public static RelativeLayout asar;
    public static TextView asar_time;
    public static RelativeLayout duhar;
    public static TextView duhar_time;
    public static SharedPreferences.Editor editor;
    public static TextView esrak_time;
    public static RelativeLayout esrek;
    public static RelativeLayout fajar;
    public static TextView fajar_time;
    public static Handler handler;
    public static TextView ifter;
    public static TextView indicate_wact;
    public static RelativeLayout isha;
    public static TextView isha_time;
    public static RelativeLayout magrib;
    public static TextView magrib_time;
    public static TextView name_asar;
    public static TextView name_duhar;
    public static TextView name_esrak;
    public static TextView name_fajar;
    public static TextView name_isha;
    public static TextView name_magrib;
    public static TextView name_tahajjud;
    public static CardView prayer_time_container;
    public static Runnable runnable;
    public static TextView sahri;
    public static ScheduledExecutorService scheduledExecutorService;
    public static SharedPreferences sharedPreferences;
    public static TextView sun_rise;
    public static TextView sun_set;
    public static SwipeRefreshLayout swipeRefresh;
    public static RelativeLayout tahajjud;
    public static TextView tahajjud_time;
    public static TextView time_has;
    public static TextView time_now;
    public static TextView today_indicate;
    public static CardView top_time_section;
    public static TextView tv_running_time;
    public static TextView tv_salat_name;
    public static TextView wact_details;
    public static CardView wact_details_container;
    BNTextView bnTextView;
    BNTextView day_now;
    private int duration = 120;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_most_recent, container, false);
        swipeRefresh = (SwipeRefreshLayout) inflate.findViewById(R.id.swipeRefresh);
        anim_by_time = (LottieAnimationView) inflate.findViewById(R.id.anim_by_time);
        fajar_time = (TextView) inflate.findViewById(R.id.fajar_time);
        esrak_time = (TextView) inflate.findViewById(R.id.esrak_time);
        duhar_time = (TextView) inflate.findViewById(R.id.duhar_time);
        asar_time = (TextView) inflate.findViewById(R.id.asar_time);
        magrib_time = (TextView) inflate.findViewById(R.id.magrib_time);
        isha_time = (TextView) inflate.findViewById(R.id.isha_time);
        tahajjud_time = (TextView) inflate.findViewById(R.id.tahajjud_time);
        tv_salat_name = (TextView) inflate.findViewById(R.id.tv_salat_name);
        tv_running_time = (TextView) inflate.findViewById(R.id.tv_running_time);
        wact_details = (TextView) inflate.findViewById(R.id.wact_details);
        this.bnTextView = (BNTextView) inflate.findViewById(R.id.day_bangla);
        time_now = (TextView) inflate.findViewById(R.id.time_now);
        this.day_now = (BNTextView) inflate.findViewById(R.id.date_bangla);
        name_fajar = (TextView) inflate.findViewById(R.id.name_fajar);
        name_esrak = (TextView) inflate.findViewById(R.id.name_esrak);
        name_duhar = (TextView) inflate.findViewById(R.id.name_duhar);
        name_asar = (TextView) inflate.findViewById(R.id.name_asar);
        name_magrib = (TextView) inflate.findViewById(R.id.name_magrib);
        name_isha = (TextView) inflate.findViewById(R.id.name_isha);
        name_tahajjud = (TextView) inflate.findViewById(R.id.name_tahajjud);
        time_has = (TextView) inflate.findViewById(R.id.time_has);
        fajar = (RelativeLayout) inflate.findViewById(R.id.fajar);
        esrek = (RelativeLayout) inflate.findViewById(R.id.esrek);
        duhar = (RelativeLayout) inflate.findViewById(R.id.duhar);
        asar = (RelativeLayout) inflate.findViewById(R.id.asar);
        magrib = (RelativeLayout) inflate.findViewById(R.id.magrib);
        isha = (RelativeLayout) inflate.findViewById(R.id.isha);
        tahajjud = (RelativeLayout) inflate.findViewById(R.id.tahajjud);
        sahri = (TextView) inflate.findViewById(R.id.sahri);
        ifter = (TextView) inflate.findViewById(R.id.ifter);
        indicate_wact = (TextView) inflate.findViewById(R.id.indicate_wact);
        sun_rise = (TextView) inflate.findViewById(R.id.sun_rise);
        sun_set = (TextView) inflate.findViewById(R.id.sun_set);
        top_time_section = (CardView) inflate.findViewById(R.id.top_time_section);
        prayer_time_container = (CardView) inflate.findViewById(R.id.prayer_time_container);
        wact_details_container = (CardView) inflate.findViewById(R.id.wact_details_container);
        today_indicate = (TextView) inflate.findViewById(R.id.today_indicate);
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("2131820591", 0);
        sharedPreferences = sharedPreferences2;
        editor = sharedPreferences2.edit();
        this.bnTextView.setProcessType(ProcessType.TODAY);
        this.day_now.setProcessType(ProcessType.ORDINAL_INDICATOR_TODAY);
        this.bnTextView.setText("");
        this.day_now.setText("");
        String format = new SimpleDateFormat("EEE", Locale.getDefault()).format(Calendar.getInstance().getTime());
        DAY = format;
        if (format.contains("Fri")) {
            name_duhar.setText("জুম'আ");
        } else {
            name_duhar.setText("যোহর");
        }
        handler = new Handler();
        Runnable r4 = new Runnable() {
            public void run() {
                MostRecentFragment.time_now.setText(Html.fromHtml("<b>" + new SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(Calendar.getInstance().getTime()) + "</b>"));
                MostRecentFragment.TIME = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Calendar.getInstance().getTime());
                MostRecentFragment.handler.postDelayed(this, 1000);
            }
        };
        runnable = r4;
        handler.post(r4);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                if (InternetChecker.checkInternet(MostRecentFragment.this.getActivity())) {
                    MostRecentFragment.getSalat_time_data(MostRecentFragment.this.getActivity());
                    return;
                }
                MostRecentFragment.showSalat_time_data(MostRecentFragment.this.getActivity());
                MostRecentFragment.swipeRefresh.setRefreshing(false);
            }
        });
        swipeRefresh.setColorSchemeResources(R.color.icon_color);
        if (InternetChecker.checkInternet(getActivity())) {
            getSalat_time_data(getActivity());
        } else {
            handler = new Handler();
            Runnable r42 = new Runnable() {
                public void run() {
                    MostRecentFragment.swipeRefresh.setRefreshing(false);
                    MostRecentFragment.showSalat_time_data(MostRecentFragment.this.getActivity());
                    if (Build.VERSION.SDK_INT >= 28) {
                        MostRecentFragment.handler.postDelayed(this, 1000, 600000);
                    }
                }
            };
            runnable = r42;
            handler.post(r42);
        }
        return inflate;
    }

    public static void getSalat_time_data(final Context context) {
        swipeRefresh.setRefreshing(true);
        Volley.newRequestQueue(context).add(new JsonObjectRequest(0, "https://api.aladhan.com/v1/timingsByAddress?address=" + getFull + "", (JSONObject) null, new Response.Listener<JSONObject>() {
//        Volley.newRequestQueue(context).add(new JsonObjectRequest(0, "https://api.aladhan.com/v1/timingsByAddress?address=" + LOCATION + ",Bangladesh", (JSONObject) null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                MostRecentFragment.swipeRefresh.setRefreshing(false);
                Log.d("check", jSONObject.toString());
                try {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(Constants.ScionAnalytics.MessageType.DATA_MESSAGE).getJSONObject("timings");
                    MostRecentFragment.editor.putString("save_Sunrise_time", jSONObject2.getString("Sunrise"));
                    MostRecentFragment.editor.putString("save_Sunset_time", jSONObject2.getString("Sunset"));
                    MostRecentFragment.editor.putString("save_Fajar_time_s", jSONObject2.getString("Fajr"));
                    MostRecentFragment.editor.putString("save_Duhar_time_s", jSONObject2.getString("Dhuhr"));
                    MostRecentFragment.editor.putString("save_Asar_time_s", jSONObject2.getString("Asr"));
                    MostRecentFragment.editor.putString("save_Magrib_time_s", jSONObject2.getString("Maghrib"));
                    MostRecentFragment.editor.putString("save_Isha_time_s", jSONObject2.getString("Isha"));
                    MostRecentFragment.editor.apply();
                    MostRecentFragment.showSalat_time_data(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                AleartDialogue.createDialogue(context);
            }
        }));
    }

    public static void showSalat_time_data(Context context) {
        Context context2 = context;
        String string = sharedPreferences.getString("save_Sunrise_time", "00:00");
        String string2 = sharedPreferences.getString("save_Sunset_time", "00:00");
        String string3 = sharedPreferences.getString("save_Fajar_time_s", "00:00");
        String string4 = sharedPreferences.getString("save_Duhar_time_s", "00:00");
        String string5 = sharedPreferences.getString("save_Asar_time_s", "00:00");
        String string6 = sharedPreferences.getString("save_Magrib_time_s", "00:00");
        String string7 = sharedPreferences.getString("save_Isha_time_s", "00:00");
        try {
            Calendar instance = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm a", Locale.getDefault());
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            Date parse = simpleDateFormat3.parse(TIME);
            Date parse2 = simpleDateFormat.parse(string);
            Date parse3 = simpleDateFormat.parse(string2);
            Date parse4 = simpleDateFormat.parse(string3);
            Date parse5 = simpleDateFormat.parse(string4);
            Date parse6 = simpleDateFormat.parse(string5);
            Date parse7 = simpleDateFormat.parse(string6);
            Date parse8 = simpleDateFormat.parse(string7);
            String format = simpleDateFormat3.format(Long.valueOf(parse2.getTime()));
            String format2 = simpleDateFormat3.format(Long.valueOf(parse3.getTime()));
            String format3 = simpleDateFormat3.format(Long.valueOf(parse4.getTime()));
            String format4 = simpleDateFormat3.format(Long.valueOf(parse5.getTime()));
            String format5 = simpleDateFormat3.format(Long.valueOf(parse6.getTime()));
            String format6 = simpleDateFormat3.format(Long.valueOf(parse7.getTime()));
            String format7 = simpleDateFormat3.format(Long.valueOf(parse8.getTime()));
            Date parse9 = simpleDateFormat3.parse(format3);
            Date parse10 = simpleDateFormat3.parse(format4);
            Date parse11 = simpleDateFormat3.parse(format5);
            String str = format5;
            Date parse12 = simpleDateFormat3.parse(format6);
            Date date = parse;
            Date parse13 = simpleDateFormat3.parse(format7);
            instance.setTime(parse9);
            Date date2 = parse9;
            String str2 = format7;
            instance.add(12, 75);
            String format8 = simpleDateFormat3.format(instance.getTime());
            instance.setTime(parse10);
            Date date3 = parse10;
            instance.add(12, 220);
            String format9 = simpleDateFormat3.format(instance.getTime());
            instance.setTime(parse11);
            instance.add(12, 40);
            String format10 = simpleDateFormat3.format(instance.getTime());
            Date parse14 = simpleDateFormat3.parse(format10);
            instance.setTime(parse14);
            Date date4 = parse14;
            String str3 = format6;
            instance.add(12, 80);
            String format11 = simpleDateFormat3.format(instance.getTime());
            instance.setTime(parse12);
            Date date5 = parse12;
            instance.add(12, 78);
            String format12 = simpleDateFormat3.format(instance.getTime());
            instance.setTime(parse13);
            Date date6 = parse13;
            instance.add(12, 240);
            String format13 = simpleDateFormat3.format(instance.getTime());
            Date parse15 = simpleDateFormat3.parse(format8);
            Date parse16 = simpleDateFormat3.parse(format9);
            Date parse17 = simpleDateFormat3.parse(format11);
            Date parse18 = simpleDateFormat3.parse(format12);
            String str4 = format12;
            Date parse19 = simpleDateFormat3.parse(format13);
            instance.setTime(parse15);
            Date date7 = parse15;
            String str5 = format13;
            instance.add(12, 25);
            String format14 = simpleDateFormat3.format(instance.getTime());
            Date parse20 = simpleDateFormat3.parse(format14);
            instance.setTime(parse20);
            Date date8 = parse20;
            String str6 = format11;
            instance.add(12, 120);
            String format15 = simpleDateFormat3.format(instance.getTime());
            Date parse21 = simpleDateFormat3.parse(format15);
            instance.setTime(parse19);
            Date date9 = parse19;
            instance.add(12, 150);
            String format16 = simpleDateFormat3.format(instance.getTime());
            Date parse22 = simpleDateFormat3.parse(format16);
            Date parse23 = simpleDateFormat2.parse(format16);
            instance.setTime(parse22);
            Date date10 = parse22;
            Date date11 = parse23;
            instance.add(12, 230);
            String format17 = simpleDateFormat3.format(instance.getTime());
            Date parse24 = simpleDateFormat3.parse(format17);
            sun_rise.setText("সূর্যোদয়\n" + format);
            sun_set.setText("সূর্যাস্ত\n" + format2);
            fajar_time.setText(format3 + " - " + format8);
            esrak_time.setText(format14 + " - " + format15);
            duhar_time.setText(format4 + " - " + format9);
            String str7 = str6;
            asar_time.setText(format10 + " - " + str7);
            String str8 = str3;
            String str9 = str4;
            magrib_time.setText(str8 + " - " + str9);
            String str10 = str2;
            String str11 = str10;
            String str12 = str5;
            isha_time.setText(str10 + " - " + str12);
            tahajjud_time.setText(format16 + " - " + format17);
            sahri.setText("সাহরী শেষ\n" + format3);
            ifter.setText("ইফতার\n" + str8);
            String str13 = format17;
            String str14 = format16;
            if (date.getTime() > date2.getTime()) {
                if (date.getTime() < date7.getTime()) {
                    indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত চলিতেছে</b>"));
                    tv_running_time.setText(Html.fromHtml("<b>শুরু- " + format3 + "<br/> শেষ- " + format8 + "</b>"));
                    tv_salat_name.setText(name_fajar.getText().toString());
                    wact_details.setText(R.string.Fajar_Details);
                    esrek.setBackgroundResource(R.drawable.button_bac_two);
                    name_esrak.setTextColor(-1);
                    esrak_time.setTextColor(-1);
                    anim_by_time.setAnimation((int) R.raw.sun_rise);
                    Context context3 = context;
                    start_count_down(context3, format8);
                    PeparationFragment.WACT_NAME = name_fajar.getText().toString();
                    PeparationFragment.WACT_PREPARATION_DETAILS = context3.getString(R.string.Fajar_Details);
                    PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context3.getString(R.string.Fajar_full_deatails);
                    return;
                }
            }
            Context context4 = context;
            if (date.getTime() > date7.getTime()) {
                if (date.getTime() < date8.getTime()) {
                    indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত আসিতেছে</b>"));
                    tv_salat_name.setText(name_esrak.getText().toString());
                    tv_salat_name.setBackgroundResource(R.drawable.button_green_aleart_bac);
                    tv_running_time.setText(Html.fromHtml("<b>শুরু- " + format14 + "<br/> শেষ- " + format15 + "</b>"));
                    tv_running_time.setTextColor(-16776961);
                    wact_details.setText(R.string.Esrak_Details);
                    esrek.setBackgroundResource(R.drawable.button_green_aleart_bac);
                    name_esrak.setTextColor(-1);
                    esrak_time.setTextColor(-1);
                    anim_by_time.setAnimation((int) R.raw.sun_rise);
                    start_count_down(context4, format14);
                    PeparationFragment.WACT_NAME = name_esrak.getText().toString();
                    PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Esrak_Details);
                    PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Esrak_full_details);
                    return;
                }
            }
            if (date.getTime() <= date8.getTime() || date.getTime() >= parse21.getTime()) {
                if (date.getTime() > parse21.getTime()) {
                    if (date.getTime() < date3.getTime()) {
                        indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত আসিতেছে</b>"));
                        tv_running_time.setText(Html.fromHtml("<b>শুরু- " + format4 + "<br/> শেষ- " + format9 + "</b>"));
                        tv_salat_name.setBackgroundResource(R.drawable.button_green_aleart_bac);
                        if (DAY.contains("Fri")) {
                            name_duhar.setText("জুম'আ");
                            wact_details.setText(R.string.Jumuah_Details);
                            PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Jumuah_Details);
                            PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Jumuah_Full_Details);
                        } else {
                            name_duhar.setText("যোহর");
                            wact_details.setText(R.string.Duhar_Details);
                            PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Duhar_Details);
                            PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Duhar_full_details);
                        }
                        anim_by_time.setAnimation((int) R.raw.sun_rise);
                        tv_salat_name.setText(name_duhar.getText().toString());
                        duhar.setBackgroundResource(R.drawable.button_green_aleart_bac);
                        name_duhar.setTextColor(-1);
                        duhar_time.setTextColor(-1);
                        start_count_down(context4, format4);
                        PeparationFragment.WACT_NAME = name_duhar.getText().toString();
                        return;
                    }
                }
                if (date.getTime() > date3.getTime() && date.getTime() < parse16.getTime()) {
                    indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত চলিতেছে</b>"));
                    tv_running_time.setText(Html.fromHtml("<b>শুরু- " + format4 + "<br/> শেষ- " + format9 + "</b>"));
                    if (DAY.contains("Fri")) {
                        name_duhar.setText("জুম'আ");
                        wact_details.setText(R.string.Jumuah_Details);
                        PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Jumuah_Details);
                        PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Jumuah_Full_Details);
                    } else {
                        name_duhar.setText("যোহর");
                        wact_details.setText(R.string.Duhar_Details);
                        PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Duhar_Details);
                        PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Duhar_full_details);
                    }
                    anim_by_time.setAnimation((int) R.raw.sun_noon);
                    tv_salat_name.setText(name_duhar.getText().toString());
                    asar.setBackgroundResource(R.drawable.button_bac_two);
                    name_asar.setTextColor(-1);
                    asar_time.setTextColor(-1);
                    start_count_down(context4, format9);
                    PeparationFragment.WACT_NAME = name_duhar.getText().toString();
                } else if (date.getTime() > date4.getTime() && date.getTime() < parse17.getTime()) {
                    indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত চলিতেছে</b>"));
                    tv_running_time.setText(Html.fromHtml("<b>শুরু- " + str + "<br/> শেষ- " + str7 + "</b>"));
                    tv_salat_name.setText(name_asar.getText().toString());
                    wact_details.setText(R.string.Asar_Details);
                    magrib.setBackgroundResource(R.drawable.button_bac_two);
                    name_magrib.setTextColor(-1);
                    magrib_time.setTextColor(-1);
                    anim_by_time.setAnimation((int) R.raw.sun_noon);
                    start_count_down(context4, str7);
                    PeparationFragment.WACT_NAME = name_asar.getText().toString();
                    PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Asar_Details);
                    PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Asar_full_details);
                } else if (date.getTime() > parse17.getTime() && date.getTime() < date5.getTime()) {
                    indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত আসিতেছে</b>"));
                    tv_salat_name.setText(name_magrib.getText().toString());
                    tv_salat_name.setBackgroundResource(R.drawable.button_green_aleart_bac);
                    tv_running_time.setText(Html.fromHtml("<b>শুরু- " + str8 + "<br/> শেষ- " + str9 + "</b>"));
                    wact_details.setText(R.string.Magrib_Details);
                    magrib.setBackgroundResource(R.drawable.button_green_aleart_bac);
                    name_magrib.setTextColor(-1);
                    magrib_time.setTextColor(-1);
                    anim_by_time.setAnimation((int) R.raw.sun_set);
                    start_count_down(context4, str8);
                    PeparationFragment.WACT_NAME = name_magrib.getText().toString();
                    PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Magrib_Details);
                    PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Magrib_full_details);
                } else if (date.getTime() > date5.getTime() && date.getTime() < parse18.getTime()) {
                    indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত চলিতেছে</b>"));
                    tv_running_time.setText(Html.fromHtml("<b>শুরু- " + str8 + "<br/> শেষ- " + str9 + "</b>"));
                    tv_salat_name.setText(name_magrib.getText().toString());
                    wact_details.setText(R.string.Magrib_Details);
                    isha.setBackgroundResource(R.drawable.button_bac_two);
                    name_isha.setTextColor(-1);
                    isha_time.setTextColor(-1);
                    anim_by_time.setAnimation((int) R.raw.night);
                    start_count_down(context4, str9);
                    PeparationFragment.WACT_NAME = name_magrib.getText().toString();
                    PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Magrib_Details);
                    PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Magrib_full_details);
                } else if (date.getTime() > date6.getTime() && date.getTime() < date9.getTime()) {
                    indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত চলিতেছে</b>"));
                    tv_running_time.setText(Html.fromHtml("<b>শুরু- " + str11 + "<br/> শেষ- " + str12 + "</b>"));
                    tv_salat_name.setText(name_isha.getText().toString());
                    wact_details.setText(R.string.Isha_Details);
                    tahajjud.setBackgroundResource(R.drawable.button_bac_two);
                    name_tahajjud.setTextColor(-1);
                    tahajjud_time.setTextColor(-1);
                    anim_by_time.setAnimation((int) R.raw.night);
                    start_count_down(context4, str12);
                    PeparationFragment.WACT_NAME = name_isha.getText().toString();
                    PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Isha_Details);
                    PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Isha_full_details);
                } else if (date.getTime() <= date9.getTime() || date.getTime() <= date11.getTime()) {
                    String str15 = str13;
                    String str16 = str14;
                    if (date.getTime() <= date10.getTime() || date.getTime() >= parse24.getTime()) {
                        tv_salat_name.setText("বিরতি");
                        wact_details.setText(R.string.Biroti_Time);
                        PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Biroti_Time);
                        PeparationFragment.wact_name.setVisibility(View.GONE);
                        return;
                    }
                    indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত চলিতেছে</b>"));
                    tv_running_time.setText(Html.fromHtml("<b>শুরু- " + str16 + "<br/> শেষ- " + str15 + "</b>"));
                    tv_salat_name.setText(name_tahajjud.getText().toString());
                    wact_details.setText(R.string.Tahajjud_Details);
                    fajar.setBackgroundResource(R.drawable.button_bac_two);
                    name_fajar.setTextColor(-1);
                    fajar_time.setTextColor(-1);
                    anim_by_time.setAnimation((int) R.raw.night);
                    start_count_down(context4, str15);
                    PeparationFragment.WACT_NAME = name_tahajjud.getText().toString();
                    PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Tahajjud_Details);
                    PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Tahajjud_full_details);
                } else {
                    indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত আসিতেছে</b>"));
                    tv_salat_name.setText(name_tahajjud.getText().toString());
                    tv_salat_name.setBackgroundResource(R.drawable.button_green_aleart_bac);
                    String str17 = str14;
                    tv_running_time.setText(Html.fromHtml("<b>শুরু- " + str17 + "<br/> শেষ- " + str13 + "</b>"));
                    wact_details.setText(R.string.Tahajjud_Details);
                    tahajjud.setBackgroundResource(R.drawable.button_green_aleart_bac);
                    name_tahajjud.setTextColor(-1);
                    tahajjud_time.setTextColor(-1);
                    anim_by_time.setAnimation((int) R.raw.night);
                    start_count_down(context4, str17);
                    PeparationFragment.WACT_NAME = name_tahajjud.getText().toString();
                    PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Tahajjud_Details);
                    PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Tahajjud_full_details);
                }
            } else {
                indicate_wact.setText(Html.fromHtml("<b>ওয়াক্ত চলিতেছে</b>"));
                tv_running_time.setText(Html.fromHtml("<b>শুরু- " + format14 + "<br/> শেষ-" + format15 + "</b>"));
                tv_salat_name.setText(name_esrak.getText().toString());
                wact_details.setText(R.string.Esrak_Details);
                duhar.setBackgroundResource(R.drawable.button_bac_two);
                name_duhar.setTextColor(-1);
                duhar_time.setTextColor(-1);
                anim_by_time.setAnimation((int) R.raw.sun_rise);
                start_count_down(context4, format15);
                PeparationFragment.WACT_NAME = name_esrak.getText().toString();
                PeparationFragment.WACT_PREPARATION_DETAILS = context4.getString(R.string.Esrak_Details);
                PeparationFragment.WACT_PREPARATION_FULL_DETAILS = context4.getString(R.string.Esrak_full_details);
            }
        } catch (Exception e) {
            Log.d("time_problem", e.toString());
        }
    }

    public static void start_count_down(Context context, final String str) {
        handler = new Handler();
        Runnable r0 = new Runnable() {
            public void run() {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                    String format = simpleDateFormat.format(Calendar.getInstance().getTime());
                    long time = simpleDateFormat.parse(str).getTime() - simpleDateFormat.parse(format).getTime();
                    MostRecentFragment.time_has.setText(Html.fromHtml("<b>" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Long.valueOf((time / 3600000) % 24)}) + " ঘন্টা " + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Long.valueOf((time / 60000) % 60)}) + " মিনিট </b>"));
                    MostRecentFragment.handler.postDelayed(this, 60000);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        runnable = r0;
        handler.post(r0);
    }
    
}
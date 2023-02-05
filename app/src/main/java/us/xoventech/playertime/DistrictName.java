package us.xoventech.playertime;

import android.content.Context;

public class DistrictName {
    public static void locationSet_data(Context context) {
        String string = MainActivity.sharedPreferences.getString("district_name", "ঢাকা*");
        MainActivity.location.setText(string);
        if (string.contains("ঢাকা*")) {
            MostRecentFragment.LOCATION = "Dhaka";
        } else if (string.contains("ফরিদপুর")) {
            MostRecentFragment.LOCATION = "Faridpur,Dhaka";
        } else if (string.contains("গাজীপুর")) {
            MostRecentFragment.LOCATION = "Gazipur,Dhaka";
        } else if (string.contains("গোপালগঞ্জ")) {
            MostRecentFragment.LOCATION = "Gopalganj,Dhaka";
        } else if (string.contains("কিশোরগঞ্জ")) {
            MostRecentFragment.LOCATION = "Kishoreganj,Dhaka";
        } else if (string.contains("মানিকগঞ্জ")) {
            MostRecentFragment.LOCATION = "Manikganj,Dhaka";
        } else if (string.contains("মুন্সীগঞ্জ")) {
            MostRecentFragment.LOCATION = "Munshiganj,Dhaka";
        } else if (string.contains("নারায়নগঞ্জ")) {
            MostRecentFragment.LOCATION = "Narayanganj,Dhaka";
        } else if (string.contains("মাদারীপুর")) {
            MostRecentFragment.LOCATION = "Madaripur,Dhaka";
        } else if (string.contains("নরসিংদী")) {
            MostRecentFragment.LOCATION = "Narsingdi,Dhaka";
        } else if (string.contains("রাজবাড়ী")) {
            MostRecentFragment.LOCATION = "Rajbari,Dhaka";
        } else if (string.contains("শরীয়তপুর")) {
            MostRecentFragment.LOCATION = "Shariatpur,Dhaka";
        } else if (string.contains("টাঙ্গাইল")) {
            MostRecentFragment.LOCATION = "Tangail,Dhaka";
        } else if (string.contains("চট্টগ্রাম*")) {
            MostRecentFragment.LOCATION = "Chattogram";
        } else if (string.contains("বান্দরবান")) {
            MostRecentFragment.LOCATION = "Bandarban,Chattogram";
        } else if (string.contains("ব্রাহ্মণবাড়িয়া")) {
            MostRecentFragment.LOCATION = "Brahmanbaria,Chattogram";
        } else if (string.contains("কুমিল্লা")) {
            MostRecentFragment.LOCATION = "Cumilla,Chattogram";
        } else if (string.contains("কক্সবাজার")) {
            MostRecentFragment.LOCATION = "Cox's Bazar,Chattogram";
        } else if (string.contains("ফেনী")) {
            MostRecentFragment.LOCATION = "Feni,Chattogram";
        } else if (string.contains("চাঁদপুর")) {
            MostRecentFragment.LOCATION = "Chandpur,Chattogram";
        } else if (string.contains("লক্ষ্ণীপুর")) {
            MostRecentFragment.LOCATION = "Lakshmipur,Chattogram";
        } else if (string.contains("খাগড়াছড়ি")) {
            MostRecentFragment.LOCATION = "Khagrachhari,Chattogram";
        } else if (string.contains("নোয়াখালী")) {
            MostRecentFragment.LOCATION = "Noakhali,Chattogram";
        } else if (string.contains("রাঙ্গামাটি")) {
            MostRecentFragment.LOCATION = "Rangamati,Chattogram";
        } else if (string.contains("রাজশাহী*")) {
            MostRecentFragment.LOCATION = "Rajshahi";
        } else if (string.contains("বগুড়া")) {
            MostRecentFragment.LOCATION = "Bogura,Rajshahi";
        } else if (string.contains("নওগা")) {
            MostRecentFragment.LOCATION = "Naogaon,Rajshahi";
        } else if (string.contains("জয়পুরহাট")) {
            MostRecentFragment.LOCATION = "Joypurhat,Rajshahi";
        } else if (string.contains("নাটোর")) {
            MostRecentFragment.LOCATION = "Natore,Rajshahi";
        } else if (string.contains("পাবনা")) {
            MostRecentFragment.LOCATION = "Pabna,Rajshahi";
        } else if (string.contains("চাঁপাই নবাবগঞ্জ")) {
            MostRecentFragment.LOCATION = "Chapai Nawabganj,Rajshahi";
        } else if (string.contains("সিরাজগঞ্জ")) {
            MostRecentFragment.LOCATION = "Sirajganj,Rajshahi";
        } else if (string.contains("সিলেট*")) {
            MostRecentFragment.LOCATION = "Sylhet";
        } else if (string.contains("হবিগঞ্জ")) {
            MostRecentFragment.LOCATION = "Habiganj,Sylhet";
        } else if (string.contains("সুনামগঞ্জ")) {
            MostRecentFragment.LOCATION = "Sunamganj,Sylhet";
        } else if (string.contains("মৌলভীবাজার")) {
            MostRecentFragment.LOCATION = "Moulvibazar,Sylhet";
        } else if (string.contains("বরিশাল*")) {
            MostRecentFragment.LOCATION = "Barishal";
        } else if (string.contains("বরগুনা")) {
            MostRecentFragment.LOCATION = "Barguna,Barishal";
        } else if (string.contains("ভোলা")) {
            MostRecentFragment.LOCATION = "Bhola,Barishal";
        } else if (string.contains("পটুয়াখালী")) {
            MostRecentFragment.LOCATION = "Patuakhali,Barishal";
        } else if (string.contains("পিরোজপুর")) {
            MostRecentFragment.LOCATION = "Pirojpur,Barishal";
        } else if (string.contains("ঝালকাঠি")) {
            MostRecentFragment.LOCATION = "Jhalokati,Barishal";
        } else if (string.contains("রংপুর*")) {
            MostRecentFragment.LOCATION = "Rangpur";
        } else if (string.contains("দিনাজপুর")) {
            MostRecentFragment.LOCATION = "Dinajpur,Rangpur";
        } else if (string.contains("কুড়িগ্রাম")) {
            MostRecentFragment.LOCATION = "Kurigram,Rangpur";
        } else if (string.contains("গাইবান্ধা")) {
            MostRecentFragment.LOCATION = "Gaibandha,Rangpur";
        } else if (string.contains("নীলফামারী")) {
            MostRecentFragment.LOCATION = "Nilphamari,Rangpur";
        } else if (string.contains("লালমনিরহাট")) {
            MostRecentFragment.LOCATION = "Lalmonirhat,Rangpur";
        } else if (string.contains("ঠাকুরগাঁও")) {
            MostRecentFragment.LOCATION = "Thakurgaon,Rangpur";
        } else if (string.contains("পঞ্চগড়")) {
            MostRecentFragment.LOCATION = "Panchagarh,Rangpur";
        } else if (string.contains("খুলনা*")) {
            MostRecentFragment.LOCATION = "Khulna";
        } else if (string.contains("বাগেরহাট")) {
            MostRecentFragment.LOCATION = "Bagerhat,Khulna";
        } else if (string.contains("চুয়াডাঙ্গা")) {
            MostRecentFragment.LOCATION = "Chuadanga,Khulna";
        } else if (string.contains("যশোর")) {
            MostRecentFragment.LOCATION = "Jashore,Khulna";
        } else if (string.contains("কুষ্টিয়া")) {
            MostRecentFragment.LOCATION = "Kushtia,Khulna";
        } else if (string.contains("মাগুরা")) {
            MostRecentFragment.LOCATION = "Magura,Khulna";
        } else if (string.contains("ঝিনাইদহ")) {
            MostRecentFragment.LOCATION = "Jhenaidah,Khulna";
        } else if (string.contains("মেহেরপুর")) {
            MostRecentFragment.LOCATION = "Meherpur,Khulna";
        } else if (string.contains("সাতক্ষিরা")) {
            MostRecentFragment.LOCATION = "Satkhira,Khulna";
        } else if (string.contains("নড়াইল")) {
            MostRecentFragment.LOCATION = "Narail,Khulna";
        } else if (string.contains("ময়মনসিংহ*")) {
            MostRecentFragment.LOCATION = "Mymensingh";
        } else if (string.contains("জামালপুর")) {
            MostRecentFragment.LOCATION = "Jamalpur,Mymensingh";
        } else if (string.contains("শেরপুর")) {
            MostRecentFragment.LOCATION = "Sherpur,Mymensingh";
        } else if (string.contains("নেত্রকোনা")) {
            MostRecentFragment.LOCATION = "Netrokona,Mymensingh";
        }
    }
}

package us.xoventech.playertime;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewpagerAdapter extends FragmentStateAdapter {
    public int getItemCount() {
        return 2;
    }

    public MyViewpagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public Fragment createFragment(int i) {
        if (i == 0) {
            return new MostRecentFragment();
        }
        if (i != 1) {
            return new MostRecentFragment();
        }
        return new PeparationFragment();
    }
}

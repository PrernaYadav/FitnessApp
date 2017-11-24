package com.fluidsoft.fluidsoft.tgconnect;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Dell2 on 9/5/2017.
 */

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;
    public Pager(FragmentManager fm,int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new ArticleFragment();
            case 1 : return new VideoFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

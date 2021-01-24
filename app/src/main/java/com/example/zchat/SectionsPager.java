package com.example.zchat;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsPager extends FragmentPagerAdapter {

    public SectionsPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                RequestFragment requestFragment = new RequestFragment();
                return requestFragment;
            case 1:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;
            case 2:
                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}

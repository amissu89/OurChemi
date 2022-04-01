package com.example.ourchemi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Adapter extends FragmentStateAdapter {

    private List<Fragment> fragList = new ArrayList<>();

    private ShowResultFragment showResultFragment;
    private BigImageFragment bigImageFragment;
    private WebViewFragment webViewFragment;
    private BlankFragment blankFragment;

     public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
         showResultFragment = new ShowResultFragment();
         fragList.add(showResultFragment);

         bigImageFragment = new BigImageFragment();
         fragList.add(bigImageFragment);

         webViewFragment = new WebViewFragment();
         fragList.add(webViewFragment);

         blankFragment = new BlankFragment();
         fragList.add(blankFragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
         System.out.println("pos : " + position);
         return fragList.get(position);
    }

    public Fragment getFragment(int position)
    {
        return fragList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragList.size();
    }
}

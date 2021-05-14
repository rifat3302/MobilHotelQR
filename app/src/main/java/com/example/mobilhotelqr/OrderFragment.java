package com.example.mobilhotelqr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobilhotelqr.Fragment.FragmentOrderDrink;
import com.example.mobilhotelqr.Fragment.FragmentOrderMeat;
import com.example.mobilhotelqr.Fragment.FragmentOrderSnack;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class OrderFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    View mView;

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private ArrayList<String> baslikListesi = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_order,container,false);

        tabLayout = mView.findViewById(R.id.tablayout);
        viewPager2 = mView.findViewById(R.id.viewPager2);

        fragmentArrayList.add(new FragmentOrderMeat());
        fragmentArrayList.add(new FragmentOrderDrink());
        fragmentArrayList.add(new FragmentOrderSnack());

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this.getActivity());

        viewPager2.setAdapter(myViewPagerAdapter);

        baslikListesi.add("");
        baslikListesi.add("");
        baslikListesi.add("");

        new TabLayoutMediator(tabLayout,viewPager2,
                (tab,position)->tab.setText(baslikListesi.get(position))).attach();

        tabLayout.getTabAt(0).setIcon(R.drawable.barbecue);
        tabLayout.getTabAt(1).setIcon(R.drawable.softdrink);
         tabLayout.getTabAt(2).setIcon(R.drawable.snack);




        return mView;

    }

    private class MyViewPagerAdapter extends FragmentStateAdapter{

        public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentArrayList.size();
        }
    }

}

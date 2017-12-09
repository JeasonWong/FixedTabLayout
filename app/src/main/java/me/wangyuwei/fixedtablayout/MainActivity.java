package me.wangyuwei.fixedtablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout vTabLayout;
    private ViewPager vViewPager;

    private MyPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        vViewPager = (ViewPager) findViewById(R.id.view_pager);

        mPagerAdapter = new MyPagerAdapter(this);
        vViewPager.setAdapter(mPagerAdapter);
        vTabLayout.setupWithViewPager(vViewPager);


    }


    public static class MyPagerAdapter extends PagerAdapter {

        private List<Pair<View, String>> mList = new ArrayList<>();

        public MyPagerAdapter(Context context) {
            int[] colors = context.getResources().getIntArray(R.array.colors);

            for (int i = 0, length = colors.length; i < length; i++) {
                View view = new View(context);
                view.setBackgroundColor(colors[i]);
                mList.add(new Pair<>(view, "item " + i));
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mList.get(position).first;
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position).first);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).second;
        }
    }


}

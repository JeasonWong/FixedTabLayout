package me.wangyuwei.fixedtablayout;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

import java.lang.reflect.Field;

import static android.support.v4.view.ViewPager.SCROLL_STATE_DRAGGING;
import static android.support.v4.view.ViewPager.SCROLL_STATE_IDLE;

public class FixedTabLayout extends TabLayout {
    public FixedTabLayout(Context context) {
        this(context, null);
    }

    public FixedTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FixedTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        try {
            Field field = TabLayout.class.getDeclaredField("mPageChangeListener");
            field.setAccessible(true);
            field.set(this, new FixedTabLayoutOnPageChangeListener(this));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static class FixedTabLayoutOnPageChangeListener
            extends TabLayout.TabLayoutOnPageChangeListener {

        private boolean isTouchState;

        public FixedTabLayoutOnPageChangeListener(TabLayout tabLayout) {
            super(tabLayout);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
            if (state == SCROLL_STATE_DRAGGING) {
                isTouchState = true;
            } else if (state == SCROLL_STATE_IDLE) {
                isTouchState = false;
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (isTouchState) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }
    }

}

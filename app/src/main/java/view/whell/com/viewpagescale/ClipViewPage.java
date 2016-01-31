package view.whell.com.viewpagescale;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/1/31 0031.
 */
public class ClipViewPage extends ViewPager {
    public ClipViewPage(Context context) {
        super(context);
    }

    public ClipViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            int index=viewOfClickOnScreenInt(ev);
           if(index!=getCurrentItem()){
                setCurrentItem(index);
           }
        }

        return super.dispatchTouchEvent(ev);
    }

    /**
     * @param ev
     * @return
     */
    private View viewOfClickOnScreen(MotionEvent ev) {
        int childCount = getChildCount();
        int[] location = new int[2];
        for (int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            v.getLocationOnScreen(location);
            int minX = location[0];
            int minY = getTop();

            int maxX = location[0] + v.getWidth();
            int maxY = getBottom();

            float x = ev.getX();
            float y = ev.getY();

            if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                return v;
            }
        }
        return null;
    }

    private int viewOfClickOnScreenInt(MotionEvent ev) {
        int index = getCurrentItem();
        int[] location = new int[2];

        this.getLocationOnScreen(location);
        int x= (int) ev.getX();

        if(x<location[0]){
            index=index-1;
        }else if(x>location[0]+getWidth()){
            index=index+1;
        }

        return index;
    }

}

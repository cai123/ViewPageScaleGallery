package view.whell.com.viewpagescale;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> imageViews;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= (ClipViewPage) findViewById(R.id.viewpage);
        imageViews=new ArrayList<View>();
        context=this;
        for (int i=0;i<5;i++){
            ImageView imageView=new ImageView(this);
            imageViews.add(imageView);
        }


        findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });
        viewPager.setOffscreenPageLimit(3);

        viewPager.setPageTransformer(true, new ZoomPageTransformer());
        //viewPager.setPageMargin(20);
        viewPager.setAdapter(new MyViewPageAdapter());

    }

    class MyViewPageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.e("MainActivity", imageViews.size() + "instantiateItem");
            ImageView imageView= (ImageView) imageViews.get(position);
          //  imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(R.mipmap.welcome);
            container.addView(imageViews.get(position));
            return imageViews.get(position);        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.e("MainActivity",imageViews.size()+"destroyItem");
            container.removeView(imageViews.get(position));
        }

        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth(position);
        }
    }
}

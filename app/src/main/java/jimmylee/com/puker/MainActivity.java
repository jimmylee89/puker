package jimmylee.com.puker;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<View> views = new ArrayList<>();
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager= (ViewPager) findViewById(R.id.vp);
        Button btnReset = (Button) findViewById(R.id.btn_reset);
        Button btnFirst = (Button) findViewById(R.id.btn_first);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setAdapter(adapter= new ViewPagerAdapter(views));
                setViews();
            }
        });

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        viewPager.setAdapter(adapter= new ViewPagerAdapter(views));
        setViews();
    }

    private void setViews(){
        views.clear();
        ImageView imageView;
        int resId = 0;
        for (int i = 3; i <= 54; i++) {
            try {
                Field field = R.mipmap.class.getDeclaredField("image_" + i);
                resId = Integer.parseInt(field.get(null).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            imageView = (ImageView) View.inflate(this, R.layout.item_activity_main, null);
            imageView.setImageResource(resId);
            views.add(imageView);
        }
        Collections.shuffle(views);
        adapter.notifyDataSetChanged();
    }

    private class ViewPagerAdapter extends PagerAdapter {
        private List<View> list;

        ViewPagerAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position), 0);

            return list.get(position);
        }
    }


}

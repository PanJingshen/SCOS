package es.source.code.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodView extends Activity {

    private ViewPager pager;
    private View view_colddish, view_hotdish, view_seafood, view_drink;
    private PagerTabStrip tabStrip;
    private ArrayList<View> viewContainer;
    private ArrayList<String> titleContainer;
    private LayoutInflater inflater;
    private PagerAdapter pagerAdapter;
    private int[] tilteDrawbleList= {R.drawable.colddish, R.drawable.hotdish,
            R.drawable.seafood, R.drawable.drink};

    private ListView listView_colddish, listView_hotdish, listView_seafood, listView_drink;
    private TextView tv_dishname, tv_price;
    private SimpleAdapter adapter_list_colddish,
            adapter_list_hotdish, adapter_list_seafood, adapter_list_drink;
    private ArrayList<HashMap<String, Object>> food_item_list;
    //Test data
    private String[] dishname = {"黄焖鸡", "啤酒鸭", "水煮鱼", "红烧肉"};
    private int[] price = {20,30,40,50};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);

        initViewPager();
        initListView();

    }

    /**
     * 界面初始化
     */
    private void initViewPager(){
        pager = (ViewPager) findViewById(R.id.viewpager);
        tabStrip = (PagerTabStrip) findViewById(R.id.tabstrip);
        viewContainer = new ArrayList<View>();
        titleContainer = new ArrayList<String>();

        inflater = getLayoutInflater();
        view_colddish = inflater.inflate(R.layout.food_view_colddish, null);
        view_hotdish = inflater.inflate(R.layout.food_view_hotdish, null);
        view_seafood = inflater.inflate(R.layout.food_view_seafood, null);
        view_drink = inflater.inflate(R.layout.food_view_drink, null);
        viewContainer.add(view_colddish);
        viewContainer.add(view_hotdish);
        viewContainer.add(view_seafood);
        viewContainer.add(view_drink);


        //取消tab下面的长横线
        tabStrip.setDrawFullUnderline(false);
        //设置当前tab页标签的下滑线颜色
        tabStrip.setTabIndicatorColorResource(R.color.colorPrimaryDark);
        tabStrip.setTextSpacing(0);
        //设置标题栏字体大小，第一个参数为“单位”，如sp（字体常用）
        tabStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
        titleContainer.add("冷菜");
        titleContainer.add("热菜");
        titleContainer.add("海鲜");
        titleContainer.add("酒水");


        pagerAdapter = new PagerAdapter() {

            /**
             * 返回要滑动的View的个数
             * @return
             */
            @Override
            public int getCount() {
                return viewContainer.size();
            }

            /**
             *
             * @param view
             * @param object
             * @return
             */

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            /**
             * 从当前container中删除指定位置（position）的View
             * @param container
             * @param position
             * @param object
             */
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewContainer.get(position));
            }

            /**
             * 将当前视图添加到container中
             * 返回当前View
             * @param container
             * @param position
             * @return
             */
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewContainer.get(position));
                return viewContainer.get(position);
            }

            /**
             * 根据position设置当前视图的title
             * @param position
             * @return
             */
            @Override
            public CharSequence getPageTitle(int position) {
                SpannableStringBuilder ssb = new SpannableStringBuilder(" "+titleContainer.get(position));
                Drawable titleDrawble = getResources().getDrawable(tilteDrawbleList[position]);
                titleDrawble.setBounds(0, 0, titleDrawble.getIntrinsicWidth(),
                        titleDrawble.getIntrinsicHeight());
                ImageSpan span = new ImageSpan(titleDrawble, ImageSpan.ALIGN_BASELINE);
                //字体颜色
                ForegroundColorSpan fcs = new ForegroundColorSpan(Color.WHITE);


                //设置图标
                ssb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //设置字体颜色
                ssb.setSpan(fcs, 1, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssb.setSpan(new RelativeSizeSpan(1.2f), 1, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                return ssb;
            }
        };


        pager.setAdapter(pagerAdapter);


    }



    private void initListView(){
        //不能直接用findViewById,因为this不一样
        listView_colddish = (ListView) view_colddish.findViewById(R.id.list_colddish);
        listView_hotdish = (ListView) view_hotdish.findViewById(R.id.list_hotdish);
        listView_seafood = (ListView) view_seafood.findViewById(R.id.list_seafood);
        listView_drink = (ListView) view_drink.findViewById(R.id.list_drink);
        food_item_list = new ArrayList<HashMap<String, Object>>();

        //装载数据
        for(int i=0;i<dishname.length;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("item_dishname", dishname[i]);
            map.put("item_price", price[i]);
            food_item_list.add(map);
        }

        String[] from = {"item_dishname", "item_price"};
        int[] to = {R.id.tx_dishname, R.id.tx_price};
        adapter_list_colddish = new SimpleAdapter(this,
                food_item_list,R.layout.food_view_item, from, to);
        adapter_list_hotdish = new SimpleAdapter(this,
                food_item_list,R.layout.food_view_item, from, to);
        adapter_list_seafood = new SimpleAdapter(this,
                food_item_list,R.layout.food_view_item, from, to);
        adapter_list_drink = new SimpleAdapter(this,
                food_item_list,R.layout.food_view_item, from, to);
        listView_colddish.setAdapter(adapter_list_colddish);
        listView_hotdish.setAdapter(adapter_list_hotdish);
        listView_seafood.setAdapter(adapter_list_seafood);
        listView_drink.setAdapter(adapter_list_drink);
    }
}

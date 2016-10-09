package es.source.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainScreen extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btn_login;
    private Button btn_orderlist;
    private Button btn_order;
    private GridView gview;
    private ArrayList<HashMap<String, Object>> item_list;
    private SimpleAdapter adapter;
    private int[] icon = {R.drawable.login, R.drawable.order_add, R.drawable.order_list}; //item图片
    private String[] iconName = {"登录", "点单", "菜单列表"};  //item名称

    public static String ACTION = "es.source.code.activity.intent.action.MainScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);


        Intent i = getIntent();
        btn_login= (Button) findViewById(R.id.login);
        btn_order= (Button) findViewById(R.id.order);
        btn_orderlist= (Button) findViewById(R.id.orderlist);
        gview= (GridView) findViewById(R.id.gridview);



        item_list= new ArrayList<HashMap<String, Object>>();
        //获取Item数据
        getData();
        String[] from = {"item_image", "item_text"};
        int[] to = {R.id.itemImage, R.id.itemName};
        adapter= new SimpleAdapter(this, item_list, R.layout.mainscreen_grid_item, from, to);
        gview.setAdapter(adapter);
        gview.setOnItemClickListener(this);


        //建立Activity时，处理相关消息
        processExtraData();






        btn_login.setOnClickListener(this);

    }

    /**
     * 更新传过来的Intent，并处理相关参数
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        processExtraData();
    }

    /**
     * 列表获取Item数据
     *
     */
    private void getData(){
        for(int i=0;i<icon.length;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("item_image", icon[i]);
            map.put("item_text", iconName[i]);
            item_list.add(map);
        }
    }

    /**
     * 处理Intent消息
     */
    private void processExtraData(){
        Intent i =getIntent();
        switch (getIntent().getStringExtra("toMainScreen")){
            case "LoginSuccess":
                btn_order.setVisibility(View.VISIBLE);
                btn_orderlist.setVisibility(View.VISIBLE);
                btn_login.setVisibility(View.GONE);
                break;
            case "FromEntry":
                    btn_login.setVisibility(View.VISIBLE);
                    btn_order.setVisibility(View.INVISIBLE);
                btn_orderlist.setVisibility(View.INVISIBLE);
                break;
            case "Return":
                btn_login.setVisibility(View.VISIBLE);
                btn_order.setVisibility(View.INVISIBLE);
                btn_orderlist.setVisibility(View.INVISIBLE);
                break;
            case "":

                break;
        }
    }

    /**
     * Button点击处理
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
            startActivity(new Intent(MainScreen.this, LoginOrRegister.class));
                break;
            case R.id.order:

                break;
            case R.id.orderlist:

                break;
        }


    }

    /**
     * 点击gridView中Item时的事件
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (icon[i]){
            case R.drawable.login:
                startActivity(new Intent(MainScreen.this, LoginOrRegister.class));
                break;
            case R.drawable.order_add:

                break;
            case R.drawable.order_list:

                break;
        }
    }
}

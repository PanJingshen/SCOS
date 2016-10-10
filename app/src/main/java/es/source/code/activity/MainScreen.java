package es.source.code.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.source.code.model.User;

public class MainScreen extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private GridView gview;
    private ArrayList<HashMap<String, Object>> item_list;
    private SimpleAdapter adapter;
    private int[] icon = {R.drawable.login, R.drawable.order_add, R.drawable.order_list}; //item图片
    private String[] iconName = {"登录", "点单", "菜单列表"};  //item名称
    private User user;
    //0未登录；1登录
    private static boolean  islogin = false;

    public static String ACTION = "es.source.code.activity.intent.action.MainScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);

        Intent i = getIntent();
        gview= (GridView) findViewById(R.id.gridview);

        item_list= new ArrayList<HashMap<String, Object>>();

        //建立Activity时，处理相关消息
        processIntent();


        String[] from = {"item_image", "item_text"};
        int[] to = {R.id.itemImage, R.id.itemName};
        adapter= new SimpleAdapter(this, item_list, R.layout.mainscreen_grid_item, from, to);
        gview.setAdapter(adapter);
        gview.setOnItemClickListener(this);





    }

    /**
     * 更新传过来的Intent，并处理相关参数
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        processIntent();
    }

    /**
     * 列表获取Item数据
     *
     */
    private void getData(int start, int end){
        for(int i=start;i<=end;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("item_image", icon[i]);
            map.put("item_text", iconName[i]);
            item_list.add(map);
        }
    }

    /**
     * 处理Intent消息
     */
    private void processIntent(){
        Intent i =getIntent();
        switch (getIntent().getStringExtra("toMainScreen")){
            case "LoginSuccess":
                login(i);
                break;
            case "FromEntry":
                entry(i);
                break;
            case "Return":
                back(i);
                break;
            case "RegisterSuccess":
                register(i);
                break;
            case "":

                break;
            default:
                user = null;

        }
    }

    /**
     * 处理返回事件
     * @param i
     */
    private void back(Intent i) {
        islogin = false;

    }

    /**
     * 处理entry事件
     * @param i
     */
    private void entry(Intent i) {
        islogin = false;
        //获取Item数据
        getData(0,0);
    }

    /**
     * 处理登录事件
     * @param intent
     */
    private void login(Intent intent){
        user = (User) intent.getSerializableExtra("user");
        islogin = true;
        //重置gridview数据
        item_list.removeAll(item_list);
        getData(1,2);
        //adapter.getView(0,)
        adapter.notifyDataSetChanged();

    }


    /**
     * 处理注册事件
     * @param intent
     */
    private void register(Intent intent){
        login(intent);
        Toast.makeText(this, "欢迎您成为SCOS新用户", Toast.LENGTH_SHORT).show();

    }



    /**
     * Button点击处理
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }


    }

    /**
     * 点击gridView中Item时的事件
     * @param adapterView
     * @param view
     * @param i item[]的下标
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(!islogin){
            switch (i){
                //未登录时，此时0位置为登录
                case 0:
                    startActivity(new Intent(MainScreen.this, LoginOrRegister.class));
                    break;
            }
        }
        else {
            switch (i){
                //登录时，此时0位置为点单
                case 0:
                    startActivity(new Intent(MainScreen.this, FoodView.class));
                    break;
                //登录时，此时1位置为查看菜单
                case 1:

                    break;
            }
        }

    }
}



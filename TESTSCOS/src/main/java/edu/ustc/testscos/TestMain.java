package edu.ustc.testscos;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestMain extends Activity {
    private Button btn_startSCOSMain,btn_startSCOSEntry;
    private Intent intent_SCOSEntry, intent_SCOSMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        btn_startSCOSEntry = (Button) findViewById(R.id.btnStartSCOSEntry);
        btn_startSCOSMain = (Button) findViewById(R.id.btnStartSCOSMain);

        /**
         * 隐式调用只能调用对方入口Activity
         * 相关资料：http://www.cnblogs.com/lijunamneg/archive/2013/02/26/2934060.html
         */
        intent_SCOSEntry = new Intent("es.source.code.activity.intent.action.SCOSEntry");
        btn_startSCOSEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_SCOSEntry);
            }
        });


        /*intent_SCOSMain = new Intent("es.source.code.activity.intent.action.MainScreen");
        btn_startSCOSMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_SCOSMain);
            }
        });*/

        /**
         * 可以直接调用非入口Activity
         * 参考资料：http://www.linuxidc.com/Linux/2012-09/70379.htm
         */
        intent_SCOSMain = new Intent("es.source.code.activity.intent.action.MainScreen");
        intent_SCOSMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent_SCOSMain.putExtra("toMainScreen","");
        ComponentName cn=new ComponentName("es.source.code.activity","es.source.code.activity.MainScreen");
        intent_SCOSMain.setComponent(cn);
        btn_startSCOSMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_SCOSMain);
            }
        });






    }
}

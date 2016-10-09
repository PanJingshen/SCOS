package es.source.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.*;

public class LoginOrRegister extends AppCompatActivity implements View.OnClickListener {

    private EditText et_username,et_password;
    private Button btn_login, btn_register, btn_getback;
    private Intent intentMainScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_register);

        et_username= (EditText)findViewById(R.id.username);
        et_password= (EditText)findViewById(R.id.password);
        btn_login= (Button)findViewById(R.id.login);
        btn_register= (Button)findViewById(R.id.register);
        btn_getback= (Button)findViewById(R.id.btnBack);

        intentMainScreen=new Intent(LoginOrRegister.this, MainScreen.class);

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_getback.setOnClickListener(this);
    }

    /**
     * 正则表达式验证用户名
     * ^[a-z0-9_-]{3,15}$
     * ^                         # 行开始
     * [a-z0-9_-]                # 匹配列表中的字符，a-z,0–9,下划线，连字符
     * {3,15}                    # 长度至少3个字符，最大长度为15
     * $                         # 行结束
     * @param username
     * @return
     */
    private boolean checkUsername(String username){
        String regex = "^[a-z0-9A-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(username);
        return m.matches();
    }


    /**
     * 正则表达式验证密码
     *((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})
     * (                        # 组开始
     *     (?=.*\d)             # 必须包含一个数字 0-9
     *     (?=.*[a-z])          # 必须包含一个小写字符
     *     (?=.*[A-Z])          # 必须包含一个大写字符
     *     (?=.*[@#$%])         # 必须包含一个列表中的特殊字符"@#$%"
     *                 .        # 检查所有字符串与前面的条件的匹配
     *                  {6,20}  # 长度至少为6个字符，最大长度为20
     * )                        # 组结束
     * @param password
     * @return
     */
    private boolean checkPassword(String password){
        String regex = "((?=.*\\d)(?=.*[a-zA-Z]).{6,20})";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    /**
     * 字符串匹配输出
     * @param type
     */
    private void setError(int type){
        String errortext="不正确";
        switch (type){
            case 0:
                errortext = "用户名"+errortext;
                break;
            case 1:
                errortext = "密码"+errortext;
                break;
        }
        Toast.makeText(LoginOrRegister.this, errortext, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                if(!checkUsername(et_username.getText().toString()))
                    setError(0);
                else if(!checkPassword(et_password.getText().toString()))
                    setError(1);
                else{

                    intentMainScreen.putExtra("toMainScreen","LoginSuccess");
                    startActivity(intentMainScreen);
                }
                break;
            case R.id.register:

                break;
            case R.id.btnBack:
                intentMainScreen.putExtra("toMainScreen","Return");
                startActivity(intentMainScreen);
                break;

        }
    }
}

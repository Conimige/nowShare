package nowshare.myzchh.cn.nowshare;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nowshare.myzchh.cn.nowshare.util.BaseActivity;
import nowshare.myzchh.cn.nowshare.util.localUser;


public class RegistActivity extends BaseActivity {

    private ImageView button_login;
    private ImageView button_login_shadow;
    private EditText textbox_user;
    private EditText textbox_password;
    private EditText textbox_password2;
    private EditText text_mail;
    private Button textbox_birthday;
    private EditText textbox_nickname;
    private RadioButton radio_men;
    private RadioButton radio_women;
    private EditText text_phone;
    private RadioGroup genderGroup;
    private TextView lb_gender;
    private ImageView button_Back;
    private TextView text_registing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);


        button_login=(ImageView)findViewById(R.id.button_login);
        button_login_shadow=(ImageView)findViewById(R.id.button_login_shadow);
        textbox_user=(EditText)findViewById(R.id.textbox_user);
        textbox_password=(EditText)findViewById(R.id.textbox_password);
        textbox_password2=(EditText)findViewById(R.id.textbox_password2);
        textbox_birthday=(Button)findViewById(R.id.textbox_birthday);
        textbox_nickname=(EditText)findViewById(R.id.textbox_nickname);
        radio_men=(RadioButton)findViewById(R.id.radio_men);
        radio_women=(RadioButton)findViewById(R.id.radio_women);
        text_mail=(EditText)findViewById(R.id.text_mail);
        text_phone=(EditText)findViewById(R.id.text_phone);
        genderGroup=(RadioGroup)findViewById(R.id.genderGroup);
        lb_gender=(TextView)findViewById(R.id.lb_gender);
        button_Back=(ImageView)findViewById(R.id.button_Back);
        text_registing=(TextView)findViewById(R.id.text_registing);


        Animation aIn = new AlphaAnimation(1f, 0f);
        aIn.setDuration(1);
        aIn.setFillAfter(true);
        button_login_shadow.startAnimation(aIn);

        Animation aIn1 = new AlphaAnimation(0f, 1f);
        aIn1.setDuration(800);
        aIn1.setFillAfter(true);
        aIn1.setStartOffset(200);
        textbox_user.startAnimation(aIn1);
        Animation aIn2 = new AlphaAnimation(0f, 1f);
        aIn2.setDuration(800);
        aIn2.setFillAfter(true);
        aIn2.setStartOffset(400);
        textbox_password.startAnimation(aIn2);
        Animation aIn3 = new AlphaAnimation(0f, 1f);
        aIn3.setDuration(800);
        aIn3.setFillAfter(true);
        aIn3.setStartOffset(600);
        textbox_password2.startAnimation(aIn3);
        Animation aIn4 = new AlphaAnimation(0f, 1f);
        aIn4.setDuration(800);
        aIn4.setFillAfter(true);
        aIn4.setStartOffset(800);
        textbox_birthday.startAnimation(aIn4);
        Animation aIn5 = new AlphaAnimation(0f, 1f);
        aIn5.setDuration(800);
        aIn5.setFillAfter(true);
        aIn5.setStartOffset(1000);
        textbox_nickname.startAnimation(aIn5);
        Animation aIn6 = new AlphaAnimation(0f, 1f);
        aIn6.setDuration(800);
        aIn6.setFillAfter(true);
        aIn6.setStartOffset(1200);
        lb_gender.startAnimation(aIn6);
        genderGroup.startAnimation(aIn6);
        Animation aIn7 = new AlphaAnimation(0f, 1f);
        aIn7.setDuration(800);
        aIn7.setFillAfter(true);
        aIn7.setStartOffset(1400);
        text_mail.startAnimation(aIn7);
        Animation aIn8 = new AlphaAnimation(0f, 1f);
        aIn8.setDuration(800);
        aIn8.setFillAfter(true);
        aIn8.setStartOffset(1600);
        text_phone.startAnimation(aIn8);

        button_login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.i("sd","ff");
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Animation aIn = new AlphaAnimation(0f, 1f);
                    aIn.setDuration(500);
                    aIn.setFillAfter(true);
                    button_login_shadow.startAnimation(aIn);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Animation aIn = new AlphaAnimation(1f, 0f);
                    aIn.setDuration(200);
                    aIn.setFillAfter(true);
                    button_login_shadow.startAnimation(aIn);
                }
                return false;
            }
        });

        textbox_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textbox_birthday.setTextColor(getResources().getColor(R.color.hint_foreground_material_light));
                textbox_birthday.setText("��������");
                //������ȡ���ں�ʱ���
                Calendar calendar = Calendar.getInstance();
                Dialog dialog = null;
                DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        //EditText editText =(EditText) findViewById(R.id.editText);
                        //Calendar�·��Ǵ�0��ʼ,����monthҪ��1
                        textbox_birthday.setText("" + year + "-" + (month + 1) + "-" + dayOfMonth + "");
                    }
                };
                dialog = new DatePickerDialog(RegistActivity.this, dateListener, 1990, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��һ���жϣ�ȷ�������Ƿ���ȷ
                if (textbox_password.getText().toString().equals(textbox_password2.getText().toString())) {
                    //�ڶ����жϣ��Ƿ�ѡ��������
                    if (!textbox_birthday.getText().toString().equals("��������") && !textbox_birthday.getText().toString().equals("��ѡ���������")) {
                        //�������жϣ���ֵУ��
                        if (!textbox_user.getText().toString().equals("")){
                            if (!textbox_password.getText().toString().equals("")){
                                if ((textbox_password.getText().toString().length()>=6)) {
                                    if (!textbox_nickname.getText().toString().equals("")) {
                                        startRegist();
                                    } else {
                                        textbox_nickname.setHintTextColor(getResources().getColor(R.color.color_error));
                                        textbox_nickname.setHint("�ǳƲ���Ϊ��");
                                    }
                                }else{
                                    textbox_password.setText("");
                                    textbox_password2.setText("");
                                    textbox_password.setHintTextColor(getResources().getColor(R.color.color_error));
                                    textbox_password.setHint("���벻��С����λ");
                                }
                            }else{
                                textbox_password.setHintTextColor(getResources().getColor(R.color.color_error));
                                textbox_password.setHint("���벻��Ϊ��");
                            }
                        }else{
                            textbox_user.setHintTextColor(getResources().getColor(R.color.color_error));
                            textbox_user.setHint("�û�������Ϊ��");
                        }
                    } else {
                        textbox_birthday.setText("��ѡ���������");
                        textbox_birthday.setTextColor(getResources().getColor(R.color.color_error));
                    }
                } else {
                    textbox_password2.setText("");
                    textbox_password2.setHintTextColor(getResources().getColor(R.color.color_error));
                    textbox_password2.setHint("ȷ���������");
                }
            }
        });

        textbox_password2.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    textbox_password2.setHintTextColor(getResources().getColor(R.color.hint_foreground_material_light));
                    textbox_password2.setHint("ȷ������");
                }
            }
        });
        textbox_user.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    textbox_user.setHintTextColor(getResources().getColor(R.color.hint_foreground_material_light));
                    textbox_user.setHint("�û���");
                }
            }
        });
        textbox_password.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    textbox_password.setHintTextColor(getResources().getColor(R.color.hint_foreground_material_light));
                    textbox_password.setHint("����");
                }
            }
        });
        textbox_nickname.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    textbox_nickname.setHintTextColor(getResources().getColor(R.color.hint_foreground_material_light));
                    textbox_nickname.setHint("�ǳ�");
                }
            }
        });

        button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void startRegist(){
        button_login.setEnabled(false);
        text_registing.setText("���ڽ���ע��...");

        Animation aIn = new AlphaAnimation(1f, 0f);
        aIn.setDuration(300);
        aIn.setFillAfter(true);
        button_login.startAnimation(aIn);
        button_login_shadow.startAnimation(aIn);

        Animation aIn1 = new AlphaAnimation(0f, 1f);
        aIn1.setDuration(300);
        aIn1.setFillAfter(true);
        text_registing.startAnimation(aIn1);

        //��ʼ��¼
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... params) {
                String url = "http://sanjin6035.vicp.cc/Weibo/user/register";
                String result = "";
                try {
                    //��������
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost post = new HttpPost(url);
                    //���ò�������html���ύ
                    String gender="��";
                    if (radio_men.isChecked()){
                        gender="��";
                    }else{
                        gender="Ů";
                    }

                    List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                    BasicNameValuePair param = new BasicNameValuePair("uusername", textbox_user.getText().toString());
                    BasicNameValuePair param2 = new BasicNameValuePair("upassword", textbox_password.getText().toString());
                    BasicNameValuePair param3 = new BasicNameValuePair("ubirthday", textbox_birthday.getText().toString());
                    BasicNameValuePair param4 = new BasicNameValuePair("unick", textbox_nickname.getText().toString());
                    BasicNameValuePair param5 = new BasicNameValuePair("uemail", text_mail.getText().toString());
                    BasicNameValuePair param6 = new BasicNameValuePair("ugender", gender);
                    BasicNameValuePair param7 = new BasicNameValuePair("utelephone", text_phone.getText().toString());
                    paramList.add(param);
                    paramList.add(param2);
                    paramList.add(param3);
                    paramList.add(param4);
                    paramList.add(param5);
                    paramList.add(param6);
                    paramList.add(param7);

                    post.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
                    //����HttpPost���󣬲�����HttpResponse����
                    HttpResponse httpResponse = httpClient.execute(post);
                    // �ж�������Ӧ״̬�룬״̬��Ϊ200��ʾ����˳ɹ���Ӧ�˿ͻ��˵�����
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        //��ȡ���ؽ��
                        result = EntityUtils.toString(httpResponse.getEntity());
                        //showMessageByToast(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //showMessageByToast("����!");
                }
                return result;
            }

            protected void onPostExecute(Object result) {
                super.onPostExecute(result);
                //showMessageByToast(result+"");
                try {
                    JSONTokener jsonParser = new JSONTokener(result + "");
                    JSONObject loginmsg = (JSONObject) jsonParser.nextValue();
                    //showMessageByToast("getResult:" + result);
                    if(loginmsg.getBoolean("status")){
                        showMessageByToast("ע��ɹ�����ӭ��," + textbox_user.getText().toString());
                        Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
                        intent.putExtra("username",textbox_user.getText().toString());
                        intent.putExtra("password",textbox_password.getText().toString());
                        startActivity(intent);
                        int version = Integer.valueOf(android.os.Build.VERSION.SDK);
                        if (version > 5) {
                            overridePendingTransition(R.anim.fade, R.anim.hold);
                        }
                        finish();
                    }else{
                        //showMessageByToast("Error1001,can't regist");
                        doErrorLogin();
                    }

                }catch (Exception e){
                    //showMessageByToast("Error0000,Exception @JSON");
                    e.printStackTrace();
                    doErrorLogin();
                }
                //
                //showMessageByToast("" + result);
            }
        }.execute();
    }

    public void doErrorLogin(){
        button_login.setEnabled(true);
        text_registing.setText("���ڽ���ע��...");

        Animation aIn = new AlphaAnimation(0f, 1f);
        aIn.setDuration(300);
        aIn.setFillAfter(true);
        button_login.startAnimation(aIn);
        //button_login_shadow.startAnimation(aIn);

        Animation aIn1 = new AlphaAnimation(1f, 0f);
        aIn1.setDuration(300);
        aIn1.setFillAfter(true);
        text_registing.startAnimation(aIn1);
    }

    public void showMessageByToast(String Msg) {
        Toast.makeText(RegistActivity.this, Msg, Toast.LENGTH_LONG).show();
    }

}

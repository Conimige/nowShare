package nowshare.myzchh.cn.nowshare;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nowshare.myzchh.cn.nowshare.util.BaseActivity;
import nowshare.myzchh.cn.nowshare.util.MapComparator;
import nowshare.myzchh.cn.nowshare.util.MyAdapter;
import nowshare.myzchh.cn.nowshare.util.localUser;


public class MainActivity extends BaseActivity {

    private ImageView btn_add_shadow;
    private ImageView btn_add;
    private ListView lv;
    private TextView title_Name;
    private ImageView btn_Menu;
    private MyAdapter sa;
    private RelativeLayout newBox;
    private EditText edit_newBox;
    private ImageView img_add_tip;
    private TextView status;
    private ImageView btn_publish;
    private RelativeLayout menu_bkg;
    private RelativeLayout menu_box;
    private LinearLayout menu_rightbox;
    private TextView text_Name;
    private ImageView image_face;


    private float old_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add=(ImageView)findViewById(R.id.btn_add);
        btn_add_shadow=(ImageView)findViewById(R.id.btn_add_shadow);
        title_Name=(TextView)findViewById(R.id.title_Name);
        btn_Menu=(ImageView)findViewById(R.id.btn_Menu);
        lv=(ListView)findViewById(R.id.listView);
        newBox=(RelativeLayout)findViewById(R.id.newBox);
        edit_newBox=(EditText)findViewById(R.id.edit_newBox);
        img_add_tip=(ImageView)findViewById(R.id.img_add_tip);
        status=(TextView)findViewById(R.id.text_status);
        btn_publish=(ImageView)findViewById(R.id.btn_publish);
        menu_box=(RelativeLayout)findViewById(R.id.menu_box);
        menu_bkg=(RelativeLayout)findViewById(R.id.menu_bkg);
        menu_rightbox=(LinearLayout)findViewById(R.id.menu_rightbox);
        text_Name=(TextView)findViewById(R.id.text_Name);
        image_face=(ImageView)findViewById(R.id.image_face);

        Intent intent = getIntent();
        String userStr = intent.getStringExtra("username");
        title_Name.setText(userStr);
        text_Name.setText(userStr);

        //Log.i("uuid", localUser.getUUID());

        Animation aIn = new AlphaAnimation(1f, 0f);
        aIn.setDuration(1);
        aIn.setFillAfter(true);
        btn_add_shadow.startAnimation(aIn);

        loadBlogger();

        btn_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowMenu();
            }
        });
        title_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowMenu();
            }
        });
        menu_bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_bkg.setClickable(false);
                menu_rightbox.setClickable(false);
                Animation aIn2 = new AlphaAnimation(1f, 0f);
                aIn2.setDuration(300);
                aIn2.setFillAfter(true);
                menu_bkg.startAnimation(aIn2);

                Animation translateIn2 = new TranslateAnimation(0, -dip2px(MainActivity.this, 360), 0, 0);
                translateIn2.setDuration(300);
                translateIn2.setFillAfter(true);
                menu_box.startAnimation(translateIn2);

                translateIn2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        menu_bkg.setVisibility(View.GONE);
                        menu_rightbox.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
        });

        btn_add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.i("sd","ff");
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Log.i("aa", "ACTION_DOWN");
                    Animation aIn = new AlphaAnimation(0f, 1f);
                    aIn.setDuration(200);
                    aIn.setFillAfter(true);
                    btn_add_shadow.startAnimation(aIn);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Animation aIn = new AlphaAnimation(1f, 0f);
                    aIn.setDuration(800);
                    aIn.setFillAfter(true);
                    btn_add_shadow.startAnimation(aIn);
                }
                return false;
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("bb", "Click");
                Animation aIn = new AlphaAnimation(0f, 1f);
                aIn.setDuration(200);
                aIn.setFillAfter(true);
                btn_add_shadow.startAnimation(aIn);

                newBox.setEnabled(true);
                newBox.setVisibility(View.VISIBLE);
                newBox.setClickable(true);
                edit_newBox.setEnabled(true);
                edit_newBox.setVisibility(View.VISIBLE);
                btn_publish.setEnabled(true);
                btn_publish.setVisibility(View.VISIBLE);
                img_add_tip.setVisibility(View.VISIBLE);

                Animation aIn2 = new AlphaAnimation(0f, 1f);
                aIn2.setDuration(400);
                aIn2.setFillAfter(true);
                newBox.startAnimation(aIn2);
            }
        });

        newBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowNewBox();
            }
        });

        btn_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (!edit_newBox.getText().toString().equals("")) {
                showMessageByToast("���ڷ����·���...");
                publishNewShare(edit_newBox.getText().toString());
                doShowNewBox();
            }else{
                edit_newBox.setHintTextColor(getResources().getColor(R.color.color_error));
                edit_newBox.setHint("�벻Ҫ����յķ���");
            }
            }
        });

        edit_newBox.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edit_newBox.setHintTextColor(getResources().getColor(R.color.hint_foreground_material_light));
                    edit_newBox.setHint("�ڴ���������");
                }
            }
        });

        edit_newBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                edit_newBox.setHintTextColor(getResources().getColor(R.color.hint_foreground_material_light));
                edit_newBox.setHint("�ڴ���������");
                return false;
            }
        });

        edit_newBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });

        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE){
                    if (old_x<=(dip2px(MainActivity.this,21))){
                        old_x=event.getX();
                    }
                    if (event.getX()-old_x>dip2px(MainActivity.this,120)){
                        doShowMenu();
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_UP){
                    old_x=0;
                }
                return false;
            }
        });

        getUserInfo();
    }

    public void loadBlogger(){
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... params) {
                String url = "http://sanjin6035.vicp.cc/Weibo/blogger/showAllBlogger";
                String result = "";
                try {
                    //��������
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost post = new HttpPost(url);
                    //���ò�������html���ύ
                    List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                    BasicNameValuePair param = new BasicNameValuePair("uuid",localUser.getUUID());
                    paramList.add(param);

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

                //showMessageByToast("getResult:" + result);

                try {
                    JSONTokener jsonParser = new JSONTokener(result + "");
                    JSONObject loginmsg = (JSONObject) jsonParser.nextValue();
                    if (loginmsg.getBoolean("status")) {
                        List<Map<String, Object>> datas=null;
                        MyAdapter se=null;
                        JSONArray blogInfo = loginmsg.getJSONArray("bloggers");
                        datas=getList(blogInfo.toString());

                        Collections.sort(datas, new MapComparator());

                        se=new MyAdapter(MainActivity.this,datas,R.layout.listviewitem_main,new String[]{"bblog","unick","bpubtime",
                                "personico","p1","p2","p1comment","p2comment"},
                                new int[]{R.id.text_Text,R.id.text_Name,R.id.text_date,R.id.ico_Person,R.id.text_critics1
                                        ,R.id.text_critics2,R.id.text_comment1,R.id.text_comment2});
                        lv.setAdapter(se);

                        Animation translateIn2 = new TranslateAnimation(0, 0, dip2px(MainActivity.this, 640), 0);
                        translateIn2.setDuration(1000);
                        translateIn2.setStartOffset(200);
                        translateIn2.setFillAfter(true);
                        lv.startAnimation(translateIn2);
                    } else {
                        //showMessageByToast("Error1001,Username & Password is not true");
                    }

                } catch (Exception e) {
                    //showMessageByToast("Error0000,Exception @JSON");
                    e.printStackTrace();
                }
                //showMessageByToast("" + result);
            }
        }.execute();
    }

    public Map<String, Object> getMap(String jsonString) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonString);   @SuppressWarnings("unchecked")
            Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext())
            {
                key = (String) keyIter.next();
                value = jsonObject.get(key);
                if (key.equals("bpubtime")){
                    value=((String)value).replace("T"," ");
                }
                valueMap.put(key, value);
            }
            addPersonIco(valueMap);
            return valueMap;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void addPersonIco(Map<String, Object> map){
        String strUuid=(String)map.get("uuid");
        int drawableID=0;
        switch (strUuid){
            case "96f64b20-04d0-4e9c-a30d-e40b0f08217d"://kongzue
                drawableID=R.drawable.face_kongzue;
                break;
            case "79b6aee6-42a4-4433-a0c4-9aa217e7609a"://sanjin6035
                drawableID=R.drawable.face_sanjin6035;
                break;
            default:
                drawableID=R.drawable.face_default;
                break;
        }
        map.put("personico", drawableID);
        image_face.setImageDrawable(getResources().getDrawable(drawableID));
    }

    public ArrayList<Map<String, Object>> getList(String jsonString) {
        ArrayList<Map<String, Object>> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;
            list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                jsonObject = jsonArray.getJSONObject(i);
                list.add(getMap(jsonObject.toString()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


//    public void loadSomeItem(){
//
//        List<Map<String, Object>> datas;
//        datas=new ArrayList<>();
//
//        Map<String,Object> data0= new HashMap<String,Object>();
//        data0.put("text","");
//
//        Map<String,Object> data1= new HashMap<String,Object>();
//        data1.put("text","�Ҹ����Ǻù����������ܰ����ǣ�Ҳ�����������Դ��˴������������������Ҳ���Ầ�㣬�Ҵ����������Լ��Ĺ�ȥ�����Ҽǵã�����Ҳ����������һ�ʹ�����");
//        data1.put("name","�ɻ�");
//        data1.put("date","@2015.5.17 12:20");
//        data1.put("personico",R.drawable.p_hua);
//        data1.put("p1","@Ф��"+"��");
//        data1.put("p1comment","����");
//        data1.put("p2","@�����"+"��");
//        data1.put("p2comment","����+1");
//
//        Map<String,Object> data2= new HashMap<String,Object>();
//        data2.put("text","�����ľ��������~~ ������ ����˼ ������ ������ �ٽ��� ������ ������ ����� ������ ~ ת��˵����ϲ���ĸ���");
//        data2.put("name","��˧��");
//        data2.put("date","@2015.5.17 12:20");
//        data2.put("personico",R.drawable.p_shuai);
//        data2.put("p1","@����"+"��");
//        data2.put("p1comment","�ۼ�˧���в�");
//        data2.put("p2","@�ɻ�"+"��");
//        data2.put("p2comment","������ ����˼ ������ ������ �ٽ��� ������ ������ ����� ������ ��ϲ��զ�죿");
//
//        Map<String,Object> data3= new HashMap<String,Object>();
//        data3.put("text", "�����и����ŵ�С���ֳ����������·���������Ǻ�������ק�������棬��С����������������û��ʱ���ҳ���������������֪��զ�棬�����ĵ��ϴ�������������ӡ������Ա����ţ������Ӷ�һ�������ֻ����ĸ�󡡭��");
//        data3.put("name","�����");
//        data3.put("date","@2015.5.17 12:20");
//        data3.put("personico",R.drawable.p_yun);
//        data3.put("p1","@�ɻ�"+"��");
//        data3.put("p1comment","���ǹ�ô��");
//        data3.put("p2","@Ф��"+"��");
//        data3.put("p2comment","���������ȥ��ô��");
//
//        Map<String,Object> data4= new HashMap<String,Object>();
//        data4.put("text", "����������������������������ӣ�һ������1Ӣ������Ȼװ��Ҫ����Ǯ�����о�����ͦ��...");
//        data4.put("name","����");
//        data4.put("date","@2015.5.17 12:20");
//        data4.put("personico",R.drawable.p_chao);
//        data4.put("p1","@�ɻ�"+"��");
//        data4.put("p1comment","������");
//        data4.put("p2","@Ф��"+"��");
//        data4.put("p2comment","�������ˣ�");
//
//        Map<String,Object> data5= new HashMap<String,Object>();
//        data5.put("text", "�������Ը���������������ʱ��ÿ�ζ������Ļ�Ϊ��β�����硰���š�֮�����Ӫ���ģ�������˵���������ظ�һ�飬��Ҫ��Ϊ�����£���ֻ�ǰѻ����жϵ�ʧ��������Լ����ϡ��������������Ǻ�����ģ�����˾ͺ����������ˡ�");
//        data5.put("name","Ф��");
//        data5.put("date","@2015.5.17 12:20");
//        data5.put("personico",R.drawable.p_dou);
//        data5.put("p1","@�ɻ�"+"��");
//        data5.put("p1comment","����");
//        data5.put("p2","@̷��Ů"+"��");
//        data5.put("p2comment","�ϱߵ���λ֤����һ��...");
//
//        Map<String,Object> data6= new HashMap<String,Object>();
//        data6.put("text", "һ���ů�ĵĻ�������Ϊ����һ���壬����һ�������ˣ��Ҿͱ����ȵ�ͷ��");
//        data6.put("name","̷��Ů");
//        data6.put("date","@2015.5.17 12:20");
//        data6.put("personico", R.drawable.p_tan);
//        data6.put("p1","@��˧��"+"��");
//        data6.put("p1comment","����ͷ������- -|||");
//        data6.put("p2","@Ф��"+"��");
//        data6.put("p2comment", "��ů����һ��");
//
//
//        //26datas.add(data0);
//        datas.add(data1);
//        datas.add(data2);
//        datas.add(data3);
//        datas.add(data4);
//        datas.add(data5);
//        datas.add(data6);
//
//        sa=new MyAdapter(this,datas,R.layout.listviewitem_main,new String[]{"text","name","date",
//                "personico","p1","p2","p1comment","p2comment"},
//                new int[]{R.id.text_Text,R.id.text_Name,R.id.text_date,R.id.ico_Person,R.id.text_critics1
//                ,R.id.text_critics2,R.id.text_comment1,R.id.text_comment2});
//        lv.setAdapter(sa);
//
//    }


    /**
     * �����ֻ��ķֱ��ʴ� ת�� dp/px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void getUserInfo(){
        //��ʼ��¼
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... params) {
                String url = "http://sanjin6035.vicp.cc/Weibo/user/showUserByUsername";
                String result = "";
                try {
                    //��������
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost post = new HttpPost(url);
                    //���ò�������html���ύ
                    List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                    BasicNameValuePair param = new BasicNameValuePair("uusername",title_Name.getText().toString());
                    paramList.add(param);

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

//                showMessageByToast("getResult:" + result);
                try {
                    JSONTokener jsonParser = new JSONTokener(result + "");
                    JSONObject loginmsg = (JSONObject) jsonParser.nextValue();
                    if (loginmsg.getBoolean("status")) {
                        JSONObject loginUser = loginmsg.getJSONObject("user");
                        title_Name.setText(loginUser.getString("unick"));
                        text_Name.setText(loginUser.getString("unick"));
                        status.setText("");
                    } else {
                        //showMessageByToast("Error1001,Username & Password is not true");
                        doErrorLogin();

                    }

                } catch (Exception e) {
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
        status.setText("����");
    }

    public void showMessageByToast(String Msg) {
        Toast.makeText(MainActivity.this, Msg, Toast.LENGTH_LONG).show();
    }

    public void publishNewShare(String strText){
        //��ʼ��¼

        final String strUUID=localUser.getUUID();
        final String strBlogText=strText;
        if (strUUID.equals("")){
            //error,not find uuid
        }else{
            new AsyncTask<String, Void, Object>() {
                @Override
                protected Object doInBackground(String... params) {
                    String url = "http://sanjin6035.vicp.cc/Weibo/blogger/addBlogger";
                    String result = "";
                    try {
                        //��������
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost post = new HttpPost(url);
                        //���ò�������html���ύ
                        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                        BasicNameValuePair param = new BasicNameValuePair("uuid",strUUID);
                        BasicNameValuePair param1 = new BasicNameValuePair("bblog",strBlogText);
                        paramList.add(param);
                        paramList.add(param1);

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

                    //showMessageByToast("getResult:" + result);
                    try {
                        JSONTokener jsonParser = new JSONTokener(result + "");
                        JSONObject blogg = (JSONObject) jsonParser.nextValue();
                        if (blogg.getBoolean("status")) {
                            showMessageByToast("�������ɹ���");
                            loadBlogger();
                        } else {
                            //showMessageByToast("Error1001,status is false");
                        }

                    } catch (Exception e) {
                        //showMessageByToast("Error0000,Exception @JSON");
                        e.printStackTrace();
                    }
                    //
                    //showMessageByToast("" + result);
                }
            }.execute();
        }
    }

    public void doShowNewBox(){
        img_add_tip.setVisibility(View.GONE);

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        Animation aIn = new AlphaAnimation(1f, 0f);
        aIn.setDuration(200);
        aIn.setFillAfter(true);
        btn_add_shadow.startAnimation(aIn);

        Animation aIn2 = new AlphaAnimation(1f, 0f);
        aIn2.setDuration(400);
        aIn2.setFillAfter(true);
        newBox.startAnimation(aIn2);

        aIn2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                newBox.setEnabled(false);
                newBox.setVisibility(View.GONE);
                newBox.setClickable(false);
                edit_newBox.setEnabled(false);
                edit_newBox.setVisibility(View.GONE);
                btn_publish.setEnabled(false);
                btn_publish.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void doShowMenu(){
        menu_bkg.setVisibility(View.VISIBLE);
        menu_bkg.setClickable(true);
        menu_rightbox.setVisibility(View.VISIBLE);
        menu_rightbox.setClickable(true);

        Animation aIn2 = new AlphaAnimation(0f, 1f);
        aIn2.setDuration(300);
        aIn2.setFillAfter(true);
        menu_bkg.startAnimation(aIn2);

        Animation translateIn2 = new TranslateAnimation(-dip2px(this, 360), 0, 0, 0);
        translateIn2.setDuration(300);
        translateIn2.setFillAfter(true);
        menu_box.startAnimation(translateIn2);
    }


}

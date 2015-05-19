package nowshare.myzchh.cn.nowshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nowshare.myzchh.cn.nowshare.util.BaseActivity;
import nowshare.myzchh.cn.nowshare.util.MyAdapter;


public class MainActivity extends BaseActivity {

    private ImageView btn_add_shadow;
    private ImageView btn_add;
    private ListView lv;
    private TextView title_Name;
    private MyAdapter sa;
    private RelativeLayout newBox;
    private EditText edit_newBox;
    private ImageView img_add_tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add=(ImageView)findViewById(R.id.btn_add);
        btn_add_shadow=(ImageView)findViewById(R.id.btn_add_shadow);
        title_Name=(TextView)findViewById(R.id.title_Name);
        lv=(ListView)findViewById(R.id.listView);
        newBox=(RelativeLayout)findViewById(R.id.newBox);
        edit_newBox=(EditText)findViewById(R.id.edit_newBox);
        img_add_tip=(ImageView)findViewById(R.id.img_add_tip);

        Intent intent = getIntent();
        String userStr = intent.getStringExtra("username");
        title_Name.setText(userStr);

        Animation aIn = new AlphaAnimation(1f, 0f);
        aIn.setDuration(1);
        aIn.setFillAfter(true);
        btn_add_shadow.startAnimation(aIn);

        loadSomeItem();

        Animation translateIn2 = new TranslateAnimation(0, 0, dip2px(this, 640), 0);
        translateIn2.setDuration(1000);
        translateIn2.setStartOffset(200);
        translateIn2.setFillAfter(true);
        lv.startAnimation(translateIn2);

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
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    public void loadSomeItem(){

        List<Map<String, Object>> datas;
        datas=new ArrayList<>();

        Map<String,Object> data0= new HashMap<String,Object>();
        data0.put("text","");

        Map<String,Object> data1= new HashMap<String,Object>();
        data1.put("text","�Ҹ����Ǻù����������ܰ����ǣ�Ҳ�����������Դ��˴������������������Ҳ���Ầ�㣬�Ҵ����������Լ��Ĺ�ȥ�����Ҽǵã�����Ҳ����������һ�ʹ�����");
        data1.put("name","�ɻ�");
        data1.put("date","@2015.5.17 12:20");
        data1.put("personico",R.drawable.p_hua);
        data1.put("p1","@Ф��"+"��");
        data1.put("p1comment","����");
        data1.put("p2","@�����"+"��");
        data1.put("p2comment","����+1");

        Map<String,Object> data2= new HashMap<String,Object>();
        data2.put("text","�����ľ��������~~ ������ ����˼ ������ ������ �ٽ��� ������ ������ ����� ������ ~ ת��˵����ϲ���ĸ���");
        data2.put("name","��˧��");
        data2.put("date","@2015.5.17 12:20");
        data2.put("personico",R.drawable.p_shuai);
        data2.put("p1","@����"+"��");
        data2.put("p1comment","�ۼ�˧���в�");
        data2.put("p2","@�ɻ�"+"��");
        data2.put("p2comment","������ ����˼ ������ ������ �ٽ��� ������ ������ ����� ������ ��ϲ��զ�죿");

        Map<String,Object> data3= new HashMap<String,Object>();
        data3.put("text", "�����и����ŵ�С���ֳ����������·���������Ǻ�������ק�������棬��С����������������û��ʱ���ҳ���������������֪��զ�棬�����ĵ��ϴ�������������ӡ������Ա����ţ������Ӷ�һ�������ֻ����ĸ�󡡭��");
        data3.put("name","�����");
        data3.put("date","@2015.5.17 12:20");
        data3.put("personico",R.drawable.p_yun);
        data3.put("p1","@�ɻ�"+"��");
        data3.put("p1comment","���ǹ�ô��");
        data3.put("p2","@Ф��"+"��");
        data3.put("p2comment","���������ȥ��ô��");

        Map<String,Object> data4= new HashMap<String,Object>();
        data4.put("text", "����������������������������ӣ�һ������1Ӣ������Ȼװ��Ҫ����Ǯ�����о�����ͦ��...");
        data4.put("name","����");
        data4.put("date","@2015.5.17 12:20");
        data4.put("personico",R.drawable.p_chao);
        data4.put("p1","@�ɻ�"+"��");
        data4.put("p1comment","������");
        data4.put("p2","@Ф��"+"��");
        data4.put("p2comment","�������ˣ�");

        Map<String,Object> data5= new HashMap<String,Object>();
        data5.put("text", "�������Ը���������������ʱ��ÿ�ζ������Ļ�Ϊ��β�����硰���š�֮�����Ӫ���ģ�������˵���������ظ�һ�飬��Ҫ��Ϊ�����£���ֻ�ǰѻ����жϵ�ʧ��������Լ����ϡ��������������Ǻ�����ģ�����˾ͺ����������ˡ�");
        data5.put("name","Ф��");
        data5.put("date","@2015.5.17 12:20");
        data5.put("personico",R.drawable.p_dou);
        data5.put("p1","@�ɻ�"+"��");
        data5.put("p1comment","����");
        data5.put("p2","@̷��Ů"+"��");
        data5.put("p2comment","�ϱߵ���λ֤����һ��...");

        Map<String,Object> data6= new HashMap<String,Object>();
        data6.put("text", "һ���ů�ĵĻ�������Ϊ����һ���壬����һ�������ˣ��Ҿͱ����ȵ�ͷ��");
        data6.put("name","̷��Ů");
        data6.put("date","@2015.5.17 12:20");
        data6.put("personico", R.drawable.p_tan);
        data6.put("p1","@��˧��"+"��");
        data6.put("p1comment","����ͷ������- -|||");
        data6.put("p2","@Ф��"+"��");
        data6.put("p2comment", "��ů����һ��");


        //26datas.add(data0);
        datas.add(data1);
        datas.add(data2);
        datas.add(data3);
        datas.add(data4);
        datas.add(data5);
        datas.add(data6);

        sa=new MyAdapter(this,datas,R.layout.listviewitem_main,new String[]{"text","name","date",
                "personico","p1","p2","p1comment","p2comment"},
                new int[]{R.id.text_Text,R.id.text_Name,R.id.text_date,R.id.ico_Person,R.id.text_critics1
                ,R.id.text_critics2,R.id.text_comment1,R.id.text_comment2});
        lv.setAdapter(sa);

    }


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
}

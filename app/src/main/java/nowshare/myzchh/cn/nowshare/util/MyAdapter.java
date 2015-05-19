package nowshare.myzchh.cn.nowshare.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import nowshare.myzchh.cn.nowshare.R;

/**
 * Created by chao on 2015/5/17.
 */

public class MyAdapter extends SimpleAdapter {
    private int mResource;
    private Context context;
    private List<? extends Map<String, Object>> mData;
    private LayoutInflater mInflater;

    private LinearLayout layout_comment;
    private TextView text_Text;
    private ImageView ico_Person;
    private TextView text_Name;
    private TextView text_date;

    public MyAdapter(Context context, List<? extends Map<String, Object>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.mInflater = LayoutInflater.from(context);
        this.mResource = resource;
        this.mData = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listviewitem_main, null);
            layout_comment=(LinearLayout)convertView.findViewById(R.id.layout_comment);
            text_Text=(TextView)convertView.findViewById(R.id.text_Text);
            ico_Person=(ImageView)convertView.findViewById(R.id.ico_Person);
            text_Name=(TextView)convertView.findViewById(R.id.text_Name);
            text_date=(TextView)convertView.findViewById(R.id.text_date);

            Log.i("text_Text",position+";"+text_Text.getText().toString());
        }
        return super.getView(position, convertView, parent);
    }
}


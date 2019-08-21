package com.llh.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.http.VolleyError;
import com.llh.chatListData.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private EditText et_text;
    private Button btn_send;
    private List<ChatListData> chatListDataList = new ArrayList<>();
    ChatListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        listView = findViewById(R.id.lv_chat);
        et_text = findViewById(R.id.et_text);
        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        //创建适配器
        adapter = new ChatListAdapter(this, chatListDataList);
        listView.setAdapter(adapter);
        //链表
        addLeftItem("你好");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                /**
                 * 逻辑：
                 * 1.获取输入框的内容
                 * 2.判断是否为空
                 * 3.判断字符长度不能大于30
                 * 4.清空输入框的内容
                 * 5.添加你输入的内容到rightitem
                 * 6.发送给机器人，请求返回内容
                 * 7.拿到机器人的返回值之后添加在leftitem
                 */
                //1.获取输入框的内容
                String text = et_text.getText().toString();
                //url
                String url = "http://www.tuling123.com/openapi/api";
                HttpParams params = new HttpParams();
                params.put("key","1c6cedb04c904faeb494d24e60b2eb6a");
                params.put("info",text);
                //2.判断是否为空
                if(!TextUtils.isEmpty(text) ){
                    //3.判断字符长度不能大于30
                    if( text.length()<30){
                        //4.清空输入框的内容
                        et_text.setText("");
                        //5.添加你输入的内容到rightitem
                        addRightItem(text);

                        //6.Post发送给机器人，请求返回内容
                        RxVolley.post(url, params, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
//                                Toast.makeText(MainActivity.this,t,Toast.LENGTH_SHORT).show();
//                                Log.d("llhData", "onSuccess: "+t);
                                getEditContent(t);
                            }

                            @Override
                            public void onFailure(VolleyError error) {
                                Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else {
                        Toast.makeText(this,"输入长度不能大于30",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"输入框不能为空！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getEditContent(String t) {
        //解析数据
        try {
            JSONObject jsonObject = new JSONObject(t);
            String str = jsonObject.getString("text");
            //将机器人说的话，写在左边
            addLeftItem(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void addLeftItem(String text) {
        ChatListData chatListData = new ChatListData();
        chatListData.setType(ChatListAdapter.VALUE_LEFT_TEXT);
        chatListData.setTalling(text);
        chatListDataList.add(chatListData);
        //通知adapter更新
        adapter.notifyDataSetChanged();
        //滚动到底部
        listView.setSelection(listView.getBottom());
    }
    private void addRightItem(String text) {
        ChatListData chatListData = new ChatListData();
        chatListData.setType(ChatListAdapter.VALUE_RIGHT_TEXT);
        chatListData.setTalling(text);
        chatListDataList.add(chatListData);
        //通知adapter更新
        adapter.notifyDataSetChanged();
        //滚动到底部
        listView.setSelection(listView.getBottom());
    }

}
/*

                    //拼接字符串
                String url = "http://op.juhe.cn/robot/index?info="+text+"&key="+"1c6cedb04c904faeb494d24e60b2eb6a";
//                    //get发送
//                        RxVolley.get(url, new HttpCallback() {//{"reason":"APPKEY错误","result":null,"error_code":10001}
//                            @Override
//                            public void onSuccess(String t) {
//                                Log.d("llhData", "onSuccess: "+t);
//                                Toast.makeText(MainActivity.this,t,Toast.LENGTH_SHORT).show();
//                                getEditContent(t);
//                            }
//
//                            @Override
//                            public void onFailure(VolleyError error) {
//                                Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
//                            }
//                        });
 */

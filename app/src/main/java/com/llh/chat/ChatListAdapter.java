package com.llh.chat;

/*
 *  项目名：  SmartButler
 *  包名：    com.imooc.smartbutler.adapter
 *  文件名:   ChatListAdapter
 *  创建者:   LGL
 *  创建时间:  2016/11/13 2:10
 *  描述：    对话adapter
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.llh.chatListData.R;

import java.util.List;

public class ChatListAdapter extends BaseAdapter {

    //左边的type
    public static final int VALUE_LEFT_TEXT = 1;
    //右边的type
    public static final int VALUE_RIGHT_TEXT = 2;

    private Context mContext;
    private LayoutInflater inflater;
    private ChatListData data;
    private List<ChatListData> mList;

    public ChatListAdapter(Context mContext, List<ChatListData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        //获取系统服务
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderLeftText viewHolderLeftText = null;
        ViewHolderRightText viewHolderRightText = null;
        //获取当前要显示的type 根据这个type来区分数据的加载
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case VALUE_LEFT_TEXT:
                    viewHolderLeftText = new ViewHolderLeftText();
                    convertView = inflater.inflate(R.layout.left_item, null);
                    viewHolderLeftText.tv_left_text = (TextView) convertView.findViewById(R.id.tv_left_text);
                    convertView.setTag(viewHolderLeftText);
                    break;
                case VALUE_RIGHT_TEXT:
                    viewHolderRightText = new ViewHolderRightText();
                    convertView = inflater.inflate(R.layout.right_item, null);
                    viewHolderRightText.tv_right_text = (TextView) convertView.findViewById(R.id.tv_right_text);
                    convertView.setTag(viewHolderRightText);
                    break;
            }
        } else {
            switch (type) {
                case VALUE_LEFT_TEXT:
                    viewHolderLeftText = (ViewHolderLeftText) convertView.getTag();
                    break;
                case VALUE_RIGHT_TEXT:
                    viewHolderRightText = (ViewHolderRightText) convertView.getTag();
                    break;
            }
        }

        //赋值
        ChatListData data = mList.get(position);
        switch (type){
            case VALUE_LEFT_TEXT:
                viewHolderLeftText.tv_left_text.setText(data.getTalling());
                break;
            case VALUE_RIGHT_TEXT:
                viewHolderRightText.tv_right_text.setText(data.getTalling());
                break;
        }
        return convertView;
    }

    //根据数据源的positiion来返回要显示的item
    @Override
    public int getItemViewType(int position) {
        ChatListData data = mList.get(position);
        int type = data.getType();
        return type;
    }

    //返回所有的layout数据
    @Override
    public int getViewTypeCount() {
        return 3; //mlisy.size + 1
    }

    //左边的文本
    class ViewHolderLeftText {
        private TextView tv_left_text;
    }

    //右边的文本
    class ViewHolderRightText {
        private TextView tv_right_text;
    }
}




//package com.llh.chat;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * 项目名:    ChatListData
// * 包名:      com.llh.chat
// * 文件名:    ChatAdapter
// * 创建者:    LLH
// * 创建时间:  2019/8/19 11:06
// * 描述:      TODO
// */
//public class ChatAdapter extends BaseAdapter  {
//
//    //上下文
//    private Context mContent;
//    private LayoutInflater inflater;
//    private List<ChatListData> chatList;
//    //private ChatListData chat;
//    public static final int LEFT_VALUE = 1;
//    public static  final int RIGHT_VALUE = 2;
//    //构造函数
//    public ChatAdapter(Context mContent,List<ChatListData> chatList){
//        this.mContent = mContent;
//        this.chatList = chatList;
//        //获取系统服务
//        inflater = (LayoutInflater) mContent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//    @Override
//    public int getCount() {
//        return chatList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return chatList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        //实例化
//        LeftViewHolder leftViewHolder = null;
//        RightViewHolder rightViewHolder = null;
//        //获取当前要显示的type，根据type来区分数据的加载
//        int type = getItemViewType(position);
//        Log.d("llhData", "type1: "+type);
//        if(convertView == null){
//            switch (type){
//                case LEFT_VALUE:
//                    leftViewHolder = new LeftViewHolder();
//                    convertView = inflater.inflate(R.layout.item_left,null);
//                    leftViewHolder.tv_left = convertView.findViewById(R.id.tv_left);
//                    convertView.setTag(R.id.tag_left,leftViewHolder);
//                    break;
//                case RIGHT_VALUE:
//                    rightViewHolder = new RightViewHolder();
//                    convertView = inflater.inflate(R.layout.item_right,null);
//                    rightViewHolder.tv_right = convertView.findViewById(R.id.tv_right);
//                    convertView.setTag(R.id.tag_right,rightViewHolder);
//                    break;
//            }
//        }else {
//            Log.d("llhData", "type2: "+type);
//            switch (type){
//                case LEFT_VALUE:
//                    //leftViewHolder = (LeftViewHolder) convertView.getTag();
//                    leftViewHolder = (LeftViewHolder) convertView.getTag(R.id.tag_left);
//                    break;
//                case RIGHT_VALUE:
//                    rightViewHolder = (RightViewHolder) convertView.getTag(R.id.tag_right);
//                    Log.d("llhData","rightViewHolder.tv_right="+rightViewHolder);
//                    break;
//            }
//        }
//        //设置数据
//        ChatListData chat = chatList.get(position);
//        Log.d("llhData", "type3: "+type);
//        switch (type){
//            case LEFT_VALUE:
//                //设置数据
//                leftViewHolder.tv_left.setText(chat.getTalling());
//                break;
//            case RIGHT_VALUE:
//
//                //rightViewHolder.tv_right.setText(chat.getTalling());
//                break;
//        }
//        return convertView;
//    }
//    //根据数据源的position来返回要显示的item
//    @Override
//    public int getItemViewType(int position) {
//        ChatListData chat = chatList.get(position);
//        int type = chat.getType();
//        return type;
//    }
//    //返回所有的layout数量
//
//    @Override
//    public int getViewTypeCount() {
//        return chatList.size()+1;//左边1个，右边一个，自己也是一个
//    }
//
//    //两个ViewHolder
//    class LeftViewHolder{
//        TextView tv_left;
//    }
//    class RightViewHolder{
//        TextView tv_right;
//    }
//}
//
///*
//switch (type){
//                 case LEFT_VALUE:
//                         if(convertView == null){
//                             leftViewHolder = new LeftViewHolder();
//                              convertView = LayoutInflater.from(mContent).inflate(R.layout.item_left,null);
//                              leftViewHolder.tv_left = convertView.findViewById(R.id.tv_left);
//                              convertView.setTag(R.id.tag_left,leftViewHolder);
//                         }else {
//                              leftViewHolder = (LeftViewHolder) convertView.getTag(R.id.tag_left);
//                         }
//                          //设置数据
//                          leftViewHolder.tv_left.setText(chat.getTalling());
//                          break;
//                      case RIGHT_VALUE:
//                          if(convertView == null){
//                              rightViewHolder = new RightViewHolder();
//                              convertView = LayoutInflater.from(mContent).inflate(R.layout.item_right,null);
//                              rightViewHolder.tv_right = convertView.findViewById(R.id.tv_right);
//                              convertView.setTag(R.id.tag_right,rightViewHolder);
//                          }else {
//                              rightViewHolder = (RightViewHolder) convertView.getTag(R.id.tag_right);
//                          }
//                          Log.d("llhData","rightViewHolder.tv_right="+rightViewHolder);
//                          //rightViewHolder.tv_right.setText(chat.getTalling());
//                          break;
//                  }
// */
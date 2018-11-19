package com.wangyukui.ywkj.jmessage;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangyukui.R;
import com.wangyukui.ywkj.content.ContentUrl;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.PromptContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by ${chenyn} on 2017/3/30.
 */

public class ConversationListAdapter extends BaseAdapter {

    private List<Conversation> mDatas;
    private Activity mContext;
    private Map<String, String> mDraftMap = new HashMap<String, String>();
    private UIHandler mUIHandler = new UIHandler(this);
    private static final int REFRESH_CONVERSATION_LIST = 0x3003;
    private SparseBooleanArray mArray = new SparseBooleanArray();
    private SparseBooleanArray mAtAll = new SparseBooleanArray();
    private HashMap<Conversation, Integer> mAtConvMap = new HashMap<Conversation, Integer>();
    private HashMap<Conversation, Integer> mAtAllConv = new HashMap<Conversation, Integer>();
    private UserInfo mUserInfo;
    private GroupInfo mGroupInfo;
    private ConversationListView mConversationListView;

    public ConversationListAdapter(Activity context, List<Conversation> data, ConversationListView convListView) {
        this.mContext = context;
        this.mDatas = data;
        this.mConversationListView = convListView;
    }

    /**
     * 收到消息后将会话置顶
     *
     * @param conv 要置顶的会话
     */
    public void setToTop(Conversation conv) {
        for (Conversation conversation : mDatas) {
            if (conv.getId().equals(conversation.getId())) {
                mDatas.remove(conversation);
                mDatas.add(0, conv);
                mUIHandler.removeMessages(REFRESH_CONVERSATION_LIST);
                mUIHandler.sendEmptyMessageDelayed(REFRESH_CONVERSATION_LIST, 200);
                return;
            }
        }
        ThreadUtil.runInUiThread(new Runnable() {
            @Override
            public void run() {
                mConversationListView.setNullConversation(true);
            }
        });
        //如果是新的会话
        mDatas.add(0, conv);
        mUIHandler.removeMessages(REFRESH_CONVERSATION_LIST);
        mUIHandler.sendEmptyMessageDelayed(REFRESH_CONVERSATION_LIST, 200);
    }

    public void setConvTop(Conversation convTop) {
        for (Conversation conversation : mDatas) {
            if (convTop.getId().equals(conversation.getId())) {
                mDatas.remove(conversation);
                mDatas.add(SharePreferenceManager.getTopSize(), convTop);
                mUIHandler.removeMessages(REFRESH_CONVERSATION_LIST);
                mUIHandler.sendEmptyMessageDelayed(REFRESH_CONVERSATION_LIST, 200);
                return;
            }
        }
    }

    @Override
    public int getCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    @Override
    public Conversation getItem(int position) {
        if (mDatas == null) {
            return null;
        }
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Conversation convItem = mDatas.get(position);
        if (convItem.getType().equals(ConversationType.single)) {
            UserInfo feedBack = (UserInfo) convItem.getTargetInfo();
            if (feedBack.getUserName().equals("feedback_Android")) {
                JMessageClient.deleteSingleConversation("feedback_Android", feedBack.getAppKey());
                mDatas.remove(position);
                notifyDataSetChanged();
            }
        }
        mConversationListView.setUnReadMsg(JMessageClient.getAllUnReadMsgCount());
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_conv_list, null);
        }
        final ImageView headIcon = ViewHolder.get(convertView, R.id.msg_item_head_icon);
        TextView convName = ViewHolder.get(convertView, R.id.conv_item_name);
        final TextView content = ViewHolder.get(convertView, R.id.msg_item_content);
        TextView datetime = ViewHolder.get(convertView, R.id.msg_item_date);
        TextView newGroupMsgNumber = ViewHolder.get(convertView, R.id.new_group_msg_number);
        TextView newMsgNumber = ViewHolder.get(convertView, R.id.new_msg_number);
        ImageView groupBlocked = ViewHolder.get(convertView, R.id.iv_groupBlocked);
        ImageView newMsgDisturb = ViewHolder.get(convertView, R.id.new_group_msg_disturb);
        ImageView newGroupMsgDisturb = ViewHolder.get(convertView, R.id.new_msg_disturb);

        final SwipeLayoutConv swipeLayout = ViewHolder.get(convertView, R.id.swp_layout);
        final TextView delete = ViewHolder.get(convertView, R.id.tv_delete);

        String draft = mDraftMap.get(convItem.getId());

        //如果会话草稿为空,显示最后一条消息
        if (TextUtils.isEmpty(draft)) {
            Message lastMsg = convItem.getLatestMessage();
            if (lastMsg != null) {
                TimeFormat timeFormat = new TimeFormat(mContext, lastMsg.getCreateTime());
                //会话界面时间
                datetime.setText(timeFormat.getTime());
                String contentStr;
                switch (lastMsg.getContentType()) {
                    case image:
                        contentStr = mContext.getString(R.string.type_picture);
                        break;
                    case voice:
                        contentStr = mContext.getString(R.string.type_voice);
                        break;
                    case location:
                        contentStr = mContext.getString(R.string.type_location);
                        break;
                    case file:
                        String extra = lastMsg.getContent().getStringExtra("video");
                        if (!TextUtils.isEmpty(extra)) {
                            contentStr = mContext.getString(R.string.type_smallvideo);
                        } else {
                            contentStr = mContext.getString(R.string.type_file);
                        }
                        break;
                    case video:
                        contentStr = mContext.getString(R.string.type_video);
                        break;
                    case eventNotification:
                        contentStr = mContext.getString(R.string.group_notification);
                        break;
                    case custom:
                        CustomContent customContent = (CustomContent) lastMsg.getContent();
                        Boolean isBlackListHint = customContent.getBooleanValue("blackList");
                        if (isBlackListHint != null && isBlackListHint) {
                            contentStr = mContext.getString(R.string.jmui_server_803008);
                        } else {
                            contentStr = mContext.getString(R.string.type_custom);
                        }
                        break;
                    case prompt:
                        contentStr = ((PromptContent) lastMsg.getContent()).getPromptText();
                        break;
                    default:
                        contentStr = ((TextContent) lastMsg.getContent()).getText();
                }

                MessageContent msgContent = lastMsg.getContent();
                Boolean isRead = msgContent.getBooleanExtra("isRead");
                Boolean isReadAtAll = msgContent.getBooleanExtra("isReadAtAll");
                if (lastMsg.isAtMe()) {
                    if (null != isRead && isRead) {
                        mArray.delete(position);
                        mAtConvMap.remove(convItem);
                    } else {
                        mArray.put(position, true);
                    }
                }
                if (lastMsg.isAtAll()) {
                    if (null != isReadAtAll && isReadAtAll) {
                        mAtAll.delete(position);
                        mAtAllConv.remove(convItem);
                    } else {
                        mAtAll.put(position, true);
                    }

                }
                long gid = 0;
                if (convItem.getType().equals(ConversationType.group)) {
                    gid = Long.parseLong(convItem.getTargetId());
                }

                if (mAtAll.get(position) && JGApplication.isAtall.get(gid) != null && JGApplication.isAtall.get(gid)) {
                    contentStr = "[@所有人] " + contentStr;
                    SpannableStringBuilder builder = new SpannableStringBuilder(contentStr);
                    builder.setSpan(new ForegroundColorSpan(Color.RED), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    content.setText(builder);
                } else if (mArray.get(position) && JGApplication.isAtMe.get(gid) != null && JGApplication.isAtMe.get(gid)) {
                    //有人@我 文字提示
                    contentStr = mContext.getString(R.string.somebody_at_me) + contentStr;
                    SpannableStringBuilder builder = new SpannableStringBuilder(contentStr);
                    builder.setSpan(new ForegroundColorSpan(Color.RED), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    content.setText(builder);
                } else {
                    if (lastMsg.getTargetType() == ConversationType.group && !contentStr.equals("[群成员变动]")) {
                        UserInfo info = lastMsg.getFromUser();
                        String fromName = info.getNotename();
                        if (TextUtils.isEmpty(fromName)) {
                            fromName = info.getNickname();
                            if (TextUtils.isEmpty(fromName)) {
                                fromName = info.getUserName();
                            }
                        }
                        if (JGApplication.isAtall.get(gid) != null && JGApplication.isAtall.get(gid)) {
                            content.setText("[@所有人] " + fromName + ": " + contentStr);
                        } else if (JGApplication.isAtMe.get(gid) != null && JGApplication.isAtMe.get(gid)) {
                            content.setText("[有人@我] " + fromName + ": " + contentStr);
                            //如果content是撤回的那么就不显示最后一条消息的发起人名字了
                        } else if (msgContent.getContentType() == ContentType.prompt) {
                            content.setText(contentStr);
                        }else if(info.getUserName().equals(JMessageClient.getMyInfo().getUserName())){
                            content.setText(contentStr);
                        }else {
                            content.setText(fromName + ": " + contentStr);
                        }
                    } else {
                        if (JGApplication.isAtall.get(gid) != null && JGApplication.isAtall.get(gid)) {
                            content.setText("[@所有人] " + contentStr);
                        } else if (JGApplication.isAtMe.get(gid) != null && JGApplication.isAtMe.get(gid)) {
                            content.setText("[有人@我] " + contentStr);
                        } else {
                            content.setText(contentStr);
                        }
                    }
                }
            } else {
                TimeFormat timeFormat = new TimeFormat(mContext, convItem.getLastMsgDate());
                datetime.setText(timeFormat.getTime());
                content.setText("");
            }
        } else {
            draft = mContext.getString(R.string.draft) + draft;
            SpannableStringBuilder builder = new SpannableStringBuilder(draft);
            builder.setSpan(new ForegroundColorSpan(Color.RED), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            content.setText(builder);
        }

        if (convItem.getType().equals(ConversationType.single)) {
            groupBlocked.setVisibility(View.GONE);
          //  convName.setText(convItem.getTitle());

            mUserInfo = (UserInfo) convItem.getTargetInfo();
            if (mUserInfo != null) {
                if (mUserInfo.getAddress()!=null&&!"".equals(mUserInfo.getAddress())){
                    Log.i("55555555555eedeee",mUserInfo.getAddress()+"ssssssssssss");
                    if (mUserInfo.getGender()== UserInfo.Gender.female){
                        Picasso.with(JGApplication.context)
                                .load(ContentUrl.BASE_ICON_URL+mUserInfo.getAddress())
                                .error(R.mipmap.avatar_f2x).into(headIcon);
                    }else{
                        Picasso.with(JGApplication.context)
                                .load(ContentUrl.BASE_ICON_URL+mUserInfo.getAddress())
                                .error(R.mipmap.avatar_m2x).into(headIcon);
                    }

                }else{
                    Log.i("44444444444eedeee",mUserInfo.getAddress()+"dddddddddddd");
                    Picasso.with(JGApplication.context).load(R.mipmap.avatar_m2x).into(headIcon);
                }

                if (mUserInfo.getNickname()!=null&&!"".equals(mUserInfo.getNickname())){
                    convName.setText(mUserInfo.getNickname());
                }else {
                    convName.setText(mUserInfo.getNickname());
                }

            } else {
                Log.i("6666666666aaaaaaaaa",mUserInfo.getAddress());
                if (mUserInfo.getAddress()!=null&&!"".equals(mUserInfo.getAddress())){
                    Picasso.with(JGApplication.context).load(ContentUrl.BASE_ICON_URL+mUserInfo.getAddress()).into(headIcon);
                }else{
                    Picasso.with(JGApplication.context).load(R.mipmap.jmui_head_icon).into(headIcon);
                }
                headIcon.setImageResource(R.mipmap.jmui_head_icon);
            }
        } else {
            mGroupInfo = (GroupInfo) convItem.getTargetInfo();
            if (mGroupInfo != null) {
                int blocked = mGroupInfo.isGroupBlocked();
                if (blocked == 1) {
                    groupBlocked.setVisibility(View.VISIBLE);
                } else {
                    groupBlocked.setVisibility(View.GONE);
                }
            }
//            headIcon.setImageResource(R.drawable.group);
            convName.setText(convItem.getTitle());
        }

        if (convItem.getUnReadMsgCnt() > 0) {
            newGroupMsgDisturb.setVisibility(View.GONE);
            newMsgDisturb.setVisibility(View.GONE);
            newGroupMsgNumber.setVisibility(View.GONE);
            newMsgNumber.setVisibility(View.GONE);
            if (convItem.getType().equals(ConversationType.single)) {
                if (mUserInfo != null && mUserInfo.getNoDisturb() == 1) {
                    newMsgDisturb.setVisibility(View.VISIBLE);
                } else {
                    newMsgNumber.setVisibility(View.VISIBLE);
                }
                if (convItem.getUnReadMsgCnt() < 100) {
                    newMsgNumber.setText(String.valueOf(convItem.getUnReadMsgCnt()));
                } else {
                    newMsgNumber.setText("99+");
                }
            } else {
                if (mGroupInfo != null && mGroupInfo.getNoDisturb() == 1) {
                    newGroupMsgDisturb.setVisibility(View.VISIBLE);
                } else {
                    newGroupMsgNumber.setVisibility(View.VISIBLE);
                }
                if (convItem.getUnReadMsgCnt() < 100) {
                    newGroupMsgNumber.setText(String.valueOf(convItem.getUnReadMsgCnt()));
                } else {
                    newGroupMsgNumber.setText("99+");
                }
            }

        } else {
            newGroupMsgDisturb.setVisibility(View.GONE);
            newMsgDisturb.setVisibility(View.GONE);
            newGroupMsgNumber.setVisibility(View.GONE);
            newMsgNumber.setVisibility(View.GONE);
        }

        //侧滑删除会话
        swipeLayout.addSwipeListener(new SwipeLayoutConv.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayoutConv layout) {

            }

            @Override
            public void onOpen(SwipeLayoutConv layout) {
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (convItem.getType() == ConversationType.single) {
                            JMessageClient.deleteSingleConversation(((UserInfo) convItem.getTargetInfo()).getUserName(),JGApplication.TARGET_APP_KEY_CODE);
                        } else {
                            JMessageClient.deleteGroupConversation(((GroupInfo) convItem.getTargetInfo()).getGroupID());
                        }
                        mDatas.remove(position);
                        if (mDatas.size() > 0) {
                            mConversationListView.setNullConversation(true);
                        } else {
                            mConversationListView.setNullConversation(false);
                        }
                        notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onStartClose(SwipeLayoutConv layout) {

            }

            @Override
            public void onClose(SwipeLayoutConv layout) {

            }

            @Override
            public void onUpdate(SwipeLayoutConv layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayoutConv layout, float xvel, float yvel) {

            }
        });


        return convertView;
    }

//    List<Conversation> mCon = new ArrayList<>();

    public void sortConvList() {
        SortConvList sortConvList = new SortConvList();
        Collections.sort(mDatas, sortConvList);

        /*for (int i = 0; i < SharePreferenceManager.getTopSize(); i++) {
            ConversationEntry topConversation = ConversationEntry.getTopConversation(i);
            for (int x = 0; x < mDatas.size(); x++) {
                if (topConversation.targetname.equals(mDatas.get(x).getTargetId())) {
                    mCon.add(mDatas.get(x));
                    mDatas.remove(mDatas.get(x));

                    mDatas.add(i, mCon.get(0));
                    mCon.clear();
                    break;

                }
            }
        }*/
        notifyDataSetChanged();
    }

    public void addNewConversation(Conversation conv) {
        mDatas.add(0, conv);
        if (mDatas.size() > 0) {
            mConversationListView.setNullConversation(true);
        } else {
            mConversationListView.setNullConversation(false);
        }
        notifyDataSetChanged();
    }

//    List<Conversation> mConv = new ArrayList<>();

    public void addAndSort(Conversation conv) {
        mDatas.add(conv);
        SortConvList sortConvList = new SortConvList();
        Collections.sort(mDatas, sortConvList);
        /*for (int i = 0; i < SharePreferenceManager.getTopSize(); i++) {
            ConversationEntry topConversation = ConversationEntry.getTopConversation(i);
            for (int x = 0; x < mDatas.size(); x++) {
                if (topConversation.targetname.equals(mDatas.get(x).getTargetId())) {
                    mConv.add(mDatas.get(x));
                    mDatas.remove(mDatas.get(x));

                    mDatas.add(i, mConv.get(0));
                    mConv.clear();
                    break;

                }
            }
        }*/
        notifyDataSetChanged();
    }

    public void deleteConversation(Conversation conversation) {
        mDatas.remove(conversation);
        notifyDataSetChanged();
    }

    public void putDraftToMap(Conversation conv, String draft) {
        mDraftMap.put(conv.getId(), draft);
    }

    public void delDraftFromMap(Conversation conv) {
        mArray.delete(mDatas.indexOf(conv));
        mAtConvMap.remove(conv);
        mAtAllConv.remove(conv);
        mDraftMap.remove(conv.getId());
        notifyDataSetChanged();
    }

    public String getDraft(String convId) {
        return mDraftMap.get(convId);
    }

    public boolean includeAtMsg(Conversation conv) {
        if (mAtConvMap.size() > 0) {
            Iterator<Map.Entry<Conversation, Integer>> iterator = mAtConvMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Conversation, Integer> entry = iterator.next();
                if (conv == entry.getKey()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean includeAtAllMsg(Conversation conv) {
        if (mAtAllConv.size() > 0) {
            Iterator<Map.Entry<Conversation, Integer>> iterator = mAtAllConv.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Conversation, Integer> entry = iterator.next();
                if (conv == entry.getKey()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getAtMsgId(Conversation conv) {
        return mAtConvMap.get(conv);
    }

    public int getatAllMsgId(Conversation conv) {
        return mAtAllConv.get(conv);
    }

    public void putAtConv(Conversation conv, int msgId) {
        mAtConvMap.put(conv, msgId);
    }

    public void putAtAllConv(Conversation conv, int msgId) {
        mAtAllConv.put(conv, msgId);
    }

    static class UIHandler extends Handler {

        private final WeakReference<ConversationListAdapter> mAdapter;

        public UIHandler(ConversationListAdapter adapter) {
            mAdapter = new WeakReference<ConversationListAdapter>(adapter);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            ConversationListAdapter adapter = mAdapter.get();
            if (adapter != null) {
                switch (msg.what) {
                    case REFRESH_CONVERSATION_LIST:
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        }
    }


}

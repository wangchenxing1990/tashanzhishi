package com.wangyukui.ywkj.jmessage;

public interface EmoticonClickListener<T> {

    void onEmoticonClick(T t, int actionType, boolean isDelBtn);
}

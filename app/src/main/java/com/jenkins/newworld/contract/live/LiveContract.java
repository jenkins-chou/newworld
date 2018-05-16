package com.jenkins.newworld.contract.live;

import com.jenkins.newworld.contract.base.BaseCallBack;

/**
 * Created by zhouzhenjian on 2018/5/15.
 */

public class LiveContract {
    public interface MView extends BaseCallBack{
        void addLiveResult(Object object);
        void removeLiveResult(Object object);
        void getLivesResult(Object object);
    }
}

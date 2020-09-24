package com.wdk.baselibrary.basepage;

import android.app.Application;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 1:04 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 1:04 PM
 * @LastCheckBy: wdk
 */
public class AppInitUtil {

    private Application application;

    private AppInitUtil(){}

    public static AppInitUtil getInstance(){
        return InnerClass.appInitUtil;
    }

    public static class InnerClass{
        public static AppInitUtil appInitUtil=new AppInitUtil();
    }

    public void init(Application application){
        this.application=application;
    }

    public Application getApplication() {
        return application;
    }
}

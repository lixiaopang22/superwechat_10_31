package cn.ucai.superwechat.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
public class ExitAppUtils {
    List<Activity> mList =new LinkedList<Activity>() ;
        private static ExitAppUtils instance = new ExitAppUtils();

        private ExitAppUtils() {
        }

        public static ExitAppUtils getInstance() {
            return instance;
        }

        public void addActivity(Activity activity) {
            mList.add(activity);
        }

        public void delActivity(Activity activity) {
            mList.remove(activity);
        }

        public void exit() {
            for (Activity activity : mList) {
                activity.finish();
            }
        }
    }

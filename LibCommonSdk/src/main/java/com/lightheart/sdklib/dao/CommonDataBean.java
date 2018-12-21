package com.lightheart.sdklib.dao;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import io.reactivex.annotations.NonNull;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.greenrobot.greendao.annotation.Generated;

/**
 * @author LiaoHui
 * @date 2018/12/14
 * @desc 通用字符串数据库缓存实体类
 */
@Entity(nameInDb = "localCommonCache")
public class CommonDataBean implements Parcelable {
    public static final String COLUMN_KEY_ID = "_id";
    public static final String USER_ID = "userId";
    public static final String JSON_DATA = "jsonData";
    public static final String SAVE_TIME = "saveTime";
    public static final String SAVE_TIME_LIMIT = "saveTimeLimit";
    public static final String SAVE_URL_KEY = "saveUrlKey";
    public static final String SAVE_PARAMS_KEY = "paramsKey";
    public static final String HELP_QUERY_KEY = "helpQueryParams";

    //无限制存储，无时间限制
    public static final int NO_LIMIT = -1;
    public static final int MINUTE = 60;
    public static final int HOUR = 60 * 60;
    public static final int DAY = 24 * 60 * 60;
    public static final int WEEK = 7 * 24 * 60 * 60;
    public static final int MONTH = 30 * 24 * 60 * 60;

    @IntDef({NO_LIMIT, MINUTE, HOUR, DAY, WEEK, MONTH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TimeLimit {
    }

    @Id(autoincrement = true)
    public long _id;
    //缓存数据
    @Property(nameInDb = JSON_DATA)
    public String jsonData;
    @Property(nameInDb = USER_ID)
    public String userId;
    //缓存时间
    @Property(nameInDb = SAVE_TIME)
    public long saveTime;
    //缓存时间限制
    @Property(nameInDb = SAVE_TIME_LIMIT)
    public int saveTimeLimit;
    //缓存链接
    @Property(nameInDb = SAVE_URL_KEY)
    public String saveUrlKey;
    //缓存链接的参数
    @Property(nameInDb = SAVE_PARAMS_KEY)
    public String paramsKey;
    //辅助查询参数
    @Property(nameInDb = HELP_QUERY_KEY)
    public String helpQueryParams;

    public CommonDataBean() {
    }

    public CommonDataBean(@NonNull String saveUrlKey, long currentTime, @TimeLimit int saveTimeLimit, String jsonData, String userId, Map<String, String> paramsMap) {
        this.jsonData = jsonData;
        this.saveTime = currentTime / 1000;
        this.saveTimeLimit = saveTimeLimit;
        this.saveUrlKey = saveUrlKey;
        this.userId = userId;
        this.paramsKey = encodeParamsMap(paramsMap);
    }

    public CommonDataBean(@NonNull String saveUrlKey, long currentTime, @TimeLimit int saveTimeLimit, String jsonData, String userId, Map<String, String> paramsMap, String helpQueryParams) {
        this(saveUrlKey, currentTime, saveTimeLimit, jsonData, userId, paramsMap);
        this.helpQueryParams = helpQueryParams;
    }

    public CommonDataBean(@NonNull String saveUrlKey, long currentTime, @TimeLimit int saveTimeLimit, String jsonData, String userId, Map<String, String> paramsMap, Map<String, String> helpQueryParamsMap) {
        this(saveUrlKey, currentTime, saveTimeLimit, jsonData, userId, paramsMap);
        this.helpQueryParams = encodeParamsMap(helpQueryParamsMap);
    }

    /**
     * 把参数排序后拼接成字符串，防止因为改变参数顺序后导致数据重复保存
     */
    public static String encodeParamsMap(Map<String, String> paramsMap) {
        StringBuilder builder = new StringBuilder();
        if (paramsMap != null && paramsMap.size() > 0) {
            TreeMap<String, String> treeMap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return !TextUtils.isEmpty(o1) ? o1.compareTo(o2) : 1;
                }
            });
            treeMap.putAll(paramsMap);
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            builder.deleteCharAt(builder.lastIndexOf("&"));
        }
        return builder.toString();
    }

    /**
     * 把字符串解析为map
     */
    public static Map<String, String> decodeParams2Map(String params) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(params)) {
            String[] split = params.split("&");
            for (String keyValue : split) {
                String[] res = keyValue.split("=");
                if (res.length == 2) {
                    map.put(res[0], res[1]);
                }
            }
        }
        return map;
    }

    protected CommonDataBean(Parcel in) {
        _id = in.readLong();
        jsonData = in.readString();
        userId = in.readString();
        helpQueryParams = in.readString();
        saveTime = in.readLong();
        saveTimeLimit = in.readInt();
        saveUrlKey = in.readString();
        paramsKey = in.readString();
    }

    @Generated(hash = 1322520423)
    public CommonDataBean(long _id, String jsonData, String userId, long saveTime, int saveTimeLimit, String saveUrlKey, String paramsKey, String helpQueryParams) {
        this._id = _id;
        this.jsonData = jsonData;
        this.userId = userId;
        this.saveTime = saveTime;
        this.saveTimeLimit = saveTimeLimit;
        this.saveUrlKey = saveUrlKey;
        this.paramsKey = paramsKey;
        this.helpQueryParams = helpQueryParams;
    }

    public static final Creator<CommonDataBean> CREATOR = new Creator<CommonDataBean>() {
        @Override
        public CommonDataBean createFromParcel(Parcel in) {
            return new CommonDataBean(in);
        }

        @Override
        public CommonDataBean[] newArray(int size) {
            return new CommonDataBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeString(jsonData);
        dest.writeString(userId);
        dest.writeString(helpQueryParams);
        dest.writeLong(saveTime);
        dest.writeInt(saveTimeLimit);
        dest.writeString(saveUrlKey);
        dest.writeString(paramsKey);
    }

    public long getId() {
        return this._id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public String getJsonData() {
        return this.jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getSaveTime() {
        return this.saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }

    public int getSaveTimeLimit() {
        return this.saveTimeLimit;
    }

    public void setSaveTimeLimit(int saveTimeLimit) {
        this.saveTimeLimit = saveTimeLimit;
    }

    public String getSaveUrlKey() {
        return this.saveUrlKey;
    }

    public void setSaveUrlKey(String saveUrlKey) {
        this.saveUrlKey = saveUrlKey;
    }

    public String getParamsKey() {
        return this.paramsKey;
    }

    public void setParamsKey(String paramsKey) {
        this.paramsKey = paramsKey;
    }

    public String getHelpQueryParams() {
        return this.helpQueryParams;
    }

    public void setHelpQueryParams(String helpQueryParams) {
        this.helpQueryParams = helpQueryParams;
    }

    public long get_id() {
        return this._id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }
}

package com.wdk.baselibrary.network.jsonconverter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.wdk.baselibrary.data.bean.ServiceDataBean;
import com.wdk.baselibrary.network.error.ResponseException;

import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/23 3:13 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/23 3:13 PM
 * @LastCheckBy: wdk
 */
public class NetGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final String TAG = "NetGsonResponseBodyConv";

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    NetGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        Log.e(TAG, "convert: "+json );
        JSONObject jsonObject = null;
        int errorCode = -1;
        String data = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (Exception e) {

        }

        if (jsonObject != null) {
            try {
                errorCode = jsonObject.getInt("errorCode");
            } catch (Exception e) {
                errorCode = -1;
            }
            try {
                data = jsonObject.getString("data");
            } catch (Exception e) {
                data = "{}";
            }

        }


        if (errorCode != 0) {
            throw new ResponseException();
        }

        try {
            return adapter.read(gson.newJsonReader(new StringReader(data)));
        } finally {
            value.close();
        }
    }
}

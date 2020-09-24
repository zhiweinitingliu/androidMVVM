package com.wdk.baselibrary.network.jsonconverter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.wdk.baselibrary.data.bean.ServiceDataBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/23 2:48 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/23 2:48 PM
 * @LastCheckBy: wdk
 */
public class NetGsonConverterFactory extends Converter.Factory {
    private static final String TAG = "NetGsonConverterFactory";

    private final Gson gson;

    public static NetGsonConverterFactory create() {
        return new NetGsonConverterFactory(new Gson());
    }

    public static NetGsonConverterFactory create(Gson gson) {
        return new NetGsonConverterFactory(gson);
    }

    private NetGsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new NetGsonResponseBodyConverter(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new NetGsonRequestBodyConverter<>(gson, adapter);
    }

}

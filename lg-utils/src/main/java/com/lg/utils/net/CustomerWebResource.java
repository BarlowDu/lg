package com.lg.utils.net;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dutc on 2016/11/8.
 */
public class CustomerWebResource {
    public static <T> T get(String url,Class<T> clazz) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);

        try {
            if (response.getStatusLine().getStatusCode() == 200) {
                String body = EntityUtils.toString(response.getEntity());
                return JSON.parseObject(body,clazz);
            }


        } finally {
            response.close();
        }

        return null;
    }


    public static <T> T post(String url, Map<String, Object> pairs,Map<String,String> headers,Class<T> clazz) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(url);
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        request.addHeader(null);
        UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params);
        for(Map.Entry<String, Object> kv :pairs.entrySet()){
            BasicNameValuePair p=new BasicNameValuePair(kv.getKey(),kv.getValue()==null?null:kv.getValue().toString());
            params.add(p);
        }
        for(Map.Entry<String,String> header:headers.entrySet()){
            request.setHeader(header.getKey(),header.getValue());
        }
        request.setEntity(entity);
        CloseableHttpResponse response = client.execute(request);

        try {
            if (response.getStatusLine().getStatusCode() == 200) {
                String body = EntityUtils.toString(response.getEntity());
                //System.out.println(body);
                return JSON.parseObject(body,clazz);
            }


        } finally {
            response.close();
        }

        return null;
    }


}

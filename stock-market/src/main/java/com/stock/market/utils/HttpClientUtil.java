package com.stock.market.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HttpClientUtil
{

    private CloseableHttpClient httpClient;
    private CookieStore cookieStore;
    private HttpClientContext context;
    private RequestConfig requestConfig;

    public static HttpClientUtil getInstance()
    {
        return new HttpClientUtil();
    }

    private HttpClientUtil()
    {
        context = HttpClientContext.create();
        cookieStore = new BasicCookieStore();
        // 配置超时时间（连接服务端超时1秒，请求数据返回超时2秒）
        requestConfig = RequestConfig.custom().setConnectTimeout( 120000 ).setSocketTimeout( 60000 )
                .setConnectionRequestTimeout( 60000 ).build();
        // 设置默认跳转以及存储cookie
        httpClient = HttpClientBuilder.create().setKeepAliveStrategy( new DefaultConnectionKeepAliveStrategy() )
                .setRedirectStrategy( new DefaultRedirectStrategy() ).setDefaultRequestConfig( requestConfig )
                .setDefaultCookieStore( cookieStore ).build();
    }

    public String get( String url ) throws Exception
    {
        StringBuilder _result = new StringBuilder();

        HttpGet httpget = new HttpGet( url );


        CloseableHttpResponse response = null;

        try
        {
            response = httpClient.execute( httpget, context );
//            printCookies();

            HttpEntity entity = response.getEntity();
            if ( entity != null )
            {
                InputStream instream = entity.getContent();
                int l;
                byte[] tmp = new byte[ 2048 ];
                while ( ( l = instream.read( tmp ) ) != -1 )
                {
                    _result.append( new String( tmp, 0, l ) );
                }
            }
        } finally
        {
            closeResponse( response );
        }

        return _result.toString();
    }

    public void post( String url, String parameters ) throws Exception
    {
        HttpPost httpPost = new HttpPost( url );
        List<NameValuePair> nvps = toNameValuePairList( parameters );
        httpPost.setEntity( new UrlEncodedFormEntity( nvps, "UTF-8" ) );
        CloseableHttpResponse response = null;
        try
        {
            response = httpClient.execute( httpPost, context );
//            printCookies();

        } finally
        {
            closeResponse( response );
        }
    }

    private static List<NameValuePair> toNameValuePairList(String parameters )
    {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String[] paramList = parameters.split( "&" );
        for ( String parm : paramList )
        {
            int index = -1;
            for ( int i = 0; i < parm.length(); i++ )
            {
                index = parm.indexOf( "=" );
                break;
            }
            String key = parm.substring( 0, index );
            String value = parm.substring( ++index, parm.length() );
            nvps.add( new BasicNameValuePair( key, value ) );
        }
//        LogUtil.info( nvps.toString() );

        return nvps;
    }

    private void closeResponse( CloseableHttpResponse response ) throws Exception
    {
        if ( response != null )
        {
            response.close();
        }
    }

    private void printCookies()
    {
        cookieStore = context.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for ( Cookie cookie : cookies )
        {
            log.info( "key:" + cookie.getName() + "  value:" + cookie.getValue() );
        }
    }
}
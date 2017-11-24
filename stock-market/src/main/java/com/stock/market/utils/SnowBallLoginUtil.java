package com.stock.market.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by UI03 on 2017/11/24.
 */
@Slf4j
public class SnowBallLoginUtil {
    @Value("${sb_login_url}")
    private static String url ;
    @Value("${sb_login_user}")
    private static String user ;
    @Value("${sb_login_pwd}")
    private static String pwd ;

    public static HttpClientUtil sbLogin(){
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance() ;
        StringBuilder paramStr = new StringBuilder().append( "username" ).append( "=" ).append(user).
                append( "&" ).append( "password" ).append( "=" ).append(pwd);
        try {
            httpClientUtil.post(url, paramStr.toString() );
        } catch (Exception e) {
            e.printStackTrace();
            log.error("login snowball error:"+e);
        }
        return httpClientUtil ;
    }
}

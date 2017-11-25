package com.stock.market.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by UI03 on 2017/11/24.
 */
@NoArgsConstructor
@Lazy(false)
@Slf4j
@Data
@Component
public class SnowBallLoginUtil {

    @Value("${sb_login_url}")
    private  String url ;
    @Value("${sb_login_user}")
    private  String user ;
    @Value("${sb_login_pwd}")
    private  String pwd ;

    public  HttpClientUtil sbLogin(){
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

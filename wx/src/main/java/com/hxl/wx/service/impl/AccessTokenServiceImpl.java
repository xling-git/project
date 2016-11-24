package com.hxl.wx.service.impl;

import com.hxl.wx.entity.AccessToken;
import com.hxl.wx.entity.WXConfig;
import com.hxl.wx.service.AccessTokenService;
import com.hxl.wx.service.HttpsService;
import com.hxl.wx.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hxl on 2016/11/21.
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    private static String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    @Autowired
    private HttpsService httpsService;
    private Logger logger = LoggerFactory.getLogger(AccessTokenService.class);

    @Override
    public AccessToken get() {
        String accessToken = httpsService.get(String.format(url, WXConfig.APP_ID, WXConfig.APP_SECRET));
        logger.info("access token get: {}", accessToken);
        return JsonUtils.toBean(accessToken, AccessToken.class);
    }

}
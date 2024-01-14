package com.lyra.job.handler;

import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestCookie;
import com.dtflys.forest.http.ForestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyra.job.domain.SignResponseDomain;
import com.lyra.job.sign.SignRequest;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JobHandler {
    private static final Logger log = LoggerFactory.getLogger(JobHandler.class);
    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("autoSignHandler")
    public void autoSignHandler() throws Exception {
        XxlJobHelper.log("开始签到");

        String username = System.getProperty("username");
        String password = System.getProperty("password");

        SignRequest client = Forest.client(SignRequest.class);
        ForestResponse<SignResponseDomain> login = client.login(username, password, "");

        List<ForestCookie> cookies = login.getCookies();

        ForestResponse<SignResponseDomain> sign = client.sign(((req, cookies1) -> {
            cookies1.addAllCookies(cookies);
        }));

        ObjectMapper objectMapper = new ObjectMapper();

        XxlJobHelper.log(objectMapper.writeValueAsString(sign.getResult()));
    }
}

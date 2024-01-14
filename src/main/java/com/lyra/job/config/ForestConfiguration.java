package com.lyra.job.config;

import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestAsyncMode;
import com.dtflys.forest.retryer.BackOffRetryer;

public class ForestConfiguration {
    public void init() {
        com.dtflys.forest.config.ForestConfiguration configuration = Forest.config();
        configuration.setBackendName("okhttp3");
        // 连接池最大连接数，默认值为500
        configuration.setMaxConnections(500);
        // 每个路由的最大连接数，默认值为500
        configuration.setMaxRouteConnections(500);
        // [自v1.5.22版本起可用] 最大请求等待队列大小
        configuration.setMaxRequestQueueSize(100);
        // [自v1.5.21版本起可用] 最大异步线程数
        configuration.setMaxAsyncThreadSize(300);
        // [自v1.5.22版本起可用] 最大异步线程池队列大小
        configuration.setMaxAsyncQueueSize(16);
        // 请求超时时间，单位为毫秒, 默认值为3000
        configuration.setTimeout(3000);
        // 连接超时时间，单位为毫秒, 默认值为2000
        configuration.setConnectTimeout(2000);
        // 设置重试器
        configuration.setRetryer(BackOffRetryer.class);
        // 请求失败后重试次数，默认为0次不重试
        configuration.setMaxRetryCount(0);
        // 单向验证的HTTPS的默认SSL协议，默认为TLS
        configuration.setSslProtocol("TLS");
        // 打开或关闭日志，默认为true
        configuration.setLogEnabled(true);
        // [自v1.5.27版本起可用] 异步模式（默认为 platform）
        configuration.setAsyncMode(ForestAsyncMode.PLATFORM);
    }
}

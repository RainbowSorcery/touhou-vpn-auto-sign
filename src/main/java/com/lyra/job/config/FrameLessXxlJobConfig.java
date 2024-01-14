package com.lyra.job.config;

import com.lyra.job.handler.JobHandler;
import com.xxl.job.core.executor.impl.XxlJobSimpleExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class FrameLessXxlJobConfig {
    private static Logger logger = LoggerFactory.getLogger(FrameLessXxlJobConfig.class);


    private static FrameLessXxlJobConfig instance = new FrameLessXxlJobConfig();

    public static FrameLessXxlJobConfig getInstance() {
        return instance;
    }


    private XxlJobSimpleExecutor xxlJobExecutor = null;

    /**
     * init
     */
    public void initXxlJobExecutor() {

        // load executor prop
        Properties xxlJobProp = loadProperties("xxl-job-executor.properties");

        // init executor
        xxlJobExecutor = new XxlJobSimpleExecutor();
        String xxlJobAdminAddress = System.getProperty("xxljobAdminAddress");
        xxlJobExecutor.setAdminAddresses(xxlJobAdminAddress);
        xxlJobExecutor.setAccessToken(xxlJobProp.getProperty("xxl.job.accessToken"));
        xxlJobExecutor.setAppname(xxlJobProp.getProperty("xxl.job.executor.appname"));
        xxlJobExecutor.setAddress(xxlJobProp.getProperty("xxl.job.executor.address"));
        xxlJobExecutor.setIp(xxlJobProp.getProperty("xxl.job.executor.ip"));
        xxlJobExecutor.setPort(Integer.valueOf(xxlJobProp.getProperty("xxl.job.executor.port")));
        xxlJobExecutor.setLogPath(xxlJobProp.getProperty("xxl.job.executor.logpath"));
        xxlJobExecutor.setLogRetentionDays(Integer.parseInt(xxlJobProp.getProperty("xxl.job.executor.logretentiondays")));

        // registry job bean
        xxlJobExecutor.setXxlJobBeanList(List.of(new JobHandler()));

        // start executor
        try {
            xxlJobExecutor.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * destroy
     */
    public void destroyXxlJobExecutor() {
        if (xxlJobExecutor != null) {
            xxlJobExecutor.destroy();
        }
    }


    public static Properties loadProperties(String propertyFileName) {
        InputStreamReader in = null;
        try {
            ClassLoader loder = Thread.currentThread().getContextClassLoader();

            in = new InputStreamReader(loder.getResourceAsStream(propertyFileName), "UTF-8");
            ;
            if (in != null) {
                Properties prop = new Properties();
                prop.load(in);
                return prop;
            }
        } catch (IOException e) {
            logger.error("load {} error!", propertyFileName);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("close {} error!", propertyFileName);
                }
            }
        }
        return null;
    }

}

package com.lyra.job;

import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestCookie;
import com.dtflys.forest.http.ForestResponse;
import com.lyra.job.config.ForestConfiguration;
import com.lyra.job.config.FrameLessXxlJobConfig;
import com.lyra.job.domain.SignResponseDomain;
import com.lyra.job.sign.SignRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JobApplication {
    private static final Logger logger = LoggerFactory.getLogger(JobApplication.class);

    public static void main(String[] args) {
        ForestConfiguration forestConfiguration = new ForestConfiguration();
        forestConfiguration.init();

        try {
            // start
            FrameLessXxlJobConfig.getInstance().initXxlJobExecutor();
            // Blocks until interrupted
            while (true) {
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            // destroy
            FrameLessXxlJobConfig.getInstance().destroyXxlJobExecutor();
        }

    }
}

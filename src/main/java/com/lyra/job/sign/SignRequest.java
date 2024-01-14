package com.lyra.job.sign;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Request;
import com.dtflys.forest.callback.OnLoadCookie;
import com.dtflys.forest.http.ForestResponse;
import com.lyra.job.domain.SignResponseDomain;

public interface SignRequest {
    @Request(value = "https://panel3.touhou.tel/auth/login", type = "post", contentEncoding = "utf-8")
    public ForestResponse<SignResponseDomain> login(@Body("email") String email, @Body("passwd") String password, @Body("code") String code);

    @Request(value = "https://panel3.touhou.tel/user/checkin", type = "post")
    public ForestResponse<SignResponseDomain> sign(OnLoadCookie onLoadCookie);
}

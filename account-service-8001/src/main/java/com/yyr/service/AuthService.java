package com.yyr.service;


import com.yyr.pojo.User;

/**
 * @Author 杨亚茹
 * @Date 2023/2/26 10:56
 * @PackageName:com.yyr.service
 * @ClassName: AuthService
 * @Description: TODO
 * @Version 1.0
 */
public interface AuthService {


    /**
     * 描述  通过token获取用户信息
     *
     * @param  token  token值
     * @return {@link null  }
     * @throws
     * @author
     */
    User getUserByToken(String token);

    /**
     * 描述  通过用户信息获取Token
     *
     * @param  user  用户信息
     * @return {@link null  }
     * @throws
     * @author
     */
    String getToken(User user);

    /**
     * 描述  登出
     *
     * @param  token  用户信息
     * @return {@link null  }
     * @throws
     * @author
     */
    void logout(String token);
}

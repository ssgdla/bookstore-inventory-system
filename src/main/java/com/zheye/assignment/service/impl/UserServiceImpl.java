package com.zheye.assignment.service.impl;

import com.zheye.assignment.constant.BusinessException;
import com.zheye.assignment.dto.LoginRequest;
import com.zheye.assignment.dto.RegisterRequest;
import com.zheye.assignment.mapper.UserMapper;
import com.zheye.assignment.model.User;
import com.zheye.assignment.model.UserExample;
import com.zheye.assignment.service.UserService;
import com.zheye.assignment.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import java.util.List;

import static com.zheye.assignment.constant.ResultCode.PASSWORD_ERROR;
import static com.zheye.assignment.constant.ResultCode.USER_EXIST;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;

    public void register(RegisterRequest registerRequest) {

        if (queryByUsername(registerRequest.getUsername()) != null) {
            throw new BusinessException(USER_EXIST);
        }

        String password = registerRequest.getPassword();
        String salt = registerRequest.getSalt();
        String md5Password = DigestUtils.md5DigestAsHex((password + salt).getBytes());

        User user = new User();
        user.setAdmin(0);
        user.setUsername(registerRequest.getUsername());
        user.setPassword(md5Password);
        user.setSalt(salt);

        userMapper.insert(user);

    }

    public String login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = queryByUsername(username);
        String md5Password = DigestUtils.md5DigestAsHex((password + user.getSalt()).getBytes());
        if (!md5Password.equals(user.getPassword())) {
            throw new BusinessException(PASSWORD_ERROR);
        }
        return JwtUtil.getToken(user);
    }

    public User queryByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(userExample);

        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

}

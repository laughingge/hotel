package com.example.hotel.serviceImpl;


import com.example.hotel.mapper.UserMapper;
import com.example.hotel.entity.User;
import com.example.hotel.service.UserService;
import com.example.hotel.utils.RedisUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    private static int ExpireTime = 60;   // redis中存储的过期时间60s
    private static final Logger log = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private RedisUtil redisUtil;

    @Override
    public List<User> getAllOnlyByMysql(int pageNum, int pageSize) {
        List<User> userList = userMapper.getAll();

        return userList;
    }


    @Override
    public List<User> getAll(int pageNum, int pageSize) {

        if (!redisUtil.hasKey("user")) {
            List<User> user = userMapper.getAll();
            for (User u : user) {
                //这里循环user 把每个对象存到 redis中list中
                redisUtil.addList("user", u, 60);
            }


            log.info("存进缓存成功");
            return user;

        }

        List<User> user = (List) redisUtil.getListPage("user", (pageNum - 1) * pageSize, pageSize * pageNum);
        log.info(String.valueOf(redisUtil.lGetListSize("user")));

        log.info("获取缓存成功");

        return user;
    }

    @Override
    public Integer insertUser(User user) {
        //先数据库增加再缓存增加
        if (userMapper.insertUser(user) == 1) {
            User user1 = userMapper.getUserByuname(user.getUname());
            redisUtil.lSet("user", user1);
            return 1;
        }
        return -1;
    }

    @Override
    public Integer deleteUsertByID(Integer userid) {
        User user = userMapper.getUserByuserid(userid);
        //先删除数据库再删除缓存
        Integer i = userMapper.deleteUsertByID(userid);
        redisUtil.lRemove("user", 1, user);


        return i;
    }

    @Override
    public User getUserByuserid(Integer userid) {
        return userMapper.getUserByuserid(userid);
    }

    @Override
    public Integer updateUserByID(User user) {
        redisUtil.lRemove("user", 1, user);
        if (userMapper.updateUserByID(user) != 1) return -1;
        User user1 = userMapper.getUserByuserid(user.getUserid());
        redisUtil.lSet("user", user1);
        return 1;
    }

    @Override
    public Integer getCount() {
        return userMapper.getCount();
    }

    @Override
    public User getUserByuname(String uname) {
        return userMapper.getUserByuname(uname);
    }
}


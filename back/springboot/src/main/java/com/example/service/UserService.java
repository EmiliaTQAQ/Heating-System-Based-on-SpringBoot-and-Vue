package com.example.service;

import com.example.common.Jwt;
import com.example.dao.RoomDao;
import com.example.dao.UserDao;
import com.example.entity.Heating_engineer;
import com.example.entity.Params;
import com.example.entity.Room;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private RoomDao roomDao;



    public User login(User user) {
        if (user.getAccount() == null || "".equals(user.getAccount())) {
            throw new CustomException("用户名不能为空");
        }
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        User userdemo = userDao.findByNameAndPassword(user.getAccount(), user.getPassword(),user.getRole());
        if (userdemo == null){
            throw new CustomException("信息输入错误");
        }
        String token = Jwt.generateJwt(userdemo.getRole(),user.getAccount(),user.getPassword());
        System.out.println(token);
        //生成该登录用户对应的token，然后跟着user一起返回到前台
        userdemo.setToken(token);
        return userdemo;

    }

    public User findByA_B(String account,String password,String role) {
        return userDao.findByNameAndPassword(account,password,role);
    }

    public User findById(Integer valueOf) {
        return userDao.findById(valueOf);
    }

    public PageInfo<User> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<User> list = userDao.findBySearch(params);
        return PageInfo.of(list);

    }

    public void add(User user) {

        if (user.getAccount() == null || "".equals(user.getAccount())) {
            throw new CustomException("账号不能为空");
        }
        if (user.getName() == null || "".equals(user.getName())) {
            throw new CustomException("姓名不能为空");
        }
        User user1 = userDao.findByAccount(user.getAccount());
        if (user1 != null) {
            throw new CustomException("账号已存在，请更换账号");
        }
        if (user.getCell().equals(null) && user.getBuilding().equals(null) && user.getGate().equals(null)) {
            throw new CustomException("地址不能为空");
        }
        if (userDao.findAddress(user.getAddress())) {
            throw new CustomException("地址已注册用户");
        }
        Integer x = roomDao.f(user.getCell(),user.getBuilding(),user.getGate());
        if (x == 0) {
            throw new CustomException("地址错误");
        }
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userDao.insertSelective(user);
    }

    public void update(User user) {
        if (userDao.findAddress(user.getAddress())) {
            throw new CustomException("地址已注册用户");
        } else {
            userDao.updateByPrimaryKeySelective(user);
        }

    }

    public void delete(Integer id) {
        userDao.deleteByPrimaryKey(id);
    }

    public List<User> find() {
        return userDao.find();
    }



    public void upB(String account, Double balance) {
        userDao.upB(account,balance);
    }

    public User findByAccount(String account) {
        return userDao.findByAccount(account);
    }

    public void upMessage(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }


    public Integer findBalance(Integer id) {
        Integer x = userDao.findBalance(id);
        return x;
    }

    public String tz(String account) {
        String x = userDao.tz(account);
        return x;
    }

}

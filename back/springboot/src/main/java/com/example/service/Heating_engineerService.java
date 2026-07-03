package com.example.service;

import com.example.common.Jwt;
import com.example.dao.Heating_engineerDao;
import com.example.entity.Heating_engineer;
import com.example.entity.Params;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Heating_engineerService {
    @Resource
    private Heating_engineerDao heating_engineerDao;


    public Heating_engineer login(Heating_engineer heating_engineer) {
        if (heating_engineer.getAccount() == null || "".equals(heating_engineer.getAccount())) {
            throw new CustomException("用户名不能为空");
        }
        if (heating_engineer.getPassword() == null || "".equals(heating_engineer.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        Heating_engineer user = heating_engineerDao.findByNameAndPassword(heating_engineer.getAccount(), heating_engineer.getPassword(),heating_engineer.getRole());
        if (user == null){
            throw new CustomException("信息输入错误");
        }
        String token = Jwt.generateJwt(user.getRole(),user.getAccount(),user.getPassword());
        System.out.println(token);
        //生成该登录用户对应的token，然后跟着user一起返回到前台
        user.setToken(token);
        return user;

    }

    public Heating_engineer findByA_B(String account,String password,String role) {
        return heating_engineerDao.findByNameAndPassword(account,password,role);
    }

    public PageInfo<Heating_engineer> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Heating_engineer> list = heating_engineerDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public Heating_engineer findById(Integer valueOf) {
        return heating_engineerDao.findById(valueOf);
    }

    public void add(Heating_engineer heatingEngineer) {
        if (heatingEngineer.getAccount() == null || "".equals(heatingEngineer.getAccount())) {
            throw new CustomException("账号不能为空");
        }
        if (heatingEngineer.getName() == null || "".equals(heatingEngineer.getName())) {
            throw new CustomException("姓名不能为空");
        }
        Heating_engineer user1 = heating_engineerDao.findByAccount(heatingEngineer.getAccount());
        if (user1 != null) {
            throw new CustomException("账号已存在，请更换账号");
        }
        if (heatingEngineer.getPassword() == null) {
            heatingEngineer.setPassword("123456");
        }
        heating_engineerDao.insertSelective(heatingEngineer);
    }

    public void update(Heating_engineer heatingEngineer) {
        heating_engineerDao.updateByPrimaryKeySelective(heatingEngineer);
    }

    public void delete(Integer id) {
        heating_engineerDao.deleteByPrimaryKey(id);
    }

    public Heating_engineer findByAccount(String account) {
        return heating_engineerDao.findByAccount(account);
    }

    public void upMessage(Heating_engineer heatingEngineer) {
        heating_engineerDao.updateByPrimaryKeySelective(heatingEngineer);
    }

    public List<String>  findByHeating() {
        return heating_engineerDao.findByHeating();
    }

    public String findName(String value) {
        return heating_engineerDao.findName(value);
    }
}

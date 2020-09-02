package com.tcl.mails.config;

import com.tcl.mails.dao.MailDao;
import com.tcl.mails.entity.Mail;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private MailDao mailDao;
    @Autowired
    private HttpServletRequest request;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String)principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<String> permissionList=new ArrayList<>();
        info.addStringPermissions(permissionList);

        if (name.equals("admin")){
            info.addRole("admin");
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String mail = (String) token.getPrincipal();
        String code = new String((char[]) token.getCredentials());

        //判断是否存在用户，不存在进行注册
        Mail m = mailDao.findByMail(mail);
        if (null == m){
            Mail m1 = new Mail(mail);
            m=mailDao.save(m1);
        }else {
            if (m.getState()==0){
                throw new AuthenticationException();
            }
        }
        //判断是否为管理员
        if (mail.equals("admin")){
            //判断管理员密码
            if (code.equals("admin")){
                return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),getName());
            }else {
                throw new AuthenticationException();
            }
        }else {
            //判断输入邮箱和验证码是否相同
            if (mail.equals(request.getSession().getAttribute("mail"))
                    &&code.equals(request.getSession().getAttribute("code"))){
                return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),getName());
            }else {
                throw new AuthenticationException();
            }
        }
    }
}

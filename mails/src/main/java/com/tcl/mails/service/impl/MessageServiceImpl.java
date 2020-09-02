package com.tcl.mails.service.impl;

import com.tcl.mails.dao.MessageDao;
import com.tcl.mails.entity.Message;
import com.tcl.mails.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private HttpSession session;

//    //生成验证码用于登录或注册
//    @Override
//    public String sendCode(String mail) {
//
//        //随机生成6位验证码
//        int s = (int) (Math.random() * 1000000);
//        //将s转为6位的字符串，如果不足左边补0
//        String code = String.format("%06d", s);
////        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
////        String date = simpleDateFormat.format(new Date());
//        //将验证码和邮箱保存到数据库中
////        Message codeMessage = new Message("admin", mail, "验证码", code, date);
////        messageDao.save(codeMessage);
//        //将验证码发送达到网络邮箱中
//        String text ="你收到了4AM系统发来的登录验证，验证码为："+code+"该验证码十分钟内有效。";
//        SendEmail.domail(mail,"验证码", text);
//        return code;
//    }

    /**
     * 发送邮件
     *
     * @param receiveMail 收件人
     * @param title       标题
     * @param message     内容
     * @return
     */
    @Override
    public Message sendMessage(String receiveMail, String title, String message) {
        //获取当前登录的邮箱
        String sendMail = (String) session.getAttribute("mail");
        //创建发送的时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = simpleDateFormat.format(new Date());
        //将发送的信息保存到对象中
        Message sendMessage = new Message(sendMail, receiveMail, title, message, date);
        Message s = null;
        s = messageDao.save(sendMessage);
        return s;
    }

    /**
     * 查询收件箱的内容
     * receiveStale!=2时为收到的邮件
     *
     * @param mail 收件人的邮箱
     * @return
     */
    @Override
    public List<Message> receiveMessage(String mail) {
        List<Message> list = messageDao.findByReceiveMailAndReceiveState(mail, 1);
        return list;
    }

    /**
     * 查询垃圾箱的内容
     * receiveStale==2时为垃圾邮件
     *
     * @param mail 收件人的邮箱
     * @return
     */
    @Override
    public List<Message> dustbin(String mail) {
        List<Message> list = messageDao.findByReceiveMailAndReceiveState(mail, 2);
        return list;
    }

    /**
     * 查看已经发送的邮件
     * sendState==1时为已经发送
     *
     * @param mail 发件人的邮箱
     * @return
     */
    @Override
    public List<Message> findSendMessage(String mail) {
        List<Message> list = messageDao.findBySendMailAndSendState(mail, 1);
        return list;
    }

    /**
     * 得到需要的邮件对象
     * @param id
     * @return
     */
    @Override
    public Message findById(int id) {
        Message message = null;
        try {
             message = messageDao.getOne(id);
        }catch (Exception e){
            System.out.println("没有对应的邮件");
        }
        return message;
    }

    /**
     * 发件人删除相应的邮件(将状态改为0)
     * @param id
     * @return
     */
    @Override
    public Message updateSendState(int id) {
        Message message = findById(id);
        message.setSendState(0);
        return messageDao.save(message);
    }

    /**
     * 收件人删除相应的邮件(将状态改为2)
     * @param id
     * @return
     */
    @Override
    public Message updateReceiveState(int id) {
        Message message = findById(id);
        message.setReceiveState(2);
        return messageDao.save(message);
    }

    /**
     * 垃圾箱删除相应的邮件（状态改为0）
     * @param id
     * @return
     */
    @Override
    public Message updateDustbinState(int id) {
        Message message = findById(id);
        message.setReceiveState(0);
        return messageDao.save(message);
    }

    /**
     * 定时清理无指向的数据
     */
    @Scheduled(fixedDelay = 60*60*1000)
    void delMessage(){
        messageDao.delete(0,0);
        System.out.println("消息更新成功");
    }
}

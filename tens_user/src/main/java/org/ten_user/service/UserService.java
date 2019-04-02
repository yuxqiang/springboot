package org.ten_user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ten_common.util.IdWorker;
import org.ten_user.dao.UserDao;
import org.ten_user.pojo.User;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * 发送短信验证码
	 * 
	 * @param mobile 手机号
	 */
	public void sendSms(String mobile) {
		// 1.生成6位短信验证码
		Random random = new Random();
		int max = 999999;// 最大数
		int min = 100000;// 最小数
		int code = random.nextInt(max);// 随机生成
		if (code < min) {
			code = code + min;
		}
		System.out.println(mobile + "收到验证码是：" + code);
		// 2.将验证码放入redis
		redisTemplate.opsForValue().set("smscode_" + mobile, code + "", 5, TimeUnit.MINUTES);// 五分钟过期
		// 3.将验证码和手机号发动到rabbitMQ中
		Map<String, String> map = new HashMap();
		map.put("mobile", mobile);
		map.put("code", code + "");
		rabbitTemplate.convertAndSend("sms", map);
	}

	/**
	 * 增加
	 * 
	 * @param user 用户
	 * @param code 用户填写的验证码
	 */
	public void add(User user, String code) {
		// 判断验证码是否正确
		String syscode = (String) redisTemplate.opsForValue().get("smscode_" + user.getMobile());
		// 提取系统正确的验证码
		if (syscode == null) {
			throw new RuntimeException("请点击获取短信验证码");
		}
		if (!syscode.equals(code)) {
			throw new RuntimeException("验证码输入不正确");
		}
		user.setId(idWorker.nextId() + "");
		user.setFollowcount(0);// 关注数
		user.setFanscount(0);// 粉丝数
		user.setOnline(0L);// 在线时长
		user.setRegdate(new Date());// 注册日期
		user.setUpdatedate(new Date());// 更新日期
		user.setLastdate(new Date());// 最后登陆日期
		userDao.save(user);
	}

	/**
	 * 查询全部标签
	 * 
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @return
	 */
	public User findById(String id) {
		return userDao.findById(id).get();
	}

	public void add(User user) {
		user.setId(idWorker.nextId() + "");// 设置ID
		userDao.save(user);
	}

	/**
	 * 修改标签
	 * 
	 * @param label
	 */
	public void update(User user) {
		userDao.save(user);
	}

	/**
	 * 删除标签
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		userDao.deleteById(id);
	}

}
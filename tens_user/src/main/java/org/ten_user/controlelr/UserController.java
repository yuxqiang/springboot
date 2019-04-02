package org.ten_user.controlelr;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ten_common.entity.Result;
import org.ten_common.entity.StatusCode;
import org.ten_user.pojo.User;
import org.ten_user.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * 发送短信验证码
	 * 
	 * @param mobile
	 */
	@RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
	public Result sendsms(@PathVariable String mobile) {
		userService.sendSms(mobile);
		return new Result(true, StatusCode.OK, "发送成功");
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 */
	@RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
	public Result register(@RequestBody User user, @PathVariable String code) {
		userService.add(user, code);
		return new Result(true, StatusCode.OK, "注册成功");
	}

	/**
	 * 查询全部列表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public Result<List> findAll() {
		return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result<User> findById(@PathVariable String id) {
		return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
	}

	/**
	 * 增加标签
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@RequestBody User user) {
		userService.add(user);
		return new Result(true, StatusCode.OK, "增加成功");
	}

	/**
	 * 修改标签
	 * 
	 * @param label
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id) {
		user.setId(id);
		userService.update(user);
		return new Result(true, StatusCode.OK, "修改成功");
	}

	/**
	 * 删除标签
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable String id) {
		userService.deleteById(id);
		return new Result(true, StatusCode.OK, "删除成功");
	}

}

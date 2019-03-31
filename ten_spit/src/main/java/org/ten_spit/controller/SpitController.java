package org.ten_spit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ten_common.entity.PageResult;
import org.ten_common.entity.Result;
import org.ten_common.entity.StatusCode;
import org.ten_spit.pojo.Spit;
import org.ten_spit.service.SpitService;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
	@Autowired
	private SpitService spitService;
	@Autowired
	private RedisTemplate redisTemplate;
/**
* 查询全部数据
* @return
*/
@RequestMapping(method= RequestMethod.GET)
public Result findAll(){
	return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
}
/**
* 根据ID查询
* @param id ID
* @return
*/
@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Result findOne(@PathVariable String id){
	return new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
}
/**
* 增加
* @param spit
*/
@RequestMapping(method=RequestMethod.POST)
public Result add(@RequestBody Spit spit ){
	spitService.add(spit);
	return new Result(true,StatusCode.OK,"增加成功");
}
/**

（1）SpitDao新增方法定义
（2）SpitService新增方法
* 修改
* @param spit
*/
@RequestMapping(value="/{id}",method=RequestMethod.PUT)
public Result update(@RequestBody Spit spit,@PathVariable String id )
{
	spit.set_id(id);
	spitService.update(spit);
	return new Result(true,StatusCode.OK,"修改成功");
}
/**
* 删除
* @param id
*/
@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
public Result deleteById(@PathVariable String id ){
	spitService.deleteById(id);
	return new Result(true,StatusCode.OK,"删除成功");
	}


/**
* 根据上级ID查询吐槽分页数据
* @param page
* @param size
* @return
*/
@RequestMapping(value="/comment/{parentId}/{page}/{size}",method=RequestMethod.GET)
public Result findByParentid(@PathVariable String parentId,
@PathVariable int page,@PathVariable int size){
Page<Spit> pageList = spitService.findByParentid(parentId,page,
size);
return new Result(true,StatusCode.OK,"查询成功",new
PageResult<Spit>(pageList.getTotalElements(), pageList.getContent() ) );
}

/**
* 点赞
* @param id
* @return
*//*
@RequestMapping(value="/thumbup/{id}",method=RequestMethod.PUT)
public Result updateThumbup(@PathVariable String id){
spitService.updateThumbup(id);
return new Result(true,StatusCode.OK,"点赞成功");
}*/


/**
* 吐槽点赞
* @param id
* @return
*/
@RequestMapping(value = "/thumbup/{id}", method = RequestMethod.PUT)
public Result updateThumbup(@PathVariable String id){
//判断用户是否点过赞
String userid="2023";// 后边我们会修改为当前登陆的用户
if(redisTemplate.opsForValue().get("thumbup_"+userid+"_"+
id)!=null){
return new Result(false,StatusCode.REPERROR,"你已经点过赞了");
}
spitService.updateThumbup(id);
redisTemplate.opsForValue().set( "thumbup_"+userid+"_"+ id,"1");
return new Result(true,StatusCode.OK,"点赞成功");
}
}


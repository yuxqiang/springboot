package org.ten_recruit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ten_common.entity.PageResult;
import org.ten_common.entity.Result;
import org.ten_common.entity.StatusCode;
import org.ten_recruit.pojo.Recruit;
import org.ten_recruit.service.RecruitService;

@RestController
@RequestMapping("/recruit")
@CrossOrigin
public class RecruitController {

	@Autowired
	private RecruitService recruitService;

	/**
	 * 查询全部列表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public Result<List> findAll() {
		return new Result(true, StatusCode.OK, "查询成功", recruitService.findAll());
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result<Recruit> findById(@PathVariable String id) {
		return new Result(true, StatusCode.OK, "查询成功", recruitService.findById(id));
	}

	/**
	 * 增加标签
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@RequestBody Recruit recruit) {
		recruitService.add(recruit);
		return new Result(true, StatusCode.OK, "增加成功");
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Result<List> findSearch(@RequestBody Map searchMap) {
		return new Result<>(true, StatusCode.OK, "查询成功", recruitService.findSearch(searchMap));
	}

	/**
	 * 修改标签
	 * 
	 * @param label
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Result update(@RequestBody Recruit recruit, @PathVariable String id) {
		recruit.setId(id);
		recruitService.update(recruit);
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
		recruitService.deleteById(id);
		return new Result(true, StatusCode.OK, "删除成功");
	}

	/**
	 * 条件+分页查询
	 * 
	 * @param searchMap
	 * @param page
	 * @param size
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
	public Result<List> findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
		Page pageList = recruitService.findSearch(searchMap, page, size);
		return new Result(true, StatusCode.OK, "查询成功",
				new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
	}

	@RequestMapping(value = "/search/recommend", method = RequestMethod.GET)
	public Result recommend() {
		List<Recruit> list = recruitService.findTop4ByStateOrderByCreatetimeDesc("2");
		return new Result(true, StatusCode.OK, "查询成功", list);
	}

	/**
	 * 最新职位列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search/newlist", method = RequestMethod.GET)
	public Result newlist() {
		return new Result(true, StatusCode.OK, "查询成功", recruitService.newlist());
	}
}

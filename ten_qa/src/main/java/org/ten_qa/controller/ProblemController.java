package org.ten_qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ten_common.entity.PageResult;
import org.ten_common.entity.Result;
import org.ten_common.entity.StatusCode;
import org.ten_qa.pojo.Problem;
import org.ten_qa.service.ProblemService;

@RestController
@RequestMapping("/problem")
@CrossOrigin
public class ProblemController {

	@Autowired
	private ProblemService problemService;

	/**
	 * 根据标签ID查询最新问题列表
	 * 
	 * @param labelid
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
	public Result findNewListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
		Page<Problem> pageList = problemService.findNewListByLabelId(labelid, page, size);
		PageResult<Problem> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
		return new Result(true, StatusCode.OK, "查询成功", pageResult);
	}

	/**
	 * 根据标签ID查询热门问题列表
	 * 
	 * @param labelid
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/hotlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
	public Result findHotListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
		Page<Problem> pageList = problemService.findHotListByLabelId(labelid, page, size);
		PageResult<Problem> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
		return new Result(true, StatusCode.OK, "查询成功", pageResult);
	}

	/**
	 * 根据标签ID查询等待回答列表
	 * 
	 * @param labelid
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/waitlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
	public Result findWaitListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
		Page<Problem> pageList = problemService.findWaitListByLabelId(labelid, page, size);
		PageResult<Problem> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
		return new Result(true, StatusCode.OK, "查询成功", pageResult);
	}
}

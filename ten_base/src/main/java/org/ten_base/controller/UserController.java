package org.ten_base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ten_base.pojo.Label;
import org.ten_base.service.LabelService;
import org.ten_common.entity.Result;
import org.ten_common.entity.StatusCode;

@RestController
@RequestMapping("/label")
@CrossOrigin
public class UserController {

	@Autowired
	private LabelService labelService;

	/**
	 * 查询全部列表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public Result<List> findAll() {
		return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result<Label> findById(@PathVariable String id) {
		return new Result(true, StatusCode.OK, "查询成功", labelService.findById(id));
	}

	/**
	 * 增加标签
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@RequestBody Label label) {
		labelService.add(label);
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
	public Result update(@RequestBody Label label, @PathVariable String id) {
		label.setId(id);
		labelService.update(label);
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
		labelService.deleteById(id);
		return new Result(true, StatusCode.OK, "删除成功");
	}
}

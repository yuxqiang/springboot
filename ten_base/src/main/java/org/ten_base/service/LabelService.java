package org.ten_base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ten_base.dao.LabelDao;
import org.ten_base.pojo.Label;
import org.ten_common.util.IdWorker;

@Service
public class LabelService {

	@Autowired
	private LabelDao labelDao;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部标签
	 * 
	 * @return
	 */
	public List<Label> findAll() {
		return labelDao.findAll();
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @return
	 */
	public Label findById(String id) {
		return labelDao.findById(id).get();
	}

	public void add(Label label) {
		label.setId(idWorker.nextId() + "");// 设置ID
		labelDao.save(label);
	}

	/**
	 * 修改标签
	 * 
	 * @param label
	 */
	public void update(Label label) {
		labelDao.save(label);
	}

	/**
	 * 删除标签
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		labelDao.deleteById(id);
	}
}

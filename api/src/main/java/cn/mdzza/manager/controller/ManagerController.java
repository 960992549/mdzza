package cn.mdzza.manager.controller;

import cn.mdzza.dto.Result;
import cn.mdzza.manager.entity.Manager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ydt on 2017/2/13.
 */
@RestController
@RequestMapping("manager")
public class ManagerController {

	/**
	 * 添加
	 * @param manager
	 * @return
	 */
	@RequestMapping("add")
	public Result<Object> add(Manager manager) {
		return null;
	}
}

package com.personal.blog.web.controller.admin;

import com.personal.blog.base.lang.Result;
import com.personal.blog.config.ContextStartup;
import com.personal.blog.modules.service.OptionsService;
import com.personal.blog.modules.service.PostSearchService;
import com.personal.blog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 系统配置
 * @author weizp
 *
 */
@Controller
@RequestMapping("/admin/options")
public class OptionsController extends BaseController {
	@Autowired
	private OptionsService optionsService;
	@Autowired
	private PostSearchService postSearchService;
	@Autowired
	private ContextStartup contextStartup;

	@RequestMapping("/index")
	public String index(ModelMap model) {
		return "/admin/options/index";
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam Map<String, String> body, ModelMap model) {
		optionsService.update(body);
		contextStartup.reloadOptions(false);
		model.put("data", Result.success());
		return "/admin/options/index";
	}

	/**
	 * 刷新系统变量
	 * @return
	 */
	@RequestMapping("/reload_options")
	@ResponseBody
	public Result reloadOptions() {
		contextStartup.reloadOptions(false);
		contextStartup.resetChannels();
		return Result.success();
	}

	@RequestMapping("/reset_indexes")
	@ResponseBody
	public Result resetIndexes() {
		postSearchService.resetIndexes();
		return Result.success();
	}
}

package com.personal.blog.web.controller.admin;

import com.personal.blog.base.lang.Result;
import com.personal.blog.base.lang.Consts;
import com.personal.blog.config.ContextStartup;
import com.personal.blog.modules.entity.Channel;
import com.personal.blog.modules.service.ChannelService;
import com.personal.blog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 栏目管理控制器
 * @author weizp
 */
@Controller("adminChannelController")
@RequestMapping("/admin/channel")
public class ChannelController extends BaseController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ContextStartup contextStartup;
	
	@RequestMapping("/list")
//	@RequiresPermissions("channel:list")
	public String list(ModelMap model) {
		model.put("list", channelService.findAll(Consts.IGNORE));
		return "/admin/channel/list";
	}
	
	@RequestMapping("/view")
	public String view(Integer id, ModelMap model) {
		if (id != null) {
			Channel view = channelService.getById(id);
			model.put("view", view);
		}
		return "/admin/channel/view";
	}
	
	@RequestMapping("/update")
//	@RequiresPermissions("channel:update")
	public String update(Channel view) {
		if (view != null) {
			channelService.update(view);

			contextStartup.resetChannels();
		}
		return "redirect:/admin/channel/list";
	}

	@RequestMapping("/weight")
	@ResponseBody
	public Result weight(@RequestParam Integer id, HttpServletRequest request) {
		int weight = ServletRequestUtils.getIntParameter(request, "weight", Consts.FEATURED_ACTIVE);
		channelService.updateWeight(id, weight);
		contextStartup.resetChannels();
		return Result.success();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
//	@RequiresPermissions("channel:delete")
	public Result delete(Integer id) {
		Result data = Result.failure("操作失败");
		if (id != null) {
			try {
				channelService.delete(id);
				data = Result.success();

				contextStartup.resetChannels();
			} catch (Exception e) {
				data = Result.failure(e.getMessage());
			}
		}
		return data;
	}
	
}

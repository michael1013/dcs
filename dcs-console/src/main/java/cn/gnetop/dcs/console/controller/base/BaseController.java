package cn.gnetop.dcs.console.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gnetop.dcs.console.service.base.BaseService;
import cn.gnetop.dcs.dao.base.DataEntity;
import cn.gnetop.dcs.dao.base.Page;
import cn.gnetop.pde.foundation.DateUtils;
import cn.gnetop.pde.foundation.JsonUtils;

public class BaseController<T extends DataEntity, S extends BaseService<T>> {

	@Autowired
	protected S service;

	@RequestMapping(value = { "", "/", "/page" })
	public String page(HttpServletRequest request, Model model, T t) {
		Page<T> page = service.findPage(t);
		model.addAttribute("page", page);
		return this.getName(t) + "/page";
	}

	@RequestMapping("/get")
	public String get(HttpServletRequest request, Model model, T t) {
		String name = this.getName(t);
		t = service.findOne(t);
		model.addAttribute(name, t);
		return name + "/get";
	}

	@ResponseBody
	@RequestMapping("/get/json")
	public String getJson(HttpServletRequest request, T t) {
		if (StringUtils.isNotBlank(t.getId())) {
			t = service.findOne(t);
		}
		return JsonUtils.toString(t);
	}

	@ResponseBody
	@RequestMapping("/list/json")
	public String listJson(Model model, HttpServletRequest request, T t) {
		t.setDelFlag(null);
		Page<T> page = service.findPage(t);
		return JsonUtils.toString(page);
	}

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request, T t) {
		t.setDelFlag(null);
		Page<T> page = service.findPage(t);
		model.addAttribute("page", page);
		return this.getName(t) + "/list";
	}

	@RequestMapping("/all")
	public String all(Model model, HttpServletRequest request, T t) {
		t.setDelFlag(null);
		Page<T> page = service.findAllPage(t);
		model.addAttribute("page", page);
		return this.getName(t) + "/list";
	}

	@ResponseBody
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model, T t) {
		try {
			t.setUpdateTime(DateUtils.getDateString());
			service.update(t);
		} catch (Exception e) {
			return "failed:" + e.getStackTrace();
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, Model model, T t) {
		try {
			t.setCreateTime(DateUtils.getDateString());
			service.insert(t);
		} catch (Exception e) {
			return "failed:" + e.getStackTrace();
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model, T t) {
		try {
			t.setUpdateTime(DateUtils.getDateString());
			t.setDelFlag(new Integer(1));
			service.update(t);
		} catch (Exception e) {
			return "failed:" + e.getStackTrace();
		}
		return "success";
	}

	protected String getName(T t) {
		return t.getClass().getSimpleName().toLowerCase();
	}
}

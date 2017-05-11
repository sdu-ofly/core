package com.ofly.core.admin.cotroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.Module;

@Controller
@RequestMapping("/resources")
public class ResourcesController {
	private static final String PATH_INIT = "core/admin/resources"; 
	@RequestMapping("/init")
	public String init() {
		return PATH_INIT;
	}
	
	@RequestMapping("/queryList")
	@ResponseBody
	public JSONObject queryList(@RequestParam(required = false) String condition, Integer page,Integer rows) {
		JSONObject result = new JSONObject();
		result.put("total", 100);
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "zhangsan");
		list.add(map);
		result.put("rows", list);
		return result;
	}
}

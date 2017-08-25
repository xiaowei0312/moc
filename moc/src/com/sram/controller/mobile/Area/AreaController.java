package com.sram.controller.mobile.Area;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.sram.controller.mobile.BaseController;
import com.sram.entity.Area;
import com.sram.service.AreaService;

/**
 * 手机的端的在地区
 * @author Administrator
 *
 */
@Controller("mobileAreaController")
@RequestMapping("/mobile/area")
public class AreaController extends BaseController {
	@Resource(name = "areaServiceImpl")
	private AreaService areaService;
	
	/**
	 * 手机的端的地区列表
	 * @param member
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public void  register(HttpServletResponse response){
		PrintWriter out=null;
		try {
		    response.setContentType("text/json"); 
			response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			Map<String,Object> map = new HashMap<String,Object>();
			List<Area> list = areaService.findRoots();
			map.put("status", "0");
			map.put("msg", "请求成功");
			List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
			for (Area area : list) {
				Map<String,Object> map2 = new HashMap<String,Object>();
				map2.put("id", area.getId());
				map2.put("name",area.getName());
				list2.add(map2);
			}
			map.put("Areas",list2 );
			System.out.println(JSON.toJSON(map).toString());
			out.print(JSON.toJSON(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", "1");
			map.put("msg", "请求失败");
			out.print(JSON.toJSON(map).toString());
		}
	}
}

package com.sram.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Course;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseLessonLearn;
import com.sram.entity.Member;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sram.service.CourseLearnService;
import com.sram.service.CourseLessonLearnService;
import com.sram.service.CourseLessonService;
import com.sram.service.CourseService;
import com.sram.service.MemberService;

/**
 * 
 * @author limin
 * 统计controller
 */
@Controller("adminAnalysisStatisticController")
@RequestMapping("/admin/analysisstatistic")
public class AnalysisStatisticController {
	
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	
	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
	
	@Resource(name = "courseLessonServiceImpl")
	private CourseLessonService courseLessonService;
	
	@Resource(name="courseLearnServiceImpl")
	private CourseLearnService courseLearnService;
	
	@Resource(name="courseLessonLearnServiceImpl")
	private CourseLessonLearnService courseLessonLearnService;
	
	/**
	 * 数据统计index
	 * @throws ParseException 
	 */
	@RequestMapping(value="/dataAnalysisList")
	public String dataAnalysisList(Pageable pageable,String beginDateStr,String endDateStr,ModelMap model,String analysisType){
		if(analysisType==null || ("").equals(analysisType)){
			analysisType="register";
		}
		Date beginDate=null;
		Date endDate=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
				if(beginDateStr==null || beginDateStr.equals("")){
					beginDate=currentMonthFirstDay();
				}else{
					beginDate=sdf.parse(beginDateStr);
				}
				if(endDateStr==null|| endDateStr.equals("")){
					endDate=new Date();
				}else{
					endDate=sdf.parse(endDateStr);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		if (beginDate != null) {
			Calendar calendar = DateUtils.toCalendar(beginDate);
			calendar.set(Calendar.HOUR_OF_DAY,
					calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE,
					calendar.getActualMinimum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND,
					calendar.getActualMinimum(Calendar.SECOND));
			beginDate = calendar.getTime();
		}
		
		if (endDate != null) {
			Calendar calendar = DateUtils.toCalendar(endDate);
			calendar.set(Calendar.HOUR_OF_DAY,
					calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE,
					calendar.getActualMaximum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND,
					calendar.getActualMaximum(Calendar.SECOND));
			endDate = calendar.getTime();
		}

		model.addAttribute("beginDateStr",beginDate);
		model.addAttribute("endDateStr",endDate);
		model.addAttribute("analysisType",analysisType);
		if(("register").equals(analysisType) || ("allUserCount").equals(analysisType)){
			Page<Member> page=memberService.findPage(pageable,beginDate,endDate);
			model.addAttribute("page", page);
			return "/admin/statistic/registerStaList";
		}else if(("course").equals(analysisType) || ("allCourseCount").equals(analysisType)){
			Page<Course> page=courseService.findPage(pageable,beginDate,endDate);
			model.addAttribute("page",page);
			return "/admin/statistic/newCourseStaList";
		}else if(("lesson").equals(analysisType)){
			Page<CourseLesson> page=courseLessonService.findPage(pageable,beginDate,endDate);
			model.addAttribute("page",page);
			return "/admin/statistic/newLessonStaList";
		}else if(("courseLearn").equals(analysisType)){
			//考虑到延迟加载影响效率，采用本地sql查询,三个实体获取数据
			Page<Object[]> page=courseLearnService.findPage(pageable,beginDate,endDate);
			model.addAttribute("page",page);
			return "/admin/statistic/courseLearnStaList";
		}else if(("finishedLesson").equals(analysisType)){
			//考虑到延迟加载影响效率，采用本地sql查询,三个实体获取数据
			Page<Object[]> page=courseLessonLearnService.findPage(pageable,beginDate,endDate);
			model.addAttribute("page",page);
			return "/admin/statistic/finishedLessonStaList";
		}
		return "";
	}
	
	/**
	 * 数据统计index
	 * @throws ParseException 
	 */
	@RequestMapping(value="/dataAnaChartIndex")
	public String dataAnaChartIndex(ModelMap model,String analysisType) throws ParseException{
		Date beginDate=currentMonthFirstDay();
		Date endDate=new Date();
		model.addAttribute("tabFlag","detail");
		model.addAttribute("beginDateStr",beginDate);
		model.addAttribute("endDateStr",endDate);
		model.addAttribute("analysisType", analysisType);
		return "/admin/statistic/dataAnaChartIndex";
	}
	
	/**
	 * 数据统计index
	 * @throws ParseException 
	 */
	@RequestMapping(value="/dataAnaChartLine")
	public String dataAnaChartLine(String beginDateStr,String endDateStr,ModelMap model,String analysisType){
		if(analysisType==null || ("").equals(analysisType)){
			analysisType="register";
		}
		Date beginDate=null;
		Date endDate=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
				if(beginDateStr==null || beginDateStr.equals("")){
					beginDate=currentMonthFirstDay();
				}else{
					beginDate=sdf.parse(beginDateStr);
				}
				if(endDateStr==null|| endDateStr.equals("")){
					endDate=new Date();
				}else{
					endDate=sdf.parse(endDateStr);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		if (beginDate != null) {
			Calendar calendar = DateUtils.toCalendar(beginDate);
			calendar.set(Calendar.HOUR_OF_DAY,
					calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE,
					calendar.getActualMinimum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND,
					calendar.getActualMinimum(Calendar.SECOND));
			beginDate = calendar.getTime();
		}
		
		if (endDate != null) {
			Calendar calendar = DateUtils.toCalendar(endDate);
			calendar.set(Calendar.HOUR_OF_DAY,
					calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE,
					calendar.getActualMaximum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND,
					calendar.getActualMaximum(Calendar.SECOND));
			endDate = calendar.getTime();
		}
		
		List<Object[]> list=new ArrayList<Object[]>();
		if(("register").equals(analysisType) || ("allUserCount").equals(analysisType)){
			/** 根据天统计会员注册*/
			list=memberService.findAnalySisList(beginDate,endDate);
		}else if(("course").equals(analysisType) || ("allCourseCount").equals(analysisType)){
			/**根据天统计课程新增情况*/
			list=courseService.findAnalySisList(beginDate,endDate);
		}else if(("lesson").equals(analysisType)){
			/**根据天统计课时新增情况*/
			list=courseLessonService.findAnalySisList(beginDate,endDate);
		}else if(("courseLearn").equals(analysisType)){
			/**指定时间范围课程学习情况*/
			list=courseLearnService.findAnalySisList(beginDate,endDate);
		}else if(("finishedLesson").equals(analysisType)){
			/**指定时间范围课时完成情况*/
			list=courseLessonLearnService.findAnalySisList(beginDate,endDate,com.sram.entity.CourseLessonLearn.Status.finished);
		}
		List<HashMap> dateList=new ArrayList<HashMap>();
		List<HashMap> countList=new ArrayList<HashMap>();
		if(("allUserCount").equals(analysisType) || ("allCourseCount").equals(analysisType)){
			//汇总每天总数
			int sum=0;
			for(Object[] obj:list){
				HashMap date=new HashMap();
				HashMap count=new HashMap();
				date.put("label",obj[0].toString());
				sum+=Integer.parseInt(String.valueOf(obj[1]));
				count.put("value",""+sum+"");
				dateList.add(date);
				countList.add(count);
			}
		}else{
			for(Object[] obj:list){
				HashMap date=new HashMap();
				HashMap count=new HashMap();
				date.put("label",obj[0].toString());
				count.put("value",obj[1].toString());
				dateList.add(date);
				countList.add(count);
			}
		}
		
		String dateStr=JSON.toJSONString(dateList);
		String countStr=JSON.toJSONString(countList);
		dateStr=dateStr.replaceAll("\"", "'");
		countStr=countStr.replaceAll("\"","'");
		
		model.addAttribute("beginDateStr",beginDate);
		model.addAttribute("endDateStr",endDate);
		model.addAttribute("analysisType",analysisType);
		model.addAttribute("dateStr",dateStr);
		model.addAttribute("countStr",countStr);
		System.out.println(dateStr);
		System.out.println(countStr);
		return "/admin/statistic/dataAnaChartLine";
	}
	
	/**
	 * 当前月第一天
	 * @throws ParseException 
	 */
	public Date currentMonthFirstDay() throws ParseException{
		Calendar calendar =Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String firstday = sdf.format(calendar.getTime());
	    return sdf.parse(firstday);
	}
	
}

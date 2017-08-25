package com.sram.controller.moc.member;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Constants;
import com.sram.Message;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.Setting;
import com.sram.controller.moc.BaseController;
import com.sram.entity.Course;
import com.sram.entity.CourseChapter;
import com.sram.entity.CourseFavorite;
import com.sram.entity.CourseLearn;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseLessonLearn;
import com.sram.entity.CourseLessonLearn.Status;
import com.sram.entity.CourseNote;
import com.sram.entity.CourseReview;
import com.sram.entity.CourseThread;
import com.sram.entity.CourseThreadPost;
import com.sram.entity.Member;
import com.sram.entity.OutlineCategory;
import com.sram.entity.OutlineCategory.Course_chapter_type;
import com.sram.entity.TestpaperResult;
import com.sram.entity.UploadFiles;
import com.sram.service.CourseChapterService;
import com.sram.service.CourseFavoriteService;
import com.sram.service.CourseLearnService;
import com.sram.service.CourseLessonLearnService;
import com.sram.service.CourseLessonService;
import com.sram.service.CourseMaterialService;
import com.sram.service.CourseNoteService;
import com.sram.service.CourseReviewService;
import com.sram.service.CourseService;
import com.sram.service.CourseThreadPostService;
import com.sram.service.CourseThreadService;
import com.sram.service.MemberService;
import com.sram.service.StaticService;
import com.sram.service.TeacherService;
import com.sram.service.TestpaperResultService;
import com.sram.service.UploadFilesService;
import com.sram.util.HttpClientUtil;
import com.sram.util.IntegralRuleHandleUtil;
import com.sram.util.SettingUtils;

@Controller("mocMemberCourseController")
@RequestMapping("/member/course")
public class CourseController extends BaseController {
	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
	@Resource(name = "teacherServiceImpl")
	private TeacherService teacherService;
	@Resource(name = "courseNoteServiceImpl")
	private CourseNoteService courseNoteService;
	@Resource(name = "courseChapterServiceImpl")
	private CourseChapterService courseChapterService;
	@Resource(name = "uploadFilesServiceImpl")
	private UploadFilesService uploadFilesService;
	@Resource(name = "courseLessonServiceImpl")
	private CourseLessonService courseLessonService;
	@Resource(name = "courseThreadServiceImpl")
	private CourseThreadService courseThreadService;
	@Resource(name = "courseThreadPostServiceImpl")
	private CourseThreadPostService courseThreadPostService;
	@Resource(name = "courseReviewServiceImpl")
	private CourseReviewService courseReviewService;
	@Resource(name = "courseLearnServiceImpl")
	private CourseLearnService courseLearnService;
	@Resource(name = "courseLessonLearnServiceImpl")
	private CourseLessonLearnService courseLessonLearnService;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;
	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;
	@Resource(name = "courseFavoriteServiceImpl")
	private CourseFavoriteService courseFavoriteService;
	@Resource(name = "courseMaterialServiceImpl")
	private CourseMaterialService courseMaterialService;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "testpaperResultServiceImpl")
	private TestpaperResultService testpaperResultService;
	
	/**
	 * 获取带锚点的路径,由大纲关联的课程章节
	 * @throws IOException 
	 */
	@RequestMapping(value="/getRelatePath",method=RequestMethod.GET)
	public void  getRelatePath(Course_chapter_type course_chapter_type,Long course_chapter_type_ordinal,Long course_chapter_id
			,HttpServletResponse respone,HttpServletRequest request) throws IOException{
		Course course;
		String path;
		
	
		try {
			if(course_chapter_type==Course_chapter_type.chapter || course_chapter_type_ordinal==Course_chapter_type.chapter.ordinal()){
				course=courseService.findByChapterId(course_chapter_id);
				path=course.getPath2()+"#course_chapter_"+course_chapter_id;
			}else{
				course=courseService.find(course_chapter_id);
				path=course.getPath2();
			}
			respone.sendRedirect(request.getContextPath()+path);
		} catch (Exception e) {
			e.printStackTrace();
			respone.sendRedirect(ERROR_VIEW);
		}
	}
	
	/**
	 * 跳到学习页面
	 */
	@RequestMapping(value = "/toLearn")
	public String toLearn(HttpServletRequest request, Long lessonId,
			Long courseId,ModelMap model) {

		
		CourseLesson currentLesson = courseLessonService.findCurrentLesson(
				courseId, lessonId);
		if(currentLesson==null){
			return RESOURCE_NOT_FOUND;
		}
		
		// 要学习的课程--course.path2--course.category.id(问答)
		Course course = courseService.find(courseId);
		
		// 获得当前登陆的用户Id
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId = principal.getId();
		Member member=memberService.find(memberId);
		//会员积分
		Integer memberCoin=member.getLearningCoin();
		
		// 查询章节
		List<CourseChapter> chapters = courseChapterService
				.findChaptersByCourse(courseId);

		// 获取用户在该课程下接触过的courseLessonLearn(含有lessonId)
		List<CourseLessonLearn> courseLessonLearns = courseLessonLearnService
				.findListByMemberCourse(memberId, courseId);

		//
		model.addAttribute("course", course);
		model.addAttribute("chapters", chapters);
		model.addAttribute("courseLessonLearns", courseLessonLearns);

		model.addAttribute("currentLesson", currentLesson);
		model.addAttribute("memberCoin", memberCoin);

		return "/moc/course/learn";
	}

	/**
	 * 跳到媒介播放页面
	 */
	@RequestMapping(value = "/toMedia")
	public String toMedia(HttpServletRequest request,HttpServletResponse response,
			Long lessonId,Long courseId,
			ModelMap model) {

		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		CourseLesson lesson=courseLessonService.find(lessonId);
		if(lesson==null){
			return ERROR_VIEW;
		}
		UploadFiles uploadFiles=null;
		if(!"text".equals(lesson.getType())){
			if(lesson.getUploadFiles()==null){
				return RESOURCE_NOT_FOUND;
			}
			uploadFiles = uploadFilesService.find(lesson.getUploadFiles().getId());
		}else{
			beginLessonLearn(principal.getId(),lessonId,courseId);
		}

		CourseLesson currentLesson = new CourseLesson();
		currentLesson.setId(lessonId);
		currentLesson.setOutlineCategory(lesson.getOutlineCategory());
		model.put("currentLesson", currentLesson);
		
		// 获取token权限
		Setting setting = SettingUtils.get();
		String token = HttpClientUtil.sendGet(setting.getCloudServerSite()
				+ "/token/retrieveToken.jhtml?userName="
				+ principal.getUsername());
		model.put("lesson", lesson);
		model.addAttribute("uploadFiles", uploadFiles);
		model.addAttribute("token", token);
		model.addAttribute("lessonId",lessonId);
		model.addAttribute("courseId",courseId);
		
		
		//处理积分任务
		IntegralRuleHandleUtil.dispatchTask(request, response,
				Constants.COURSE_SEE_VIDEO);
		return "/moc/course/media";
	}
	
	/**
	 * 开始测评
	 */
	@RequestMapping(value = "/toEvalution")
	public String toEvalution(HttpServletRequest request,Long lessonId,ModelMap model
			,Pageable pageable){
		pageable.setPageSize(6);
		//当前登录用户
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		CourseLesson lesson=courseLessonService.find(lessonId);
		OutlineCategory outlineCategory=lesson.getOutlineCategory();
		Page<TestpaperResult> page=null;
		Long outlineCategoryId=(long) 0;
		Long rootOutlineCategoryId=null;
		if(outlineCategory!=null && outlineCategory.getId()!=0){
			outlineCategoryId=outlineCategory.getId();
			page=testpaperResultService.findTestpaperPage(pageable,outlineCategory.getId(), principal.getId());
			if (outlineCategory.getParent()!=null) {
				String treePath =outlineCategory.getTreePath();
				rootOutlineCategoryId=Long.parseLong(treePath.substring(1, treePath.indexOf(",", 1)));	
			}else {
				rootOutlineCategoryId=outlineCategoryId;
			}
		}
		model.put("page",page);
		model.put("lessonId",lessonId);
		model.put("outlineCategoryId",outlineCategoryId);
		model.put("rootOutlineCategoryId",rootOutlineCategoryId);
		return "/moc/course/evalution";
	}
	
	
	/**
	 * 开始学习课时
	 */
	@RequestMapping(value = "/lessonLearn", method = RequestMethod.POST)
	public @ResponseBody
	Message lessonLearn(HttpServletRequest request,Long lessonId,Long courseId){
		//取得当前登陆的用户编号
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId = principal.getId();
		beginLessonLearn(memberId,lessonId,courseId);
		return SUCCESS_MESSAGE;
	}
	
	
	/**
	 * 
	 */
	public void beginLessonLearn(Long memberId,Long lessonId,Long courseId){
		// 要学习的课程
		Course course = courseService.find(courseId);
		//课程课时对象不重复插入
		CourseLessonLearn courseLessonLearn = courseLessonLearnService
			.findByLessonId(lessonId, memberId);
		CourseLearn courseLearn = courseLearnService.findByCourseId(courseId,
				memberId);
		
		if (courseLessonLearn == null) {
			courseLessonLearn = new CourseLessonLearn();
			courseLessonLearn.setCourse(course);
			courseLessonLearn.setUserId(memberId);
			CourseLesson courseLesson=new CourseLesson();
			courseLesson.setId(lessonId);
			courseLessonLearn.setCourseLesson(courseLesson);
			courseLessonLearn
					.setStatus(com.sram.entity.CourseLessonLearn.Status.learning);
			courseLessonLearn.setCreateDate(new Date());
			courseLessonLearnService.save(courseLessonLearn);
		}
		
		// 开始学习，向会员与课程关系表插入数据，已插入则不执行任何操作。
		if (courseLearn == null) {
			courseLearn = new CourseLearn();
			Member member = new Member();
			member.setId(memberId);
			courseLearn.setMember(member);
			courseLearn.setCourse(course);
			courseLearn.setCreateDate(new Date());
			courseLearn.setFinishLessonNum(0);
			courseLearn.setStatus(com.sram.entity.CourseLearn.Status.learning);
			courseLearnService.save(courseLearn);

			// 开始学习后course实体学员人数增1
			course.setStudentNum(course.getStudentNum() + 1);
			courseService.update(course);
			final Course tempCourse = course;
			// 为了修改学员人数、最新学员，采用异步方式重新生成课程静态页面，
			taskExecutor.execute(new Runnable() {
				public void run() {

					// 生成最新的静态页面
					staticService.build(tempCourse);
				}
			});
		}
	}

	/**
	 * 初始化卡片indexCard--所要初始化的卡片索引 1--笔记，2---问答
	 */
	@RequestMapping(value = "/cardInit")
	public @ResponseBody
	Object cardInit(Long lessonId,Long courseId, Integer indexCard, HttpServletRequest request) {
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		if (indexCard == 1) {

			// 获取用户在该课时的笔记
			return courseNoteService.findNotes(principal.getId(), null, null,
					lessonId, null);
		}
		if(indexCard ==3){
			
			//获取该课时下的资料及公共资料
			return courseMaterialService.findListByLessonId(lessonId, courseId);
		}
		// 获取用该课时下的所有的thread
		return courseThreadService.getThreads(null, lessonId, null, null);
	}
	
	@RequestMapping(value = "/hasBuyLesson", method = RequestMethod.POST)
	public @ResponseBody
	String hasBuyLesson(Long lessonId,HttpServletRequest request,Integer lessonCoin){
		String flag="false";
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId = principal.getId();
		CourseLessonLearn courseLessonLearn=
			courseLessonLearnService.findByLessonId(lessonId,memberId);
		if(courseLessonLearn!=null){
			flag="true";
		}else{
			Member member=memberService.find(memberId);
			Integer learningCoin=member.getLearningCoin();
			if(learningCoin<lessonCoin){
				flag="unEnounghCoin";
			}
		}
		return flag;
	}
	
	/**
	 * 消耗金币，购买课程
	 */
	@RequestMapping(value = "/spendCoin", method = RequestMethod.POST)
	public @ResponseBody
	String spendCoin(HttpServletRequest request,Integer lessonCoin){
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId = principal.getId();
		memberService.updateCoinByUserId(memberId,lessonCoin);
		return Integer.toString(lessonCoin);
	}
	
	/**
	 * 查询用户积分
	 */
	@RequestMapping(value = "/findMemberCoin", method = RequestMethod.POST)
	public @ResponseBody
	String findMemberCoin(HttpServletRequest request){
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId = principal.getId();
		Member member=memberService.find(memberId);
		Integer learningCoin=member.getLearningCoin();
		return Integer.toString(learningCoin);
	}

	/**
	 * 保存问答题目
	 */
	@RequestMapping(value = "/saveThread")
	public @ResponseBody
	String saveThread(CourseThread courseThread, HttpServletRequest request) {
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		String content = request.getParameter("content");
		courseThread.setContent(content);
		Long memberId = principal.getId();
		Member member = new Member();
		member.setId(memberId);
		courseThread.setMember(member);
		courseThreadService.save(courseThread);

		return "{\"id\":\"" + courseThread.getId() + "\"," + "\"headImage\":\""
				+ principal.getHeadImage() + "\"}";
	}

	/**
	 * 保存问答回复
	 */
	@RequestMapping(value = "/savePost")
	public @ResponseBody
	String savePost(CourseThreadPost courseThreadPost,
			HttpServletRequest request) {
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		String content = request.getParameter("content");
		courseThreadPost.setContent(content);
		Long memberId = principal.getId();
		Member member = new Member();
		member.setId(memberId);
		courseThreadPost.setMember(member);
		courseThreadPostService.save(courseThreadPost);

		return "{\"id\":\"" + courseThreadPost.getId() + "\"," +
				"\"headImage\":\""+principal.getHeadImage()+"\"}";
	}

	/**
	 * 查找该thread的post---及该thread的content
	 */
	@RequestMapping(value = "/getPosts")
	public @ResponseBody
	Object getPosts(Long threadId) {
		String threadContent = courseThreadService.find(threadId).getContent();
		if (null == threadContent) {
			threadContent = "";
		}
		Map<String, List<CourseThreadPost>> data = new HashMap<String, List<CourseThreadPost>>();
		data.put(threadContent, courseThreadPostService.findPosts(threadId,
				null, null, null, null));
		return data;
	}

	/**
	 * 保存评价
	 */
	@RequestMapping(value = "/saveReview")
	public @ResponseBody
	String saveReview(CourseReview courseReview, HttpServletRequest request) {
		String content = request.getParameter("content");
		courseReview.setContent(content);
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId = principal.getId();
		Member member = new Member();
		member.setId(memberId);
		courseReview.setMember(member);
		courseReviewService.save(courseReview);

		return "{\"headImage\":\"" + principal.getHeadImage() + "\"}";
	}

	/**
	 * 保存笔记、更新笔记(若该课时下存在笔记)
	 */
	@RequestMapping(value = "/saveNote")
	public @ResponseBody
	String saveNote(CourseNote courseNote, HttpServletRequest request) {
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		String content = request.getParameter("content");
		courseNote.setContent(content);
		Long memberId = principal.getId();
		Member member = new Member();
		member.setId(memberId);
		courseNote.setMember(member);
		
		if (courseNote.getId() != null) {
			courseNoteService.update(courseNote);
		} else {
			courseNoteService.save(courseNote);
		}
		return "{\"message\":\"操作成功\",\"id\":\""+courseNote.getId()+"\"}";
	}

	/**
	 * 课时学习完成
	 */
	@RequestMapping(value = "/learnFinish", method = RequestMethod.GET)
	public @ResponseBody
	Message learnFinish(HttpServletRequest request, Long lessonId) {

		// 获得当前登陆的用户Id
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId = principal.getId();

		// 获得已完成学习课时实体对象
		CourseLesson courseLesson = courseLessonService.find(lessonId);

		// 查找登录用户学习课时的数据
		CourseLessonLearn courseLessonLearn = courseLessonLearnService
				.findByLessonId(lessonId, memberId);

		if (courseLessonLearn != null) {
			// 重复学习不执行计数
			if ((Status.learning).equals(courseLessonLearn.getStatus())) {
				// 获得课程对象的总课时数
				Course course = courseLesson.getCourse();

				// 课时学习完成，CourseLearn中课时完成人数加1
				CourseLearn courseLearn = courseLearnService.findByCourseId(
						course.getId(), memberId);
				courseLearn
						.setFinishLessonNum(courseLearn.getFinishLessonNum() + 1);
				courseLearnService.updateFinLessonNum(
						courseLearn.getFinishLessonNum(), course.getId(),
						memberId);

				// 课时完成学习，已学学员人数加1
				courseLesson.setLearnedNum(courseLesson.getLearnedNum() + 1);

				// 更新已学完学员人数字段
				courseLessonService.updateLearnedNum(courseLesson);
				// 课时完成后更新课程课时学习状态
				courseLessonLearn
						.setStatus(com.sram.entity.CourseLessonLearn.Status.finished);
				courseLessonLearn.setModifyDate(new Date());
				courseLessonLearnService.updateStatus(courseLessonLearn);
				
				// 获得该会员已完成的课时数
				Integer finishCouLesCount = courseLessonLearnService
						.findFinishCount(memberId, course.getId(),
								Status.finished);
				Integer allLessonCount = course.getLessonNum();
				if (allLessonCount.equals(finishCouLesCount)) {
					courseLearnService.updateCourseLearn(memberId,
							course.getId(),
							com.sram.entity.CourseLearn.Status.finished);
					course.setFinishStuNum(course.getFinishStuNum() + 1);
					courseService.updateFinishStuNum(course.getFinishStuNum(),
							course.getId());
				}
			}
			return Message.success("您已完成该课时的学习");
		}else{
			return Message.error("您还没有开始学习该课时");
		}
	}

	/**
	 * 个人中心我的课程
	 */
	@RequestMapping(value = "/myCourseList")
	public String myCourseList(HttpServletRequest request, ModelMap model,String tabFlag) {
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		if(tabFlag==null || ("").equals(tabFlag)){
			tabFlag="learning";
		}
		if(("favorite").equals(tabFlag)){
			String courseIds="";
			//查询该会员收藏的课程信息
			List<CourseFavorite> list=courseFavoriteService.findFavoriteByUser(principal.getId());
			if(list!=null && list.size()>0){
				courseIds="(";
				for(CourseFavorite courseFavorite:list){
					courseIds=courseIds+"'"+courseFavorite.getCourse().getId()+"',";
				}
				courseIds=courseIds.substring(0,courseIds.length()-1);
				courseIds=courseIds+")";
			}else{
				courseIds="(0)";
			}
			// 查询该会员收藏的所有课程
			List<Object[]> favCourseList = courseService
					.findfavByMemberId(principal.getId(),courseIds);
			model.addAttribute("courseList", favCourseList);
		}else{
			// 查询出该会员所有学完和未学完的课程
			List<Object[]> courseList = courseService
					.findByMemberId(principal.getId(),tabFlag);
			model.addAttribute("courseList", courseList);
		}
		if(principal!=null){
			model.addAttribute("headImage",principal.getHeadImage());
		}
		model.addAttribute("tabFlag",tabFlag);
		return "/moc/member/course/myCourseList";
	}
	

}

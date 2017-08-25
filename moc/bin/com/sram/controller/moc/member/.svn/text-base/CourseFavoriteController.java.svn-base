package com.sram.controller.moc.member;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Constants;
import com.sram.Message;
import com.sram.Principal;
import com.sram.controller.moc.BaseController;
import com.sram.entity.Course;
import com.sram.entity.CourseFavorite;
import com.sram.entity.Member;
import com.sram.service.CourseFavoriteService;
import com.sram.util.IntegralRuleHandleUtil;
import com.sram.util.WebUtils;

/**
 * Controller - 收藏课程
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("mocCourseFavoriteController")
@RequestMapping("/member/coursefavorite")
public class CourseFavoriteController extends BaseController {
	@Resource(name = "courseFavoriteServiceImpl")
	private CourseFavoriteService courseFavoriteService;

	@RequestMapping(value = "/collectCourse", method = RequestMethod.GET)
	public @ResponseBody
	Message collectCourse(Long courseId, HttpServletRequest request,
			HttpServletResponse response, boolean collectFlag) {
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);

		boolean flag = courseFavoriteService.courseFavoriteExists(courseId,
				principal.getId());
		// 没有收藏过的才收藏
		if (!(flag)) {
			Member member = new Member();
			member.setId(principal.getId());
			Course course = new Course();
			course.setId(courseId);
			CourseFavorite courseFavorite = new CourseFavorite();
			courseFavorite.setCourse(course);
			courseFavorite.setMember(member);
			courseFavorite.setCreateDate(new Date());
			courseFavoriteService.save(courseFavorite);

			IntegralRuleHandleUtil.dispatchTask(request, response,
					Constants.COURSE_FAVORITE_URL);
			return Message.success("课程收藏成功");
		} else {
			return Message.error("您已收藏过该课程");
		}
	}

	/**
	 * 删除收藏课程
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(HttpServletRequest request, Long courseId,
			HttpServletResponse response) {
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		courseFavoriteService.deleteCourFav(courseId, principal.getId());
		String courseFavoriteCode = WebUtils.getCookie(request,
				"courseFavoriteCode");
		if (courseFavoriteCode != null) {
			courseFavoriteCode = "," + courseFavoriteCode + ",";
			courseFavoriteCode = courseFavoriteCode.replace(
					"," + courseId.toString() + ",", "");
			WebUtils.addCookie(request, response, "courseFavoriteCode",
					courseFavoriteCode);
		}
		return Message.success("收藏课程移除成功");
	}
}

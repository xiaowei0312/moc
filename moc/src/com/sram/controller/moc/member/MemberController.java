package com.sram.controller.moc.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.FileInfo.FileType;
import com.sram.Constants;
import com.sram.Message;
import com.sram.Principal;
import com.sram.controller.moc.BaseController;
import com.sram.entity.Member;
import com.sram.service.FileService;
import com.sram.service.MemberRankService;
import com.sram.service.MemberService;
import com.sram.util.IntegralRuleHandleUtil;
import com.sram.util.WebUtils;

/**
 * 个人中心
 */
@Controller("mocMemberController")
@RequestMapping("/member/profile")
public class MemberController extends BaseController {

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	@Resource(name = "fileServiceImpl")
	private FileService fileService;
	
	@Resource(name = "memberRankServiceImpl")
	private MemberRankService memberRankService;

	/**
	 * 个人中心页面
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(String redirectUrl, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		String username = WebUtils.getCookie(request, "username");
		if (username == null) {
			return "redirect:/login/index.jhtml";
		}
		return "moc/member/index";
	}



	/**
	 * 修改个人信息页面
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(HttpServletRequest request,
			RedirectAttributes redirectAttributes, ModelMap model) {
		Member member = memberService.getCurrent();
		model.addAttribute("member", member);
		model.addAttribute("headImage", member.getHeadImage());
		return "moc/member/profile/edit";
	}

	/**
	 * 修改个人信息action
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(FileType fileType,@RequestParam MultipartFile myfile,
			Member member, HttpServletRequest request,HttpServletResponse response,
			RedirectAttributes redirectAttributes, HttpSession session) {
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		//当前登陆用户名判断用户是否更改用户名，有些情况用户只需要更新用户名以外数据，不需要对用户名进行校验
		String currentUserName=principal.getUsername();
		Message message = null;
		session = request.getSession();
	
		if (StringUtils.isEmpty(member.getUsername())) {
			message = Message.warn("用户名为空，无法更改");
			addFlashMessage(redirectAttributes, message);
			return "redirect:edit.jhtml";
		}
		
		if(!(currentUserName.equals(member.getUsername()))){
			if (memberService.usernameDisabled(member.getUsername()) || memberService.usernameExists(member.getUsername())) {
				message = Message.warn("用户名被禁用或已存在，无法更改");
				addFlashMessage(redirectAttributes, message);
				return "redirect:edit.jhtml";
			}
		}
		
		if(myfile.getOriginalFilename()!=null && !("").equals(myfile.getOriginalFilename())){
			String url="";
			if (!fileService.isValid(fileType, myfile)) {
				message = Message.warn("上传文件格式或大小不正确");
			} else {
				url = fileService.upload(fileType, myfile, false);
				if (url == null) {
					message = Message.warn("上传文件出现错误");
				} else {
					message = SUCCESS_MESSAGE;
				}
			}
			member.setHeadImage(url);
		}	
		member.setIsEnabled(true);
		member.setMemberRank(memberRankService.find((long) 1));
		memberService.update(member,"salt","password","email","balance","headImage","amount");
		session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), member.getUsername(),member.getHeadImage()));
		message=Message.success("用户信息修改成功");
		addFlashMessage(redirectAttributes, message);
		
		//处理积分任务
		IntegralRuleHandleUtil.dispatchTask(request, response,
				Constants.MEMBERINFO_ADD_URL);
		
		return "redirect:edit.jhtml";
	}

}

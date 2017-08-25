/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.persistence.LockModeType;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.Setting;
import com.sram.SramException;
import com.sram.dao.CourseLearnDao;
import com.sram.dao.DepositDao;
import com.sram.dao.MemberDao;
import com.sram.entity.Admin;
import com.sram.entity.Deposit;
import com.sram.entity.Member;
import com.sram.entity.MemberRank;
import com.sram.entity.QuestionUploadAnalyseReport;
import com.sram.service.MemberImageService;
import com.sram.service.MemberService;
import com.sram.util.PasswordUtils;
import com.sram.util.SettingUtils;

/**
 * Service - 会员
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("memberServiceImpl")
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService {
    
	// excel头部
	public   String[] TITLE_MEMBER = {"用户名*,username","密码,password","姓名,name","身份证号,idCard"
		,"邮箱*,email","手机号码,mobile","qq,qq","微博,weibo","微信,weixin","salt,salt","createDate,createDate","registerIp,registerIp","loginIp,loginIp"};
	
	@Resource(name="memberImageServiceImpl")
	private MemberImageService memberImageService;
	
	@Resource(name = "memberDaoImpl")
	private MemberDao memberDao;
	@Resource(name = "depositDaoImpl")
	private DepositDao depositDao;
	
   @Resource(name="courseLearnDaoImpl")
	private CourseLearnDao courseLearnDao;

	@Resource(name = "memberDaoImpl")
	public void setBaseDao(MemberDao memberDao) {
		super.setBaseDao(memberDao);
	}

	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return memberDao.usernameExists(username);
	}
    
	
	@Override
	public void delete(Long... ids) {
		//判断会员是否已学习课程，已学习不能删除
		Member member;
		for(Long id:ids){
			member=super.find(id);
			boolean flag=true;
			flag=courseLearnDao.memberCourseLearn(id);
			if(flag){
				throw new SramException("会员[" + member.getName()+"]已经学习了课程，不能删除该会员");
			}
			memberImageService.deleteMemberHeadImage(member);
			super.delete(id);
		}
	}
	
	
	@Transactional(readOnly = true)
	public boolean usernameDisabled(String username) {
		Assert.hasText(username);
		Setting setting = SettingUtils.get();
		if (setting.getDisabledUsernames() != null) {
			for (String disabledUsername : setting.getDisabledUsernames()) {
				if (StringUtils.containsIgnoreCase(username, disabledUsername)) {
					return true;
				}
			}
		}
		return false;
	}

	@Transactional(readOnly = true)
	public boolean emailExists(String email) {
		return memberDao.emailExists(email);
	}

	@Transactional(readOnly = true)
	public boolean emailUnique(String previousEmail, String currentEmail) {
		if (StringUtils.equalsIgnoreCase(previousEmail, currentEmail)) {
			return true;
		} else {
			if (memberDao.emailExists(currentEmail)) {
				return false;
			} else {
				return true;
			}
		}
	}

	public void save(Member member, Admin operator) {
		Assert.notNull(member);
		memberDao.persist(member);
		
	}

	

	@Transactional(readOnly = true)
	public Member findByUsernameOrPhone(String username) {
		return memberDao.findByUsernameOrPhone(username);
	}

	@Transactional(readOnly = true)
	public List<Member> findListByEmail(String email) {
		return memberDao.findListByEmail(email);
	}


	public void update(Member member, Integer modifyPoint, BigDecimal modifyBalance, String depositMemo, Admin operator) {
		Assert.notNull(member);

		memberDao.lock(member, LockModeType.PESSIMISTIC_WRITE);

		if (modifyPoint != null && modifyPoint != 0 && member.getPoint() + modifyPoint >= 0) {
			member.setPoint(member.getPoint() + modifyPoint);
		}
		

		if (modifyBalance != null && modifyBalance.compareTo(new BigDecimal(0)) != 0 && member.getBalance().add(modifyBalance).compareTo(new BigDecimal(0)) >= 0) {
			member.setBalance(member.getBalance().add(modifyBalance));
			Deposit deposit = new Deposit();
			if (modifyBalance.compareTo(new BigDecimal(0)) > 0) {
				deposit.setType(operator != null ? Deposit.Type.adminRecharge : Deposit.Type.memberRecharge);
				deposit.setCredit(modifyBalance);
				deposit.setDebit(new BigDecimal(0));
			} else {
				deposit.setType(operator != null ? Deposit.Type.adminChargeback : Deposit.Type.memberPayment);
				deposit.setCredit(new BigDecimal(0));
				deposit.setDebit(modifyBalance);
			}
			deposit.setBalance(member.getBalance());
			deposit.setOperator(operator != null ? operator.getUsername() : null);
			deposit.setMemo(depositMemo);
			deposit.setMember(member);
			depositDao.persist(deposit);
		}
		
		memberDao.merge(member);
	}


	@Transactional(readOnly = true)
	public String getCurrentUsername() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}

	public boolean isAuthenticated() {
		return false;
	}
    
	@Transactional(readOnly = true)
	public Member getCurrent() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
			if (principal != null) {
				return memberDao.find(principal.getId());
			}
		}
		return null;
	}

	public Page<Member>  findPage(Pageable pageable, MemberRank memberRank) {
		return memberDao.findPage(pageable,memberRank);
	}

	public QuestionUploadAnalyseReport analyzeXLS3(MultipartFile file,String registPath,MemberRank memberRank) {
		try{
			List<Member> members=new ArrayList<Member>();
			POIFSFileSystem fs = new POIFSFileSystem(
					file.getInputStream());
			HSSFWorkbook workBook = new HSSFWorkbook(fs);
			String[] title=TITLE_MEMBER;
			String className="com.sram.entity.Member";
			QuestionUploadAnalyseReport report = new QuestionUploadAnalyseReport();
			report.setFileName(file.getOriginalFilename());
			report.setOk(true);
			HSSFSheet sheet = workBook.getSheet("sheet1");
			if (sheet == null) {
				report.setOk(false);
				report.msg = "sheet1表不存在。";
				return report;
			}
			// 设置msgReport的sheet表格名称
			report.setSheet("sheet1");
			StringBuffer sb = new StringBuffer();
			// 获取行数
			int rows = sheet.getPhysicalNumberOfRows();
			if (rows > 1) {

				//总行数大于1，表明有数据，开始读取数据
		    	 for (int j = 1; j < rows; j++) { // 行循环 
		    		 HSSFRow row = sheet.getRow(j);
		    		 if (row != null) {
				        //取得列数
		    			 int cells = row.getLastCellNum();//获得列数
				    	 //定义一个数组用来存放从单元格取出来的数据，长度为表头的长度
				    	 String [] sj=new String[title.length];
				    	//通过类名称加载一个类
				    	 Class cls=Class.forName(className);
				    	 Object o=cls.newInstance();
				    	 for (int k = 0; k < title.length; k++) { // 列循环 
				    		  String[] str=new String[2];    		 
					    	   str =title[k].split(",");
						        HSSFCell cell = row.getCell(k);
						        Method setMethod = cls.getDeclaredMethod("set"+str[1].substring(0,1).toUpperCase()+str[1].substring(1), String.class); 
						        if (cell != null) {
						        	cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					        		sj[k]=cell.getStringCellValue();
					        		setMethod.invoke(o,sj[k].trim());
						        }else{
						        	setMethod.invoke(o,"");
						        }
					      }

							//执行检查（过滤*空值）  
				    	  boolean flag= false;//检查行带*,防止后面数据为空，不过滤
							for (int m = 0; m< title.length;m++) {
								 String[] str = title[m].split(",");
								 if(title[m].contains("*")){
									 Method  method = cls.getMethod("get"+str[1].substring(0,1).toUpperCase()+str[1].substring(1), null);
									 if(!flag && (method.invoke(o, null)!=null?method.invoke(o, null).toString():"").equals("")){
										 report.setOk(false);
										 sb.append("第"+(j+1)+"行存在"+str[0]+"列值为空。<br/>"); 
										 flag=true;
									 }else{
										 if(("username").equals(str[1])){
											 if(memberDao.usernameExists((String)method.invoke(o, null))){
												 report.setOk(false);
												 sb.append("第"+(j+1)+"行存在"+str[0]+"已存在。<br/>");
												 flag=true;
											 }
										 }else if(("email").equals(str[1])){
											 if(memberDao.emailExists((String)method.invoke(o, null))){
												 report.setOk(false);
												 sb.append("第"+(j+1)+"行存在"+str[0]+"已存在。<br/>");
												 flag=true;
											 }
										 }
									 }
								 }else{
									 Method  method = cls.getMethod("get"+str[1].substring(0,1).toUpperCase()+str[1].substring(1), null);
									 if(("idCard").equals(str[1])){
										 if(!flag && method.invoke(o, null)!=null && !("").equals(method.invoke(o, null))){
											//定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
											Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
											 //通过Pattern获得Matcher  
							                Matcher idNumMatcher = idNumPattern.matcher((String)method.invoke(o, null));  
							                //判断用户输入是否为身份证号  
				                            if(!(idNumMatcher.matches())){ 
				                            	report.setOk(false);
				                            	sb.append("第"+(j+1)+"行存在"+str[0]+"格式不正确。<br/>");
				                            	flag=true;
				                            }
										 }
									 }
								 }
						  }
						  if(!flag){
							  members.add((Member)o);
						  }
		    		 }
		    	 }
		    	 if(report.isOk()){
		    		 for(Member member:members){
		    			 member.setAmount(new BigDecimal("0.0"));
		    			 member.setBalance(new BigDecimal("0.0"));
		    			 String salt = UUID.randomUUID().randomUUID().toString().replace("-", "");
		 				 member.setSalt(salt);
		    			 member.setPassword(PasswordUtils.encodePassword(member.getPassword(), salt));
		    			 member.setLocked(false);
		    			 member.setLoginFailureCount(0);
		    			 member.setLockedDate(null);
		    			 member.setRegisterIp(registPath);
		    			 member.setLoginIp(null);
		    			 member.setLoginDate(null);
		    			 member.setSafeKey(null);
		    			 member.setIsEnabled(true);
		    			 member.setMemberRank(memberRank);
		    			 memberDao.persist(member);
		    		 }
		    		 report.setMsg("导入成功");
		    	 }else{
		    		 sb.append("导入失败");
		    		 report.setMsg(sb.toString());
		    	 }
			}else{
				report.setMsg("表中数据为空，未导入任何数据。<br/>");
				report.setOk(true);
			}
			return report;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public QuestionUploadAnalyseReport analyzeXLS(MultipartFile file,String registPath,MemberRank memberRank) {
		// TODO Auto-generated method stub
		try{
			List<Member> members=new ArrayList<Member>();
			POIFSFileSystem fs = new POIFSFileSystem(
					file.getInputStream());
			HSSFWorkbook workBook = new HSSFWorkbook(fs);
			String[] title=TITLE_MEMBER;
			String className="com.sram.entity.Member";
			QuestionUploadAnalyseReport report = new QuestionUploadAnalyseReport();
			report.setFileName(file.getOriginalFilename());
			report.setOk(true);
			HSSFSheet sheet = workBook.getSheet("sheet1");
			if (sheet == null) {
				report.setOk(false);
				report.msg = "sheet1表不存在。";
				return report;
			}
			// 设置msgReport的sheet表格名称
			report.setSheet("sheet1");
			StringBuffer sb = new StringBuffer();
			// 获取行数
			int rows = sheet.getPhysicalNumberOfRows();
			if (rows > 1) {
				
				//总行数大于1，表明有数据，开始读取数据
				for (int j = 1; j < rows; j++) { // 行循环 
					HSSFRow row = sheet.getRow(j);
					if (row != null) {
						//取得列数
						int cells = row.getLastCellNum();//获得列数
						//定义一个数组用来存放从单元格取出来的数据，长度为表头的长度
						String [] sj=new String[title.length];
						//通过类名称加载一个类
						Class cls=Class.forName(className);
						Class date=Class.forName("java.util.Date");
						Object o=cls.newInstance();
						for (int k = 0; k < title.length; k++) { // 列循环 
							String[] str=new String[2];    		 
							str =title[k].split(",");
							HSSFCell cell = row.getCell(k);
							Method setMethod=null;
							if(k==10){
								setMethod = cls.getSuperclass().getDeclaredMethod("set"+str[1].substring(0,1).toUpperCase()+str[1].substring(1), date); 
							}else{
								 setMethod = cls.getDeclaredMethod("set"+str[1].substring(0,1).toUpperCase()+str[1].substring(1), String.class); 
							}
							if (cell != null) {
								cell.setCellType(HSSFCell.CELL_TYPE_STRING);
								sj[k]=cell.getStringCellValue();
								if(k==10){
									Date date2 = new Date();
									date2.setTime(Long.parseLong(sj[k].trim()));
									System.out.println(date2);
									if(date2==null){
										setMethod.invoke(o,null);
									}else{
										setMethod.invoke(o,date2);
									}
								}else{
									setMethod.invoke(o,sj[k].trim());
								}
							}else{
								setMethod.invoke(o,"");
							}
						}
						
						//执行检查（过滤*空值）  
						boolean flag= false;//检查行带*,防止后面数据为空，不过滤
						for (int m = 0; m< title.length;m++) {
							String[] str = title[m].split(",");
							if(title[m].contains("*")){
								Method  method = cls.getMethod("get"+str[1].substring(0,1).toUpperCase()+str[1].substring(1), null);
								if(!flag && (method.invoke(o, null)!=null?method.invoke(o, null).toString():"").equals("")){
									report.setOk(false);
									sb.append("第"+(j+1)+"行存在"+str[0]+"列值为空。<br/>"); 
									flag=true;
								}else{
									if(("username").equals(str[1])){
										if(memberDao.usernameExists((String)method.invoke(o, null))){
											report.setOk(false);
											sb.append("第"+(j+1)+"行存在"+str[0]+"已存在。<br/>");
											flag=true;
										}
									}else if(("email").equals(str[1])){
										if(memberDao.emailExists((String)method.invoke(o, null))){
											report.setOk(false);
											sb.append("第"+(j+1)+"行存在"+str[0]+"已存在。<br/>");
											flag=true;
										}
									}
								}
							}else{
								Method  method=null;
								if(m!=10){
									 method = cls.getMethod("get"+str[1].substring(0,1).toUpperCase()+str[1].substring(1), null);
								}
								if(("idCard").equals(str[1])){
									if(!flag && method.invoke(o, null)!=null && !("").equals(method.invoke(o, null))){
										//定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
										Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
										//通过Pattern获得Matcher  
										Matcher idNumMatcher = idNumPattern.matcher((String)method.invoke(o, null));  
										//判断用户输入是否为身份证号  
										if(!(idNumMatcher.matches())){ 
											report.setOk(false);
											sb.append("第"+(j+1)+"行存在"+str[0]+"格式不正确。<br/>");
											flag=true;
										}
									}
								}
							}
						}
						if(!flag){
							members.add((Member)o);
						}
					}
				}
				if(report.isOk()){
					for(Member member:members){
						member.setAmount(new BigDecimal("0.0"));
						member.setBalance(new BigDecimal("0.0"));
						//String salt = UUID.randomUUID().randomUUID().toString().replace("-", "");
						//member.setSalt(salt);
						//member.setPassword(PasswordUtils.encodePassword(member.getPassword(), salt));
						member.setLocked(false);
						member.setLoginFailureCount(0);
						member.setLockedDate(null);
						member.setRegisterIp(registPath);
						//member.setLoginIp(null);
						member.setLoginDate(null);
						member.setSafeKey(null);
						member.setIsEnabled(true);
						member.setMemberRank(memberRank);
						memberDao.persist(member);
					}
					report.setMsg("导入成功");
				}else{
					sb.append("导入失败");
					report.setMsg(sb.toString());
				}
			}else{
				report.setMsg("表中数据为空，未导入任何数据。<br/>");
				report.setOk(true);
			}
			return report;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Object[]> findHotMember() {
		return memberDao.findHotMember();
	}

	public String findHeadImage(Principal principal) {
		if(principal==null){
			return null;
		}
		return memberDao.findHeadImage(principal);
	}

	public List<Member> findList(Date beginDate, Date endDate) {
		return memberDao.findList(beginDate,endDate);
	}

	public Page<Member> findPage(Pageable pageable, Date beginDate, Date endDate) {
		return memberDao.findPage(pageable,beginDate,endDate);
	}

	public List<Object[]> findAnalySisList(Date beginDate, Date endDate) {
		return memberDao.findAnalySisList(beginDate,endDate);
	}

	public void updateExperienceCoin(Integer experienceValue,
			Integer learningCoin, Long memberId) {
		memberDao.updateExperienceCoin(experienceValue,learningCoin,memberId);
	}

	public Object[] getExperienceCoin(Long memberId) {
		return memberDao.getExperienceCoin(memberId);
	}

	public void updateCoinByUserId(Long memberId,
			Integer lessonCoin) {
		// TODO Auto-generated method stub
		memberDao.updateCoinByUserId(memberId,lessonCoin);
	}

	public void updateExamAreaAndExamOutlineCategory(String userName,Long examOutlineCategoryId, Long examAreaId) {
		memberDao.updateExamAreaAndExamOutlineCategory(userName, examOutlineCategoryId, examAreaId);
	}
}
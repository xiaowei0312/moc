[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.course.list")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>

<script type="text/javascript">
   function validate_form(thisform) {
      if (thisform.username.value == "") {
         alert("请输入用户名");
         return false;
      } 
      if (thisform.password.value == "") {
        alert("请输入密码");
        return false;
      }
       if (thisform.repassword.value == "") {
        alert("请输入确认密码");
        return false;
      }
      if(thisform.password.value!=thisform.repassword.value){
         alert("密码与确认密码不同");
         return false;
      }
      if (thisform.email.value == "") {
         alert("请输入邮箱号");
         return false;
      }
      if(thisform.email.value!=""){
          var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
          if(pattern.test(thisform.email.value)==false){
              alert("邮箱格式不符合规范，请重新输入");
              return false;
          }
      }
         var roleCode="";
      $('input[name="roleIds"]:checked').each(function(){
          roleCode+=$(this).val()+",";   
      });
      if(roleCode==""){
          alert("请选择角色");
          return false;
      }
   }
</script>
</head>
<body>
   <form id="inputForm" action="saveUserInfo.jhtml" name="accountForm" method="post" onsubmit="return validate_form(this);"  enctype="multipart/form-data">
        <input type="hidden" name="teacherId" value="${teacherId}"/>
      <table class="input tabContent">
          <tr>
            <th><span class="requiredField">*</span>用户名：</th>
            <td>
               <input type="text" id="username" name="username" class="text" maxlength="200" />
            </td>
          </tr>
          <tr>
            <th><span class="requiredField">*</span>密码：</th>
            <td>
               <input type="password" id="password" name="password" class="text" maxlength="200" />
            </td>
          </tr>
          <tr>
            <th><span class="requiredField">*</span>确认密码：</th>
            <td>
               <input type="password" id="repassword" name="repassword" class="text" maxlength="200" />
            </td>
          </tr>
             <tr>
            <th><span class="requiredField">*</span>email：</th>
            <td>
               <input type="text" id="email" name="email" class="text" maxlength="200" />
            </td>
          </tr>
          <tr class="roles">
				<th>
					<span class="requiredField">*</span>角色:
				</th>
				<td>
					<span class="fieldSet">
						[#list roles as role]
							<label>
								<input type="checkbox" class="roleIds" name="roleIds" value="${role.id}" />${role.name}
							</label>
						[/#list]
					</span>
				</td>
			</tr>
			<tr>
				<th>
					是否启用:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="true" checked="checked" />${message("Admin.isEnabled")}
						<input type="hidden" name="_isEnabled" value="false" />
					</label>
				</td>
			</tr>
			</table>
          <table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
				    <input type="submit" class="button" value="提交"/>
				</td>
			</tr>
      </table>
   </form>
</body>
</html>
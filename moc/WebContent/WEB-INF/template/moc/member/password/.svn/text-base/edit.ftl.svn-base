<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/common.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen"/>
<link rel="stylesheet" href="${base}/resources/moc/css/member.css"/>

<script type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/mine.js"></script>


<script type="text/javascript">
      $().ready(function() {
          var $submitForm = $("#submitForm");
          var $oldpassword =$("#oldpassword");
          var $password = $("#password");
          var $submit = $(":submit");
          [@flash_message /]
          
          //自定义验证，验证新旧密码不能相同
          $.validator.addMethod("notequalTo",function(value,element,params){
			var comValue=$(params).val();
			if(comValue==value){
				return false;
			}else{
				return true;
			}
		 },"新旧密码不能相同");
          
          $submitForm.validate({
              rules: {
                 oldpassword:{
                     required:true,
                     remote: {
					    url: "check_current_password.jhtml",
					    cache: false
				     }
                 },
                 password:{
                     required:true,
                     pattern: /^[^\s&\"<>]+$/,
				     minlength: 5,
				     maxlength:20,
				     notequalTo:"#oldpassword"
                 },
                 rePassword:{
                    required:true,
                    equalTo: "#password"
                 }                         
              },
              messages:{
                 oldpassword:{
                      required:'旧密码必须填写',
                      remote:'旧密码不正确'
                 },
                 password:{
                      required:'新密码必须填写',
                      pattern: "密码格式不正确",
				      minlength:'密码长度必须大于5位',
				      maxlength:'密码长度必须小于20位'
                 },
                 rePassword:{
                      required:'确认密码必须填写',
                      equalTo:'新密码,确认密码必须相同'
                 }
              },
              errorPlacement: function(error, element){
	              error.appendTo(element.next('span'));
	           }
          });
      });
</script>
</head>
<body>
  [#include "/moc/include/memberHeader.ftl" /]


	<div class="membcont"> 
<h3 class="mem-h3">修改密码</h3>
<div class="box demo2">
    <div class="tab_box">
     <form id="submitForm"  method="post" action="update.jhtml">
      <table class="membUpdate mas" width="800" height="150">
      <tr>
        <td width="70" height="40" class="mbmlabel"><span class="requiredField">*</span>旧密码</td>
        <td width="718">
          <input  type="password" class="short" id="oldpassword" name="oldpassword"> 
          <span class="validtext"></span>
          </td>
      </tr>
      <tr>
        <td width="70" height="40" class="mbmlabel"><span class="requiredField">*</span>新密码</td>
        <td width="718">
          <input  type="password" class="short" id="password" name="password">
          <span class="validtext"></span>
          </td>
      </tr>
      <tr>
        <td height="45" class="mbmlabel"><span class="requiredField">*</span>确认密码</td>
        <td>
          <input type="password" class="short" id="rePassword" name="rePassword">
          <span class="validtext"></span>
          </td>
      </tr>
      <tr>
      	  <td height="46"></td>
          <td><input type="submit" class="uploadbtn ub1" value="保存"></td>
          </tr>
      </table>
     </form>
    </div>   
</div>
</div>


<div class="clearh"></div>
</div>
[#include "/moc/include/footer.ftl" /]
</body>
</html>
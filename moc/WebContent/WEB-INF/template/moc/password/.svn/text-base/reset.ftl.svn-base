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

<style type="text/css">
   .membcont {
	width:100%;
	float:left;
	border:1px solid #CCC;
	min-height:600px;
	padding-bottom:20px;
	background:#fff;
	border-radius:5px
	}
	
	div.membcont .captcha {
			width: 90px;
			text-transform: uppercase;
			ime-mode: disabled;
	}

	div.membcont .captchaImage {
			margin-left: 10px;
			vertical-align: middle;
			cursor: pointer;
	}
</style>

<script type="text/javascript">
    $().ready(function() {
        var $passwordForm = $("#passwordForm");
	    var $password = $("#password");
	    var $captcha = $("#captcha");
	    var $captchaImage = $("#captchaImage");
	    var $submit = $(":submit");
	    
	    // 更换验证码
	    $captchaImage.click(function() {
		   $captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	    });
	    
		    	// 表单验证
		$passwordForm.validate({
			rules: {
				newPassword: {
					required: true,
					pattern: /^[^\s&\"<>]+$/,
					minlength:5,
					maxlength:20
				},
				rePassword: {
					required: true,
					equalTo: "#newPassword"
				},
				captcha: "required"
			},
			messages: {
			   newPassword:{
			       required:'新密码必须填写',
			       pattern:'新密码格式不正确',
			       minlength:'新密码长度必须大于5位',
			       maxlength:'新密码长度不能超过20位'
			   },
			   rePassword:{
			       required:'确认密码必须填写',
			       equalTo:'密码,确认密码必须相同'
			   },
			   captcha:{
			       required:'验证码必须填写'
			   }
			},
			submitHandler: function(form) {
				$.ajax({
					url: $passwordForm.attr("action"),
					type: "POST",
					data: $passwordForm.serialize(),
					dataType: "json",
					cache: false,
					beforeSend: function() {
						$submit.prop("disabled", true);
					},
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							setTimeout(function() {
								$submit.prop("disabled", false);
								location.href = "${base}/";
							}, 3000);
						} else {
							$submit.prop("disabled", false);
							[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("resetPassword")]
								$captcha.val("");
								$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
							[/#if]
						}
					}
				});
			}
		 });
    });
</script>
</head>
<body>
   [#include "/moc/include/header.ftl" /]
    
	<div class="membcont"> 
<h3 class="mem-h3">修改密码</h3>
<div class="box demo2">
    <div class="tab_box">
     <form id="passwordForm" action="reset.jhtml" method="post">
     <input type="hidden" name="captchaId" value="${captchaId}" />
	 <input type="hidden" name="username" value="${member.username}" />
	 <input type="hidden" name="key" value="${key}" />
      <table class="membUpdate mas" width="800" height="150">
       <tr>
        <td width="70" height="40" class="mbmlabel"><span class="requiredField">*</span>昵称</td>
        <td width="718">
          ${member.username}
          </td>
      </tr>
      <tr>
        <td width="70" height="40" class="mbmlabel"><span class="requiredField">*</span>新密码</td>
        <td width="718">
          <input type="password" id="newPassword" name="newPassword" class="short">
          <span class="validtext"></span>
          </td>
      </tr>
      <tr>
        <td height="45" class="mbmlabel"><span class="requiredField">*</span>确认密码</td>
        <td>
          <input type="password" id="rePassword" name="rePassword" class="short">
          <span class="validtext"></span>
          </td>
      </tr>
      [#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("resetPassword")]
      <tr>
        <td height="45" class="mbmlabel"><span class="requiredField">*</span>验证码</td>
        <td>
          <input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off" />
          <img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" />
          <span class="validtext"></span>
          </td>
      </tr>
      [/#if]
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
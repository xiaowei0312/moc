<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/common.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen"/>
<link rel="stylesheet" href="${base}/resources/moc/css/register-login.css"/>

<script type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
<script type="text/javascript">
     $().ready(function() {
          var $passwordForm = $("#passwordForm");
	      var $username = $("#username");
	      var $email = $("#email");
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
					username: "required",
					email: {
						required: true,
						email: true
					},
					captcha: "required"
				},
				messages: {
				    username: {
					    required:'昵称必须填写'
					},
					email: {
					    required:'邮箱必须填写',
					    email:'请输入有效的邮箱地址'
					}
					,captcha:{
					   required:"验证码必须填写"
					} 
				},
	            errorPlacement: function(error, element){
		            error.appendTo(element.parent().next('span'));
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
								[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("findPassword")]
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
        <div class="register">
        <h2>忘记密码</h2>
        <h4 style="color:#FA5C53">通过注册邮箱链接重设密码</h4>
    <form class="alogin" id="passwordForm" action="find.jhtml" method="post">
    <input type="hidden" name="captchaId" value="${captchaId}" />
    <div>
        <p class="formrow"><label class="control-label" for="register_email">昵称</label>
       <input type="text" name="username"></p>
        <span class="text-danger"></span>
    </div>
    <div>
        <p class="formrow"><label class="control-label" for="register_email">邮箱地址</label>
       <input type="text" name="email"></p>
        <span class="text-danger"></span>
    </div>
    [#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("findPassword")]
     <div>
        <p class="formrow"><label class="control-label" for="register_email">验证码</label>
        <input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off" style="height:35px; line-height:35px; width:185px;"/>
		<img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" style=" margin-left:5px;height:35px;width:95px;"/></p>
        <span class="text-danger"></span>
    </div>
    [/#if]
    <div class="loginbtn reg">
       <button type="submit" class="uploadbtn ub1">发送</button>
    </div>

   </form>
    </div>
     [#include "/moc/include/footer.ftl" /]
</body>
</html>
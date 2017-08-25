<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>谋刻职业教育在线测评与学习平台</title>
<link rel="stylesheet" href="${base}/resources/moc/css/common.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/login.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/register-login.css"/>
<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen"/>

<script type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/mine.js"></script>
<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>

<script type="text/javascript">
    $().ready(function() {
       var $registerForm = $("#registerForm");
       var $username = $("#username");
	   var $password = $("#password");
	   var $email = $("#email");
	   var $captchaImage = $("#captchaImage");
	   var $submit = $(":submit");
	   var $captcha = $("#captcha");
	   
	   // 更换验证码
	$captchaImage.click(function() {
		$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	});
	
       // 表单验证
	$registerForm.validate({
	    rules: {
	       username:{
	          required: true,
		      pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
		      maxlength:14,
		      remote: {
				 url: "${base}/register/check_username.jhtml",
				 cache: false
			  }
	       },
			password: {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: 5,
				maxlength:20
			},
			rePassword: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true,
				remote: {
					url: "${base}/register/check_email.jhtml",
					cache: false
				}
			}
			,captcha: "required"
	    },
	    messages: {
			username: {
			    required:'昵称必须填写',
				pattern: "昵称不符合规则",
				remote: "用户名被禁用或已存在",
				maxlength:'用户名最多不能超过14位'
			},
			password: {
			    required:'密码必须填写',
				pattern: "密码格式不正确",
				minlength:'密码长度必须大于5位',
				maxlength:'密码长度必须小于20位'
			}
			,rePassword:{
			   required:'确认密码必须填写',
			   equalTo:'密码,确认密码必须相同'
			}
			,email: {
			    required:'邮箱必须填写',
			    email:'请输入有效的邮箱地址',
				remote: "邮箱已注册"
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
				url: "${base}/common/public_key.jhtml",
				type: "GET",
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$submit.prop("disabled", true);
				},
				success: function(data) {
					var rsaKey = new RSAKey();
					rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
					var enPassword = hex2b64(rsaKey.encrypt($password.val()));
					$.ajax({
						url: $registerForm.attr("action"),
						type: "POST",
						data: {
							username: $username.val(),
							enPassword: enPassword,
							email: $email.val(),
							captchaId: "${captchaId}",
							captcha: $captcha.val()
						},
						dataType: "json",
						cache: false,
						success: function(message) {
							$.message(message);
							if (message.type == "success") {
								setTimeout(function() {
									$submit.prop("disabled", false);
									location.href = "${base}/login/index.jhtml";
								}, 3000);
							} else {
								$submit.prop("disabled", false);
									$captcha.val("");
									$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
							}
						}
					});
				}
			});
		}
	});
    });
</script>
</head>

<body>

 [#include "/moc/include/header.ftl" /]
<!-- InstanceBeginEditable name="EditRegion1" -->
<div class="register" style="background:url(${base}/resources/moc/images/13.jpg) right center no-repeat #fff;">
<h2>注册</h2>
<form class="aregister" style="width:600px" id="registerForm" action="${base}/register/submit.jhtml" method="post">
    <div>
    <p class="formrow"><label class="control-label" for="register_email">邮箱地址</label>
    <input type="text" id="email" name="email"></p>
    <span class="text-danger"></span>
    </div>
    <div>
    <p class="formrow"><label class="control-label" for="register_email">昵称</label>
    <input type="text" id="username" name="username"></p>
    <span class="text-danger"></span>
    </div>
    <div>
    <p class="formrow"><label class="control-label" for="register_email">密码</label>
    <input type="password" id="password" name="password"></p>
    <span class="text-danger"></span>
    </div>
    <div>
    <p class="formrow"><label class="control-label" for="register_email">确认密码</label>
    <input type="password" type="password" id="rePassword" name="rePassword"></p>
    <span class="text-danger"></span>
    </div>
    <div>
    <p class="formrow"><label class="control-label" for="register_email">验证码</label>
    	<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off" style="height:35px; line-height:35px; width:185px;"/>&nbsp;
    	<img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" style=" margin-left:-5px;height:35px;"/></p>
    <span class="text-danger"></span>
    </div>
    <div class="loginbtn reg">
     <input type="submit" class="uploadbtn ub1" value="注册" />
    </div>

</form>
</div>
<div class="clearh"></div>
 [#include "/moc/include/footer.ftl" /]
 </body>
</html>

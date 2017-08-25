<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
		<!-- InstanceBeginEditable name="doctitle" -->
		<title>谋刻职业教育在线测评与学习平台</title>
	
		<link rel="stylesheet" href="${base}/resources/moc/css/course.css"/>
		<link rel="stylesheet" href="${base}/resources/moc/css/register-login.css"/>
		<link rel="stylesheet" href="${base}/resources/moc/css/tab.css" media="screen"/>
		<link rel="stylesheet" href="${base}/resources/moc/css/common.css"/>
		
		<script type="text/javascript" src="${base}/resources/moc/js/jquery.js"></script>
		<script type="text/javascript" src="${base}/resources/moc/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${base}/resources/moc/js/jsbn.js"></script>
		<script type="text/javascript" src="${base}/resources/moc/js/prng4.js"></script>
		<script type="text/javascript" src="${base}/resources/moc/js/rng.js"></script>
		<script type="text/javascript" src="${base}/resources/moc/js/rsa.js"></script>
		<script type="text/javascript" src="${base}/resources/moc/js/base64.js"></script>
		<script type="text/javascript" src="${base}/resources/moc/js/common.js"></script>
		
		<style type="text/css">
		   div.login .captcha {
				width: 90px;
				text-transform: uppercase;
				ime-mode: disabled;
			}

			div.login .captchaImage {
				margin-left: 10px;
				vertical-align: middle;
				cursor: pointer;
			}
		</style>
		
		<script type="text/javascript">
		    $().ready(function() {
		        var $loginForm = $("#loginForm");
	            var $username = $("#username");
	            var $password = $("#password");
	            var $captcha = $("#captcha");
	            var $captchaImage = $("#captchaImage");
	            var $isRememberUsername = $("#isRememberUsername");
	            var $submit = $(":submit");
	            
	             // 更换验证码
	       		$captchaImage.click(function() {
					$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
				});
				
				   // 表单验证
	           $loginForm.validate({
	               rules: {
						username: "required",
						password: "required",
				        captcha: "required"
		           },
		           messages:{
		                username: {
		                    required:'昵称必须填写'
		                },
						password: {
						    required:'密码必须填写'
						},
						captcha:{
						    required:'验证码必须填写'
						}
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
									url: $loginForm.attr("action"),
									type: "POST",
									data: {
										username: $username.val(),
										enPassword: enPassword,
										captchaId: "${captchaId}",
										captcha: $captcha.val()
									},
									dataType: "json",
									cache: false,
									success: function(message) {
										if ($isRememberUsername.prop("checked")) {
											addCookie("memberUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
										} else {
											removeCookie("memberUsername");
										}
										$submit.prop("disabled", false);
										if (message.type == "success") {
											[#if redirectUrl??]
												location.href = "${redirectUrl}";
											[#else]
												location.href = "${base}/";
											[/#if]
										} else {
											$.message(message);
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
       <div class="login" style="background:url(${base}/resources/moc/images/12.jpg) right center no-repeat #fff">
		<h2>登录</h2>
		<form id="loginForm" style="width:600px" action="${base}/login/submit.jhtml" method="post">
		<div>
		    <p class="formrow">
		    <label class="control-label" for="register_email">帐号</label>
		    <input type="text" id="username" name="username">
		    </p>
		    <span class="text-danger">请输入Email地址 / 用户昵称</span>
		</div>
		<div>
		    <p class="formrow">
		    <label class="control-label" for="register_email">密码</label>
		    <input type="password" id="password" name="password">
		    </p>
		    <p class="help-block"><span class="text-danger">请输入密码</span></p>
		</div>
		<div>
		    <p class="formrow">
		        <label class="control-label" for="register_email">验证码</label>
		    	<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off" />&nbsp;<img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" /></p>
		    </p>
		    <p class="help-block"><span class="text-danger">请输入验证码</span></p>
		</div>
		<div class="loginbtn">
			<label><input type="checkbox" id="isRememberUsername" name="isRememberUsername"> <span class="jzmm">记住用户名</span> </label>&nbsp;&nbsp;
		    <button type="submit" class="uploadbtn ub1">登录</button>
		    
		</div>
		<div class="loginbtn lb">
		   <a href="${base}/register/index.jhtml" class="link-muted">还没有账号？立即免费注册</a>
		   <span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>   
		   <a href="${base}/password/find.jhtml" class="link-muted">找回密码</a>
		</div>
		</form>
		<div class="hezuologo">
		    <span class="hezuo">使用合作网站账号登录</span>
		    <div class="hezuoimg">
		    <img src="${base}/resources/moc/images/hezuoqq.png" class="hzqq" title="QQ" width="40" height="40"/>
		    <img src="${base}/resources/moc/images/hezuowb.png" class="hzwb" title="微博" width="40" height="40"/>
		    </div>
		    
		  </div>
		</div>
   [#include "/moc/include/footer.ftl" /]
</body>
</html>
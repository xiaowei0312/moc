 var captchaIdTemp;

$().ready(function(){
	 
		$('.demo2').Tabs({
			event:'click'
		});
		$('.demo3').Tabs({
			event:'click'
		});
	 var $captchaImage =$("#captchaImage");	
     var $username=$("#username");
	 var $password=$("#password");
	 var $captcha=$("#captcha");
	 var $isRememberUsername=$("#isRememberUsername");
	 var $loginBtn=$("#loginBtn");
     var $loginForm = $("#loginForm");
     var $registerForm = $("#registerForm");
     var $email = $("#email");
     var $registerBtn=$("#registerBtn");
     var $regusername=$("#regusername");
     var $regpassword=$("#regpassword");
     var $regrepassword=$("#regrepassword");
     var $regcaptcha=$("#regcaptcha");
     var $regcaptchaImage=$("#regcaptchaImage");
     var preusername = getCookie("memberUsername");
     
     // 更换验证码
		$captchaImage.click(function() {
			$captchaImage.attr("src", sram.base +"/common/captcha.jhtml?captchaId="+captchaIdTemp+"&timestamp=" + (new Date()).valueOf());
		});
		
	     // 更换验证码
		$regcaptchaImage.click(function() {
			$regcaptchaImage.attr("src", sram.base +"/common/captcha.jhtml?captchaId="+captchaIdTemp+"&timestamp=" + (new Date()).valueOf());
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
		       errorPlacement: function(error, element){
	                error.appendTo(element.parent().next('span'));
	            },
			   submitHandler: function(form) {
					$.ajax({
						url: sram.base + "/common/public_key.jhtml",
						type: "GET",
						dataType: "json",
						cache: false,
						beforeSend: function() {
							$loginBtn.prop("disabled", true);
						},
						success: function(data) {
							var rsaKey = new RSAKey();
							rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
							var enPassword = hex2b64(rsaKey.encrypt($password.val()));
							$.ajax({
								url:$loginForm.attr("action"),
								type: "POST",
								data: {
									username: $username.val(),
									enPassword: enPassword,
									captchaId: captchaIdTemp,
									captcha: $captcha.val()
								},
								dataType: "json",
								cache: false,
								success: function(message) {
									if(preusername!=$username.val()){
										removeCookie("memberUsername");
									}
									if ($isRememberUsername.prop("checked")) {
										addCookie("memberUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
									} else {
										removeCookie("memberUsername");
									}
									$loginBtn.prop("disabled", false);
									if (message.type == "success") {
										$.message("success","登录成功");
										reglog_close();
										try{
											if(typeof(eval('loginSuccess'))=="function"){
											    loginSuccess();
											}
										}catch(e){
											
										}
									} else {
										$.message(message);
										$captcha.val("");
										$captchaImage.attr("src",sram.base + "/common/captcha.jhtml?captchaId="+captchaIdTemp+"&timestamp=" + (new Date()).valueOf());
									}
								}
							});
						}
					});
				}
		   });
		   
		   
		    //注册
			$registerForm.validate({
			    rules: {
			    	 regusername:{
			          required: true,
				      pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				      maxlength:14,
				      remote: {
						 url: sram.base +"/register/check_username.jhtml",
						 cache: false,
						 data: { 
							  username: function() {
								  return $regusername.val();
							  }
						 }
					  }
			       },
			       regpassword: {
						required: true,
						pattern: /^[^\s&\"<>]+$/,
						minlength: 5,
						maxlength:20
					},
					regrePassword: {
						required: true,
						equalTo: "#regpassword"
					},
					email: {
						required: true,
						email: true,
						remote: {
							url: sram.base +"/register/check_email.jhtml",
							cache: false
						}
					}
					,regcaptcha: "required"
			    },
			    messages: {
			    	regusername: {
					    required:'昵称必须填写',
						pattern: "昵称不符合规则",
						remote: "用户名被禁用或已存在",
						maxlength:'用户名最多不能超过14位'
					},
					regpassword: {
					    required:'密码必须填写',
						pattern: "密码格式不正确",
						minlength:'密码长度必须大于5位',
						maxlength:'密码长度必须小于20位'
					}
					,regrePassword:{
					   required:'确认密码必须填写',
					   equalTo:'密码,确认密码必须相同'
					}
					,email: {
					    required:'邮箱必须填写',
					    email:'请输入有效的邮箱地址',
						remote: "邮箱已注册"
					}
					,regcaptcha:{
						required:'验证码必须填写'
					}
				},
			    errorPlacement: function(error, element){
	                error.appendTo(element.parent().next('span'));
	            },
				submitHandler: function(form) {
					$.ajax({
						url: sram.base +"/common/public_key.jhtml",
						type: "GET",
						dataType: "json",
						cache: false,
						beforeSend: function() {
							$registerBtn.prop("disabled", true);
						},
						success: function(data) {
							var rsaKey = new RSAKey();
							rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
							var enPassword = hex2b64(rsaKey.encrypt($regpassword.val()));
							$.ajax({
								url: $registerForm.attr("action"),
								type: "POST",
								data: {
									username: $regusername.val(),
									enPassword: enPassword,
									email: $email.val(),
									captchaId:captchaIdTemp,
									captcha: $regcaptcha.val()
								},
								dataType: "json",
								cache: false,
								success: function(message) {
									$.message(message);
									if (message.type == "success") {
										$registerBtn.prop("disabled", false);
										reglog_close();
									} else {
									    $registerBtn.prop("disabled", false);
									    $regcaptcha.val("");
									    $regcaptchaImage.attr("src", sram.base +"/common/captcha.jhtml?captchaId="+captchaIdTemp+"&timestamp=" + (new Date()).valueOf());
									}
								}
							});
						}
					});
				}
			});
});
 
 //验证当前是否有用户登录
	function volidLogin(){
		var b=true;
		$.ajax( {
			type : "GET",
			url :sram.base + "/course/aleradyLogin.jhtml?number="+Math.random(),
			async:false,
			cache:false,
			dataType:"json",
			success : function(message) {
				//没有登录 fail
				if(message.aleradyLogin=='fail'){
					b=false;
					var $captchaImage =$("#captchaImage");
					var $regcaptchaImage=$("#regcaptchaImage");
					$captchaImage.attr("src",$captchaImage.attr("src")+message.captchaId);
					$regcaptchaImage.attr("src",$regcaptchaImage.attr("src")+message.captchaId);
					captchaIdTemp=message.captchaId;
					reglog_open();
				}else{
					b=true;
				}
			}
		});
		return b;
	}
	
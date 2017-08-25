<div id="reglog">
<span class="close"  onclick="reglog_close();">×</span>
<div class="loginbox">
    <div class="demo3 rlog">
			<ul class="tab_menu rlog">
				<li class="current">登录</li>
				<li>注册</li>
			</ul>
			<div class="tab_box">
				<div>
                <form id="loginForm" class="loginform pop" action="${base}/login/submit.jhtml" method="post">
                <span>
                    <p class="formrow">
                    <label class="control-label pop" for="register_email">帐号</label>
                    <input type="text"  id="username" name="username" class="popinput"/>
                    </p>
                    <span class="text-danger"></span>
                    
                </span>
                
                <span>
                    <p class="formrow">
                    <label class="control-label pop" for="register_email">密码</label>
                    <input type="password" id="password" name="password" class="popinput"/>
                    </p>
                   <span class="text-danger"></span>
                </span>
             	<span>
				    <p class="formrow" style="position:relative">
				        <label class="control-label pop" for="register_email">验证码</label>
				    	<input type="text" id="captcha" name="captcha" class="popinput error" maxlength="4" autocomplete="off" style="width:100px;margin-left:0px;"/>
				    	&nbsp;
				    	<span style="position:absolute; top:7px;">
				    		<img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" />
				    	</span>
				    </p>
				    <span class="text-danger"></span>
				</span>
                
                <div class="clearh"></div>
                <div class="popbtn">
                    <label><input id="isRememberUsername" name="isRememberUsername" class="rembchec" type="checkbox" checked> <span class="jzmm">记住密码</span> </label>&nbsp;&nbsp;
                    <button id="loginBtn" type="submit" class="uploadbtn ub1">登录</button>
                    
                </div>
                <div class="popbtn lb">
                   <a href="${base}/register/index.jhtml" class="link-muted">还没有账号？立即免费注册</a>
                   <span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>   
                   <a href="${base}/password/find.jhtml" class="link-muted">找回密码</a>
                </div>
              
                        
                        <div class="popbtn hezuologo">
                        <span class="hezuo z1">使用合作网站账号登录</span>
                        <div class="hezuoimg z1">
                        <img src="${base}/resources/moc/images/hezuoqq.png" class="hzqq" title="QQ" width="40" height="40">
                        <img src="${base}/resources/moc/images/hezuowb.png" class="hzwb" title="微博" width="40" height="40">
                        </div>
                        </div>
                </form>
				</div>
				<div class="hide">
					<div>
					<form class="loginform pop" id="registerForm" action="${base}/register/submit.jhtml"  method="post">
                     <div>
                        <p class="formrow">
                        <label class="control-label pop" for="register_email">邮箱地址</label>
                        <input type="text" id="email" name="email" class="popinput"/>
                        </p>
                        <span class="text-danger"></span>
                    </div>
                	<div>
                        <p class="formrow">
                        <label class="control-label pop" for="register_email">昵称</label>
                        <input type="text" id="regusername" name="regusername"  class="popinput"/>
                        </p>
                        <span class="text-danger"></span>
                    </div>
                    <div>
                        <p class="formrow">
                        <label class="control-label pop" for="register_email">密码</label>
                        <input type="password"  id="regpassword" name="regpassword" class="popinput"/>
                        </p>
                        <span class="text-danger"></span>
                    </div>
                    <div>
                        <p class="formrow">
                        <label class="control-label pop" for="register_email">确认密码</label>
                        <input type="password" id="regrePassword" name="regrePassword" class="popinput"/>
                        </p>
                        <span class="text-danger"></span>
                    </div>
                     <div>
                        <p class="formrow" style="position:relative">
                        <label class="control-label pop" for="register_email">验证码</label>
                        <input id="regcaptcha" name="regcaptcha" type="text" class="popinput" maxlength="4" autocomplete="off" style="width:100px;"/>
                        <span style="position:absolute; top:7px;">
                         	<img id="regcaptchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="点击刷新" />
                        </span>
                        </p>
                        <span class="text-danger"></span>
                    </div>
                    
                    <button id="registerBtn" type="submit" class="uploadbtn ub1">注册</button>
                   
                    
                
                </form>
                    
				</div>
				</div>
				
			</div>
		</div>
   
    </div>
</div>
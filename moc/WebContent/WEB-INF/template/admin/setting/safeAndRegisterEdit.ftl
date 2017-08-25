<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.setting.edit")} - Powered By Sram</title>
<meta name="author" content="Sram Team" />
<meta name="copyright" content="Sram" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {
	
	var $inputForm = $("#inputForm");
	var $browserButton = $("input.browserButton");
	var $smtpFromMail = $("#smtpFromMail");
	var $smtpHost = $("#smtpHost");
	var $smtpPort = $("#smtpPort");
	var $smtpUsername = $("#smtpUsername");
	var $smtpPassword = $("#smtpPassword");
	var $toMailWrap = $("#toMailWrap");
	var $toMail = $("#toMail");
	var $mailTest = $("#mailTest");
	var $mailTestStatus = $("#mailTestStatus");
	
	[@flash_message /]
	
	$browserButton.browser();
	
	// 邮件测试
	$mailTest.click(function() {
		var $this = $(this);
		if ($this.val() == "${message("admin.setting.mailTest")}") {
			$this.val("${message("admin.setting.sendMail")}");
			$toMailWrap.show();
		} else {
			function valid(element) {
				return $inputForm.validate().element(element);
			}
			$.ajax({
				url: "mail_test.jhtml",
				type: "POST",
				data: {smtpFromMail: $smtpFromMail.val(), smtpHost: $smtpHost.val(), smtpPort: $smtpPort.val(), smtpUsername: $smtpUsername.val(), smtpPassword: $smtpPassword.val(), toMail: $toMail.val()},
				dataType: "json",
				cache: false,
				beforeSend: function() {
					if (valid($smtpFromMail) & valid($smtpHost) & valid($smtpPort) & valid($smtpUsername) & valid($toMail)) {
						$mailTestStatus.html('<span class="loadingIcon">&nbsp;<\/span>${message("admin.setting.sendMailLoading")}');
						$this.prop("disabled", true);
					} else {
						return false;
					}
				},
				success: function(message) {
					$mailTestStatus.empty();
					$this.prop("disabled", false);
					$.message(message);
				}
			});
		}
	});
	
	$.validator.addMethod("compareLength", 
		function(value, element, param) {
			return this.optional(element) || $.trim(value) == "" || $.trim($(param).val()) == "" || parseFloat(value) >= parseFloat($(param).val());
		},
		"${message("admin.setting.compareLength")}"
	);
	
	// 表单验证
	$inputForm.validate({
		rules: {
			siteName: "required",
			siteUrl: "required",
			logo: "required",
			email: "email",
			siteCloseMessage: "required",
			largeProductImageWidth: {
				required: true,
				integer: true,
				min: 1
			},
			largeProductImageHeight: {
				required: true,
				integer: true,
				min: 1
			},
			mediumProductImageWidth: {
				required: true,
				integer: true,
				min: 1
			},
			mediumProductImageHeight: {
				required: true,
				integer: true,
				min: 1
			},
			thumbnailProductImageWidth: {
				required: true,
				integer: true,
				min: 1
			},
			thumbnailProductImageHeight: {
				required: true,
				integer: true,
				min: 1
			},
			defaultLargeProductImage: "required",
			defaultMediumProductImage: "required",
			defaultThumbnailProductImage: "required",
			watermarkAlpha: {
				required: true,
				digits: true,
				max: 100
			},
			watermarkImageFile: {
				extension: "${setting.uploadImageExtension}"
			},
			defaultMarketPriceScale: {
				required: true,
				min: 0,
				decimal: {
					integer: 3,
					fraction: 3
				}
			},
			usernameMinLength: {
				required: true,
				integer: true,
				min: 1,
				max: 117
			},
			usernameMaxLength: {
				required: true,
				integer: true,
				min: 1,
				max: 117,
				compareLength: "#usernameMinLength"
			},
			passwordMinLength: {
				required: true,
				integer: true,
				min: 1,
				max: 117
			},
			passwordMaxLength: {
				required: true,
				integer: true,
				min: 1,
				max: 117,
				compareLength: "#passwordMinLength"
			},
			registerPoint: {
				required: true,
				integer: true,
				min: 0
			},
			registerAgreement: "required",
			accountLockCount: {
				required: true,
				integer: true,
				min: 1
			},
			accountLockTime: {
				required: true,
				digits: true
			},
			safeKeyExpiryTime: {
				required: true,
				digits: true
			},
			uploadMaxSize: {
				required: true,
				digits: true
			},
			imageUploadPath: "required",
			flashUploadPath: "required",
			mediaUploadPath: "required",
			fileUploadPath: "required",
			smtpFromMail: {
				required: true,
				email: true
			},
			smtpHost: "required",
			smtpPort: {
				required: true,
				digits: true
			},
			smtpUsername: "required",
			toMail: {
				required: true,
				email: true
			},
			currencySign: "required",
			currencyUnit: "required",
			stockAlertCount: {
				required: true,
				digits: true
			},
			defaultPointScale: {
				required: true,
				min: 0,
				decimal: {
					integer: 3,
					fraction: 3
				}
			},
			taxRate: {
				required: true,
				min: 0,
				decimal: {
					integer: 3,
					fraction: 3
				}
			},
			cookiePath: "required",
			courseShare:"required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 注册与安全
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
		<input type="hidden" name="editType" value="${editType}"/>
		<table class="input tabContent">
			<tr>
				<th>
					是否开放注册:
				</th>
				<td>
					<input type="checkbox" name="isRegisterEnabled" value="true"[#if setting.isRegisterEnabled] checked="checked"[/#if] />
					<input type="hidden" name="_isRegisterEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					是否允许E-mail重复注册:
				</th>
				<td>
					<input type="checkbox" name="isDuplicateEmail" value="true"[#if setting.isDuplicateEmail] checked="checked"[/#if] />
					<input type="hidden" name="_isDuplicateEmail" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.disabledUsername")}:
				</th>
				<td>
					<input type="text" name="disabledUsername" class="text" value="${setting.disabledUsername}" maxlength="200" title="${message("admin.setting.disabledUsernameTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.usernameMinLength")}:
				</th>
				<td>
					<input type="text" id="usernameMinLength" name="usernameMinLength" class="text" value="${setting.usernameMinLength}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.usernameMaxLength")}:
				</th>
				<td>
					<input type="text" name="usernameMaxLength" class="text" value="${setting.usernameMaxLength}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.passwordMinLength")}:
				</th>
				<td>
					<input type="text" id="passwordMinLength" name="passwordMinLength" class="text" value="${setting.passwordMinLength}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.passwordMaxLength")}:
				</th>
				<td>
					<input type="text" name="passwordMaxLength" class="text" value="${setting.passwordMaxLength}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.registerPoint")}:
				</th>
				<td>
					<input type="text" name="registerPoint" class="text" value="${setting.registerPoint}" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.registerAgreement")}:
				</th>
				<td>
					<textarea name="registerAgreement" class="text">${setting.registerAgreement?html}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isEmailLogin")}:
				</th>
				<td>
					<input type="checkbox" name="isEmailLogin" value="true"[#if setting.isEmailLogin] checked="checked"[/#if] />
					<input type="hidden" name="_isEmailLogin" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					验证码类型:
				</th>
				<td>
					[#list captchaTypes as captchaType]
						<label>
							<input type="checkbox" name="captchaTypes" value="${captchaType}"[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains(captchaType)] checked="checked"[/#if] />${message("Setting.CaptchaType." + captchaType)}
						</label>
					[/#list]
				</td>
			</tr>
			<tr>
				<th>
					账号锁定类型:
				</th>
				<td>
					[#list accountLockTypes as accountLockType]
						<label>
							<input type="checkbox" name="accountLockTypes" value="${accountLockType}"[#if setting.accountLockTypes?? && setting.accountLockTypes?seq_contains(accountLockType)] checked="checked"[/#if] />${message("Setting.AccountLockType." + accountLockType)}
						</label>
					[/#list]
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>连续登录失败最大次数:
				</th>
				<td>
					<input type="text" name="accountLockCount" class="text" value="${setting.accountLockCount}" maxlength="9" title="${message("admin.setting.accountLockCountTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>自动解锁时间:
				</th>
				<td>
					<input type="text" name="accountLockTime" class="text" value="${setting.accountLockTime}" maxlength="9" title="${message("admin.setting.accountLockTimeTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>安全密匙有效时间:
				</th>
				<td>
					<input type="text" name="safeKeyExpiryTime" class="text" value="${setting.safeKeyExpiryTime}" maxlength="9" title="${message("admin.setting.safeKeyExpiryTimeTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>上传文件最大限制:
				</th>
				<td>
					<input type="text" name="uploadMaxSize" class="text" value="${setting.uploadMaxSize}" maxlength="9" title="${message("admin.setting.uploadMaxSizeTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					允许上传图片扩展名:
				</th>
				<td>
					<input type="text" name="uploadImageExtension" class="text" value="${setting.uploadImageExtension}" maxlength="200" title="${message("admin.setting.uploadImageExtensionTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					允许上传Flash扩展名:
				</th>
				<td>
					<input type="text" name="uploadFlashExtension" class="text" value="${setting.uploadFlashExtension}" maxlength="200" title="${message("admin.setting.uploadFlashExtensionTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					允许上传媒体扩展名:
				</th>
				<td>
					<input type="text" name="uploadMediaExtension" class="text" value="${setting.uploadMediaExtension}" maxlength="200" title="${message("admin.setting.uploadMediaExtensionTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					允许上传文件扩展名:
				</th>
				<td>
					<input type="text" name="uploadFileExtension" class="text" value="${setting.uploadFileExtension}" maxlength="200" title="${message("admin.setting.uploadFileExtensionTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>图片上传路径:
				</th>
				<td>
					<input type="text" name="imageUploadPath" class="text" value="${setting.imageUploadPath}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>Flash上传路径:
				</th>
				<td>
					<input type="text" name="flashUploadPath" class="text" value="${setting.flashUploadPath}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>媒体上传路径:
				</th>
				<td>
					<input type="text" name="mediaUploadPath" class="text" value="${setting.mediaUploadPath}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>文件上传路径:
				</th>
				<td>
					<input type="text" name="fileUploadPath" class="text" value="${setting.fileUploadPath}" maxlength="200" />
				</td>
			</tr>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='../common/index.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
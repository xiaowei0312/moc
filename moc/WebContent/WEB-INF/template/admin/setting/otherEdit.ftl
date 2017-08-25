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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 其它设置
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
	<input type="hidden" name="editType" value="${editType}"/>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>云服务器地址: 
				</th>
				<td>
					<input type="text" name="cloudServerSite" class="text" value="${setting.cloudServerSite}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.currencySign")}: 
				</th>
				<td>
					<input type="text" name="currencySign" class="text" value="${setting.currencySign}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.currencyUnit")}: 
				</th>
				<td>
					<input type="text" name="currencyUnit" class="text" value="${setting.currencyUnit}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.stockAlertCount")}: 
				</th>
				<td>
					<input type="text" name="stockAlertCount" class="text" value="${setting.stockAlertCount}" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.stockAllocationTime")}: 
				</th>
				<td>
					<select name="stockAllocationTime">
						[#list stockAllocationTimes as stockAllocationTime]
							<option value="${stockAllocationTime}"[#if stockAllocationTime == setting.stockAllocationTime] selected="selected"[/#if]>${message("Setting.StockAllocationTime." + stockAllocationTime)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.defaultPointScale")}: 
				</th>
				<td>
					<input type="text" name="defaultPointScale" class="text" value="${setting.defaultPointScale}" maxlength="7" title="${message("admin.setting.defaultPointScaleTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isDevelopmentEnabled")}:
				</th>
				<td>
					<label title="${message("admin.setting.isDevelopmentEnabledTitle")}">
						<input type="checkbox" name="isDevelopmentEnabled" value="true"[#if setting.isDevelopmentEnabled] checked="checked"[/#if] />
						<input type="hidden" name="_isDevelopmentEnabled" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isReviewEnabled")}:
				</th>
				<td>
					<input type="checkbox" name="isReviewEnabled" value="true"[#if setting.isReviewEnabled] checked="checked"[/#if] />
					<input type="hidden" name="_isReviewEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isReviewCheck")}:
				</th>
				<td>
					<input type="checkbox" name="isReviewCheck" value="true"[#if setting.isReviewCheck] checked="checked"[/#if] />
					<input type="hidden" name="_isReviewCheck" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.reviewAuthority")}: 
				</th>
				<td>
					<select name="reviewAuthority">
						[#list reviewAuthorities as reviewAuthority]
							<option value="${reviewAuthority}"[#if reviewAuthority == setting.reviewAuthority] selected="selected"[/#if]>${message("Setting.ReviewAuthority." + reviewAuthority)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isConsultationEnabled")}:
				</th>
				<td>
					<input type="checkbox" name="isConsultationEnabled" value="true"[#if setting.isConsultationEnabled] checked="checked"[/#if] />
					<input type="hidden" name="_isConsultationEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isConsultationCheck")}:
				</th>
				<td>
					<input type="checkbox" name="isConsultationCheck" value="true"[#if setting.isConsultationCheck] checked="checked"[/#if] />
					<input type="hidden" name="_isConsultationCheck" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.consultationAuthority")}: 
				</th>
				<td>
					<select name="consultationAuthority">
						[#list consultationAuthorities as consultationAuthority]
							<option value="${consultationAuthority}"[#if consultationAuthority == setting.consultationAuthority] selected="selected"[/#if]>${message("Setting.ConsultationAuthority." + consultationAuthority)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isInvoiceEnabled")}:
				</th>
				<td>
					<input type="checkbox" name="isInvoiceEnabled" value="true"[#if setting.isInvoiceEnabled] checked="checked"[/#if] />
					<input type="hidden" name="_isInvoiceEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.isTaxPriceEnabled")}:
				</th>
				<td>
					<input type="checkbox" name="isTaxPriceEnabled" value="true" title="${message("admin.setting.taxRateTitle")}"[#if setting.isTaxPriceEnabled] checked="checked"[/#if] />
					<input type="hidden" name="_isTaxPriceEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.taxRate")}:
				</th>
				<td>
					<input type="text" name="taxRate" class="text" value="${setting.taxRate}" maxlength="7" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Setting.cookiePath")}: 
				</th>
				<td>
					<input type="text" name="cookiePath" class="text" value="${setting.cookiePath}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.cookieDomain")}: 
				</th>
				<td>
					<input type="text" name="cookieDomain" class="text" value="${setting.cookieDomain}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Setting.kuaidi100Key")}: 
				</th>
				<td>
					<input type="text" name="kuaidi100Key" class="text" value="${setting.kuaidi100Key}" maxlength="200" />
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
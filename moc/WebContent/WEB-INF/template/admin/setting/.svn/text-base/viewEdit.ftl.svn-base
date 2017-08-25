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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 显示设置
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
	<input type="hidden" name="editType" value="${editType}"/>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>课程图片(大):
				</th>
				<td>
					<span class="fieldTitle">宽度:</span>
					<input type="text" name="largeCourseImageWidth" class="text" value="${setting.largeCourseImageWidth}" maxlength="9" style="width: 50px;" title="${message("admin.setting.widthTitle")}" />
					<span class="fieldTitle">高度:</span>
					<input type="text" name="largeCourseImageHeight" class="text" value="${setting.largeCourseImageHeight}" maxlength="9" style="width: 50px;" title="${message("admin.setting.heightTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>课程图片(中):
				</th>
				<td>
					<span class="fieldTitle">宽度:</span>
					<input type="text" name="mediumCourseImageWidth" class="text" value="${setting.mediumCourseImageWidth}" maxlength="9" style="width: 50px;" title="${message("admin.setting.widthTitle")}" />
					<span class="fieldTitle">高度:</span>
					<input type="text" name="mediumCourseImageHeight" class="text" value="${setting.mediumCourseImageHeight}" maxlength="9" style="width: 50px;" title="${message("admin.setting.heightTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>课程图片(缩略):
				</th>
				<td>
					<span class="fieldTitle">宽度:</span>
					<input type="text" name="thumbnailCourseImageWidth" class="text" value="${setting.thumbnailCourseImageWidth}" maxlength="9" style="width: 50px;" title="${message("admin.setting.widthTitle")}" />
					<span class="fieldTitle">高度:</span>
					<input type="text" name="thumbnailCourseImageHeight" class="text" value="${setting.thumbnailCourseImageHeight}" maxlength="9" style="width: 50px;" title="${message("admin.setting.heightTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>默认课程图片(大):
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="defaultLargeCourseImage" class="text" value="${setting.defaultLargeCourseImage}" maxlength="200" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${setting.defaultLargeCourseImage}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>默认课程图片(中):
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="defaultMediumCourseImage" class="text" value="${setting.defaultMediumCourseImage}" maxlength="200" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${setting.defaultMediumCourseImage}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>默认课程图片(缩略):
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="defaultThumbnailCourseImage" class="text" value="${setting.defaultThumbnailCourseImage}" maxlength="200" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${setting.defaultThumbnailCourseImage}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>水印透明度:
				</th>
				<td>
					<input type="text" name="watermarkAlpha" class="text" value="${setting.watermarkAlpha}" maxlength="9" title="${message("admin.setting.watermarkAlphaTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					水印图片:
				</th>
				<td>
					<span class="fieldSet">
						<input type="file" name="watermarkImageFile" />
						<a href="${base}${setting.watermarkImage}" target="_blank">${message("admin.common.view")}</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					水印位置:
				</th>
				<td>
					<select name="watermarkPosition">
						[#list watermarkPositions as watermarkPosition]
							<option value="${watermarkPosition}"[#if watermarkPosition == setting.watermarkPosition] selected="selected"[/#if]>${message("Setting.WatermarkPosition." + watermarkPosition)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					价格精确位数:
				</th>
				<td>
					<select name="priceScale">
						<option value="0"[#if setting.priceScale == 0] selected="selected"[/#if]>${message("admin.setting.priceScale0")}</option>
						<option value="1"[#if setting.priceScale == 1] selected="selected"[/#if]>${message("admin.setting.priceScale1")}</option>
						<option value="2"[#if setting.priceScale == 2] selected="selected"[/#if]>${message("admin.setting.priceScale2")}</option>
						<option value="3"[#if setting.priceScale == 3] selected="selected"[/#if]>${message("admin.setting.priceScale3")}</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					价格精确方式:
				</th>
				<td>
					<select name="priceRoundType">
						[#list roundTypes as roundType]
							<option value="${roundType}"[#if roundType == setting.priceRoundType] selected="selected"[/#if]>${message("Setting.RoundType." + roundType)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					是否前台显示市场价:
				</th>
				<td>
					<input type="checkbox" name="isShowMarketPrice" value="true"[#if setting.isShowMarketPrice] checked="checked"[/#if] />
					<input type="hidden" name="_isShowMarketPrice" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>默认市场价换算比例:
				</th>
				<td>
					<input type="text" name="defaultMarketPriceScale" class="text" value="${setting.defaultMarketPriceScale}" maxlength="7" title="${message("admin.setting.defaultMarketPriceScaleTitle")}" />
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
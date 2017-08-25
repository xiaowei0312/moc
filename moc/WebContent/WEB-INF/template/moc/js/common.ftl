/*
 * 
 * JavaScript - Common
 * Version: 3.0
 */

var sram = {
	base: "${base}",
	locale: "${locale}"
};

var setting = {
	priceScale: "${setting.priceScale}",
	priceRoundType: "${setting.priceRoundType}",
	currencySign: "${setting.currencySign}",
	currencyUnit: "${setting.currencyUnit}",
	uploadImageExtension: "${setting.uploadImageExtension}",
	uploadFlashExtension: "${setting.uploadFlashExtension}",
	uploadMediaExtension: "${setting.uploadMediaExtension}",
	uploadFileExtension: "${setting.uploadFileExtension}"
};


var messages = {
	"moc.message.success": "${message("moc.message.success")}",
	"moc.message.error": "${message("moc.message.error")}",
	"moc.dialog.ok": "${message("moc.dialog.ok")}",
	"moc.dialog.cancel": "${message("moc.dialog.cancel")}",
	"moc.dialog.deleteConfirm": "${message("moc.dialog.deleteConfirm")}",
	"moc.dialog.clearConfirm": "${message("moc.dialog.clearConfirm")}",
	"moc.validate.required": "${message("moc.validate.required")}",
	"moc.validate.email": "${message("moc.validate.email")}",
	"moc.validate.url": "${message("moc.validate.url")}",
	"moc.validate.date": "${message("moc.validate.date")}",
	"moc.validate.dateISO": "${message("moc.validate.dateISO")}",
	"moc.validate.pointcard": "${message("moc.validate.pointcard")}",
	"moc.validate.number": "${message("moc.validate.number")}",
	"moc.validate.digits": "${message("moc.validate.digits")}",
	"moc.validate.minlength": "${message("moc.validate.minlength")}",
	"moc.validate.maxlength": "${message("moc.validate.maxlength")}",
	"moc.validate.rangelength": "${message("moc.validate.rangelength")}",
	"moc.validate.min": "${message("moc.validate.min")}",
	"moc.validate.max": "${message("moc.validate.max")}",
	"moc.validate.range": "${message("moc.validate.range")}",
	"moc.validate.accept": "${message("moc.validate.accept")}",
	"moc.validate.equalTo": "${message("moc.validate.equalTo")}",
	"moc.validate.remote": "${message("moc.validate.remote")}",
	"moc.validate.integer": "${message("moc.validate.integer")}",
	"moc.validate.positive": "${message("moc.validate.positive")}",
	"moc.validate.negative": "${message("moc.validate.negative")}",
	"moc.validate.decimal": "${message("moc.validate.decimal")}",
	"moc.validate.pattern": "${message("moc.validate.pattern")}",
	"moc.validate.extension": "${message("moc.validate.extension")}"
};

// 添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path : "") + (options.domain ? "; domain=" + options.domain : ""), (options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

// 货币格式化
function currency(value, showSign, showUnit) {
	if (value != null) {
		var price;
		if (setting.priceRoundType == "roundHalfUp") {
			price = (Math.round(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		} else if (setting.priceRoundType == "roundUp") {
			price = (Math.ceil(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		} else {
			price = (Math.floor(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		}
		if (showSign) {
			price = setting.currencySign + price;
		}
		if (showUnit) {
			price += setting.currencyUnit;
		}
		return price;
	}
}

// 多语言
function message(code) {
	if (code != null) {
		var content = messages[code] != null ? messages[code] : code;
		if (arguments.length == 1) {
			return content;
		} else {
			if ($.isArray(arguments[1])) {
				$.each(arguments[1], function(i, n) {
					content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			} else {
				$.each(Array.prototype.slice.apply(arguments).slice(1), function(i, n) {
					content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			}
		}
	}
}

(function($) {

	var zIndex = 100;

	// 检测登录
	$.checkLogin = function() {
		var result = false;
		$.ajax({
			url: sram.base + "/login/check.jhtml",
			type: "GET",
			dataType: "json",
			cache: false,
			async: false,
			success: function(data) {
				result = data;
			}
		});
		return result;
	}

	// 跳转登录
	$.redirectLogin = function (redirectUrl, message) {
		var href = sram.base + "/login.jhtml";
		if (redirectUrl != null) {
			href += "?redirectUrl=" + encodeURIComponent(redirectUrl);
		}
		if (message != null) {
			$.message("warn", message);
			setTimeout(function() {
				location.href = href;
			}, 1000);
		} else {
			location.href = href;
		}
	}

	// 消息框
	var $message;
	var messageTimer;
	$.message = function() {
		var message = {};
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
		} else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.content = arguments[1];
		} else {
			return false;
		}
		
		if (message.type == null || message.content == null) {
			return false;
		}
		
		if ($message == null) {
			$message = $('<div class="xxMessage"><div class="messageContent message' + message.type + 'Icon"><\/div><\/div>');
			if (!window.XMLHttpRequest) {
				$message.append('<iframe class="messageIframe"><\/iframe>');
			}
			$message.appendTo("body");
		}
		
		$message.children("div").removeClass("messagewarnIcon messageerrorIcon messagesuccessIcon").addClass("message" + message.type + "Icon").html(message.content);
		$message.css({"margin-left": - parseInt($message.outerWidth() / 2), "z-index": zIndex ++}).show();
		
		clearTimeout(messageTimer);
		messageTimer = setTimeout(function() {
			$message.hide();
		}, 3000);
		return $message;
	}

	// 令牌	
	$(document).ajaxSend(function(event, request, settings) {
		if (!settings.crossDomain && settings.type != null && settings.type.toLowerCase() == "post") {
			var token = getCookie("token");
			if (token != null) {
				request.setRequestHeader("token", token);
			}
		}
	});
	
	$(document).ajaxComplete(function(event, request, settings) {
		var loginStatus = request.getResponseHeader("loginStatus");
		var tokenStatus = request.getResponseHeader("tokenStatus");
		
		if (loginStatus == "accessDenied") {
			$.redirectLogin(location.href, "${message("moc.login.accessDenied")}");
		} else if (tokenStatus == "accessDenied") {
			var token = getCookie("token");
			if (token != null) {
				$.extend(settings, {
					global: false,
					headers: {token: token}
				});
				$.ajax(settings);
			}
		}
	});

})(jQuery);

// 令牌
$().ready(function() {

	$("form").submit(function() {
		var $this = $(this);
		if ($this.attr("method") != null && $this.attr("method").toLowerCase() == "post" && $this.find("input[name='token']").size() == 0) {
			var token = getCookie("token");
			if (token != null) {
				$this.append('<input type="hidden" name="token" value="' + token + '" \/>');
			}
		}
	});

});

// 验证消息
if ($.validator != null) {
	$.extend($.validator.messages, {
		required: message("moc.validate.required"),
		email: message("moc.validate.email"),
		url: message("moc.validate.url"),
		date: message("moc.validate.date"),
		dateISO: message("moc.validate.dateISO"),
		pointcard: message("moc.validate.pointcard"),
		number: message("moc.validate.number"),
		digits: message("moc.validate.digits"),
		minlength: $.validator.format(message("moc.validate.minlength")),
		maxlength: $.validator.format(message("moc.validate.maxlength")),
		rangelength: $.validator.format(message("moc.validate.rangelength")),
		min: $.validator.format(message("moc.validate.min")),
		max: $.validator.format(message("moc.validate.max")),
		range: $.validator.format(message("moc.validate.range")),
		accept: message("moc.validate.accept"),
		equalTo: message("moc.validate.equalTo"),
		remote: message("moc.validate.remote"),
		integer: message("moc.validate.integer"),
		positive: message("moc.validate.positive"),
		negative: message("moc.validate.negative"),
		decimal: message("moc.validate.decimal"),
		pattern: message("moc.validate.pattern"),
		extension: message("moc.validate.extension")
	});
	
	$.validator.setDefaults({
		errorClass: "fieldError",
		ignore: ".ignore",
		ignoreTitle: true,
		errorPlacement: function(error, element) {
			var fieldSet = element.closest("span.fieldSet");
			if (fieldSet.size() > 0) {
				error.appendTo(fieldSet);
			} else {
				error.insertAfter(element);
			}
		},
		submitHandler: function(form) {
			$(form).find(":submit").prop("disabled", true);
			form.submit();
		}
	});
}
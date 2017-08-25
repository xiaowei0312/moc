<!doctype html>
<html><!-- InstanceBegin template="/Templates/dwt.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
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
<script type="text/javascript" src="${base}/resources/moc/js/mine.js"></script>
<!--显示图片js-->
<script type="text/javascript" src="${base}/resources/moc/js/showImage.js"></script>

   <script type="text/javascript">
    $().ready(function() {
       var $inputForm = $("#inputForm");
       var $browserButton = $("#browserButton");
       [@flash_message /]
       
	   
       // 表单验证
	   $inputForm.validate({
		       rules: {
		          username:{
			          required: true,
				      pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				      maxlength:14
			       },
		          name: {
					  required: true,
					  pattern: /^[\u4E00-\u9FA5]{2,4}$/
				  },
				  idCard:{
				      required: false,
					  pattern: /^[1-9]\d{16}[\d|x|X]$/
				  },
				  mobile:{
				      required: true,
				      pattern: /(^((\(\d{3}\))|(\d{3}\-))?(1[358]\d{9})$)/
				  },
				  qq: {
					  required: false,
					  pattern: /^[1-9]\d{4,9}$/
				  }
		       },
		       messages:{
		          username:{
		              required:'昵称必须填写',
		              pattern: "昵称不符合规则",
					  maxlength:'用户名最多不能超过14位'
		          },
		          name: {
		              required:'姓名必须填写',
		              pattern:"姓名只能输入中文，两个字到四个字之间"
		          },
		          idCard:{
		              pattern:"身份证格式有误，请重新输入"
		          },
		          mobile:{
		             required:'手机号码必须填写',
		             pattern:"手机号码格式有误，请重新输入"
		          },
		          qq: {
		             pattern:'qq号格式不正确'
		          }
		       },
		       errorPlacement: function(error, element){
	              error.appendTo(element.next('span'));
	           }
	   });
    });
</script>
</head>
<body>
      [#include "/moc/include/memberHeader.ftl" /]

	<div class="membcont"> 
<h3 class="mem-h3">修改信息</h3>
<div class="box demo2">
    <div class="tab_box">
     <form name="memberForm" id="inputForm" action="${base}/member/profile/update.jhtml" method="post" enctype="multipart/form-data">
      <table class="membUpdate mas" width="700" height="508">
      <tr>
          <td class="mbmlabel" width="70" height="30"><span class="requiredField">*</span>昵称</td>
          <td width="350">
          	<input type="hidden" name="id" value="${member.id}"/>
          	<input type="text"  id="username" name="username" value="${member.username}"/>
          	<span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel mlhead">头像</td>
          <td>
          <input type="file" name="myfile" onchange="PreviewImage(this,'imgHeadPhoto','divPreview')"  id="browsefile"/><br>
          <div class="images_show" id="divPreview">
              <img id="imgHeadPhoto"  style="width: 150px; height: 150px; border: solid 1px #d2e2e2;" alt="" 
                 src=
                  [#if member.headImage?? && member.headImage!=Null]
  					"${member.headImage}"
  				  [#else]
  					"${base}/resources/moc/images/0-0.JPG"
  				  [/#if]
              />
              <span style="float:right; display:inline-block; width:400px;padding-top:30px">
	              <p class="first">上传图片预览区</p>
	              <p>图片仅限JPG、PNG格式</p>
	              <p>文件尺寸：532×400px</p>
	              <p>文件大小：200K以内</p>
	          </span>
			<div class="clearh" style="height:0"></div>
	           
      </div>
        <!--  <input id="browserButton" class="uploadbtn ub1"  type="button" value="上传">-->
          </td>
      </tr>
      <tr>
          <td class="mbmlabel"><span class="requiredField">*</span>姓名</td>
          <td>
         	 <input type="text"  name="name" value="${member.name}"/>
         	 <span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel">性别</td>
          <td>
              <input type="radio" name="gender" value="male" [#if member.gender="male"] checked [/#if]/>
              <label>男</label>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="radio" name="gender" value="female" [#if member.gender="female"] checked [/#if]/>
              <label>女</label>
              <span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel">身份证号</td>
          <td>
          <input  type="text" name="idCard" value="${member.idCard}"/>
          <span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel"><span class="requiredField">*</span>手机号码</td>
          <td>
              <input type="text" name="mobile" value="${member.mobile}"/>
              <span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel">公司</td>
          <td>
          	<input type="text" name="company" value="${member.company}"/>
          	<span class="validtext"></span>
          </td>
          
      </tr>
      <tr>
          <td class="mbmlabel">职业</td>
          <td>
          	<input type="text" name="occupation" value="${member.occupation}"/>
          	<span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel">头衔</td>
          <td>
          	<input type="text" name="honor" value="${member.honor}" />
          	<span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel">个人签名</td>
          <td>
          	<input type="text" name="signature" value="${member.signature}">
          	<span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel" height="88">自我介绍</td>
          <td>
          	<textarea rows="6" name="introduce">${member.introduce}</textarea>
          	<span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel">个人主页</td>
          <td>
          	<input type="text" name="site" value="${member.site}"/>
          	<span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel">微博</td>
          <td>
          	<input type="text" name="weibo" value="${member.weibo}"/>
          	<span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel">微信</td>
          <td>
          	<input type="text" name="weixin" value="${member.weixin}"/>
          	<span class="validtext"></span>
          </td>
      </tr>
      <tr>
          <td class="mbmlabel">QQ</td>
          <td>
              <input type="text" name="qq" value="${member.qq}"/>
              <span class="validtext"></span>
          </td>
      </tr>
      <tr>
      	  <td></td>
          <td><input type="submit" class="uploadbtn ub1" value="保存"></td>
          </tr>
      </table>
     </form>
    </div>   
  </div>
  </div>
        <div class="clearh"></div>
   </div>
   <div class="clearh"></div>
    [#include "/moc/include/footer.ftl" /]
</body>
</html>
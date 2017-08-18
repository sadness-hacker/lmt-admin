<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>运维平台-登录</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/login.css" />
</head>
<body>
	<div class ="box1">
			<img class="bg" src="${ctx }/images/background.jpg" />
			<div class="title">运维平台</div>
			<div id="log_center">
				<div id="log_contain">
					<div id="log1">
						<span>登录</span>
					</div>
					<br/>
					<form id="login" action="login" method="post" onsubmit="return ajaxSubmit(this)">
						<div id="login_form">
							<div id="log_name">
								<input type="text" id="log_user" name="username" value="" placeholder="请输入用户名"/>
							</div>
							<div id="log_pwdd">
								<input type="password" id="log_pwd" name="password" value=""  placeholder="请输入密码"/>
							</div>
						</div>
						<br />
						<div id="login_bottom">
							<span style="color:red;" id="error-msg">${error_msg }</span>
						</div>
						<div id="log_btnn">
							<input type="submit" id="log_btn" value="登录"  />
						</div>
					</form>
			</div>
		</div>
		<div class="footer">
			<span>Power By <a href="http://www.duchengxian.com" target="_blank">悲情黑客</a> @2013-2017 </span>
		</div>
	</div>
<script type="text/javascript" src="${ctx }/admin/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript">
function ajaxSubmit(o){
	$("#log_btn").attr("disabled",true);
	var options = {
		dataType : 'json',
		success:function(data){
			if(data.code == 200){
				window.location.href = "index";
			}else{
				$("#error-msg").html(data.msg);
				$("#log_btn").attr("disabled",false);
			}
		}
	}
	$(o).ajaxSubmit(options);
	return false;
}
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
request.setAttribute("adminManageActive", "active");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>管理员管理-管理后台</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@include file="../top.jsp" %>
</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">
<%@include file="../header.jsp" %>
<%@include file="../aside.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        管理员管理
        <small>管理员编辑</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${ctx }/admin/index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="${ctx }/admin/list"><i class="fa fa-user-secret"></i> 管理员列表</a></li>
        <li class="active"><i class="fa fa-edit"></i> 管理员编辑</li>
      </ol>
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="box box-danger">
         <!-- /.box-header -->
         <!-- form start -->
         <form class="form-horizontal" id="save-form" method="post" onsubmit="return checkForm(this)" data-toggle="validator" autocomplete="off">
           <input type="hidden" name="id" value="${admin.id }" />
           <input type="hidden" name="status" value="${admin.status }" />
           <div class="box-body">
           	<div class="form-group">
               <label for="username" class="col-sm-2 control-label">用户名</label>
               <div class="col-sm-10">
                 <input type="text" class="form-control" value="${admin.username }" name="username" id="username" placeholder="请输管理员用户名" required="required" autocomplete="off" <c:if test="${admin != null }">readonly="readonly"</c:if>>
               </div>
             </div>
             <c:if test="${admin == null }">
             <div class="form-group">
               <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
               <div class="col-sm-10">
                 <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="请输入密码" autocomplete="off">
               </div>
             </div>
             </c:if>
             <div class="form-group">
               <label for="realname" class="col-sm-2 control-label">真实姓名</label>
               <div class="col-sm-10">
                 <input type="text" class="form-control" value="${admin.realname }" name="realname" id="realname" placeholder="请输管理员真实姓名" required="required" autocomplete="off">
               </div>
             </div>
           	 <div class="form-group">
               <label for="inputEmail3" class="col-sm-2 control-label">邮箱</label>
               <div class="col-sm-10">
                 <input type="email" name="email" class="form-control" id="inputEmail3" placeholder="请输入邮箱地址" value="${admin.email }">
               </div>
             </div>
             <div class="form-group">
			  <label for="phoneNum" class="col-sm-2 control-label">手机号</label>
			  <div class="col-sm-10">
			    <input name="phoneNum" type="text" class="form-control" id="phoneNum" value="${admin.phoneNum }" placeholder="请输入手机号" required="required">
			  </div>
		  	</div>
		  	<div class="form-group">
			  <label for="avatar" class="col-sm-2 control-label">头像</label>
			  <div class="col-sm-10">
			    <input name="avatar" type="text" class="form-control" id="avatar" value="${admin.avatar }" placeholder="请上传用户头像" required="required" style="width:90%;display: inline-block;">
			    <input type="button" class="btn btn-default btn-flat" value="上传" style="width:9%;" onclick="autoUpload('upfile','${ctx}/upload',uploadCallback,'avatar','.png,.jpeg,.jpg,.bmp,.gif')">
			  </div>
		  	</div>
		  	<div class="form-group">
			  <label for="brief" class="col-sm-2 control-label">简介</label>
			  <div class="col-sm-10">
			    <textarea name="brief" type="text" class="form-control" id="brief" value="${admin.brief }" placeholder="请输用户简介" required="required">${admin.brief}</textarea>
			  </div>
		  	</div>
		  	
		  	<div class="form-group">
			  	<label for="submit" class="col-sm-2 control-label"></label>
			  	<div class="col-sm-10" style="text-align: center;">
					<span style="color:red;" id="error-msg"></span>
				</div>
			</div>
		  	<div class="form-group">
			  	<label for="submit" class="col-sm-2 control-label"></label>
			  	<div class="col-sm-10" style="text-align: center;">
					<input id="btn-submit" value="保存" type="submit" class="btn btn-success btn-flat" style="width:15%;" onclick="$('#save-form').attr('action','${ctx}/admin/save');$('#save-form').attr('target','_self');">
				</div>
			</div>
         </form>
       </div>
    </section>
    <!-- /.content -->
  </div>
<%@ include file="../footer.jsp" %>
</div>

<%@ include file="../bottom.jsp" %>

<script type="text/javascript" charset="utf-8" src="${ctx }/js/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/js/upload.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/js/validator.min.js"></script>
<script>
function uploadCallback(result,id){
	$("#" + id).val(result.url);
}
function checkForm(o){
	$("#btn-submit").attr("disabled",true);
	var options = {
		dataType : 'json',
		success:function(data){
			if(data.code == 200){
				window.location.href = "list";
			}else{
				$("#error-msg").html(data.msg);
				$("#btn-submit").attr("disabled",false);
			}
		}
	}
	$(o).ajaxSubmit(options);
	return false;
}
</script>
</body>
</html>

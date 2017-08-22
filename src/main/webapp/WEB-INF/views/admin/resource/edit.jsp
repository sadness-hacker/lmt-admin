<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
request.setAttribute("resourceManageActive", "active");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>资源管理-管理后台</title>
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
        资源管理
        <small>资源编辑</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${ctx }/admin/index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="${ctx }/admin/resource/list"><i class="fa fa-diamond"></i> 资源列表</a></li>
        <li class="active"><i class="fa fa-edit"></i> 资源编辑</li>
      </ol>
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="box box-danger">
         <!-- /.box-header -->
         <!-- form start -->
         <form class="form-horizontal" id="save-form" method="post" onsubmit="return checkForm(this)" data-toggle="validator" autocomplete="off">
           <input type="hidden" name="id" value="${res.id }" />
           <input type="hidden" name="status" value="1" />
           <div class="box-body">
           	<div class="form-group">
               <label for="name" class="col-sm-2 control-label">资源名</label>
               <div class="col-sm-10">
                 <input type="text" class="form-control" value="${res.name }" name="name" id="name" placeholder="请输资源名" required="required" autocomplete="off">
               </div>
             </div>
             <div class="form-group">
               <label for="name" class="col-sm-2 control-label">是否需要验证</label>
               <div class="col-sm-10">
                 <select class="form-control" name="needAuth">
                 	<option value="1" <c:if test="${res.needAuth == 1 }">selected="selected"</c:if>>是</option>
                 	<option value="0" <c:if test="${res.needAuth == 0 }">selected="selected"</c:if>>否</option>
                 </select>
               </div>
             </div>
             <div class="form-group">
               <label for="pid" class="col-sm-2 control-label">资源标识</label>
               <div class="col-sm-10">
                 <input type="text" class="form-control" value="${res.key }" name="key" id="key" placeholder="请输管理员真实姓名" required="required" autocomplete="off">
               </div>
             </div>
             <div class="form-group">
               <label for="pid" class="col-sm-2 control-label">上级资源</label>
               <div class="col-sm-10">
                 <select class="form-control" name="pid">
                 	<option value="0">顶级资源</option>
                 	<c:forEach var="r" items="${resList }">
                 	  <c:if test="${r.id != res.id }">
                 		<option value="${r.id }" <c:if test="${r.id == res.pid }">selected="selected"</c:if>>${r.name }-${r.key }</option>
                 	  </c:if>
                 	</c:forEach>
                 </select>
               </div>
             </div>
             <div class="form-group">
               <label for="pid" class="col-sm-2 control-label">资源类型</label>
               <div class="col-sm-10">
                 <select class="form-control" name="type">
                 	<c:forEach var="e" items="${resourceTypeMap }">
                 		<option value="${e.key }" <c:if test="${res.type eq e.key }">selected="selected"</c:if>>${e.value }</option>
                 	</c:forEach>
                 </select>
               </div>
             </div>
             <div class="form-group">
               <label for="pid" class="col-sm-2 control-label">资源链接</label>
               <div class="col-sm-10">
                 <input type="text" class="form-control" value="${res.url }" name="url" id="url" placeholder="请输资源链接" required="required" autocomplete="off">
               </div>
             </div>
           	 <div class="form-group">
               <label for="returnType" class="col-sm-2 control-label">返回值类型</label>
               <div class="col-sm-10">
                 <select class="form-control" name="returnType" 
                 <c:forEach var="e" items="${returnValueMap }">
					data-${e.key }='<c:out value="${e.value }"/>'
				 </c:forEach>
                 onchange="returnTypeChange(this)">
                 
                 	<c:forEach var="e" items="${returnTypeMap }">
                 		<option value="${e.key }" <c:if test="${res.returnType eq e.key }">selected="selected"</c:if>>${e.value }</option>
                 	</c:forEach>
                 </select>
               </div>
             </div>
             <div class="form-group">
               <label for="returnValue" class="col-sm-2 control-label">返回值</label>
               <div class="col-sm-10">
                 <input type="returnValue" name="returnValue" value="${res.returnValue }" class="form-control" id="returnValue" placeholder="请输入返回值">
               </div>
             </div>
             <div class="form-group">
			  <label for="developer" class="col-sm-2 control-label">开发者</label>
			  <div class="col-sm-10">
			    <input name="developer" type="text" class="form-control" id="developer" value="${res.developer }" placeholder="请输入开发者姓名" required="required">
			  </div>
		  	</div>
		  	<div class="form-group">
			  <label for="description" class="col-sm-2 control-label">资源描述</label>
			  <div class="col-sm-10">
			    <textarea name="description" type="text" class="form-control" id="description" placeholder="请输资源描述" required="required">${res.description}</textarea>
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
					<input id="btn-submit" value="保存" type="submit" class="btn btn-success btn-flat" style="width:15%;" onclick="$('#save-form').attr('action','${ctx}/admin/resource/save');$('#save-form').attr('target','_self');">
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
function returnTypeChange(o){
	var key = $(o).val();
	$("#returnValue").val($(o).data(key));
}
</script>
</body>
</html>

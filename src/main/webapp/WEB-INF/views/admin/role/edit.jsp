<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
request.setAttribute("roleManageActive", "active");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>角色编辑-管理后台</title>
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
        角色管理
        <small>角色编辑</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${ctx }/admin/index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="${ctx }/admin/role/list"><i class="fa fa-group"></i> 角色列表</a></li>
        <li class="active"><i class="fa fa-edit"></i> 角色编辑</li>
      </ol>
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="box box-danger">
         <!-- /.box-header -->
         <!-- form start -->
         <form class="form-horizontal" id="save-form" method="post" onsubmit="return checkForm(this)" data-toggle="validator" autocomplete="off">
           <input type="hidden" name="id" value="${role.id }" />
           <input type="hidden" name="status" value="1" />
           <input type="hidden" name="srcIds" value="" id="src-ids">
           <div class="box-body">
           	<div class="form-group">
               <label for="name" class="col-sm-2 control-label">角色名</label>
               <div class="col-sm-10">
                 <input type="text" class="form-control" value="${role.name }" name="name" id="name" placeholder="请输角色名" required="required" autocomplete="off">
               </div>
             </div>
		  	<div class="form-group">
			  <label for="description" class="col-sm-2 control-label">角色描述</label>
			  <div class="col-sm-10">
			    <textarea name="description" type="text" class="form-control" id="description" placeholder="请输角色描述" required="required">${role.description}</textarea>
			  </div>
		  	</div>
		  	<div class="form-group">
			  <label for="resourceIds" class="col-sm-2 control-label">角色权限</label>
			  <div class="col-sm-10">
			    <table class="table table-bordered">
			    	<tbody id="src-tbody">
			    	  <c:forEach var="r" items="${resList }">
				       <tr class="res-tr" data-id="${r.id }">
				        <td>${r.name }</td>
				        <td>${r.type }</td>
				        <td>${r.key }<br>${r.url }</td>
				        <td>${r.description }</td>
				       </tr>
				      </c:forEach>
			    	</tbody>
			    </table>
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
					<input id="btn-submit" value="保存" type="submit" class="btn btn-success btn-flat" style="width:15%;" onclick="$('#save-form').attr('action','${ctx}/admin/role/save');$('#save-form').attr('target','_self');">
				</div>
			</div>
         </form>
       </div>
         <div class="form-group box-header with-border" id="select-resource-div">
	           
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
	var ids = '';
	$(".res-tr").each(function(){
		var id = $(this).data('id');
		if(ids == ''){
			ids = id;
		}else{
			ids = ids + "," + id;
		}
	});
	$("#src-ids").val(ids);
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
function queryResouce(o){
	var options = {
		dataType : 'html',
		success:function(html){
			$("#select-resource-div").html(html);
			$(".src-input").each(function(){
				var si = $(this);
				$(".res-tr").each(function(){
					if($(this).data('id') == si.val()){
						si.prop('checked','checked');
					}
				});
			});
		}
	}
	$(o).ajaxSubmit(options);
	return false;
}
function selectAllSrc(o){
	if($(o).is(':checked')){
		$(".src-input").prop('checked','checked');
	}else{
		$(".src-input").removeAttr("checked");
	}
	$(".src-input").each(function(){
		var si = $(this);
		if($(this).is(':checked')){
			var b = true;
			$(".res-tr").each(function(){
				if($(this).data('id') == si.val()){
					b = false;
				}
			});
			if(b){
				var html = '<tr class="res-tr" data-id="' + $(this).val() + '">' + 
	        	'<td>' + $(this).data('name') + '</td>' + 
	        	'<td>' + $(this).data('type') + '</td>' +
	        	'<td>' + $(this).data('key') + '</td>' +
	        	'<td>' + $(this).data('url') + '</td>' +
	       		'</tr>';
	       	$("#src-tbody").append(html);
			}
		}else{
			$(".res-tr").each(function(){
				if($(this).data('id') == si.val()){
					$(this).remove();
				}
			});
		}
	});
}
function selectSrc(o){
	if($(o).is(':checked')){
		var html = '<tr class="res-tr" data-id="' + $(o).val() + '">' + 
        	'<td>' + $(o).data('name') + '</td>' + 
        	'<td>' + $(o).data('type') + '</td>' +
        	'<td>' + $(o).data('key') + '</td>' +
        	'<td>' + $(o).data('url') + '</td>' +
       		'</tr>';
       	$("#src-tbody").append(html);
	}else{
		$(".res-tr").each(function(){
			if($(this).data('id') == $(o).val()){
				$(this).remove();
			}
		});
	}
	var b = true;
	$(".src-input").each(function(){
		if($(this).is(':checked')){
		}else{
			b = false;
		}
	});
	if(b){
		$("#src-input-all").prop('checked','checked');
	}else{
		$("#src-input-all").removeAttr("checked");
	}
}
$(function(){
	var url = "${ctx}/admin/resource/queryResource";
	$.get(url,function(html){
		$("#select-resource-div").html(html);
		$(".src-input").each(function(){
			var si = $(this);
			$(".res-tr").each(function(){
				if($(this).data('id') == si.val()){
					si.prop('checked','checked');
				}
			})
		});
	});
});
</script>
</body>
</html>

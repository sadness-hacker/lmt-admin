<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setAttribute("roleManageActive", "active");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>角色列表-管理后台</title>
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
        角色列表
        <small>角色管理</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${ctx }/admin/index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">角色列表</li>
      </ol>
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="box box-danger">
      	<div class="box-header with-border">
         	<form action="" id="ducx-query-form">
         	<input type="hidden" name="currPage" value="1" id="ducx-query-curr-page">
         	<input type="hidden" name="pageSize" value="10">
            <label>关键字：</label>
            <input type="text" name="keyword" placeholder="请输入关键字..." value="${param.keyword }" style="padding: 4px 12px;margin-right: 10px;"/>
            
            <button type="submit" class="btn btn-default btn-flat" style="margin-right: 10px;"><i class="fa fa-search"></i> 查寻</button>
         	<a href="${ctx }/admin/role/edit" class="btn btn-info btn-flat">
         		<i class="fa fa-plus-circle"></i> 添加
         	</a>
         </form>
         </div>
         <!-- /.box-header -->
         <div class="box-body">
           <table class="table table-bordered">
             <tbody>
             <tr>
               <th>编号</th>
               <th>角色名</th>
               <th>角色描述</th>
               <th>状态</th>
               <th>操作</th>
             </tr>
           <c:forEach var="u" items="${pageModel.list }">
             <tr>
               <td>${u.id }</td>
               <td><label>${u.name }</label></td>
               <td>${u.description }</td>
               <td>
               	<c:choose>
               	  <c:when test="${u.status == 1 }">
               	  	<span style="color:green;">正常</span>
               	  </c:when>
               	  <c:when test="${u.status == 0 }">
               	  	<span style="color:red;">未保存</span>
               	  </c:when>
               	  <c:otherwise>
               	  	未知
               	  </c:otherwise>
               	</c:choose>
               	</td>
               <td>
                 <a href="${ctx }/admin/role/edit?id=${u.id}" class="btn btn-xs btn-success btn-flat">编辑</a>
                 <a href="${ctx }/admin/role/delete?id=${u.id}" class="btn btn-xs btn-danger btn-flat">删除</a>
               </td>
             </tr>
           </c:forEach>
           </tbody>
          </table>
         </div>
         <!-- /.box-body -->
         <div class="box-footer clearfix">
           <ul class="pagination pagination-sm no-margin pull-right">
           <c:if test="${pageModel.currPage > 1 }">
           	 <li><a href="javascript:void(0);" onclick="$('#ducx-query-curr-page').val(1);$('#ducx-query-form').submit();">首页</a></li>
             <li><a href="javascript:void(0);" onclick="$('#ducx-query-curr-page').val(${pageModel.currPage - 1});$('#ducx-query-form').submit();">«</a></li>
           </c:if>
           <c:forEach var="page" begin="1" end="${pageModel.totalPage + 1}" varStatus="status">
           	  <li <c:if test="${page == pageModel.currPage }">class="active"</c:if>><a href="javascript:void(0);" onclick="$('#ducx-query-curr-page').val(${page});$('#ducx-query-form').submit();">${page}</a></li>
           </c:forEach>
           <c:if test="${pageModel.currPage < pageModel.totalPage }">
             <li><a href="javascript:void(0)" onclick="$('#ducx-query-curr-page').val(${pageModel.currPage + 1});$('#ducx-query-form').submit();">»</a></li>
             <li><a href="javascript:void(0)" onclick="$('#ducx-query-curr-page').val(${pageModel.totalPage});$('#ducx-query-form').submit();">尾页</a></li>
           </c:if>
           </ul>
         </div>
       </div>
    </section>
    <!-- /.content -->
  </div>
<%@ include file="../footer.jsp" %>
</div>

<%@ include file="../bottom.jsp" %>
</body>
</html>

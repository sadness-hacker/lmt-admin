<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="box box-danger">
  <div class="box-header with-border">
   <form action="${ctx }/admin/resource/queryResource" id="ducx-query-form" onsubmit="return queryResouce(this)" method="post">
  	<input type="hidden" name="currPage" value="1" id="ducx-query-curr-page">
  	<input type="hidden" name="pageSize" value="10">
     <label>关键字：</label>
     <input id="queryResource-keyword" type="text" name="keyword" placeholder="请输入关键字..." value="${param.keyword }" style="padding: 4px 12px;margin-right: 10px;">
     <button type="submit" class="btn btn-default btn-flat" style="margin-right: 10px;"><i class="fa fa-search"></i> 查寻</button>
   </form>
  </div>
  <!-- /.box-header -->
  <div class="box-body">
    <table class="table table-bordered">
      <tbody>
      <tr>
        <th><input type="checkbox" onclick="selectAllSrc(this)" id="src-input-all"/>全选</th>
        <th>资源名</th>
        <th>资源类型</th>
        <th>资源标识/链接</th>
        <th>描述</th>
      </tr>
      <c:forEach var="r" items="${pageModel.list }">
       <tr>
        <td><input class="src-input" 
        	type="checkbox" 
        	value="${r.id }" 
        	onclick="selectSrc(this)"
        	data-name="${r.name }"
        	data-type="${r.type }"
        	data-key="${r.key }"
        	data-url="${r.url }"
        	/>
        </td>
        <td><label>${r.name }</label></td>
        <td>${r.type }</td>
        <td>${r.key }<br>${r.url }</td>
        <td>${r.description }</td>
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
    <c:forEach var="page" begin="1" end="${pageModel.totalPage}" varStatus="status">
    	  <li <c:if test="${page == pageModel.currPage }">class="active"</c:if>><a href="javascript:void(0);" onclick="$('#ducx-query-curr-page').val(${page});$('#ducx-query-form').submit();">${page}</a></li>
    </c:forEach>
    <c:if test="${pageModel.currPage < pageModel.totalPage }">
      <li><a href="javascript:void(0)" onclick="$('#ducx-query-curr-page').val(${pageModel.currPage + 1});$('#ducx-query-form').submit();">»</a></li>
      <li><a href="javascript:void(0)" onclick="$('#ducx-query-curr-page').val(${pageModel.totalPage});$('#ducx-query-form').submit();">尾页</a></li>
    </c:if>
    </ul>
  </div>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar">
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu">
      <li class="header">主菜单</li>
      <li class="${dashboardManageActive } treeview">
          <a href="${ctx }/admin/index"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a>
      </li>
      <c:if test="${1 == 1}">
      <li class="${adminManageActive } treeview">
        <a href="${ctx }/admin/list"><i class="fa fa-user-secret"></i> <span>管理员</span></a>
      </li>
      </c:if>
      <c:if test="${1 == 1}">
      <li class="${roleManageActive } treeview">
        <a href="${ctx }/admin/role/list"><i class="fa fa-group"></i> <span>角色管理</span></a>
      </li>
      </c:if>
      <c:if test="${1 == 1}">
      <li class="${resourceManageActive } treeview">
        <a href="${ctx }/admin/resource/list"><i class="fa  fa-diamond"></i> <span>资源管理</span></a>
      </li>
      </c:if>
    </ul>
  </section>
  <!-- /.sidebar -->
</aside>
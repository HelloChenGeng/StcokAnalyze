<%--
  Created by IntelliJ IDEA.
  User: ChenGeng
  Date: 2017/12/7
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加假期</title>
    <jsp:include page="/WEB-INF/include/include.jsp"></jsp:include>
    <script type="text/javascript" src="/js/addstock.js"></script>
</head>
<body class="skin-blue sidebar-mini" style="height: auto; min-height: 100%;">

<div class="wrapper" style="height: auto; min-height: 100%;">

    <jsp:include page="/WEB-INF/include/header.jsp"/>

    <jsp:include page="/WEB-INF/include/left.jsp"/>
    <!--  头部 -->

    <div class="content-wrapper" style="min-height: 960px;">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                监控股票列表
                <small>Version 2.0</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Dashboard</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">股票列表</h3>
                    </div>
                    <div class="box-body">
                        <div class="dataTables_wrapper form-inline dt-bootstrap">
                            <div class="row">
                                <div class="col-sm-12">
                                    <table class="table table-bordered table-hover dataTable">
                                        <thead>
                                            <tr>
                                                <th>股票代码</th>
                                                <th>股票名称</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${stockList}" var="stock" varStatus="status">
                                                <tr class="<c:if test="${status.index}%2==0">odd</c:if><c:if test="${status.index}%2==">even</c:if>">
                                                    <td>${stock.stockCode}</td>
                                                    <td>${stock.stockName}</td>
                                                    <td><a href="/addStock/delete?stockCode=${stock.stockCode}">删除</a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>

        </section>
        <!-- /.content -->
    </div>


    <!-- 尾部 -->
    <jsp:include page="/WEB-INF/include/footer.jsp" />

</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: ChenGeng
  Date: 2017/12/9
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>停牌股票</title>
    <jsp:include page="/WEB-INF/include/include.jsp"></jsp:include>
</head>
<script type="text/javascript" src="/js/tingpai.js" ></script>
<body class="skin-blue sidebar-mini" style="height: auto; min-height: 100%;">

<div class="wrapper" style="height: auto; min-height: 100%;">

    <jsp:include page="/WEB-INF/include/header.jsp"/>

    <jsp:include page="/WEB-INF/include/left.jsp"/>
    <!--  头部 -->

    <div class="content-wrapper" style="min-height: 960px;">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                停牌股票
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">停牌股票</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

        <div class="row" >

            <div class="col-md-12">
                <!-- general form elements -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">停牌股票</h3>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->

                        <div class="box-body">
                            <div>
                                <table id="tingPaiStockList" class="table table-bordered table-hover dataTable">
                                    <thead>
                                        <tr>
                                            <th>股票代码</th>
                                            <th>股票名称</th>
                                            <th>停牌时间</th>
                                            <th>复牌时间</th>
                                            <th>停牌类型</th>
                                            <th>停牌原因</th>
                                            <th>所在板块</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="stock" items="${list}" varStatus="status">
                                        <tr class="<c:if test="${status.index}%2==0">odd</c:if><c:if test="${status.index}%2==">even</c:if>">
                                            <td>${stock.stockCode}</td>
                                            <td>${stock.stockName}</td>
                                            <td>${stock.stopDateTime}</td>
                                            <td>${stock.continueDateTime}</td>
                                            <td>${stock.type}</td>
                                            <td>${stock.reason}</td>
                                            <td>${stock.marketPlate}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- /.box-body -->

                </div>
                <!-- /.box -->
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

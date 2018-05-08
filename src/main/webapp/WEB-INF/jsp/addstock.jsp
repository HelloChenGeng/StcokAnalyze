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
    <title>添加股票</title>
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
                添加股票
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
                <div class="col-md-12">
                    <form class="form-horizontal" action="/addStock/add" method="post">
                        <div class="box box-success">
                            <div class="box-header with-border">
                                <h3 class="box-title">添加股票</h3>
                            </div>
                            <div class="box-body">
                                <div class="col-md-3">
                                    <div class="box-body no-padding">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">股票代码</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="stockCode" name="stockCode" placeholder="股票代码" onchange="getStockInformation(this.value)">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="box-body no-padding">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">股票名称</label>
                                            <div class="col-sm-8">
                                                <input id="stockName" disabled type="text" name="stockName" class="form-control" placeholder="股票名称">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">当前股价</label>
                                            <div class="col-sm-8">
                                                <input id="nowPrice" disabled type="text" class="form-control" placeholder="当前股价">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="box-body no-padding">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">5分钟周期</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">EXPMA12</label>
                                            <div class="col-sm-4">
                                                <input id="EXPMA512" name="expma512" type="text" class="form-control" placeholder="5分钟EXPMA12">
                                            </div>
                                            <label class="col-sm-2 control-label">EXPMA26</label>
                                            <div class="col-sm-4">
                                                <input id="EXPMA526" name="expma526" type="text" class="form-control" placeholder="5分钟EXPMA26">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">DEA</label>
                                            <div class="col-sm-4">
                                                <input id="DEA5" name="DEA5" type="text" class="form-control" placeholder="5分钟DEA">
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">15分钟周期</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">EXPMA12</label>
                                            <div class="col-sm-4">
                                                <input id="EXPMA1512" name="expma1512" type="text" class="form-control" placeholder="15分钟EXPMA12">
                                            </div>
                                            <label class="col-sm-2 control-label">EXPMA26</label>
                                            <div class="col-sm-4">
                                                <input id="EXPMA1526" name="expma1526" type="text" class="form-control" placeholder="15分钟EXPMA26">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">DEA</label>
                                            <div class="col-sm-4">
                                                <input id="DEA15" name="DEA15" type="text" class="form-control" placeholder="15分钟DEA">
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">30分钟周期</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">EXPMA12</label>
                                            <div class="col-sm-4">
                                                <input id="EXPMA3012" name="expma3012" type="text" class="form-control" placeholder="30分钟EXPMA12">
                                            </div>
                                            <label class="col-sm-2 control-label">EXPMA26</label>
                                            <div class="col-sm-4">
                                                <input id="EXPMA3026" name="expma3026" type="text" class="form-control" placeholder="30分钟EXPMA26">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">DEA</label>
                                            <div class="col-sm-4">
                                                <input id="DEA30" name="DEA30" type="text" class="form-control" placeholder="30分钟DEA">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button class="btn btn-info pull-right" type="submit">提交</button>
                            </div>
                        </div>
                    </form>
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

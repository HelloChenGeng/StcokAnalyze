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
                添加假期
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
                    <form class="form-horizontal" action="/holiday/add">
                        <div class="box box-success">
                            <div class="box-header with-border">
                                <h3 class="box-title">添加假期</h3>
                            </div>
                            <div class="box-body">
                                <div class="col-md-12">
                                    <div class="box-body no-padding">
                                        <div class="form-group">
                                            <label class="col-sm-1 control-label">年份</label>
                                            <div class="col-sm-3">
                                                <select class="form-control" name="year">
                                                    <option>2017</option>
                                                    <option>2018</option>
                                                    <option>2019</option>
                                                </select>
                                            </div>
                                            <label class="col-sm-1 control-label">月份</label>
                                            <div class="col-sm-3">
                                                <select class="form-control" name="month">
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                    <option>6</option>
                                                    <option>7</option>
                                                    <option>8</option>
                                                    <option>9</option>
                                                    <option>10</option>
                                                    <option>11</option>
                                                    <option>12</option>
                                                </select>
                                            </div>
                                            <label class="col-sm-1 control-label">日期</label>
                                            <div class="col-sm-3">
                                                <select class="form-control" name="day">
                                                    <c:forEach var="day" items="${dayList}">
                                                        <option value="${day}">${day}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">确定</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">假期列表</h3>
                    </div>
                    <div class="box-body">
                        <div class="dataTables_wrapper form-inline dt-bootstrap">
                            <div class="row">
                                <div class="col-sm-12">
                                    <table class="table table-bordered table-hover dataTable">
                                        <thead>
                                            <tr>
                                                <th>日期</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${holidays}" var="holiday" varStatus="status">
                                                <tr class="<c:if test="${status.index}%2==0">odd</c:if><c:if test="${status.index}%2==">even</c:if>">
                                                    <td>${holiday.holidayDate}</td>
                                                    <td><a href="/holiday/delete?dateString=${holiday.holidayDate}">删除</a></td>
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

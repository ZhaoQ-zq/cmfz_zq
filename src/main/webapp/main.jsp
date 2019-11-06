<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.js"></script>

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持明法洲后台管理系统</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li><a>欢迎:&nbsp;&nbsp;${sessionScope.name}</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/dropLogin">安全退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<br/>
<br/>
<br/>
<div class="row">
    <div class="col-md-2">


        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            轮播图管理
                        </a>
                    </h4>
                </div>
               <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <li type="submit" class="btn btn-default"><a href="javascript:$('#right').load('${pageContext.request.contextPath}/mainBanner.jsp')">轮播图详情</a></li>
                    </div>
                </div>
                <%--<ul id="collapseOne" class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="javascript:$('#right').load('${pageContext.request.contextPath}/mainBanner.jsp')">轮播图详情</a></li>
                </ul>--%>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <li type="submit" class="btn btn-default"><a href="javascript:$('#right').load('${pageContext.request.contextPath}/mainAblum.jsp')">专辑详情</a></li>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <li type="submit" class="btn btn-default"><a href="javascript:$('#right').load('${pageContext.request.contextPath}/mainArticle.jsp')">查询文章</a></li>

                    </div>
                </div>
            </div>
            <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingThree2">
                <h4 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree2" aria-expanded="false" aria-controls="collapseThree">
                        用户管理
                    </a>
                </h4>
            </div>
            <div id="collapseThree2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                <div class="panel-body">
                    <li type="submit" class="btn btn-default"><a href="javascript:$('#right').load('${pageContext.request.contextPath}/mainUser.jsp')">用户详情</a></li>
                </div>
            </div>
        </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree3">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree3" aria-expanded="false" >
                            统计管理
                        </a>
                    </h4>
                </div>
                <div id="collapseThree3" class="panel-collapse collapse" role="tabpanel" >
                    <div class="panel-body">
                        <li type="submit" class="btn btn-default"><a href="javascript:$('#right').load('${pageContext.request.contextPath}/echarts.jsp')">用户统计表</a></li>
                    </div>
                    <div class="panel-body">
                        <li type="submit" class="btn btn-default"><a href="javascript:$('#right').load('${pageContext.request.contextPath}/china.jsp')">用户分布图</a></li>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-10" id="right">
        <div class="jumbotron">
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;持明法洲后台管理系统</p>
        </div>
        <img style="width: 1240px;height:400px" src="${pageContext.request.contextPath}/image/shouye.png">
    </div>
</div>
<br/>
<div class="panel panel-default" style="text-align: center">
    <div class="panel-heading">持明法洲后台管理系统@百知教育 2019-08-13</div>
</div>
</body>
</html>
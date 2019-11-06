<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <!--引入BootStrap的css样式-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css">
    <!--BootStrap与JQGRID整合后的css样式-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <!--引入jquery的js文件-->
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/jquery-3.3.1.min.js"></script>
    <!--引入BootStrap的js文件-->
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
    <!--jqgrid与bootstrap整合的国际化的js文件-->
    <script src="${pageContext.request.contextPath}/assets/bootstrap-jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <!--jqgrid与bootstrap整合的js文件-->
    <script src="${pageContext.request.contextPath}/assets/bootstrap-jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
    <script type="application/javascript">
        $(function () {
            $("#table").jqGrid({
                styleUI:"Bootstrap",
                url:"${pageContext.request.contextPath}/user/selectAll",//获取数据的地址
                datatype:"json",//自动把后台响应回的json协议串自动转换为jquery的对象或集合
                autowidth:true,
                pager:"#pager",
                page:1,
                rowNum:3,
                rowList:["3","6","9","12"], //动态调整分页的条数
                viewrecords:true, //显示总条数
                height:400,
                editurl:"${pageContext.request.contextPath}/user/edit",
                colNames:["id","头像","法名","用户名","性别","省","市","个性签名","手机号","密码","创建日期","状态"],
                colModel:[
                    {name:"id",hidden:true},
                    {name:"upload",edittype:"file",editable:true,editoptions:{enctype:"multipart/form-data"},
                    formatter:function (cellvalue,options,rowObject) {
                        return "<img src='${pageContext.request.contextPath}/image/"+rowObject.image+"' width='50px'/>"
                    }},
                    {name:"fname",editable:true},
                    {name:"username",editable:true},
                    {name:"sex",editable:true,edittype:"select",editoptions:{value:"男:男;女:女"}},
                    {name:"province",editable:true},
                    {name:"city",editable:true},
                    {name:"sign",editable:true},
                    {name:"phone",editable:true},
                    {name:"password",editable:true},
                    {name:"createDate"},
                    {name:"status",editable:true,edittype:"select",editoptions:{value:"正常:正常;冻结:冻结"}}
                ]
            }).jqGrid("navGrid","#pager",{edit:true},{
                closeAfterEdit:true,//修改完成关闭
                afterSubmit:function(response){
                    $.ajaxFileUpload({
                       url:"${pageContext.request.contextPath}/user/upload",
                       fileElementId:"upload",
                       type:"post",
                       success:function () {
                           $("#table").trigger("reloadGrid");
                       }
                    });
                    return "123";
                }
            },{
                //添加的额外控制
                closeAfterAdd:true,
                afterSubmit:function (response) {//上传添加的文件
                    $.ajaxFileUpload({
                       url:"${pageContext.request.contextPath}/user/upload",
                       fileElementId:"upload",
                       type:"post",
                       success:function () {
                           $("#table").trigger("reloadGrid");
                       }
                    });
                    return "147";
                }
            });
        });
    </script>
</head>
<body>
<!--页头-->
<div class="page-header">
    <h1>展示所有用户</h1>
</div>
<table id="table"></table>
<div id="pager"></div>


</body>
</html>
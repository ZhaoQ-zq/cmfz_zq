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
            $("#album-table").jqGrid({
                styleUI:"Bootstrap",
                url:"${pageContext.request.contextPath}/album/selectAllAlbum",//获取数据的地址
                datatype:"json",//自动把后台响应回的json协议串自动转换为jquery的对象或集合
                pager:"#pager", //开启分页的工具栏
                page:1,
                viewrecords:true, //显示总条数
                subGrid : true,
                height:400,
                editurl:"${pageContext.request.contextPath}/album/edit",
                colNames:["id","标题","封面","集数","评分","作者","播音","简介","状态","创建时间"],
                colModel:[
                    {name:"id",hidden:true},
                    {name:"title",editable:true},
                    {name:"upload",edittype:"file",editable:true,editoptions:{enctype:"multipart/form-data"},
                    formatter:function (cellvalue,options,rowObject) {
                        return "<img src='${pageContext.request.contextPath}/image/"+rowObject.cover+"' width='50px'/>"
                    }},
                    {name:"chapterCount",editable:true},
                    {name:"score",editable:true},
                    {name:"author",editable:true},
                    {name:"broadcast",editable:true},
                    {name:"brief",editable:true},
                    {name:"status",editable:true,edittype:"select",editoptions:{value:"正常:正常;冻结:冻结"}},
                    {name:"createDate"}
                ],
                autowidth:true,//自动调整表格的宽度
                rowNum:3,
                rowList:["3","6","9","12"], //动态调整分页的条数

                //子表格               承载二级表格容器的id 专辑的id
                subGridRowExpanded : function(subgrid_id, row_id) {

                    var subgrid_table_id = subgrid_id + "_t";
                    var pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                        "<div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid(
                        {

                            url : "${pageContext.request.contextPath}/chapter/selectAllChaptersByAlbumId?id="+row_id,
                            datatype : "json", //开启分页的工具栏
                            height:200,
                            editurl:"${pageContext.request.contextPath}/chapter/edit?albumId="+row_id,
                            colNames : [ '编号', '章节名称', '章节大小', '时长','创建日期','章节音频'],
                            colModel : [
                                {name : "id",hidden:true},
                                {name : "title",editable:true,width:"35px"},
                                {name : "size",width:"35px",formatter:function (value,options,rows) {
                                        return rows.size+"M";}},
                                {name : "duration",width:"35px"},
                                {name : "createDate",width:"35px"},
                                {name : "upload",editable:true,edittype:"file",formatter:function (value,options,rows) {
                                        return "<audio controls><source src='${pageContext.request.contextPath}/music/"+rows.url+"' type='audio/mpeg'></audio>";
                                    }}
                            ],
                            autowidth:true,//自动调整表格的宽度
                            styleUI:"Bootstrap",
                            pager : pager_id,
                            rowNum:3,
                            rowList:["3","6","9","12"],
                            viewrecords:true, //显示总条数
                        }).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : true,
                            add : true,
                            del : true
                        },{
                            closeAfterEdit:true,//修改完成关闭
                            afterSubmit:function(response){
                                $.ajaxFileUpload({
                                    url:"${pageContext.request.contextPath}/chapter/upload",
                                    fileElementId:"upload", //上传文件的属性名
                                    type:"post",
                                    success:function () {
                                        //$("#" + subgrid_table_id).trigger("reloadGrid");//刷新表格
                                        $("#album-table").trigger("reloadGrid");
                                    }
                                });
                                return "123";  //返回任意字符串
                            }
                        },{
                            closeAfterAdd:true,
                            afterSubmit:function (response) {//上传添加的文件
                                $.ajaxFileUpload({
                                    url:"${pageContext.request.contextPath}/chapter/upload",
                                    fileElementId:"upload",
                                    type:"post",
                                    success:function () {
                                        $("#album-table").trigger("reloadGrid");
                                    }
                                });
                                return "147";
                            }
                        },{
                            closeAfterEdit:true,
                            afterSubmit:function () {
                                $("#album-table").trigger("reloadGrid");
                                return "123";
                            }
                        });
                },
            }).jqGrid("navGrid","#pager",{edit:true},{
                closeAfterEdit:true,//修改完成关闭
                afterSubmit:function(response){
                    $.ajaxFileUpload({
                       url:"${pageContext.request.contextPath}/album/upload",
                       fileElementId:"upload", //上传文件的属性名
                       type:"post",
                       success:function () {
                           $("#album-table").trigger("reloadGrid");//刷新表格
                       }
                    });
                    return "123";  //返回任意字符串
                }
            },{
                //添加的额外控制
                closeAfterAdd:true,
                afterSubmit:function (response) {//上传添加的文件
                    $.ajaxFileUpload({
                       url:"${pageContext.request.contextPath}/album/upload",
                       fileElementId:"upload",
                       type:"post",
                       success:function () {
                           $("#album-table").trigger("reloadGrid");
                       }
                    });
                    return "147";
                }
            });
        });
        //导出专辑
        function exportAlbum() {
            location.href="${pageContext.request.contextPath}/album/exportAlbum"
        }
    </script>
</head>
<body>
<!--页头-->
<div class="page-header">
    <h1>展示所有专辑</h1>
</div>
<li><a onclick="exportAlbum()">导出专辑</a> </li>
<table id="album-table"></table>
<div id="pager"></div>
</body>
</html>
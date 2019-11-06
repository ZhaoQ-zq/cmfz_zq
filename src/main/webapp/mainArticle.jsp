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
    <!--引入kindedit的js-->
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>

    <script type="application/javascript">
        $(function () {
            $("#table").jqGrid({
                styleUI:"Bootstrap",
                url:"${pageContext.request.contextPath}/article/selectAll",//获取数据的地址
                datatype:"json",//自动把后台响应回的json协议串自动转换为jquery的对象或集合
                autowidth:true,//自动调整表格的宽度
                pager:"#pager", //开启分页的工具栏
                caption : "文章的详细信息",
                page:1,
                rowNum:3,
                rowList:["3","6","9","12"], //动态调整分页的条数
                viewrecords:true, //显示总条数
                height:400,
                editurl:"${pageContext.request.contextPath}/article/edit",
                colNames:["id","标题","作者","内容","创建日期","操作"],
                colModel:[
                    {name:"id",hidden:true},
                    {name:"title",editable:true},
                    {name:"author",editable:true},
                    {name:"content",editable:true},
                    {name:"createDate"},
                    {name:"option",formatter:function(cellvalue, options, rowObject){
                            return  "<a class='btn btn-warning' onclick=\"openModal('edit','"+rowObject.id+"')\">修改</a>";
                        }
                    }
                ]
            }).jqGrid("navGrid","#pager",{
                edit : false,
                add : false,
                del : true,
                search:false
            });
            //添加
         /*   $("#add").click(function() {
                //给对应的目标JQGRID表格做添加操作
                $("#table").jqGrid('editGridRow', "new", {
                    height : 300,
                    reloadAfterSubmit : true, //添加过后是否进行刷新
                    closeAfterAdd:true
                });
            });*/
        });
        function update(rowid) {
            //获取选中行的rowid
            if (rowid != null)
            //调用修改的方法
                $("#table").jqGrid('editGridRow', rowid, {
                    height : 300,
                    reloadAfterSubmit : true,
                    closeAfterEdit:true
                });
            else
                alert("请选中一行");
        }
        //打开模态框
        function openModal(oper,id) {
            KindEditor.html("#editor_id","");
            //获取行数据

            var article = $("#table").jqGrid('getRowData',id);
            $("#article-oper").val(oper);
            $("#article-id").val(article.id);
            $("#article-title").val(article.title);
            $("#article-author").val(article.author);
           // $("#editor_id").val(article.content);

            KindEditor.html("#editor_id",article.content);
            $("#article-modal").modal('show');
        }
        //编辑kindedit相关参数
        KindEditor.create('#editor_id',{
                width: '100%',
                height:'300px',
                resizeType:1,
                //显示图片空间按钮
                allowFileManager:true,
                //图片空间按钮发送的url路径
                fileManagerJson: '${pageContext.request.contextPath}/article/browser',
                //指定上传文件
                uploadJson: '${pageContext.request.contextPath}/article/upload',
                //将编辑器内容进行格式转换
                afterBlur:function () {
                    this.sync();
                },
                /*afterChange:function () {
                    this,sync();
                }*/
            });
        
        //添加文章
        function saveArticle() {
            $.ajax({
                url:'${pageContext.request.contextPath}/article/edit',
                datatype:'json',
                data:$("#article-form").serialize(),
                type:'POST',
                success:function () {
                    //关闭模态框
                    $("#article-modal").modal('hide');
                    //刷新表格
                    $("#table").trigger('reloadGrid');
                }
            })
        }
        //导出文章
        function exportArticle() {
            location.href="${pageContext.request.contextPath}/article/exportArticle";
        }
    </script>

    <div class="modal fade" role="dialog" id="article-modal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">我的文章</h4>
                </div>
                <div class="modal-body">
                    <form class="form-inline" id="article-form">
                        <input type="hidden" name="oper" id="article-oper">
                        <input type="hidden" name="id" id="article-id" >
                        <div class="form-group">
                            <label for="article-title">标题</label>
                            <input type="text" name="title" class="form-control" id="article-title" placeholder="请输入标题">
                        </div>
                        <div class="form-group">
                            <label for="article-author">作者</label>
                            <input type="text" name="author" class="form-control" id="article-author" placeholder="请输入作者">
                        </div>
                        <div class="form-group">
                           <textarea id="editor_id" name="content">

                                &lt;strong&gt;HTML内容&lt;/strong&gt;
                           </textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="saveArticle()">保存</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</head>
<body>
<!--页头-->
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#">所有文章</a>
    </li>
    <li id="add"><a onclick="openModal('add')">添加文章</a></li>
    <li><a onclick="exportArticle()">导出文章</a></li>
</ul>
<table id="table"></table>
<div id="pager"></div>
</body>
</html>
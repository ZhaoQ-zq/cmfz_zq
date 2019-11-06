<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id',{
                width: '100px',
                height:'100px',
                minWidth:1000,
                minHeight:200,
                resizeType:3,
                //显示图片空间按钮
                allowFileManager:true,
                //图片空间按钮发送的url路径
                fileManagerJson: '${pageContext.request.contextPath}/article/browser',
                //指定上传文件
                uploadJson: '${pageContext.request.contextPath}/article/upload',
                /* filterMode: true,
                // htmlTags:
                 wellFormatMode:true,

                 themeType:"??",
                 langType:"zh-CN",
                 designMode: true,
                 fullscreenMode: true,
                 //basePath:
                 themesPath:basePath + 'themes/',
                 pluginsPath: basePath + 'plugins/',
                 langPath:basePath + 'lang/',
                 minChangeSize:5,
                 urlType:"rt",
                 newlineTag:"p",
                 pasteType:2,
                 dialogAlignType:"page",
                 shadowMode:true,
                 zIndex:8555,
                 useContextmenu:true,
                 syncType:"from",
                 indentChar:"\t",
                 //cssPath:
                 //cssData:
                 bodyClass:"key-content",
                 colorTable:[
                     ['#E53333', '#E56600', '#FF9900', '#64451D', '#DFC5A4', '#FFE500'],
                     ['#009900', '#006600', '#99BB00', '#B8D100', '#60D978', '#00D5FF'],
                     ['#337FE5', '#003399', '#4C33E5', '#9933E5', '#CC33E5', '#EE33EE'],
                     ['#FFFFFF', '#CCCCCC', '#999999', '#666666', '#333333', '#000000']
                 ],
                // afterCreate:
                 //afterChange:
                // afterTab:
                // afterFocus:
                // afterBlur:
                // afterUpload:
                 allowPreviewEmoticons:true,
                 allowImageUpload:true,
                 allowFlashUpload:true,
                 allowMediaUpload:true,
                 allowFileUpload:true,

                 fontSizeTable:['9px', '10px', '12px', '14px', '16px', '18px', '24px', '32px'],
                 imageTabIndex:0,
                 formatUploadUrl:true,
                 fullscreenShortcut:false,
                 //extraFileUploadParams:
                 filePostName:"imgFile",
                 fillDescAfterUploadImage:false,
                 afterSelectFile:true,
                 //pagebreakHtml:
                 allowImageRemote:true,
                 autoHeightMode:false,
                 fixToolBar:false*/
            });
        });
    </script>
</head>
<body>
<textarea id="editor_id" name="content">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>
</body>
</html>
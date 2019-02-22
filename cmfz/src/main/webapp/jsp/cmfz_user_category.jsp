<%--
  Created by IntelliJ IDEA.
  User: du
  Date: 2019/1/5
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>功课管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <script>
        $(function () {
            $('#dg').datagrid({
                title:"功课展示",
                url:'${pageContext.request.contextPath}/lesson/getLessonsByPage',
                pagination:true,
                columns:[[
                    {field:'lessonId',title:'功课编号',width:100},
                    {field:'lessonName',title:'功课名',width:100},
                    {field:'name',title:'用户名',width:100,align:'right',formatter:function (value, row) {
                            return row.user.name;
                        }},
                    {field:'lessonStatus',title:'状态',width:100,align:'right',formatter:function (value, row) {
                            switch (value) {
                                case 1: return "必修";
                                default : return "选修";
                            }
                        }},
                ]]
            });
        });


    </script>
</head>
<body>

    <table id="dg"></table>
</body>
</html>
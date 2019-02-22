<%--
  Created by IntelliJ IDEA.
  User: du
  Date: 2019/1/4
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">

    <script type="text/javascript">

        $(function () {
            $("#myDataGrid").datagrid({
                url:"${pageContext.request.contextPath}/adminLog/selectLogByPage",
                pagination:true,
                toolbar: [{
                    text:"删除",
                    iconCls: 'icon-help',
                    handler: function(){
                        multiDelete();
                    }
                }],
                columns:[[
                    {field:"guruI1d",checkbox:true},
                    {field:'logId',title:'编号',width:100},
                    {field:'logAction',title:'操作',width:100},
                    {field:'adminUsername',title:'操作人',width:100,align:'center'},
                    {field:'adminId',title:'用户id',width:100,align:'center'},
                    {field:'logDate',title:'时间',width:220,align:'center'},
                    {field:'logIp',title:'登录ip',width:100,align:'center'},
                    {field:'logResult',title:'执行结果',width:100,align:'center'},
                    {field:'gg',title:'操作',width:123,formatter: function(value,row,index){

                                return '<a href="#"  onclick =deleteById('+row.logId+')>删除</a>';
                        }
                    }
                    ]],

            });

        });

        function doSearch() {

            $("#myDataGrid").datagrid("reload",{
                username:$("#userName").val()
            })
        }


        function multiDelete() {
            var rows=$("#myDataGrid").datagrid("getSelections");
            if (rows.length == 0) {
                $.messager.alert("提示", "选择内容为空,请先选择内容后再删除", "warning");
            }else {
                // console.log(rows);
                var ids = new Array(rows.lenth);
                $.each(rows,function (index, first) {
                    ids[index] = first.logId;
                });

                $.messager.confirm("提示","确认删除？",function (data) {
                    // console.log(data);
                    if (data) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/adminLog/multiDelete",
                            data: "ids=" + ids,
                            success:function (success) {
                                if (success) {
                                    $.messager.alert("提示", "删除成功!");
                                    $("#myDataGrid").datagrid("reload");
                                }else {
                                    $.messager.alert("提示", "删除失败", "warning");
                                }
                            }
                        });
                    }
                })

            }
        }

        function deleteById(id) {
            // alert(id);
            $.messager.confirm("提示","确认要删除吗？",function (data) {
                if (data) {
                    $.ajax({
                        url:"${pageContext.request.contextPath}/adminLog/deleteById",
                        data:{"id":id},
                        success:function (data) {
                            if(data){
                                $("#myDataGrid").datagrid("reload");
                            }
                        }

                    });
                }
            });

        }

    </script>
</head>
<body>

    <div id="doSearch" >
        <input id="userName" type="text"><input type="button" onclick="doSearch()" value="搜索"/>
    </div>
    <table id="myDataGrid"></table>



</body>
</html>

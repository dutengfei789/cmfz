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
                url:"${pageContext.request.contextPath}/admin/getAdminsByPage",
                pagination:true,
                toolbar: [{
                    text:"添加",
                    iconCls: 'icon-edit',
                    handler: function(){
                        $("#dd").dialog("open");
                    }
                },'-',{
                    text:"删除",
                    iconCls: 'icon-help',
                    handler: function(){
                        multiDelete();
                    }
                }],
                columns:[[
                    {field:"guruI1d",checkbox:true},
                    {field:'id',title:'用户编号',width:100},
                    {field:'username',title:'用户名',width:100},
                    {field:'password',title:'密码',width:100,align:'right'},
                    {field:'userStatus',title:'帐户状态',width:100,formatter: function(value,row,index){
                            switch (value) {
                                case 1: return "正常";
                                case 2: return "冻结";
                                default: return "正常";
                            }
                        }
                    },
                    {field:'banner',title:'操作',width:123,formatter: function(value,row,index){
                            if (row.guruId == 1) {
                                // return '<a href="#" onclick=updateState('+row+')>修改图片状态 </a>';
                                return '<a href="${pageContext.request.contextPath}/banner/updateStatus?guruId='+row.guruId+'&bannnerState=2" class="easyui-linkbutton" >展示图片</a>  \n';
                            }else {
                                return '<a href="${pageContext.request.contextPath}/banner/updateStatus?bannerId='+row.guruId+'&bannnerState=1" class="easyui-linkbutton" >取消展示</a>  \n';
                            }
                        }
                    }
                    ]],
                onDblClickRow:function(rowIndex, rowData){
                    //打开修改对话
                    //把原本的内容写入到修改对话框中对应的输入框中，显示出来
                    toOpenUpdateDialog(rowIndex,rowData);

                },

            });
        });

        function addForm() {
            $("#addForm").form("submit",{
                url:"${pageContext.request.contextPath}/admin/addAdmin",
                success:function (data) {
                    if (data) {
                        $.messager.alert("提示", "添加成功");
                        $("#dd").dialog("close");
                        $("#myDataGrid").datagrid("reload");

                    }else {
                        $.messager.alert("提示", "添加失败", "warning");
                    }
                }
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
                    ids[index] = first.id;
                });

                $.messager.confirm("提示","确认删除？",function (data) {
                    // console.log(data);
                    if (data) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/admin/multiDelete",
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

        function toOpenUpdateDialog(rowIndex,rowDate) {
            $("#updateDialog").dialog("open");
            $("#username").val(rowDate.username);
            $("#pwd").val(rowDate.password);
            $("#userId").val(rowDate.id);
        }

        function updateForm() {
            $("#updateForm").form("submit", {
                url: "${pageContext.request.contextPath}/admin/updateAdmin",
                success:function (data) {
                    // console.log(data);
                    var jsObj = JSON.parse(data);
                    // alert(jsObj);
                    if (jsObj) {
                        $("#updateDialog").dialog("close");
                        $.messager.alert("提示", "修改成功！", "info");
                        $("#myDataGrid").datagrid("reload");
                    }else {
                        $.messager.alert("提示", "修改失败", "warning");
                    }
                }

            });
        }
    </script>
</head>
<body>

    <table id="myDataGrid"></table>

    <div id="dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
         data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

        <form id="addForm">
            用户名：<input type="text" name="username"><br/>
            密码: <input type="text" name="password"/><br/>
            <<input type="button" value="添加" onclick="addForm()">

        </form>
    </div>

    <div id="updateDialog" class="easyui-dialog" title="管理员帐户修改" style="width:400px;height:200px;"
         data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
        <form id="updateForm">
            <input type="hidden" name="id" id="userId"/>
            用户名：<input type="text" id="username" name="username"><br/>
            密码: <input type="text" name="password" id="pwd"/><br/>
            <<input type="button" value="添加" onclick="updateForm()">

        </form>

    </div>


</body>
</html>

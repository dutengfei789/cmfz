<%--
  Created by IntelliJ IDEA.
  User: du
  Date: 2019/1/4
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上师管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">

    <script type="text/javascript">

        $(function () {
            $("#guruDateGrid").datagrid({
                url:"${pageContext.request.contextPath}/guru/getGurusByPage",
                pagination:true,
                columns:[[
                    {field:"guruI1d",checkbox:true},
                    {field:'guruId',title:'上师编号',width:100},
                    {field:'guruImage',title:'上师头像',width:100,align:'right',formatter: function(value,row,index){
                            return '<img src="http://localhost:8081/shouye/'+row.guruImage+'" width="30">' ;
                        }
                    },
                    {field:'guruName',title:'上师姓名',width:100},
                    {field:'guruNickname',title:'法号',width:100,align:'right'},
                    {field:'guruStatus',title:'状态',width:100,formatter: function(value,row,index){
                            switch (value) {
                                case 1: return "正常";
                                case 2: return "冻结";
                                default: return "已删除";
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
                    },


                ]],
                toolbar: [{
                    iconCls: 'icon-add',
                    handler: function(){
                        $("#addBanner").dialog("open");
                    }
                },'-',{
                    iconCls: 'icon-cancel',
                    handler: function(){
                        var row=$("#bannerDataGrid").datagrid("getSelections");
                        multiDelete(row);
                    }
                }]


            });
        });

        function doSearch() {
            var name=$("#searchName").val();
            $("#guruDateGrid").datagrid("load",{"name":name});
        }

        function importGuru() {
            $("#fileForm").form("submit",{
                url: "${pageContext.request.contextPath}/guru/importData",
                success:function (data) {
                    var jsObj = JSON.parse(data);
                    if (data) {
                        $.messager.alert("提示", "导入成功！");
                        $("#guruDateGrid").datagrid("reload");
                    }else {
                        $.messager("提示", "添加失败", "warning");
                    }
                }
            });

        }

    </script>
</head>
<body>

    <div id="searchDiv">
        <input type="text" name="searchName" id="searchName"/>
        <a href="javascript:void(0)" onclick="doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
    </div>
    <form method="post" enctype="multipart/form-data" id="fileForm">
        使用excel批量添加：<input type="file" name="file">
        <input type="button" onclick="importGuru()" value="上传"/>
    </form>
    <a href="${pageContext.request.contextPath}/guru/exportExcel" class="easyui-linkbutton" data-options="iconCls:'icon-2012080412486'">导出</a>
    <table id="guruDateGrid"></table>


</body>
</html>

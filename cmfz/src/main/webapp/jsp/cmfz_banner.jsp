<%--
  Created by IntelliJ IDEA.
  User: du
  Date: 2019/1/3
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min1.3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript" >
        $(function () {

            $("#bannerDataGrid").datagrid({
                url:"${pageContext.request.contextPath}/banner/getBannerByPage",
                pagination:true,
                striped:true,
                columns:[[
                    {field:"id",checkbox:true},
                    {field:'bannerId',title:'id',width:100},
                    {field:'bannerOldName',title:'名字',width:100},
                    {field:'bannerDate',title:'上传时间',width:100,align:'right'},
                    {field:'bannerDescription',title:'描述',width:100},
                    {field:'bannerImageUrl',title:'图片路径',width:300,align:'right',formatter: function(value,row,index){
                           return '<img src="http://localhost:8081'+row.bannerImageUrl+'" width="30">' ;
                        }
                    },
                    {field:'bannerState',title:'状态',width:100,formatter: function(value,row,index){
                            switch (value) {
                                case 1: return "取消展示";
                                case 2: return "正在展示";
                                default: return "已删除";
                            }
                        }
                    },
                    {field:'banner',title:'操作',width:123,formatter: function(value,row,index){
                            if (row.bannerState == 1) {
                                // return '<a href="#" onclick=updateState('+row+')>修改图片状态 </a>';
                                <%--return '<a href="${pageContext.request.contextPath}/banner/updateStatus?bannerId='+row.bannerId+'&bannnerState=2" class="easyui-linkbutton" >展示图片</a>  \n';--%>
                                return '<a id="btn" href="#" class=\"easyui-linkbutton\"  onclick="updateStatus('+row.bannerId+',2)">展示</a>';
                            }else {
                                return '<a id="btn" href="#" class=\"easyui-linkbutton\"  onclick="updateStatus('+row.bannerId+',1)">取消展示</a>';
                                <%--return '<a href="${pageContext.request.contextPath}/banner/updateStatus?bannerId='+row.bannerId+'&bannnerState=1" class="easyui-linkbutton" >取消展示</a>  \n';--%>
                            }
                        }
                    },


                ]],
                toolbar: [{
                    text:"添加",
                    iconCls: 'icon-add',
                    handler: function(){
                        $("#addBanner").dialog("open");
                    }
                },'-',{
                    text:"批量删除",
                    iconCls: 'icon-cancel',
                    handler: function(){
                        var row=$("#bannerDataGrid").datagrid("getSelections");
                        multiDelete(row);
                    }
                }]


            });

            $("#addBanner").dialog({
                title:"添加轮播图",
                collapsible:true,
                closed:true
            });



        });

        function multiDelete(row) {
            // alert("进来了");
            var ids = new Array(row.length);
            $.each(row,function (index, first) {
                console.log(first);
                ids[index]=first.bannerId;
            });
            // console.log(ids);
            if (ids.length == 0) {
                $.messager.alert("警告", "选择内容为空，请选择删除的内容", "warning");
            }else {
                $.messager.confirm("删除确认","确定要删除选择的图片吗？",function (data) {
                    if (data) {
                        $.ajax({
                            url:"${pageContext.request.contextPath}/banner/multiDelete",
                            data:"ids="+ids,
                            success:function (msg) {
                                if (msg) {
                                    $.messager.alert("温馨提醒", "删除成功！");
                                }else {
                                    $.messager.alert("警告", "删除失败！", "warning");

                                }
                            }
                        });
                    }
                })
            }
        }

        function addBanner() {
            $("#addForm").form("submit", {
                url:"${pageContext.request.contextPath}/banner/addBanner",
                success:function (data) {
                    // console.log(data);
                    if(data){
                        $("#addBanner").dialog("close");
                        $.messager.alert("提示", "添加成功", "info");
                        $("#bannerDataGrid").datagrid("reload");
                    }else {
                        $.messager.alert("警告", "添加失败，请稍后再试！", "warning");
                    }
                }
            });
        }

        //修改图片是否展示
        function updateStatus(id,status) {
            // alert(status);
            $.ajax({
                url:"${pageContext.request.contextPath}/banner/updateStatus",
                data:{"bannerId":id,"bannerState":status},
                dataType: "json",
                success:function (data) {
                    var jsObj=JSON.parse(data);
                    if (jsObj) {
                        $.messager.alert("提示", "修改成功");
                        $("#bannerDataGrid").datagrid("reload");
                    }
                }
            });
        }


        <%--function updateState(row) {--%>
            <%--$.ajax({--%>
                <%--url:"${pageContext.request.contextPath}/banner/updateState}"--%>
            <%--});--%>
        <%--}--%>
    </script>
</head>
<body>
<a href="#" class="easyui-linkbutton"  onclick="updateStatus()">easyui</a>
<table id="bannerDataGrid"></table>
    <div id="addBanner" title="添加轮播图">
        <form id="addForm" method="post" enctype="multipart/form-data"><br/>
            图片名称：<input type="text" name="bannerOldName"/><br/>
            图片上传：<input type="file" name="fileName"/><br/>
            描述：<input type="text" name="bannerDescription"/><br/>
            <input type="button"  onclick="addBanner()" value="添加"/>
        </form>
    </div>

</body>
</html>

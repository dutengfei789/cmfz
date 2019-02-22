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
                url:"${pageContext.request.contextPath}/user/getUsersByPage",
                pagination:true,
                columns:[[
                    {field:"guruI1d",checkbox:true},
                    {field:'userId',title:'用户编号',width:100},
                    {field:'werw',title:'用户头像',width:100,align:'right',formatter: function(value,row,index){
                            return '<img src="http://localhost:8081/userpicture'+row.userImage+'" width="30">' ;
                        }
                    },
                    {field:'telphone',title:'联系电话',width:100},
                    {field:'nickname',title:'法号',width:100,align:'right'},
                    {field:'name',title:'姓名',width:100,align:'right'},
                    {field:'autograph',title:'修改签名',width:100,align:'right'},
                    {field:'sex',title:'性别',width:100,align:'right'},
                    {field:'userProvince',title:'性别',width:100,align:'right'},
                    {field:'userCity',title:'性别',width:100,align:'right'},
                    {field:'guruName',title:'所拜上师',width:100,formatter: function(value,row,index){
                            return row.guru.guruName;
                        }
                    },
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
                    },


                ]],
            });
        });

    </script>
</head>
<body>

    <table id="myDataGrid"></table>

</body>
</html>

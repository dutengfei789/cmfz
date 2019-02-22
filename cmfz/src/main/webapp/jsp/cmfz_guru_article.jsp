<%--
  Created by IntelliJ IDEA.
  User: du
  Date: 2019/1/5
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>专辑管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <script type="text/javascript">

        $(function () {
            $("#myDataGrid").datagrid({
                url:"${pageContext.request.contextPath}/article/getArticleByPage",
                pagination:true,
                columns:[[
                    {field:"guruI1d",checkbox:true},
                    {field:'articleName',title:'文章名称',width:100},
                    {field:'werw',title:'图片',width:100,align:'right',formatter: function(value,row,index){
                            return '<img src="http://localhost:8081/shouye'+row.articleImage+'" width="30">' ;
                        }
                    },
                    {field:'articleContent',title:'内容',width:100,align:'right'},
                    {field:'articleDate',title:'写作日期',width:100,align:'right'},
                    {field:'guruName',title:'作者',width:100,align:'right',formatter:function (value,row) {
                            return row.guru.guruNickname;
                        }},
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

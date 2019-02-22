<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>emplist</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>

		<%--要引入两个css文件--%>
		<link href="${pageContext.request.contextPath}/themes/icon.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/themes/default/easyui.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
            $(function () {
                $("#myDatagrid").datagrid({
                    title:"用户列表",
                    url:"${pageContext.request.contextPath}/findByPage",
                    //让datagrid做分页
                    pagination:true,
                    rownumbers:true,//显示行号
                    loadMsg:"拼命加载中...",//加载信息
                    // singleSelect:true,//只允许选中一行
                    //checkOnSelect:false,//当选中一行的时候，前面的复选框不选中
                    columns:[[
                        //一个对象表示的是一列==》一个{}表示一列
                        {field:"gg",checkbox:true},
                        {title:"用户编号",field:"id"},
                        {title:"用户名",field:"username"},
                        {title:"密码",field:"password"},
                        {title:"salt",field:"salt"},
                        {title:"操作",field:"cz",formatter:function (value, rowDate, rowIndex) {
								return "<a href=\"${pageContext.request.contextPath}/delete?id="+rowDate.id+"\">delete emp</a>&nbsp;<a href=\"${pageContext.request.contextPath}/select?id="+rowDate.id+"\">update emp</a>"
                            }},

                    ]]

                });
            });
		</script>

	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<div id="header">
					<div id="rightheader">
						<p>
							2009/11/20 
							<br />
							安全退出
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="#">main</a>
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						Welcome!
					</h1>


					<table id="myDatagrid"></table>
					<p>
						<input type="button" class="button" value="Add Employee" onclick="location='addEmp.jsp'"/>
					</p>
				</div>
				<a href="">1</a>
				<a href="">2</a>
				<a href="">3</a>
			</div>
			<div id="footer">
				<div id="footer_bg">
				ABC@126.com
				</div>
			</div>
		</div>


	</body>
</html>

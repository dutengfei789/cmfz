<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
<script type="text/javascript">
	<!--菜单处理-->
    $(function(){

        $.ajax({
            url: "${pageContext.request.contextPath}/menu/findAllMenu",
            type: "get",
            dataType: "JSON",
            success: function (data) {
                /*参数1：集合   参数2：元素下标  参数3：当前元素的名字*/
                var each = $.each(data, function (index, first) {
                    // console.log(first);
                    var c = "";
                    $.each(first.children, function (index1, second) {
                        c += "<div style='text-align: center'><a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-ok',plain:true\" onclick=\"addTabs('" + second.menuName + "','" + second.menuUrl + "')\">" + second.menuName + "</a></div>";
                    })
                    $('#aa').accordion('add', {
                        title: first.menuName,
                        content: c,
                        selected: false
                    });
                });
            }

        })

    })

    function addTabs(munuName,menuUrl) {


        var isExist = $("#tt").tabs("exists",munuName);
        // console.log(isExist);
        if (isExist) {
            $("#tt").tabs("select", munuName);
        }else {
            $('#tt').tabs('add',{
                title: munuName,
                selected: true,
                closable:true,
                <%--href: "${pageContext.request.contextPath}" + menuUrl，--%>
                //...
                content:"<iframe src='${pageContext.request.contextPath}"+menuUrl+"' width='100%' height='100%' ></iframe>"
            });
        }





    }
</script>

</head>
<body class="easyui-layout">
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:xxxxx &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/invalid" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>

    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true">

		</div>

    </div>
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>
    </div>
</body>
</html>


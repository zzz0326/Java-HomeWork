<%--
  Created by IntelliJ IDEA.
  User: zzz
  Date: 2019/10/16
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title></title>
    asdas
</head>
<body>

</body>
</html>
<script type='text/javascript' language="javascript" charset="gb2312">
    $.ajax({
        type:"post",
        url:"http://localhost:8080/Weather_war_exploded/hi/say",
        //data:{"name":name},
        dataType:"json",
        success : function(data) {
            //data = JSON.stringify(data);
            document.write(data);
        }
    })
</script>
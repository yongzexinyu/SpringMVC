<%--
  Created by IntelliJ IDEA.
  User: 86151
  Date: 2021/9/3
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>--%>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
        <div>
            <form>
                <table>
                <tr>
                    <th>名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td><input type="text" value="水稻" readonly="readonly"></td>
                    <td><input type="number" value="100" readonly="readonly"></td>
                    <td><input type="number" required="required"  min="1" title="字段不能为空"></td>
                    <td><a href="#" onclick="goumai(this)">购买</a></td>
                </tr>
                <tr>
                    <td><input type="text"  value="玉米" readonly="readonly"></td>
                    <td><input type="number" value="150" readonly="readonly"></td>
                    <td><input type="number"required="required" min="1" title="字段不能为空"></td>
                    <td><a href="#" onclick="goumai(this)">购买</a></td>
                </tr>
                <tr>
                    <td><input type="text" value="马铃薯" readonly="readonly"></td>
                    <td><input type="number" value="200" readonly="readonly"></td>
                    <td><input type="number" required="required" min="1" title="字段不能为空"></td>
                    <td><a href="#" onclick="goumai(this)">购买</a></td>
                </tr>
                </table>
            </form>
        </div>
<script>

        function goumai(btn) {
            var tds = $(btn).parent().siblings().children();
            var name = tds.eq(0).val();
            var price = tds.eq(1).val();
            var number = tds.eq(2).val();
            var nongzuo={
                name:name,
                price:price,
                number:number
            }
            $.ajax({
                url:"/api/admin/nongZuoWu",
                type:"POST",
                dataType:'JSON',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify(nongzuo),
                success:function (res) {
                    console.log(res)
                }
            })
          //  console.log(name, price, number);
        }

</script>
</body>
</html>

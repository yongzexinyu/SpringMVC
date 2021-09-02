<%--
  Created by IntelliJ IDEA.
  User: 86151
  Date: 2021/8/31
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="/res/layui-v2.5.6/layui/layui.js"></script>
    <link rel="stylesheet" type="text/css" href="/res/layui-v2.5.6/layui/css/layui.css">
</head>
<body>
<%--<button class="layui-btn layui-btn-danger">测试静态资源是否能够访问</button>--%>
<div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>注册</legend>
</fieldset>
<form class="layui-form" action="" onsubmit="return false">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="adminName"  class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="adminPwd"  class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">重复密码</label>
            <div class="layui-input-inline">
                <input type="password" name="adminPwdR"  class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
    <div class="layui-inline">
        <label class="layui-form-label">入职时间</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="adminTime" name="adminTime" placeholder="yyyy-MM-dd HH:mm:ss">
        </div>
    </div>
    </div>
   <%-- //性别 单选框--%>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="adminSex" value="男" title="男" checked="">
            <input type="radio" name="adminSex" value="女" title="女">
        </div>
    </div>
  <%--  //爱好 多选框--%>
    <div class="layui-form-item">
        <label class="layui-form-label">爱好</label>
        <div class="layui-input-block">
            <input type="checkbox" lay-filter="number" name="adminAiHao" title="写作"  value="写作">
            <input type="checkbox" lay-filter="number" name="adminAiHao" title="阅读" value="阅读" checked>
            <input type="checkbox" lay-filter="number" name="adminAiHao" title="游戏" value="游戏">
        </div>
    </div>


   <%-- //就业城市 下拉框--%>
    <div class="layui-form-item">
        <label class="layui-form-label">就业城市</label>
        <div class="layui-input-block">
            <select name="adminCity" lay-filter="aihao">
                <option value=""></option>
                <option value="0"></option>
                <option value="北京" selected="">北京</option>
                <option value="上海">上海</option>
                <option value="广州">广州</option>
                <option value="深圳">深圳</option>
            </select>
        </div>
    </div>
   <%-- //开关是否专升本--%>
    <div class="layui-form-item">
        <label class="layui-form-label">是否专升本</label>
        <div class="layui-input-block">
            <input type="checkbox" checked="" name="adminOpen" lay-skin="switch" lay-filter="switchTest" lay-text="NO|YES">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="regBtn">立即注册</button>
           <%-- <button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
        </div>
    </div>
</form>
</div>

<script>
    layui.use(['form','layer','jquery','laydate'],function () {
        var form = layui.form;
        var layer = layui.layer;
        var $ = layui.jquery;
        var laydate = layui.laydate;

        //将页面全部复选框选中的值拼接到一个数组中
      /*  var arr_box = [];
        $('input[type=checkbox]:checked').each(function() {
            arr_box.push($(this).val());
        });*/


        laydate.render({
            elem: '#adminTime'
            ,type: 'datetime'
        });

        form.on('submit(regBtn)',function (data) {
            var arr=[];//定义一个数组
            var arr2=$("input[type=checkbox]:checked");//获取多选框的数组
            for (var i = 0; i <arr2.length-1 ; i++) {
                arr.push(arr2[i].value);//把多选框数组的value放入自己定义的里面
            }
          /*  $.each(arr2,function () {
                arr.push($(this).val())
            })*/
           alert(arr)
            //data.field.adminAiHao=arr
           // layer.msg(JSON.stringify(data.field.adminAiHao.title))
            $.ajax({
                url:"/api/admin/regBean",
                type:'POST',
                data:{
                    "adminName":data.field.adminName,
                    "adminPwd":data.field.adminPwd,
                    "adminPwdR":data.field.adminPwdR,
                    "adminTime":data.field.adminTime,
                    "adminSex":data.field.adminSex,
                    "adminAiHao":arr,
                    "adminCity":data.field.adminCity,
                    "adminOpen":data.field.adminOpen
                },
                dataType:'JSON',
                success:function (res) {
                    console.log(res)
                    if(res.code==0){
                      window.location.href="/pages/login"
                    }else if(res.code==4401){
                        layer.msg("你输入的秘码和重复密码不一致，注册失败")
                    }else{
                        layer.msg("填写完整表单")
                    }
                }
            });
        });
        //监听指定开关
        form.on('switch(switchTest)', function(data){
            var check=this.checked ? true : false;
            if(check){
               data.value="是"
                console.log(data.value)
            }else {
                data.value="否"
                console.log(data.value)
            }
           /* layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });*/
          //  layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });
    });
</script>
</body>
</html>

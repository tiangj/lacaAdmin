layui.use(['form', 'layedit'], function(){

    var form = layui.form
        ,layer = layui.layer


    //监听提交
    form.on('submit(add)', function(data){
        var arr = new Array();
        $("input:checkbox[name='roleId']:checked").each(function(i){
            arr[i] = $(this).val();
        });
        data.field.roleId = arr.join(",");//将数组合并成字符串


        $.ajax({
            url:ctxPath+'sysUserRole/saveUserRole',
            method:'post',
            data:data.field,
            dataType:'JSON',
            async:true,
            success:function(res){
                if(res.code==1){
                    layer.msg(res.msg,{icon:6},function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    });
                }else{
                    layer.msg(res.msg,{icon:5});
                }

            }
        })

        return false;
    });


});
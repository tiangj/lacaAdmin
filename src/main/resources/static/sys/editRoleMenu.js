
var setting = {
    //显示复选框
    check: {
        enable: true
    },
};


$(function(){
    var menusList=$("#menusList").val();
    $.fn.zTree.init($("#treeDemo"), setting, JSON.parse(menusList));


    layui.use(['form','layer'], function() {
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer
        form.verify();

        //监听提交
        form.on('submit(add)', function(data){
            var roleId=$("#roleId").val();


            var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
            var nodes=treeObj.getCheckedNodes(true);
            var v="";
            for(var i=0;i<nodes.length;i++){
                v+=nodes[i].id + ",";
            }
            console.log(v.substring(0,v.length-1));

            $.ajax({
                url:ctxPath+'sysRoleMenu/saveRoleMenu',
                method:'post',
                data:{roleId:roleId,menuIds:v},
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
        })
    });

});


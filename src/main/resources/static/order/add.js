
layui.use(['form','layer'], function() {
    $ = layui.jquery;
    var form = layui.form
        ,layer = layui.layer
    form.verify();

    //监听提交
    form.on('submit(add)', function(data){
        //不同产品的个数
        var childrenNumber=$('#productDiv').children("div").length;

        var formData=data.field;
        formData.prductTypeSize=childrenNumber;

        var productIds=new Array();

        for(var i=1;i<=childrenNumber;i++){

            productIds.push($("#productId_"+i).find("option:selected").val());
            var productIdAttr="productId_"+i;
            var productNumAttr="productNum_"+i;
            formData[productIdAttr]=$("#productId_"+i).find("option:selected").val();
            formData[productNumAttr]=$("#productNum_"+i).val();
        }

        var checkFlgProductIds=productIds.slice().sort();

        var flag=true;

        for(var i=0;i<checkFlgProductIds.length;i++){
            if (checkFlgProductIds[i]==checkFlgProductIds[i+1]){
                flag=false;
            }
        }


        if(flag==false){
            layer.msg("存在相同的产品数据,请检查数据是否正确...",{icon:5});
            return false;
        }else{
            $.ajax({
                url:ctxPath+'lacaProductOrder/saveOrder',
                method:'post',
                data:formData,
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
        }

    })
});

function addProductItem() {
    $.ajax({
        url:ctxPath+'lacaProductOrder/getAllLacaProduct',
        method:'post',
        async:true,
        success:function(res){
            var childrenNumber=$('#productDiv').children("div").length;
            var lacaProductListOption="";
            for(var i=0;i<res.length;i++){
                lacaProductListOption+="<option value='"+res[i].id+"'>"+res[i].productName+"</option>";
            }

            var index=childrenNumber+1;
            var id="prductItem_"+index;

            var selectName="productId_"+index;

            var productNumName="productNum_"+index;

            var html='<div class="layui-form-item" id="'+id+'">' +
                '            <div class="layui-inline">' +
                '                <div class="layui-input-block">' +
                '                    <select id="'+selectName+'" name="'+selectName+'" lay-verify="required">' +
                                        lacaProductListOption+
                '                    </select>' +
                '                </div>' +
                '            </div>' +
                '            <div class="layui-inline">' +
                '                <label class="layui-form-label">个数</label>' +
                '                <div class="layui-input-block">' +
                '                    <input type="text" id="'+productNumName+'" name="'+productNumName+'"  autocomplete="off" class="layui-input" lay-verify="number">' +
                '                </div>' +
                '            </div>' +
                '            <div class="layui-inline"><a href="javascript:void (0)" onclick="addProductItem()" style="font-size: 30px">+</a></div>' +
                '            <div class="layui-inline"><a href="javascript:void (0)" onclick="delProductItem('+index+')" style="font-size: 30px">-</a></div>' +
                '        </div>';
            $("#productDiv").append(html);

            var form = layui.form;
            form.render('select');

        }
    });

}

function delProductItem(index) {
    $("#prductItem_"+index).remove();
}
var height = $(document.body).height() * 0.8;


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



layui.use('table', function () {
    var table = layui.table;
    //方法级渲染
    table.render({
        elem: '#LAY_table_customerOrderInfo'
        , url: ctxPath + 'lacaProductOrder/getProductOrderInfo'
        , cols: [[
            {field: 'id', hide: true}
            , {field: 'saleType', title: '类型', width: '10%', sort: true}
            , {field: 'customerName', title: '客户姓名', width: '10%', sort: true}
            , {field: 'userShopFee', title: '用店费', width: '10%', sort: true}
            , {field: 'totalPrice', title: '总金额', width: '10%', sort: true}
            , {field: 'createDate', title: '创建时间', width: '20%', sort: true}
            , {
                filed: 'cz', title: '操作', width: '20%', templet: function (d) {
                    var html = "";
                    html += '<a class="layui-btn layui-btn-xs" lay-event="choose">选择</a>';
                    return html;
                }
            }
        ]]
        , id: 'testReload'
        , page: true
        , height: height
        , done: function (res, curr, count) {
            $("[data-field='id']").css('display', 'none');
        }
    });
    //监听工具条
    table.on('tool(customerOrder)', function (obj) {
        var data = obj.data;
        if (obj.event === 'choose') {
            //选择具体操作
            var orderId=data.id;
            setInnerOrderId(orderId);
            //关闭dialog
        }
    });

    var $ = layui.$, active = {
        reload: function () {
            var customerName = $('#customerName').val();
            var data = {
                customerName: customerName
            }
            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: data
            });
        }
    };

    $('.customerOrderTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});



function selectCustomerInfo() {
    // var customerName=$("#customerName").val();
    layer.open({
        id:'select',
        area: ['750px', '400px'],
        type: 1,
        title: '客戶订单信息',
        content: $('#dialog'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
    });
    //x_admin_show('客户信息', 'lacaProductOrder/toSelectCustomerInfo?customerName=' + customerName, 800, 600);
}



/****
 * 设置订单id到订单登记的表单
 * @param orderId
 */
function setInnerOrderId(orderId) {
    //关闭最新的dialog
    layer.close(layer.index);
    $("#innerOrderId").val(orderId);

}
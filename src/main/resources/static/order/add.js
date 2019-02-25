var height = $(document.body).height() * 0.8;


layui.use(['form','layer'], function() {
    $ = layui.jquery;
    var form = layui.form
        ,layer = layui.layer
    form.verify();

    form.on('select(productTypeFilter_1)', function(data){
        var optionHtml="";
        if(data.value!=null && data.value!=''){
            var productTypeId=data.value;
            $.ajax({
                url:ctxPath+'lacaProductOrder/getAllLacaProduct',
                method:'post',
                data:{productTypeId:productTypeId},
                async:true,
                success:function(res){
                    for(var i=0;i<res.length;i++){
                        optionHtml+='<option value="'+res[i].id+'">'+res[i].productName+'</option>';
                    }
                    $("#productId_1").html(optionHtml);
                    form.render("select");
                }
            });

        }
    });

    form.on('select(productTypeFilter_2)', function(data){
        var optionHtml="";
        if(data.value!=null && data.value!=''){
            var productTypeId=data.value;
            $.ajax({
                url:ctxPath+'lacaProductOrder/getAllLacaProduct',
                method:'post',
                data:{productTypeId:productTypeId},
                async:true,
                success:function(res){
                    for(var i=0;i<res.length;i++){
                        optionHtml+='<option value="'+res[i].id+'">'+res[i].productName+'</option>';
                    }
                    $("#productId_2").html(optionHtml);
                    form.render("select");
                }
            });

        }
    });

    form.on('select(productTypeFilter_3)', function(data){
        var optionHtml="";
        if(data.value!=null && data.value!=''){
            var productTypeId=data.value;
            $.ajax({
                url:ctxPath+'lacaProductOrder/getAllLacaProduct',
                method:'post',
                data:{productTypeId:productTypeId},
                async:true,
                success:function(res){
                    for(var i=0;i<res.length;i++){
                        optionHtml+='<option value="'+res[i].id+'">'+res[i].productName+'</option>';
                    }
                    $("#productId_3").html(optionHtml);
                    form.render("select");
                }
            });

        }
    });

    form.on('select(productTypeFilter_4)', function(data){
        var optionHtml="";
        if(data.value!=null && data.value!=''){
            var productTypeId=data.value;
            $.ajax({
                url:ctxPath+'lacaProductOrder/getAllLacaProduct',
                method:'post',
                data:{productTypeId:productTypeId},
                async:true,
                success:function(res){
                    for(var i=0;i<res.length;i++){
                        optionHtml+='<option value="'+res[i].id+'">'+res[i].productName+'</option>';
                    }
                    $("#productId_4").html(optionHtml);
                    form.render("select");
                }
            });

        }
    });

    form.on('select(productTypeFilter_5)', function(data){
        var optionHtml="";
        if(data.value!=null && data.value!=''){
            var productTypeId=data.value;
            $.ajax({
                url:ctxPath+'lacaProductOrder/getAllLacaProduct',
                method:'post',
                data:{productTypeId:productTypeId},
                async:true,
                success:function(res){
                    for(var i=0;i<res.length;i++){
                        optionHtml+='<option value="'+res[i].id+'">'+res[i].productName+'</option>';
                    }
                    $("#productId_5").html(optionHtml);
                    form.render("select");
                }
            });

        }
    });


    //监听提交
    form.on('submit(add)', function(data){
        //不同产品的个数
        var childrenNumber=$('.productDiv').children("div").length;

        var formData=data.field;
        formData.productTypeSize=childrenNumber;

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

        var saleType=formData.saleType;

        var status=formData.status;

        if(saleType==1 && status==1){
            layer.msg("数据状态存在问题,请检查数据是否正确...",{icon:5});
            return false;
        }
        if(saleType==2 && status==2){
            layer.msg("数据状态存在问题,请检查数据是否正确...",{icon:5});
            return false;
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
    var childrenNumberResult=$('.productDiv').children("div").length;
    if(childrenNumberResult>=5){
        layer.msg("最多新增5中类型的产品",{icon:5});
        return false;
    }

    var html="";
    $.ajax({
        url:ctxPath+'lacaProductOrder/getAllLacaProductType',
        method:'post',
        async:true,
        success:function(res){
            var childrenNumber=$('.productDiv').children("div").length;
            var lacaProductTypeListOption="<option value=''>请选择</option>";
            for(var i=0;i<res.length;i++){
                lacaProductTypeListOption+="<option value='"+res[i].id+"'>"+res[i].name+"</option>";
            }
            var index=childrenNumber+1;
            var id="prductItem_"+index;

            var selectName="productType_"+index;

            var filter="productTypeFilter_"+index;

            html+='<div class="layui-form-item" id="'+id+'">' +
                        '<div class="layui-inline">'+
                            ' <div class="layui-input-block">'+
                                    '<select id="'+selectName+'" name="'+selectName+'" lay-verify="required" lay-verify="required" lay-filter="'+filter+'" model="select" >' +
                                        lacaProductTypeListOption+
                                    '</select>'+
                            '</div>'+
                        '</div>';


            $.ajax({
                url:ctxPath+'lacaProductOrder/getAllLacaProduct',
                method:'post',
                async:true,
                success:function(res){
                    var childrenNumber=$('.productDiv').children("div").length;
                    var lacaProductListOption="";
                    for(var i=0;i<res.length;i++){
                        lacaProductListOption+="<option value='"+res[i].id+"'>"+res[i].productName+"</option>";
                    }

                    var index=childrenNumber+1;
                    var id="prductItem_"+index;

                    var selectName="productId_"+index;

                    var productNumName="productNum_"+index;

                    html+='            <div class="layui-inline">' +
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
                        '            <div class="layui-inline"><a href="javascript:void (0)" onclick="delProductItem('+index+')" style="font-size: 30px">-</a></div></div>' ;
                    $(".productDiv").append(html);

                    var form = layui.form;
                    form.render('select');

                }
            });

        }
    });





}

function delProductItem(index) {
    $("#prductItem_"+index).remove();
}



layui.use('table', function () {
    var table = layui.table;
    $ = layui.jquery;

    //方法级渲染
    table.render({
        elem: '#LAY_table_customerOrderInfo'
        , url: ctxPath + 'lacaProductOrder/getProductOrderInfo'
        , where:{customerName:$("#customerName").val(),orderId:$("#id").val()}
        , cols: [[
            {field: 'id', hide: true}
            , {field: 'saleType', title: '类型', width: '10%', sort: true, templet: function (d) {
                    if(d.saleType==1){
                        return "进";
                    }
                    if(d.saleType==2){
                        return "出";
                    }
                }
             }
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
            var customerName = $('#customerNameDialog').val();
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

$(function () {
    var exsitOrder=$("#exsitOrder").val();
    if(exsitOrder>0){

        debugger

        var productIds=new Array();

        var productNums=new Array();

        var productTypes=new Array();

        var productTypeNames=new Array();

        //获取所有的产品
        var inputs = document.getElementsByName('productInfo');//获取所有的input标签对象。
        for(var i=0;i<inputs.length;i++){

            var obj = inputs[i];
            if(obj.type=='checkbox'){
                if(obj.checked==true){
                    productIds[i] =obj.value;
                    productNums[i]=obj.title;
                }
            }
        }

        //获取所有产品类型
        var inputsType = document.getElementsByName('productTypeInfo');//获取所有的input标签对象。
        for(var i=0;i<inputsType.length;i++){

            var obj = inputsType[i];
            if(obj.type=='checkbox'){
                if(obj.checked==true){
                    productTypes[i] =obj.value;
                    productTypeNames[i]=obj.title;
                }
            }
        }

        var html="";
        for(var i=1;i<=exsitOrder;i++){

            var productId=$("#productId_"+i).val();

            var productNum=$("#productName_"+i).val();

            var selectName="productId_"+i;

            var productNumName="productNum_"+i;

            var lacaProductListOption="";

            for(var j=0;j<productIds.length;j++){
                if(productIds[j]==productId){
                    lacaProductListOption+='<option value="'+productId+'" selected="true">'+productNums[j]+'</option>';
                }else{
                    lacaProductListOption+='<option value="'+productIds[j]+'">'+productNums[j]+'</option>';
                }
            }


            var productTypeId=$("#productType_"+i).val();

            var selectTypeName="productType_"+i;

            var lacaProductListTypeOption="";

            var productTypeFilter="productTypeFilter_"+i;

            for(var j=0;j<productTypes.length;j++){
                if(productTypes[j]==productTypeId){
                    lacaProductListTypeOption+='<option value="'+productTypeId+'" selected="true">'+productTypeNames[j]+'</option>';
                }else{
                    lacaProductListTypeOption+='<option value="'+productTypes[j]+'">'+productTypeNames[j]+'</option>';
                }
            }



            if(i==1){
                html+='<div class="layui-form-item" id="prductItem_1">\n' +
                    '            <div class="layui-inline">\n' +
                    '                <label class="layui-form-label">产品信息</label>\n' +
                    '                <div class="layui-input-block">\n' +
                    '                    <select id="productType_1" name="productType_1" lay-verify="required" lay-filter="productTypeFilter_1" model="select">\n' +
                    lacaProductListTypeOption+
                    '                    </select>\n' +
                    '                </div>\n' +
                    '            </div>\n' +



                    '            <div class="layui-inline">\n' +
                    '                <label class="layui-form-label"></label>\n' +
                    '                <div class="layui-input-block">\n' +
                    '                    <select id="productId_1" name="productId_1" lay-verify="required">\n' +
                    lacaProductListOption+
                    '                    </select>\n' +
                    '                </div>\n' +
                    '            </div>\n' +


                    '            <div class="layui-inline">\n' +
                    '                <label class="layui-form-label">个数</label>\n' +
                    '                <div class="layui-input-block">\n' +
                    '                    <input type="text" id="productNum_1" name="productNum_1" value="'+productNum+'" autocomplete="off" class="layui-input" lay-verify="number">\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '\n' +
                    '            <div class="layui-inline"><a href="javascript:void (0)" onclick="addProductItem()" style="font-size: 30px">+</a></div>\n' +
                    '        </div>';
            }else{
                html+='<div class="layui-form-item" id="'+id+'">' +

                    '            <div class="layui-inline">' +
                    '                <div class="layui-input-block">' +
                    '                    <select id="'+selectTypeName+'" name="'+selectTypeName+'" lay-verify="required" lay-filter="'+productTypeFilter+'" model="select">' +
                    lacaProductListTypeOption+
                    '                    </select>' +
                    '                </div>' +
                    '            </div>' +


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
                    '                    <input type="text" id="'+productNumName+'" name="'+productNumName+'"  value="'+productNum+'" autocomplete="off" class="layui-input" lay-verify="number">' +
                    '                </div>' +
                    '            </div>' +
                    '            <div class="layui-inline"><a href="javascript:void (0)" onclick="addProductItem()" style="font-size: 30px">+</a></div>' +
                    '            <div class="layui-inline"><a href="javascript:void (0)" onclick="delProductItem('+i+')" style="font-size: 30px">-</a></div>' +
                    '        </div>';
            }
        }



        $(".productDiv").html(html);
        layui.use('form', function() {
            var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
            form.render();
        });
    }



})
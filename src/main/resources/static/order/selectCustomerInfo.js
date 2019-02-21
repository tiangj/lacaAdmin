var height = $(document.body).height() * 0.8;


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

/****
 * 设置订单id到订单登记的表单
 * @param orderId
 */
function setInnerOrderId(orderId) {
    parent.$("#layui-layer-iframe1").contents().find('#innerOrderId').val(orderId);
    var index=parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    console.log(parent.$("#layui-layer-iframe1").contents())
    $("#innerOrderId").val(orderId);

}
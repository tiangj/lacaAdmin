var height = $(document.body).height() * 0.8;

layui.use('table', function () {
    var table = layui.table;
    //方法级渲染
    table.render({
        elem: '#LAY_table_productOrder'
        , url: ctxPath + 'lacaProductOrder/listData'
        , cols: [[
            {field: 'id', hide: true}
            , {field: 'name', title: '产品名称', width: '20%', sort: true}

            , {field: 'createDate', title: '创建时间', width: '20%', sort: true}
            , {
                filed: 'cz', title: '操作', width: '20%', templet: function (d) {
                    var html = "";
                    html += '<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>';
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
    table.on('tool(productOrder)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            x_admin_show('修改订单信息', 'lacaProductOrder/toAdd?id=' + data.id, 900, 750);
        }
    });

    var $ = layui.$, active = {
        reload: function () {
            var name = $('#name').val();
            var data = {
                name: name
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

    $('.productOrderTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
})

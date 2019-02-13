var height = $(document.body).height() * 0.8;

layui.use('table', function () {
    var table = layui.table;
    //方法级渲染
    table.render({
        elem: '#LAY_table_menu'
        , url: ctxPath + 'sysMenu/listData'
        , cols: [[
            {field: 'id', hide: true}
            ,{field: 'parentId', hide: true}
            , {field: 'menuName', title: '菜单名称', width: '15%', sort: true}
            , {field: 'parentMenuName', title: '父级菜单', width: '15%', sort: true}
            , {field: 'href', title: '链接', width: '15%'}
            , {field: 'sort', title: '排序', width: '15%'}
            , {field: 'createDate', title: '创建时间', width: '20%', sort: true}
            , {
                filed: 'cz', title: '操作', width: '20%', templet: function (d) {
                    var html = "";
                    html += '<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>';
                    html += '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>';
                    return html;
                }
            }
        ]]
        , id: 'testReload'
        , page: true
        , height: height
        , done: function (res, curr, count) {
            $("[data-field='id']").css('display', 'none');
            $("[data-field='parentId']").css('display', 'none');
        }
    });
    //监听工具条
    table.on('tool(menu)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {

                $.ajax({
                    url: ctxPath + 'sysMenu/delMenu',
                    method: 'post',
                    data: {id: data.id},
                    dataType: 'JSON',
                    async: true,
                    success: function (res) {
                        if (res.code == 1) {
                            layer.msg(res.msg, {icon: 6});
                            obj.del();
                            layer.close(index);
                        } else {
                            layer.msg(res.msg, {icon: 2});
                        }
                    }
                })

            });
        }else if (obj.event === 'edit') {
            x_admin_show('修改菜单信息', 'sysMenu/toAdd?id=' + data.id, 800, 600);
            //图片上传页面
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

    $('.menuTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
})

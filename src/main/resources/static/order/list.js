var height = $(document.body).height() * 0.8;

layui.use('table', function () {
    var table = layui.table;
    //方法级渲染
    table.render({
        elem: '#LAY_table_productOrder'
        , url: ctxPath + 'lacaProductOrder/listData'
        , cols: [[
            {field: 'id', title: '订单id', width: '5%', sort: true}
            , {field: 'productInfos', title: '产品信息', width: '16%', sort: true}
            , {field: 'totalNum', title: '总数', width: '5%', sort: true}
            , {field: 'saleType', title: '类型', width: '5%', sort: true, templet: function (d) {
                    if(d.saleType==1){
                        return "进";
                    }
                    if(d.saleType==2){
                        return "出";
                    }
                }
              }
            , {field: 'joinShop', title: '加盟商', width: '5%', sort: true}
            , {field: 'customerName', title: '客户姓名', width: '5%', sort: true}
            , {field: 'designer', title: '设计师', width: '5%', sort: true}
            , {field: 'userShopFee', title: '用店费', width: '5%', sort: true}
            , {field: 'postWay', title: '支付方式', width: '6%', sort: true, templet: function (d) {
                    var html="";
                    if(d.postWay !=null && d.postWay!='' && d.postWay.indexOf('1')>-1){
                        html+="现金 &nbsp;";
                    }
                    if(d.postWay !=null && d.postWay!='' && d.postWay.indexOf('2')>-1){
                        html+="刷卡 &nbsp;";
                    }
                    if(d.postWay !=null && d.postWay!='' && d.postWay.indexOf('3')>-1){
                        html+="微信 &nbsp;";
                    }
                    if(d.postWay !=null && d.postWay!='' &&  d.postWay.indexOf('4')>-1){
                        html+="支付宝 &nbsp;";
                    }
                    return html;
                }
              }
            , {field: 'totalPrice', title: '总金额', width: '6%', sort: true}
            , {field: 'status', title: '状态', width: '6%', sort: true, templet: function (d) {
                    //1:借货,2:还货,3:已完成
                    if(d.status==1){
                        return "借货";
                    }else if(d.status==2){
                        return "还货";
                    }else if(d.status==3){
                        return "已完成";
                    }
                }
              }
            , {field: 'remark', title: '备注', width: '10%', sort: true}
            , {field: 'innerOrderId', title: '还货关联订单id', width: '6%', sort: true}
            , {field: 'createDate', title: '创建时间', width: '8%', sort: true}
            , {
                filed: 'cz', title: '操作', width: '6%', templet: function (d) {
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
           // $("[data-field='id']").css('display', 'none');
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
            var productName = $('#productName').val();

            var saleType=$("#saleType").find("option:selected").val();

            var joinShop=$("#joinShop").val();

            var customerName=$("#customerName").val();

            var designer=$("#designer").val();

            var data = {
                productName: productName,
                saleType:saleType,
                joinShop:joinShop,
                customerName:customerName,
                designer:designer
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

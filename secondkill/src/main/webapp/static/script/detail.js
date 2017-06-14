/**
 * Created by chaoge on 2017/6/12.
 */

function init() {
    $name = $('.goods-name');
    $name.hide();
    $.ajax({
        url: window.location.pathname,
        type: 'POST',
        success: function(goods) {
            $name.html(goods.name);
            $name.show();
            countDown(goods);
        }
    })
}

function countDown(goods) {
    var seckillBox = $('#seckill-box');
    if (goods.nowTime > goods.endTime) {
        //秒杀结束
        seckillBox.html('秒杀结束!');
    } else if (goods.nowTime < goods.startTime) {
        //秒杀未开始,计时事件绑定
        var killTime = new Date(goods.startTime + 1000);//todo 防止时间偏移
        seckillBox.countdown(killTime, function (event) {
            //时间格式
            var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒 ');
            seckillBox.html(format);
        }).on('finish.countdown', function () {
            //时间完成后回调事件
            //获取秒杀地址,控制现实逻辑,执行秒杀
            console.log('______fininsh.countdown');
            handlerSeckill(goods, seckillBox);
        });
    } else {
        //秒杀开始
        handlerSeckill(goods, seckillBox);
    }
}

function handlerSeckill(goods, node) {
    //获取秒杀地址,控制显示器,执行秒
    node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');

    if (goods.ongoing) {
        //绑定一次点击事件
        $('#killBtn').one('click', function () {
            //执行秒杀请求
            //1.先禁用按钮
            $(this).addClass('disabled');//,<-$(this)===('#killBtn')->
            //2.发送秒杀请求执行秒杀
            $.ajax({
                url: '/goods/kill',
                type: 'POST',
                data: {goodsId: goods.goodsId, uuid: goods.uuid, number: 1},
                success: function (result) {
                    if (result && result.data) {
                        //显示秒杀结果
                        node.html('<span class="label label-success">秒杀成功</span>');
                    } else {
                        node.html('<span class="label label-success">秒杀失败</span>');
                    }
                }
            });
        });
        node.show();
    } else {
        countDown(goods, node);
    }
};
init();
/**
 * Created by chaoge on 2017/6/12.
 */

function getDate(time) {
    return new Date(time).Format("yyyy-MM-dd hh:mm:ss");
}

function createListItem(item) {
    console.log(item.startTime)
    console.log(getDate(item.startTime))
    $tr = $("<tr></tr>");
    $tr.append($("<td>" + item.name + "</td>"));
    $tr.append($("<td>" + item.total + "</td>"));
    $tr.append($("<td>" + getDate(item.startTime) + "</td>"));
    $tr.append($("<td>" + getDate(item.endTime) + "</td>"));
    $tr.append($("<td>" + getDate(item.createTime) + "</td>"));
    $tr.append($('<td><a class="btn btn-info" href="/goods/detail/' + item.goodsId +'" target="_blank">详情</a></td>'));
    return $tr
}

$.ajax({
    url: '/goods',
    type: 'POST',
    success: function(list) {
        $sample = $('.list-tr');
        $table = $sample.parent();
        $.each(list, function(i, item){
            $table.append(createListItem(item));
        })
        $sample.remove();
    },
    error: function() {
        alert('数据加载失败')
    }
});

/**
 * 时间格式化
 * @param fmt
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
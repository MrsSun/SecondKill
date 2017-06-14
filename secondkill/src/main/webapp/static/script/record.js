/**
 * Created by chaoge on 2017/6/12.
 */

function getDate(time) {
    return new Date(time).Format("yyyy-MM-dd hh:mm:ss");
}

function createListItem(item) {
    $tr = $("<tr></tr>");
    $tr.append($("<td>" + item.goods.name + "</td>"));
    $tr.append($("<td>" + item.number + "</td>"));
    $tr.append($("<td>" + getDate(item.createTime) + "</td>"));
    return $tr
}

$.ajax({
    url: '/records',
    type: 'POST',
    success: function(response) {
        $sample = $('.list-tr');
        $table = $sample.parent();
        if (response.status == 200) {
            $.each(response.data, function(i, item){
                $table.append(createListItem(item));
            })
        } else {
            alert('数据加载失败')
        }
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
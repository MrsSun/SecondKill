package secondkill.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import secondkill.biz.GoodsBiz;
import secondkill.biz.UserBiz;
import secondkill.biz.dto.GoodsDetail;
import secondkill.biz.dto.SecondKillBizRequest;
import secondkill.entity.Goods;
import secondkill.entity.User;
import secondkill.util.UserUtils;
import secondkill.web.protocol.CommonWebResponse;
import secondkill.web.protocol.ResponseCode;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author chaoge
 * @date 2017/6/12
 */
@Controller
public class GoodsController {

    private final Logger logger = Logger.getLogger(GoodsController.class);

    @Autowired
    private GoodsBiz goodsBiz;

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/goods")
    @ResponseBody
    public List<Goods> getAllGoods() {
        logger.info("getAllGoods begin");
        List<Goods> goodses = goodsBiz.queryAllGoods();
        logger.info("getAllGoods success: " + goodses);
        return goodses;
    }

    @RequestMapping(value = "/goods/detail/{goodsId}", method = RequestMethod.GET)
    public String getGoodsGet(Long goodsId) {
        return "detail";
    }

    @RequestMapping(value = "/goods/detail/{goodsId}", method = RequestMethod.POST)
    @ResponseBody
    public GoodsDetail getGoods(@PathVariable("goodsId") Long goodsId) {
        logger.info("getGoods: " + goodsId);
        GoodsDetail goodsDetail = goodsBiz.queryGoodsDetails(goodsId);
        return goodsDetail;
    }

    @RequestMapping(value = "/goods/kill", method = RequestMethod.POST)
    @ResponseBody
    public CommonWebResponse<Boolean> secondKill(Long goodsId, String uuid, Integer number, HttpSession session) {
        logger.info("secondKill: " + goodsId + ", " + uuid + ", " + number);
        CommonWebResponse<Boolean> response = new CommonWebResponse<Boolean>();
        // 先拿用户信息，做信息验证
        User user = (User) session.getAttribute(UserUtils.USER_AUTH_KEY);
        logger.info(user);
        if (false == userBiz.validate(user)) {
            response.setData(false);
            response.setStatus(ResponseCode.WEB_STATUS_AUTH_ERROR);
            return response;
        }
        SecondKillBizRequest request = new SecondKillBizRequest(user.getUserId(), goodsId, uuid, number);
        boolean result = goodsBiz.secondKill(request);
        response.setData(result);
        response.setStatus(ResponseCode.WEB_STATUS_OK);
        return response;
    }

}

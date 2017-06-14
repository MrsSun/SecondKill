package secondkill.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import secondkill.biz.RecordBiz;
import secondkill.biz.UserBiz;
import secondkill.entity.Record;
import secondkill.entity.User;
import secondkill.util.UserUtils;
import secondkill.web.protocol.CommonWebResponse;
import secondkill.web.protocol.ResponseCode;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author chaoge
 * @date 2017/6/13
 */
@Controller
public class RecordController {

    @Autowired
    private RecordBiz recordBiz;

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/records", method = RequestMethod.GET)
    public String getRecordsPage() {
        return "record";
    }

    @RequestMapping(value = "/records", method = RequestMethod.POST)
    @ResponseBody
    public CommonWebResponse<List<Record>> getRecords(HttpSession session) {
        CommonWebResponse<List<Record>> response = new CommonWebResponse<>();
        User user = (User) session.getAttribute(UserUtils.USER_AUTH_KEY);
        if (false == userBiz.validate(user)) {
            response.setStatus(ResponseCode.WEB_STATUS_AUTH_ERROR);
            return response;
        }
        List<Record> records = recordBiz.getUserAllRecords(user.getUserId());
        response.setData(records);
        response.setStatus(ResponseCode.WEB_STATUS_OK);
        return response;
    }

}

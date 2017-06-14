package secondkill.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import secondkill.biz.RecordBiz;
import secondkill.dao.RecordDAO;
import secondkill.entity.Record;

import java.util.List;

/**
 * @author chaoge
 * @date 2017/6/13
 */
@Service
public class RecordBizImpl implements RecordBiz {

    @Autowired
    private RecordDAO recordDAO;

    @Override
    public List<Record> getUserAllRecords(Long userId) {
        return recordDAO.queryUserAllRecord(userId);
    }
}

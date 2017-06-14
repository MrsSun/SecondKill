package secondkill.biz;

import secondkill.entity.Record;

import java.util.List;

/**
 * @author chaoge
 * @date 2017/6/9
 */
public interface RecordBiz {

    List<Record> getUserAllRecords(Long userId);



}

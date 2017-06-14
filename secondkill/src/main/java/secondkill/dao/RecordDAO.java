package secondkill.dao;

import secondkill.entity.Record;

import java.util.List;

/**
 * @author chaoge
 * @date 2017/6/9
 */
public interface RecordDAO {

    List<Record> queryUserAllRecord(Long userId);

    int addRecord(Record record);

}

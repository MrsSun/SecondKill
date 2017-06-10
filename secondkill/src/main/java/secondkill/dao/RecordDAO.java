package secondkill.dao;

import secondkill.entity.Record;

import java.util.List;

/**
 * @author sunxin08
 * @date 2017/6/9
 */
public interface RecordDAO {

    List<Record> getUserAllRecord(Long userId);

    int addRecord(Record record);

}

package DataAccess.Contracts;

import DataAccess.Entities.Record;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RecordDao {
    List<Record> getRecordsByUserId(int userId) throws SQLException;
    List<Record> getTopRecords() throws SQLException;
    void createRecord(int score, int user_id, LocalDate date, LocalTime playTime) throws SQLException;
}

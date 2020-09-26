package cn.pw.studyJavaDemo.ZKLOCK.dao;

import cn.pw.studyJavaDemo.ZKLOCK.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordDao extends JpaRepository<Record,Integer> {
}

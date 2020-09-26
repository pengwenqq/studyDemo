package cn.pw.studyJavaDemo.ZKLOCK.dao;

import cn.pw.studyJavaDemo.ZKLOCK.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDao extends JpaRepository<Stock,Integer> {
}

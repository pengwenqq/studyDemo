package cn.pw.studyJavaDemo.ZKLOCK.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="t_record")
public class Record {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /**
     * 库存扣减计数
     */
    @Column(name="stock_record")
    private Integer stockRecord;
}

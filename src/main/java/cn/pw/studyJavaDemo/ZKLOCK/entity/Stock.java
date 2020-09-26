package cn.pw.studyJavaDemo.ZKLOCK.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="t_stock")
public class Stock {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    /**
     * 剩余库存
     */
    @Column(name="stock_num")
    private Integer stockNum;
}

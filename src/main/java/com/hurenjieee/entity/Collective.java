package com.hurenjieee.entity;
// Generated 2017-3-13 13:30:52 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Collective generated by hbm2java
 */
@Entity
@Table(name = "collective",catalog = "strms")
public class Collective implements java.io.Serializable {

    private Long    colId;
    private String  colName;
    private Integer colYear;
    private Long    colMajId;

    public Collective() {}

    public Collective(String colName, Integer colYear, Long colMajId) {
        this.colName = colName;
        this.colYear = colYear;
        this.colMajId = colMajId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "col_id",unique = true,nullable = false)
    public Long getColId(){
        return this.colId;
    }

    public void setColId(Long colId){
        this.colId = colId;
    }

    @Column(name = "col_name",length = 16)
    public String getColName(){
        return this.colName;
    }

    public void setColName(String colName){
        this.colName = colName;
    }

    @Column(name = "col_year")
    public Integer getColYear(){
        return this.colYear;
    }

    public void setColYear(Integer colYear){
        this.colYear = colYear;
    }

    @Column(name = "col_maj_id")
    public Long getColMajId(){
        return this.colMajId;
    }

    public void setColMajId(Long colMajId){
        this.colMajId = colMajId;
    }

}

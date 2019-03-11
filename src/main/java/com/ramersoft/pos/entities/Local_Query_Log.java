package com.ramersoft.pos.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;


@Entity(name="local_query_log")
@Table(name="local_query_log")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
  )
public class Local_Query_Log {

    @Id
    @SequenceGenerator(name="identifier", sequenceName="local_query_log_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id",insertable=false)
    public Long id;
    
    @Column(name="query")
    private String query;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ts")
    private Date ts;
    
    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    @Column(name="outlet_id")
    private Long outlet_id;
    
    
    @Column(name="error")
    private String error;
    
    @Column(name="th_id")
    private String th_id;
    
    
    /**
     * Add  sync_status and delivery_status
     */
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getOutlet_id() {
        return outlet_id;
    }

    public void setOutlet_id(Long outlet_id) {
        this.outlet_id = outlet_id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTh_id() {
        return th_id;
    }

    public void setTh_id(String th_id) {
        this.th_id = th_id;
    }
    
    
    
    @Override
    public String toString() {
        return "Local_query_log [id=" + id + ", query=" + query + ", ts=" + ts + ", outlet_id=" + outlet_id + ", error="
                + error + ", th_id=" + th_id + "]";
    }


}

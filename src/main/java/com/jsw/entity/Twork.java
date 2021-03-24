package com.jsw.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_work")
public class Twork {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 工作日期
     */
    @Column(name = "work_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workDate;

    /**
     * 工作开始时间
     */
    @Column(name = "start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 工作结束时间
     */
    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 是否打车
     */
    private Boolean taxi;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 工作时长
     */
    @Column(name = "over_time")
    private Double overTime;

    /**
     * 加班时长
     */
    @Column(name = "hard_time")
    private Double hardTime;

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取工作日期
     *
     * @return work_date - 工作日期
     */
    public Date getWorkDate() {
        return workDate;
    }

    /**
     * 设置工作日期
     *
     * @param workDate 工作日期
     */
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    /**
     * 获取工作开始时间
     *
     * @return start_time - 工作开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置工作开始时间
     *
     * @param startTime 工作开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取工作结束时间
     *
     * @return end_time - 工作结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置工作结束时间
     *
     * @param endTime 工作结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取是否打车
     *
     * @return taxi - 是否打车
     */
    public Boolean getTaxi() {
        return taxi;
    }

    /**
     * 设置是否打车
     *
     * @param taxi 是否打车
     */
    public void setTaxi(Boolean taxi) {
        this.taxi = taxi;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 获取工作时长
     *
     * @return over_time - 工作时长
     */
    public Double getOverTime() {
        return overTime;
    }

    /**
     * 设置工作时长
     *
     * @param overTime 工作时长
     */
    public void setOverTime(Double overTime) {
        this.overTime = overTime;
    }

    /**
     * 获取加班时长
     *
     * @return hard_time - 加班时长
     */
    public Double getHardTime() {
        return hardTime;
    }

    /**
     * 设置加班时长
     *
     * @param hardTime 加班时长
     */
    public void setHardTime(Double hardTime) {
        this.hardTime = hardTime;
    }
}
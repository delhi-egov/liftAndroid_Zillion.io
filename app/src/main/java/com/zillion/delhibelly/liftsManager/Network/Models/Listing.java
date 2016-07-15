package com.zillion.delhibelly.liftsManager.Network.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Listing {
    @SerializedName("assocDeputyId")
    @Expose
    private Integer assocDeputyId;
    @SerializedName("assocForm")
    @Expose
    private AssocForm assocForm;
    @SerializedName("assocInspector")
    @Expose
    private Integer assocInspector;
    @SerializedName("assocReport")
    @Expose
    private Integer assocReport;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("scheduledDate")
    @Expose
    private String scheduledDate;
    @SerializedName("completedOn")
    @Expose
    private String completedOn;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    /**
     * @return The assocDeputyId
     */
    public Integer getAssocDeputyId() {
        return assocDeputyId;
    }

    /**
     * @param assocDeputyId The assocDeputyId
     */
    public void setAssocDeputyId(Integer assocDeputyId) {
        this.assocDeputyId = assocDeputyId;
    }

    /**
     * @return The assocForm
     */
    public AssocForm getAssocForm() {
        return assocForm;
    }

    /**
     * @param assocForm The assocForm
     */
    public void setAssocForm(AssocForm assocForm) {
        this.assocForm = assocForm;
    }

    /**
     * @return The assocInspector
     */
    public Integer getAssocInspector() {
        return assocInspector;
    }

    /**
     * @param assocInspector The assocInspector
     */
    public void setAssocInspector(Integer assocInspector) {
        this.assocInspector = assocInspector;
    }

    /**
     * @return The assocReport
     */
    public Integer getAssocReport() {
        return assocReport;
    }

    /**
     * @param assocReport The assocReport
     */
    public void setAssocReport(Integer assocReport) {
        this.assocReport = assocReport;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The scheduledDate
     */
    public String getScheduledDate() {
        return scheduledDate;
    }

    /**
     * @param scheduledDate The scheduledDate
     */
    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    /**
     * @return The completedOn
     */
    public String getCompletedOn() {
        return completedOn;
    }

    /**
     * @param completedOn The completedOn
     */
    public void setCompletedOn(String completedOn) {
        this.completedOn = completedOn;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
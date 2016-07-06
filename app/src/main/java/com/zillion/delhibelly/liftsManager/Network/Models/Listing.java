package com.zillion.delhibelly.liftsManager.Network.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Listing {

    @SerializedName("assocDeputyId")
    @Expose
    private AssocDeputyId assocDeputyId;
    @SerializedName("assocForm")
    @Expose
    private AssocForm assocForm;
    @SerializedName("assocInspector")
    @Expose
    private AssocInspector assocInspector;
    @SerializedName("inspectorStatus")
    @Expose
    private String inspectorStatus;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    /**
     * @return The assocDeputyId
     */
    public AssocDeputyId getAssocDeputyId() {
        return assocDeputyId;
    }

    /**
     * @param assocDeputyId The assocDeputyId
     */
    public void setAssocDeputyId(AssocDeputyId assocDeputyId) {
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
    public AssocInspector getAssocInspector() {
        return assocInspector;
    }

    /**
     * @param assocInspector The assocInspector
     */
    public void setAssocInspector(AssocInspector assocInspector) {
        this.assocInspector = assocInspector;
    }

    /**
     * @return The inspectorStatus
     */
    public String getInspectorStatus() {
        return inspectorStatus;
    }

    /**
     * @param inspectorStatus The inspectorStatus
     */
    public void setInspectorStatus(String inspectorStatus) {
        this.inspectorStatus = inspectorStatus;
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

}

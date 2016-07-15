package com.zillion.delhibelly.liftsManager.Network.Models;

/**
 * Created by kohli on 12/07/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Form {
    @SerializedName("assignId")
    @Expose
    private Integer assignId;
    @SerializedName("date_of_inspection")
    @Expose
    private String dateOfInspection;
    @SerializedName("applicant_name")
    @Expose
    private String applicantName;
    @SerializedName("applicant_address")
    @Expose
    private String applicantAddress;
    @SerializedName("site_address")
    @Expose
    private String siteAddress;
    @SerializedName("is_new_lift")
    @Expose
    private Boolean isNewLift;
    @SerializedName("firm_name")
    @Expose
    private String firmName;
    @SerializedName("lift_type")
    @Expose
    private String liftType;
    @SerializedName("max_person")
    @Expose
    private Integer maxPerson;
    @SerializedName("num_of_stop")
    @Expose
    private Integer numOfStop;
    @SerializedName("depth_of_pit")
    @Expose
    private Integer depthOfPit;
    @SerializedName("overhead_clearance")
    @Expose
    private Double overheadClearance;
    @SerializedName("height_mc_room")
    @Expose
    private Double heightMcRoom;
    @SerializedName("shaft_width")
    @Expose
    private Double shaftWidth;
    @SerializedName("shaft_height")
    @Expose
    private Double shaftHeight;
    @SerializedName("door_width")
    @Expose
    private Double doorWidth;
    @SerializedName("door_height")
    @Expose
    private Double doorHeight;
    @SerializedName("other")
    @Expose
    private String other;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("completedOn")
    @Expose
    private String completedOn;

    /**
     * @return The assignId
     */
    public Integer getAssignId() {
        return assignId;
    }

    /**
     * @param assignId The assignId
     */
    public void setAssignId(Integer assignId) {
        this.assignId = assignId;
    }

    /**
     * @return The dateOfInspection
     */
    public String getDateOfInspection() {
        return dateOfInspection;
    }

    /**
     * @param dateOfInspection The date_of_inspection
     */
    public void setDateOfInspection(String dateOfInspection) {
        this.dateOfInspection = dateOfInspection;
    }

    /**
     * @return The applicantName
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * @param applicantName The applicant_name
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    /**
     * @return The applicantAddress
     */
    public String getApplicantAddress() {
        return applicantAddress;
    }

    /**
     * @param applicantAddress The applicant_address
     */
    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    /**
     * @return The siteAddress
     */
    public String getSiteAddress() {
        return siteAddress;
    }

    /**
     * @param siteAddress The site_address
     */
    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    /**
     * @return The isNewLift
     */
    public Boolean getIsNewLift() {
        return isNewLift;
    }

    /**
     * @param isNewLift The is_new_lift
     */
    public void setIsNewLift(Boolean isNewLift) {
        this.isNewLift = isNewLift;
    }

    /**
     * @return The firmName
     */
    public String getFirmName() {
        return firmName;
    }

    /**
     * @param firmName The firm_name
     */
    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    /**
     * @return The liftType
     */
    public String getLiftType() {
        return liftType;
    }

    /**
     * @param liftType The lift_type
     */
    public void setLiftType(String liftType) {
        this.liftType = liftType;
    }

    /**
     * @return The maxPerson
     */
    public Integer getMaxPerson() {
        return maxPerson;
    }

    /**
     * @param maxPerson The max_person
     */
    public void setMaxPerson(Integer maxPerson) {
        this.maxPerson = maxPerson;
    }

    /**
     * @return The numOfStop
     */
    public Integer getNumOfStop() {
        return numOfStop;
    }

    /**
     * @param numOfStop The num_of_stop
     */
    public void setNumOfStop(Integer numOfStop) {
        this.numOfStop = numOfStop;
    }

    /**
     * @return The depthOfPit
     */
    public Integer getDepthOfPit() {
        return depthOfPit;
    }

    /**
     * @param depthOfPit The depth_of_pit
     */
    public void setDepthOfPit(Integer depthOfPit) {
        this.depthOfPit = depthOfPit;
    }

    /**
     * @return The overheadClearance
     */
    public Double getOverheadClearance() {
        return overheadClearance;
    }

    /**
     * @param overheadClearance The overhead_clearance
     */
    public void setOverheadClearance(Double overheadClearance) {
        this.overheadClearance = overheadClearance;
    }

    /**
     * @return The heightMcRoom
     */
    public Double getHeightMcRoom() {
        return heightMcRoom;
    }

    /**
     * @param heightMcRoom The height_mc_room
     */
    public void setHeightMcRoom(Double heightMcRoom) {
        this.heightMcRoom = heightMcRoom;
    }

    /**
     * @return The shaftWidth
     */
    public Double getShaftWidth() {
        return shaftWidth;
    }

    /**
     * @param shaftWidth The shaft_width
     */
    public void setShaftWidth(Double shaftWidth) {
        this.shaftWidth = shaftWidth;
    }

    /**
     * @return The shaftHeight
     */
    public Double getShaftHeight() {
        return shaftHeight;
    }

    /**
     * @param shaftHeight The shaft_height
     */
    public void setShaftHeight(Double shaftHeight) {
        this.shaftHeight = shaftHeight;
    }

    /**
     * @return The doorWidth
     */
    public Double getDoorWidth() {
        return doorWidth;
    }

    /**
     * @param doorWidth The door_width
     */
    public void setDoorWidth(Double doorWidth) {
        this.doorWidth = doorWidth;
    }

    /**
     * @return The doorHeight
     */
    public Double getDoorHeight() {
        return doorHeight;
    }

    /**
     * @param doorHeight The door_height
     */
    public void setDoorHeight(Double doorHeight) {
        this.doorHeight = doorHeight;
    }

    /**
     * @return The other
     */
    public String getOther() {
        return other;
    }

    /**
     * @param other The other
     */
    public void setOther(String other) {
        this.other = other;
    }

    /**
     * @return The remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark The remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
}


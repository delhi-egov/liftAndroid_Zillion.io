package com.zillion.delhibelly.liftsManager.Network.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssocForm {

    @SerializedName("applicant_name")
    @Expose
    private String applicantName;
    @SerializedName("applicant_address")
    @Expose
    private String applicantAddress;
    @SerializedName("agent_name")
    @Expose
    private String agentName;
    @SerializedName("agent_address")
    @Expose
    private String agentAddress;
    @SerializedName("previous_license")
    @Expose
    private String previousLicense;
    @SerializedName("firm_name")
    @Expose
    private String firmName;
    @SerializedName("firm_address")
    @Expose
    private String firmAddress;
    @SerializedName("lift_type")
    @Expose
    private String liftType;
    @SerializedName("lift_speed_max")
    @Expose
    private String liftSpeedMax;
    @SerializedName("lift_capacity_weight")
    @Expose
    private Integer liftCapacityWeight;
    @SerializedName("lift_total_weight")
    @Expose
    private Integer liftTotalWeight;
    @SerializedName("max_passengers")
    @Expose
    private Integer maxPassengers;
    @SerializedName("weight_counter")
    @Expose
    private Integer weightCounter;
    @SerializedName("description_weight_num")
    @Expose
    private Integer descriptionWeightNum;
    @SerializedName("supporting_cable_description")
    @Expose
    private String supportingCableDescription;
    @SerializedName("supporting_cable_weight")
    @Expose
    private Integer supportingCableWeight;
    @SerializedName("supporting_cable_size")
    @Expose
    private Integer supportingCableSize;
    @SerializedName("depth_pit")
    @Expose
    private Double depthPit;
    @SerializedName("top_clearance")
    @Expose
    private Double topClearance;
    @SerializedName("bottom_clearance")
    @Expose
    private Integer bottomClearance;
    @SerializedName("overhead_arrangement")
    @Expose
    private String overheadArrangement;
    @SerializedName("number_of_stops")
    @Expose
    private String numberOfStops;
    @SerializedName("contact_person_name")
    @Expose
    private String contactPersonName;
    @SerializedName("contact_person_number")
    @Expose
    private String contactPersonNumber;
    @SerializedName("assocApplicant")
    @Expose
    private Integer assocApplicant;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("isReportReceived")
    @Expose
    private Boolean isReportReceived;
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
     * @return The agentName
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * @param agentName The agent_name
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * @return The agentAddress
     */
    public String getAgentAddress() {
        return agentAddress;
    }

    /**
     * @param agentAddress The agent_address
     */
    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    /**
     * @return The previousLicense
     */
    public String getPreviousLicense() {
        return previousLicense;
    }

    /**
     * @param previousLicense The previous_license
     */
    public void setPreviousLicense(String previousLicense) {
        this.previousLicense = previousLicense;
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
     * @return The firmAddress
     */
    public String getFirmAddress() {
        return firmAddress;
    }

    /**
     * @param firmAddress The firm_address
     */
    public void setFirmAddress(String firmAddress) {
        this.firmAddress = firmAddress;
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
     * @return The liftSpeedMax
     */
    public String getLiftSpeedMax() {
        return liftSpeedMax;
    }

    /**
     * @param liftSpeedMax The lift_speed_max
     */
    public void setLiftSpeedMax(String liftSpeedMax) {
        this.liftSpeedMax = liftSpeedMax;
    }

    /**
     * @return The liftCapacityWeight
     */
    public Integer getLiftCapacityWeight() {
        return liftCapacityWeight;
    }

    /**
     * @param liftCapacityWeight The lift_capacity_weight
     */
    public void setLiftCapacityWeight(Integer liftCapacityWeight) {
        this.liftCapacityWeight = liftCapacityWeight;
    }

    /**
     * @return The liftTotalWeight
     */
    public Integer getLiftTotalWeight() {
        return liftTotalWeight;
    }

    /**
     * @param liftTotalWeight The lift_total_weight
     */
    public void setLiftTotalWeight(Integer liftTotalWeight) {
        this.liftTotalWeight = liftTotalWeight;
    }

    /**
     * @return The maxPassengers
     */
    public Integer getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * @param maxPassengers The max_passengers
     */
    public void setMaxPassengers(Integer maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    /**
     * @return The weightCounter
     */
    public Integer getWeightCounter() {
        return weightCounter;
    }

    /**
     * @param weightCounter The weight_counter
     */
    public void setWeightCounter(Integer weightCounter) {
        this.weightCounter = weightCounter;
    }

    /**
     * @return The descriptionWeightNum
     */
    public Integer getDescriptionWeightNum() {
        return descriptionWeightNum;
    }

    /**
     * @param descriptionWeightNum The description_weight_num
     */
    public void setDescriptionWeightNum(Integer descriptionWeightNum) {
        this.descriptionWeightNum = descriptionWeightNum;
    }

    /**
     * @return The supportingCableDescription
     */
    public String getSupportingCableDescription() {
        return supportingCableDescription;
    }

    /**
     * @param supportingCableDescription The supporting_cable_description
     */
    public void setSupportingCableDescription(String supportingCableDescription) {
        this.supportingCableDescription = supportingCableDescription;
    }

    /**
     * @return The supportingCableWeight
     */
    public Integer getSupportingCableWeight() {
        return supportingCableWeight;
    }

    /**
     * @param supportingCableWeight The supporting_cable_weight
     */
    public void setSupportingCableWeight(Integer supportingCableWeight) {
        this.supportingCableWeight = supportingCableWeight;
    }

    /**
     * @return The supportingCableSize
     */
    public Integer getSupportingCableSize() {
        return supportingCableSize;
    }

    /**
     * @param supportingCableSize The supporting_cable_size
     */
    public void setSupportingCableSize(Integer supportingCableSize) {
        this.supportingCableSize = supportingCableSize;
    }

    /**
     * @return The depthPit
     */
    public Double getDepthPit() {
        return depthPit;
    }

    /**
     * @param depthPit The depth_pit
     */
    public void setDepthPit(Double depthPit) {
        this.depthPit = depthPit;
    }

    /**
     * @return The topClearance
     */
    public Double getTopClearance() {
        return topClearance;
    }

    /**
     * @param topClearance The top_clearance
     */
    public void setTopClearance(Double topClearance) {
        this.topClearance = topClearance;
    }

    /**
     * @return The bottomClearance
     */
    public Integer getBottomClearance() {
        return bottomClearance;
    }

    /**
     * @param bottomClearance The bottom_clearance
     */
    public void setBottomClearance(Integer bottomClearance) {
        this.bottomClearance = bottomClearance;
    }

    /**
     * @return The overheadArrangement
     */
    public String getOverheadArrangement() {
        return overheadArrangement;
    }

    /**
     * @param overheadArrangement The overhead_arrangement
     */
    public void setOverheadArrangement(String overheadArrangement) {
        this.overheadArrangement = overheadArrangement;
    }

    /**
     * @return The numberOfStops
     */
    public String getNumberOfStops() {
        return numberOfStops;
    }

    /**
     * @param numberOfStops The number_of_stops
     */
    public void setNumberOfStops(String numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    /**
     * @return The contactPersonName
     */
    public String getContactPersonName() {
        return contactPersonName;
    }

    /**
     * @param contactPersonName The contact_person_name
     */
    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    /**
     * @return The contactPersonNumber
     */
    public String getContactPersonNumber() {
        return contactPersonNumber;
    }

    /**
     * @param contactPersonNumber The contact_person_number
     */
    public void setContactPersonNumber(String contactPersonNumber) {
        this.contactPersonNumber = contactPersonNumber;
    }

    /**
     * @return The assocApplicant
     */
    public Integer getAssocApplicant() {
        return assocApplicant;
    }

    /**
     * @param assocApplicant The assocApplicant
     */
    public void setAssocApplicant(Integer assocApplicant) {
        this.assocApplicant = assocApplicant;
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
     * @return The isReportReceived
     */
    public Boolean getIsReportReceived() {
        return isReportReceived;
    }

    /**
     * @param isReportReceived The isReportReceived
     */
    public void setIsReportReceived(Boolean isReportReceived) {
        this.isReportReceived = isReportReceived;
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

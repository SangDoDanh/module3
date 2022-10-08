package model.model;

import java.sql.Date;

public class Contract {
    //id, start_date, end_date, deposit, employee_id, customer_id, facility_id
    private int id;
    private Date startDay;
    private Date endDay;
    private double deposit;
    private int employeeId;
    private int customerId;
    private int facilityID;

    public Contract() {
    }

    public Contract(int id, Date startDay, Date endDay, double deposit, int employeeId, int customerId, int facilityID) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.deposit = deposit;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.facilityID = facilityID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(int facilityID) {
        this.facilityID = facilityID;
    }
}

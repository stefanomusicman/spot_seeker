package models;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private double transactionAmount;
    private int renterId;
    private int renteeId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int listingId;

    public Transaction() {
    }

    public Transaction(int transactionId, double transactionAmount, int renterId, int renteeId, LocalDateTime startDate, LocalDateTime endDate, int listingId) {
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.renterId = renterId;
        this.renteeId = renteeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.listingId = listingId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public int getRenteeId() {
        return renteeId;
    }

    public void setRenteeId(int renteeId) {
        this.renteeId = renteeId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(transactionId);
    }
}

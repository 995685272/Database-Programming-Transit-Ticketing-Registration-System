package team.group33.bean;

public class TrainInfo {
    private String trainNumber;
    private String originStation;
    private String destinationStation;
    private String travelDate;
    private String departureTime;
    private String arriveTime;
    private String runningTime;
    private String fare;
    private String transitLineName;

    public String getTransitLineName() { return transitLineName; }

    public void setTransitLineName(String transitLineName) { this.transitLineName = transitLineName; }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getOriginStation() {
        return originStation;
    }

    public void setOriginStation(String originStation) {
        this.originStation = originStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String date) {
        this.travelDate = date;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }


}

package qtrip_qa.pages;

class ReservationHistory {
    private String transactionId;
    private String bookingName;
    private String adventure;
    private String persons;
    private String date;
    private String price;
    private String bookingtime;

    public ReservationHistory(String transactionId, String bookingName, String adventure,
            String persons, String date, String price, String bookingtime) {
        this.transactionId = transactionId;
        this.bookingName = bookingName;
        this.adventure = adventure;
        this.persons = persons;
        this.date = date;
        this.price = price;
        this.bookingtime = bookingtime;
    }
}
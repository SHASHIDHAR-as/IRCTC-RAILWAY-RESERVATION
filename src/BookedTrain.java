public class BookedTrain {
    int train_no;
    String train_name;
    String source;
    String destination;
    String arrival_time;
    String destinationTime;
    int seatsAvailable;
    int cost;

    BookedTrain(int train_no,String train_name,String source,String destination,String arrival_time,String destinationTime,int cost,int seatsAvailable){
        this.train_no=train_no;
        this.train_name=train_name;
        this.source=source;
        this.destination=destination;
        this.arrival_time=arrival_time;
        this.destinationTime=destinationTime;
        this.cost=cost;
        this.seatsAvailable=seatsAvailable;
    }
}
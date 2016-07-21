package nl.rws.re.facts.housebuilding;

/**
 * Created by Md. Mainul Hasan Patwary on 20-Jul-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class ServiceReply {
    private String message;

    private ServiceReply(){

    }

    public ServiceReply(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "["+message+"]";
    }
}

import java.util.ArrayList;

public abstract class Message {
    private ArrayList<Response> responses = null;

    public boolean requiresResponses() {
        return responses != null;
    }
}

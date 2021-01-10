package be.elevado.experiment.mysqlconnect.dto;

public class ConnectResult {
    private boolean connected;
    private String message;

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

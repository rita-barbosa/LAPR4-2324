package jobs4u.base.network;

public enum FollowUpRequestCodes {

    COMMTEST(0),
    DISCONN(1),
    ACK(2),
    ERR(3),
    AUTH(4),
    EMAIL(5),
    NOTIFLIST(6),
    JOBOPLIST(7),
    APPLIST(8);

    private final int code;

    FollowUpRequestCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

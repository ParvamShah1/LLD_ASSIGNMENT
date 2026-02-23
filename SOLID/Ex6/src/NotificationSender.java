// Contract:
// - send() accepts any non-null Notification and never throws.
// - Returns SendResult indicating success or failure with reason.
// - Channels may use only the fields relevant to them (e.g., SMS ignores subject).
public interface NotificationSender {
    SendResult send(Notification n);
}

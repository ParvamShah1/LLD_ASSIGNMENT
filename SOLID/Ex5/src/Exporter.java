// Contract:
// - export() accepts any non-null ExportRequest and never throws.
// - If the exporter cannot handle the request, it returns ExportResult.ofError(message).
public interface Exporter {
    ExportResult export(ExportRequest req);
}

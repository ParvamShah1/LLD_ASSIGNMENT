import java.util.*;

public class OnboardingService {
    private final StudentStore store;
    private final InputParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentStore store) {
        this.store = store;
        this.parser = new InputParser();
        this.validator = new StudentValidator();
        this.printer = new OnboardingPrinter();
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        Map<String, String> kv = parser.parse(raw);
        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        List<String> errors = validator.validate(name, email, phone, program);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(store.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        store.save(rec);

        printer.printSuccess(id, store.count(), rec);
    }
}

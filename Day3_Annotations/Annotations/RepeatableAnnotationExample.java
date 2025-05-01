package Annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Repeatable(BugReports.class)
@interface BugReport {
    String description();
}

@Retention(RetentionPolicy.RUNTIME)
@interface BugReports {
    BugReport[] value();
}

class BugTracker {
    @BugReport(description = "NullPointerException may occur.")
    @BugReport(description = "Performance issue under heavy load.")
    void analyze() {
        System.out.println("Analyzing bugs...");
    }
}

public class RepeatableAnnotationExample {
    public static void main(String[] args) throws Exception {
        Method method = BugTracker.class.getMethod("analyze");
        BugReports bugReports = method.getAnnotation(BugReports.class);

        for (BugReport report : bugReports.value()) {
            System.out.println("Bug: " + report.description());
        }
        
        new BugTracker().analyze();
    }
}
package com.skillsoft.mocks;

import java.util.HashMap;
import java.util.Map;

public class WeeklyReportsBatchJob {

    private ResourceProvisioner resourceProvisioner;
    private EmailSender emailSender;

    private final Map<String, String> reportGenerationConfig = new HashMap<>();

    public WeeklyReportsBatchJob() {
    }

    public void initializeReportGenerationConfig(int numMachines,
                                                 String operatingSystem,
                                                 String framework) {
        reportGenerationConfig.put("numMachines", String.valueOf(numMachines));
        reportGenerationConfig.put("operatingSystem", operatingSystem);
        reportGenerationConfig.put("framework", framework);
    }

    public boolean generateWeeklyReport(String reportType, String emailRecipient) {

        // NOTE: Assume that this runs some kind of batch job to prepare and generate
        // weekly reports and then sends an update to the email recipient that the
        // weekly report has been generated

        int numMachines = Integer.parseInt(reportGenerationConfig.get("numMachines"));
        String operatingSystem = reportGenerationConfig.get("operatingSystem");
        String framework = reportGenerationConfig.get("framework");

        int count = 0;
        for (int i = 0; i < numMachines; i++) {

            if (resourceProvisioner.setupMachine(operatingSystem, framework)) {
                count++;
            } else {
                break;
            }
        }

        if (count > 0) {
            return emailSender.sendEmail(
                    emailRecipient,
                    String.format("The %s weekly report has been generated", reportType));
        }

        return false;
    }
}

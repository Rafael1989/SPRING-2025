package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeeklyReportsBatchJobTest {

    @Mock
    private EmailSender emailSenderMock;

    @Mock
    private ResourceProvisioner resourceProvisionerMock;


    @InjectMocks
    WeeklyReportsBatchJob batchJob;

    @Test
    public void testGenerateWeeklyReport() {

        when(resourceProvisionerMock.setupMachine( "Linux", "Spark"))
                .thenReturn(true).thenReturn(true).thenReturn(false);

        when(emailSenderMock.sendEmail("john@infomoto.com",
                "The Sales weekly report has been generated")).thenReturn(true);

        batchJob.initializeReportGenerationConfig(4, "Linux", "Spark");
        batchJob.generateWeeklyReport("Sales", "john@infomoto.com");

        verify(resourceProvisionerMock, atLeast(2))
                 .setupMachine("Linux", "Spark");
        verify(resourceProvisionerMock, atMost(4))
                .setupMachine("Linux", "Spark");
        verify(emailSenderMock, only())
                .sendEmail("john@infomoto.com", "The Sales weekly report has been generated");
        verify(emailSenderMock, never())
                .sendEmailWithAttachment("john@infomoto.com",
                        "The Sales weekly report has been generated", "someString".getBytes());
    }
}

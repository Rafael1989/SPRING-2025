package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeeklyReportsBatchJobTest {

    @Mock
    private EmailSender emailSenderMock;

    @Mock
    private ResourceProvisioner resourceProvisionerMock;

    @Mock
    private DataPreparer dataPreparerMock;

    @InjectMocks
    WeeklyReportsBatchJob batchJob;

    @Test
    public void testGenerateWeeklyReport() {
        when(resourceProvisionerMock.setupCluster(
                4, "Linux", "Spark")).thenReturn(true);

        when(dataPreparerMock.prepareRawData()).thenReturn(true);

        when(emailSenderMock.sendEmail("john@infomoto.com",
                "The Sales weekly report has been generated")).thenReturn(true);

        batchJob.initializeReportGenerationConfig(4, "Linux", "Spark");
        batchJob.generateWeeklyReport("Sales", "john@infomoto.com");

        InOrder inOrderResourceProvisionerMock = inOrder(resourceProvisionerMock);
        inOrderResourceProvisionerMock.verify(resourceProvisionerMock).initialize();
        inOrderResourceProvisionerMock.verify(resourceProvisionerMock)
                .setupCluster(4, "Linux", "Spark");

        verifyNoMoreInteractions(resourceProvisionerMock);

        InOrder inOrderDataPreparerMock = inOrder(dataPreparerMock);
        inOrderDataPreparerMock.verify(dataPreparerMock).initialize();
        inOrderDataPreparerMock.verify(dataPreparerMock).prepareRawData();

        verifyNoMoreInteractions(dataPreparerMock);

        verify(emailSenderMock).sendEmail("john@infomoto.com",
                "The Sales weekly report has been generated");

        verifyNoMoreInteractions(emailSenderMock);
    }

    @Test
    public void sendWeeklyReport_withAttachment() {
        when(resourceProvisionerMock.setupSingleMachine(
                "Linux", "Java")).thenReturn(true);

        when(dataPreparerMock.prepareRawData()).thenReturn(true);

        when(emailSenderMock.sendEmailWithAttachment(
                "john@infomoto.com",
                "The Profits weekly report has been generated",
                "Sales have been going up!".getBytes())).thenReturn(true);

        batchJob.initializeReportSendingConfig("Linux", "Java");
        batchJob.sendWeeklyReport("Profits", "john@infomoto.com");

        InOrder inOrderResourceProvisionerMock = inOrder(resourceProvisionerMock);
        inOrderResourceProvisionerMock.verify(resourceProvisionerMock).initialize();
        inOrderResourceProvisionerMock.verify(resourceProvisionerMock)
                .setupSingleMachine("Linux", "Java");

        verifyNoMoreInteractions(resourceProvisionerMock);

        InOrder inOrderDataPreparerMock = inOrder(dataPreparerMock);
        inOrderDataPreparerMock.verify(dataPreparerMock).initialize();
        inOrderDataPreparerMock.verify(dataPreparerMock).prepareRawData();

        verifyNoMoreInteractions(dataPreparerMock);

        verify(emailSenderMock).sendEmailWithAttachment(
                "john@infomoto.com",
                "The Profits weekly report has been generated",
                "Sales have been going up!".getBytes());

        verifyNoMoreInteractions(emailSenderMock);
    }
}

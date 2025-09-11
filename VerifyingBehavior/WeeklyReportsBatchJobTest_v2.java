package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    public void testInitialize() {
        batchJob.initialize();

        verify(resourceProvisionerMock).initialize();
        verify(dataPreparerMock).initialize();

        verifyNoInteractions(emailSenderMock);
    }
}

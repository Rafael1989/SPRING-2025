
// ############################ Overall recording notes

// -- Same as for other demos


// ############################## demo-05-VerifyingBehavior ##########################

-- Verify for simple invocation on mock

-- Show ResourceProvisioner.java (v1)

-- Show DataPreparer.java (v1)

-- Show EmailSender.java (v1)

-- WeeklyReportsBatchJob.java (v1)

-- Show WeeklyReportsBatchJobTest.java (v1)


// ############################

-- Show WeeklyReportsBatchJobTest.java (v2)

-- Just update this one test case

    @Test
    public void testInitialize() {
        batchJob.initialize();

        verify(resourceProvisionerMock).initialize();
        verify(dataPreparerMock).initialize();

        verifyNoInteractions(emailSenderMock);
    }


// ############################

-- Change the code as shown below, the test should then fail

    @Test
    public void testInitialize() {
        batchJob.initialize();

        verify(resourceProvisionerMock).initialize();

        verifyNoInteractions(dataPreparerMock);

        verifyNoInteractions(emailSenderMock);
    }

-- In the console output for the failure click on the link which looks something like this "WeeklyReportsBatchJobTest.java:33"

-- Then click on the link which points to the actual code looks like this "WeeklyReportsBatchJob.java:20"


// ############################

-- Show WeeklyReportsBatchJobTest.java (v3)

-- This has one additional test case testGenerateWeeklyReport()

-- Run and show everything passes


// ############################

-- Make a single line change to the code in WeeklyReportsBatchJob.java

-- We have one extra invocation to dataPreparer.prepareRawData() right before the if statement

	public boolean generateWeeklyReport(String reportType, String emailRecipient) {

		// Rest of the code is the same

        dataPreparer.prepareRawData();

        if (resourceProvisioner.setupCluster(numMachines, operatingSystem, framework)

    }

-- Run the code, you will get an error

-- Click on all the links with the line numbers and show that they give us the 

Wanted 1 time:
-> at com.skillsoft.mocks.WeeklyReportsBatchJobTest.testGenerateWeeklyReport(WeeklyReportsBatchJobTest.java:55)
But was 2 times:
-> at com.skillsoft.mocks.WeeklyReportsBatchJob.generateWeeklyReport(WeeklyReportsBatchJob.java:47)
-> at com.skillsoft.mocks.WeeklyReportsBatchJob.generateWeeklyReport(WeeklyReportsBatchJob.java:50)


-- MAKE SURE YOU REMOVE THIS ERROR THAT YOU HAVE INTRODUCED IN THE CODE


// ############################

-- MAKE SURE YOU HAVE REMOVED THAT EXTRA LINE FROM YOUR CODE FOR dataPreparer.prepareRawData()

-- Show WeeklyReportsBatchJobTest.java (v4)

-- Run and show that everything passes


// ############################


-- Checking the order of invocations in mocks

-- Show WeeklyReportsBatchJob.java (v2) 

-- I have made the initialize() method private and have called initialize() from inside the methods generateWeeklyReport and sendWeeklyReport

-- Show WeeklyReportsBatchJobTest.java (v5)

-- Run the whole class and show that everything works



// ############################

-- Show WeeklyReportsBatchJobTest.java (v6)

-- Test the use of verifyNoMoreInteractions()

-- Run and show that everything passes


// ############################

-- Show WeeklyReportsBatchJobTest.java (v7)

-- Verify interactions with a flexible or any argument

-- Run the whole class and show that everything passes


// ############################

-- I have now majorly simplified the whole set up for the next few demos

-- Show EmailSender.java (v2)

-- Show ResourceProvisioner.java (v2)

-- Show WeeklyReportsBatchJob.java (v3)

-- Show WeeklyReportsBatchJobTest.java (v8)

-- Run and show that everything works


// ############################

-- Show WeeklyReportsBatchJob.java (v4)

-- I have added a break statement if any machine does not get set up

-- Run the same WeeklyReportsBatchJobTest as before

-- It should fail

org.mockito.exceptions.verification.TooFewActualInvocations: 
resourceProvisionerMock.setupMachine(
    "Linux",
    "Spark"
);
Wanted 4 times:
-> at com.skillsoft.mocks.WeeklyReportsBatchJobTest.testGenerateWeeklyReport(WeeklyReportsBatchJobTest.java:37)
But was 2 times:
-> at com.skillsoft.mocks.WeeklyReportsBatchJob.generateWeeklyReport(WeeklyReportsBatchJob.java:37)
-> at com.skillsoft.mocks.WeeklyReportsBatchJob.generateWeeklyReport(WeeklyReportsBatchJob.java:37)


-- Change the test to be WeeklyReportsBatchJobTest.java (v9)

        verify(resourceProvisionerMock, atLeastOnce())
                 .setupMachine("Linux", "Spark");

-- Run again and this time the test will pass


// ############################

-- Show WeeklyReportsBatchJobTest.java (v10)


-- Run and show that tests pass























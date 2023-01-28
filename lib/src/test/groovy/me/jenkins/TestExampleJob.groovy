package me.jenkins
import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.*

class TestExampleJob extends BasePipelineTest {

   def exampleJob

   @Before
   public void setup() {
      super.setUp()
   }

   @Test
   void shouldExecuteWithoutErrors() {
      exampleJob = loadScript('job/ExampleJob.jenkins')
      exampleJob.execute()
      //runScript('job/ExampleJob.jenkins')
      printCallStack()
   }
}
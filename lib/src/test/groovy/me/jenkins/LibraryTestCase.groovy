package me.jenkins

import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library

import org.junit.Test

import com.lesfurets.jenkins.unit.BasePipelineTest
import com.lesfurets.jenkins.unit.global.lib.ProjectSource

class LibraryTestCase extends BasePipelineTest {

   @Test
   void testLibrary() {
      super.setUp()
      
      Object library = library()
            .name('commons')
            .retriever(ProjectSource.projectSource())
            .targetPath('path/to/clone')
            .defaultVersion("master")
            .allowOverride(true)
            .implicit(false)
            .build()
      helper.registerSharedLibrary(library)

      runScript('job/library/exampleJob.jenkins')

      printCallStack()
   }
}
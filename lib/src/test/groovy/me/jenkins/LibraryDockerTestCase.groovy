package me.jenkins

import org.junit.Before
import org.junit.Test

import com.lesfurets.jenkins.unit.BasePipelineTest
import com.lesfurets.jenkins.unit.*


/**
 * Test the DockerMock class
 *
 * It would be a bit redundant to write regular unit tests for DockerMock, because most of
 * the methods are empty or void. So instead this test class loads a Jenkins pipeline file
 * which exercises all of the methods of the docker singleton.
 */
class LibraryDockerTestCase extends BasePipelineTest {
   
  def script
  String testImage = 'test'

  @Before
  @Override
  void setUp() {
    super.setUp()
    script = loadScript('job/docker.jenkins')
  }

  @Test
  void verifyDockerBuild() throws Exception {
    script.test_docker_build(testImage)
    printCallStack()
    assertCallStackContains('DockerMock.build(test')
  }

  @Test
  void verifyDockerBuildTwoArgs() throws Exception {
    script.test_docker_build_two_args(testImage)
    printCallStack()
    assertCallStackContains('DockerMock.build(test, --build_arg arg1=val1 --build_arg arg2=val2)')
  }

  @Test
  void verifyWithServer() throws Exception {
    script.test_with_server(testImage)
    printCallStack()
    assertCallStackContains('DockerMock.withServer(test.server, fake-credentials, groovy.lang.Closure)')
    assertCallStackContains('Hello from withServer')
  }

  @Test
  void verifyWithTool() throws Exception {
    script.test_with_tool(testImage)
    runScript('job/docker.jenkins')
    printCallStack()
    assertCallStackContains('DockerMock.withTool(test-docker, groovy.lang.Closure)')
    assertCallStackContains('Hello from withTool')
  }

  @Test
  void verifyWithRegistry() throws Exception {
    script.test_with_registry(testImage)
    printCallStack()
    assertCallStackContains('DockerMock.withRegistry(test.registry, fake-credentials, groovy.lang.Closure)')
    assertCallStackContains('Hello from withRegistry')
  }

  @Test
  void verifyInside() throws Exception {
    script.test_inside(testImage)
    printCallStack()
    assertCallStackContains('Image.inside(groovy.lang.Closure)')
    assertCallStackContains('Hello from inside')
  }

  @Test
  void verifyWithRun() throws Exception {
    runScript('job/docker.jenkins')
    script.test_image_with_run(testImage)
    printCallStack()
    assertCallStackContains('Image.withRun(groovy.lang.Closure)')
    assertCallStackContains('Hello from withRun')
  }

  @Test
  void verifyImageName() throws Exception {
    script.test_image_name(testImage)
    printCallStack()
    assertCallStackContains('Image.imageName()')
  }

  @Test
  void verifyImagePull() throws Exception {
    script.test_image_pull(testImage)
    runScript('job/docker.jenkins')
    printCallStack()
    assertCallStackContains('Image.pull()')
  }

  @Test
  void verifyImagePush() throws Exception {
    script.test_image_push(testImage)
    printCallStack()
    assertCallStackContains('Image.push(test-tag)')
  }

  @Test
  void verifyImageRun() throws Exception {
    script.test_image_run(testImage)
    printCallStack()
    assertCallStackContains('Image.run()')
    assertCallStackContains('Container.stop()')
  }

  @Test
  void verifyImageTag() throws Exception {
    script.test_image_tag(testImage)
    printCallStack()
    assertCallStackContains('Image.tag(test)')
  }
}

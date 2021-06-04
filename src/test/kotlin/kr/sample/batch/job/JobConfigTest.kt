package kr.sample.batch.job

import kr.sample.batch.job.step.SampleStepConfig
import kr.sample.model.Sample
import kr.sample.model.SampleRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class JobConfigTest {

    @Autowired
    private lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    private lateinit var sampleRepository: SampleRepository

    @Test
    fun jobTest() {
        // given
        addSamples((0..10).map {
            Sample()
        })

        // when
        jobLauncherTestUtils.launchStep(
            SampleStepConfig.STEP_NAME,
            jobLauncherTestUtils.uniqueJobParameters
        )

        // then
        sampleRepository.findAll().map {
            println("${it.id} ${it.count}")
        }
    }

    private fun addSamples(samples: List<Sample>): List<Sample> {
        return sampleRepository.saveAll(samples)
    }
}

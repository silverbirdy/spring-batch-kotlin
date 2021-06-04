package kr.sample.batch.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JobConfig(
    private val jobBuilderFactory: JobBuilderFactory
) {

    @Bean
    fun sampleJob(
        taskletStep: Step,
        sampleCountStep: Step
    ): Job {
        return jobBuilderFactory.get(JOB_NAME)
            .start(taskletStep) // tasklet 하나와 Reader & Processor & Writer 한 묶음이 같은 레벨
            .next(sampleCountStep)
            .build()
    }

    companion object {
        const val JOB_NAME = "SAMPLE_JOB"
    }
}

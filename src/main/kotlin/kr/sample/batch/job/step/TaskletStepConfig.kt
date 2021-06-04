package kr.sample.batch.job.step

import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TaskletStepConfig(
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun taskletStep(): Step {
        return stepBuilderFactory.get(STEP_NAME)
            .tasklet(sampleTasklet())
            .build()
    }

    private fun sampleTasklet() = Tasklet { contribution, chunkContext ->
        RepeatStatus.FINISHED
    }

    companion object {
        const val STEP_NAME = "TASKLET_STEP"
    }
}

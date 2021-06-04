package kr.sample.batch.config

import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@ComponentScan
class TestJobConfig {

    @Bean
    fun jobLauncherTestUtils(): JobLauncherTestUtils {
        return JobLauncherTestUtils()
    }
}

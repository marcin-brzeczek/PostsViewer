package mbitsystem.com.postsviewer.testutil

import io.reactivex.schedulers.TestScheduler
import mbitsystem.com.postsviewer.utils.SchedulerProvider

class TestSchedulerProvider(val testScheduler: TestScheduler) : SchedulerProvider {

    override fun uiScheduler() = testScheduler

    override fun ioScheduler() = testScheduler
}
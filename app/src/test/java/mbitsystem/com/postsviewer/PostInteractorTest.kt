package mbitsystem.com.postsviewer

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import mbitsystem.com.postsviewer.data.PostInteractor
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.data.network.PostsApi
import mbitsystem.com.postsviewer.state.PostState
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostInteractorTest : BaseTest() {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var api: PostsApi

    lateinit var underTest: PostInteractor

    @Before
    fun setUp() {
        underTest = PostInteractor(api)
    }

    @Test
    fun `get files ascending`() {
        val files = dummyAllPosts
        Mockito.`when`(api.getPosts().toObservable()).thenReturn(Observable.just(files))

        val result = underTest.getPosts()

        val testObserver = TestObserver<PostState>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val fileState = testObserver.values()[0] as PostState.DataState
        val resultList = fileState.data
        assertThat(resultList.size, `is`(4))
        assertThat(resultList[0].title, `is`("FileTest1"))
        assertThat(resultList[1].title, `is`("FileTest2"))
    }

    private val dummyAllPosts: ArrayList<Post>
        get() {
            val dummyFileList = ArrayList<Post>()
            dummyFileList.add(Post("1", "FileTest1", "urlPath", ""))
            dummyFileList.add(Post("79", "FileTest2", "PosterPath2", ""))
            dummyFileList.add(Post("987", "FileTest3", "PosterPath3", ""))
            dummyFileList.add(Post("4223", "FileTest4", "PosterPath4", ""))
            return dummyFileList
        }
}
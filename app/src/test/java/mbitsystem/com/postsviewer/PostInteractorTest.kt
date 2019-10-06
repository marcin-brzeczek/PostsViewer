package mbitsystem.com.postsviewer

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import mbitsystem.com.postsviewer.data.PostInteractor
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.data.network.CommentsApi
import mbitsystem.com.postsviewer.data.network.PostsApi
import mbitsystem.com.postsviewer.data.network.UsersApi
import mbitsystem.com.postsviewer.main.MainPresenter
import mbitsystem.com.postsviewer.main.MainView
import mbitsystem.com.postsviewer.state.PostState
import mbitsystem.com.postsviewer.testutil.TestSchedulerProvider
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostInteractorTest : BaseTest() {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var postsApi: PostsApi

    @Mock
    lateinit var usersApi: UsersApi

    @Mock
    lateinit var commentsApi: CommentsApi

    @Mock
    lateinit var mainView: MainView

    lateinit var underTest: PostInteractor

    lateinit var mainPresenter: MainPresenter

    lateinit var testScheduler: TestScheduler

    @Before
    fun setUp() {
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        underTest = PostInteractor(postsApi, usersApi, commentsApi)
        mainPresenter = MainPresenter(underTest, testSchedulerProvider)
        mainPresenter.view = mainView
    }

    @Test
    fun `test observer when get posts`() {

        //Given
        val posts = dummyAllPosts

        //When
        Mockito.`when`(postsApi.getPosts()).thenReturn(Observable.just(posts))
        val result = underTest.getPosts()

        val testObserver = TestObserver<PostState>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val fileState = testObserver.values()[0] as PostState.DataState
        val resultList = fileState.data

        // Then
        assertThat(resultList.size, `is`(4))
        assertThat(resultList[0].title, `is`("urlPath"))
        assertThat(resultList[1].title, `is`("PosterPath2"))
    }

    @Test
    fun `verify view when get post`() {

        //Given
        val posts = dummyAllPosts

        //When
        Mockito.`when`(postsApi.getPosts()).thenReturn(Observable.just(posts))
        testGetPosts()
        testScheduler.triggerActions()

        // Then
        verify(mainView).render(PostState.LoadingState)
        verify(mainView).render(PostState.DataState(posts))
    }

    @Test
    fun `verify view when error get post`() {

        //Given
        val testError ="error"
        val observable : Observable<List<Post>> = Observable.create{
            emitter ->  emitter.onError(Exception(testError))
        }

        //When
        Mockito.`when`(postsApi.getPosts()).thenReturn(observable)
        testGetPosts()
        testScheduler.triggerActions()

        // Then
        verify(mainView).render(PostState.LoadingState)
        verify(mainView).render(PostState.ErrorState(testError))
    }

    private fun testGetPosts() {
        underTest.getPosts()
            .startWith(PostState.LoadingState)
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .subscribe { mainView.render(it) }
    }

    private val dummyAllPosts: ArrayList<Post>
        get() {
            val dummyPostList = ArrayList<Post>()
            dummyPostList.add(Post("1", "77", "urlPath", ""))
            dummyPostList.add(Post("79", "88", "PosterPath2", ""))
            dummyPostList.add(Post("987", "99", "PosterPath3", ""))
            dummyPostList.add(Post("4223", "11", "PosterPath4", ""))
            return dummyPostList
        }
}
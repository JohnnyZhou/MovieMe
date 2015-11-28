# MovieMe

MovieMe is a demo movie database application that displays the latest box office movies and the current most popular actors/actresses using the 
<a href="https://www.themoviedb.org">themoviedb API</a>. You can also search for your favourite movies and movie stars.

## Screenshots
<img src="https://github.com/JohnnyZhou/MovieMe/blob/master/screenshot/Movie%20List.png" width="350" />
&nbsp;
<img src="https://github.com/JohnnyZhou/MovieMe/blob/master/screenshot/Movie%20Detail.png" width="350" />


## Installation
You will require a themoviedb API key. To get your own API key you can <a href="https://www.themoviedb.org/account/signup?"> sign up here.</a>
</br> 
Once you have your API key, insert it here: 
```java
public class NetworkUtil {
    public static final String API_KEY = "API_KEY_HERE";
    ...
}
```
## Application Architecture
MVP (Model-View-Presenter) with dependency injection.

## Libraries
Third party libraries:

- <a href="http://google.github.io/dagger/">Dagger 2</a>
- <a href="http://square.github.io/retrofit/">Retrofit 2</a>
- <a href="https://github.com/ReactiveX/RxJava">RxJava</a>
- <a href="https://github.com/ReactiveX/RxAndroid">RxAndroid 2</a>
- <a href="https://github.com/bumptech/glide">Glide</a>
- <a href="https://github.com/greenrobot/EventBus">EventBus 3.0</a>
- <a href="http://jakewharton.github.io/butterknife/">Butterknife</a>

Debugging Libraries:

- <a href="http://facebook.github.io/stetho/">Stetho</a>
- <a href="https://github.com/square/leakcanary">LeakCanary</a>
- <a href="https://github.com/JakeWharton/timber">Timber</a>

package fr.airweb.grandlac.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import io.github.wbinarytree.base.BaseActivity
import io.github.wbinarytree.base.BaseTranslator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject


/**
 * Created by yaoda on 1/30/18.
 */
@SuppressLint("Registered")
abstract class BaseUiActivity<A, R, T : BaseTranslator<A, R>> : BaseActivity() {

    protected lateinit var translator: T
    //    private val actions: Subject<A> = PublishSubject.create<A>().toSerialized()
    private val actions: Subject<A> = PublishSubject.create()
    protected val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        translator = translator()
        translator.sendAction(actions).addTo(disposables)
        translator.getUiModel()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    render(it)
                }
                .addTo(disposables)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    abstract fun render(ui: R): Boolean

    abstract fun translator(): T


    protected fun addAction(uiAction: A) {
        actions.onNext(uiAction)
    }

//    fun hideKeyboard() {
//        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//        //Find the currently focused view, so we can grab the correct window token from it.
//        //If no view currently has focus, create a new one, just so we can grab a window token from it
//        var view = currentFocus?:View(this)
//        imm.hideSoftInputFromWindow(view.windowToken, 0)
//    }
}
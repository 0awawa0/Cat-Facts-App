package ru.awawa.catfactsapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.EXTRA_TEXT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.awawa.catfactsapp.retrofit.FactDataModel

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"
    private val presenter: MainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btLogo.setOnClickListener { run {
            tvFact.visibility = View.INVISIBLE
            loadingPanel.visibility = View.VISIBLE
            animateButtonsFadeOut()
            presenter.getRandomFact()
        }}

        btShare.setOnClickListener { run {
            val intent = Intent(ACTION_SEND)
            intent.putExtra(EXTRA_TEXT, getString(R.string.shareMessage, tvFact.text.toString()))
            intent.type = "text/plain"
            Intent.createChooser(intent, getString(R.string.shareTitle))
            startActivity(intent)
        }}
    }

    suspend fun showFact(fact: String) {
        withContext(Dispatchers.Main) {
            tvFact.text = fact
            loadingPanel.visibility = View.GONE
            animateButtonsFadeIn()
            tvFact.visibility = View.VISIBLE
        }
    }

    private fun animateButtonsFadeOut() {
        val animator1 = ObjectAnimator.ofFloat(
            btLogo,
            View.ALPHA,
            0f)
        val animator2 = ObjectAnimator.ofFloat(
            btShare,
            View.ALPHA,
            0f
        )
        animator1.duration = 500
        animator2.duration = 500

        val set = AnimatorSet()
        set.playTogether(animator1, animator2)
        set.start()
        btLogo.isEnabled = false
        btShare.isEnabled = false
    }

    private fun animateButtonsFadeIn() {
        btLogo.isEnabled = true
        btShare.isEnabled = true
        val animator1 = ObjectAnimator.ofFloat(
            btLogo,
            View.ALPHA,
            1f
        )
        val animator2 = ObjectAnimator.ofFloat(
            btShare,
            View.ALPHA,
            1f
        )
        animator1.duration = 500
        animator2.duration = 500
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animator1, animator2)
        animatorSet.start()
    }
}

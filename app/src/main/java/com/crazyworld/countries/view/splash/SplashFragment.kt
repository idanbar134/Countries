package com.crazyworld.countries.view.splash

import android.animation.Animator
import androidx.navigation.fragment.findNavController
import com.crazyworld.countries.R
import com.crazyworld.countries.common.BaseFragment
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : BaseFragment() {

    override fun resource(): Int = R.layout.splash_fragment

    override fun render() {

        splashAnim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                splashTitle.animate().alpha(1f).setDuration(1000).start()
            }

            override fun onAnimationEnd(animation: Animator?) {
                findNavController().navigate(R.id.action_splash_to_home)
            }

            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationCancel(animation: Animator?) {}

        })

    }
}
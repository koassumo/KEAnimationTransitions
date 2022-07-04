package com.example.keanimationtransitions.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.keanimationtransitions.R
import com.example.keanimationtransitions.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var toRightAnimation = false

    // 1. Сделать запись в gradle
    // 2. Создаем вспомогательный объект
    private var _binding: MainFragmentBinding? = null
    // 3. Создаем объект
    private val binding get() = _binding!!
    // 4. занулить вспомогательный binding здесь
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    // 5. см. ниже добавить строки в onCreateView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 5. изменение для binding здесь:
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        _binding = MainFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //
        binding.btn333.setOnClickListener {
            val changeBounds = ChangeBounds()
            changeBounds.setPathMotion (ArcMotion())    // add "arch" to path
            changeBounds.duration = 5000                // speed

            // .beginDelayedTransition - for viewGroup
            // .go - for scene
            TransitionManager.beginDelayedTransition (
                binding.myContainerAnim3,               // arg1: container must be named in layout file
                changeBounds                            // arg2: action or action set
            )
            // Arg2 LIST:
            // - Fade
            // - ChangeBounds
            // - Explode
            // - Default AutoTransition (no arg2) - set of: FadeOut, ChangeBounds, Fade



            toRightAnimation = !toRightAnimation

            val params : FrameLayout.LayoutParams = binding.btn333.layoutParams as FrameLayout.LayoutParams
            params.gravity = if (toRightAnimation)
                Gravity.END or Gravity.BOTTOM
            else
                Gravity.START or Gravity.TOP
            binding.btn333.layoutParams = params
        }
    }
}
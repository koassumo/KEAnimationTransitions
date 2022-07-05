package com.example.keanimationtransitions.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.keanimationtransitions.R
import com.example.keanimationtransitions.databinding.MainFragmentBinding
import android.widget.AdapterView.OnItemClickListener


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


        val myTitles: MutableList<String> = ArrayList()
        for (i: Int in 0..444) {
            //myTitles.add(String.format("Item %d   ", i+1))
            myTitles.add(String.format("0"))
        }
        myTitles.add(3, "1")
        createViews(binding.myContainerAnimGrid, myTitles)
        //renderViews(myTitles, 5)

//        val gridviewOnItemClickListener =
//            OnItemClickListener { parent, v, position, id -> // выводим номер позиции
//                //mSelectText.setText(position.toString())
//            }

    }

    private fun renderViews (myTitles: MutableList<String>, positionCar: Int) {

        Toast.makeText(requireContext(), "index: $positionCar", Toast.LENGTH_SHORT).show()
        val changeBounds = ChangeBounds()
        changeBounds.setPathMotion (ArcMotion())    // add "arch" to path
        changeBounds.duration = 2000                // speed

        TransitionManager.beginDelayedTransition(binding.myContainerAnimGrid, changeBounds)

        // второе состояние - пересоздание вьюшек
        val buffer = myTitles[53]
        myTitles[53] = myTitles[3]
        myTitles[3] = buffer

        createViews(binding.myContainerAnimGrid, myTitles)

//            toRightAnimation = !toRightAnimation
//
//            val params : FrameLayout.LayoutParams = binding.btn333.layoutParams as FrameLayout.LayoutParams
//            params.gravity = if (toRightAnimation)
//                Gravity.END or Gravity.BOTTOM
//            else
//                Gravity.START or Gravity.TOP
//            binding.btn333.layoutParams = params
    }


    private fun createViews(layout: ViewGroup, titles: MutableList <String>) {
        layout.removeAllViews()
        for (index in titles.indices) {
            val textView = TextView(requireContext())
            textView.setBackgroundColor(Color.BLUE)

            textView.text = "0"
            if (titles[index] == "0") textView.setTextColor(Color.BLUE)
            else textView.setTextColor(Color.YELLOW)
            //textView.gravity = Gravity.CENTER_HORIZONTAL
            textView.setOnClickListener {
                renderViews(titles, index)
                //Toast.makeText(requireContext(), "layoutPosition: ${textView.layo}", Toast.LENGTH_SHORT).show()
            }

            ViewCompat.setTransitionName(textView, titles[index])
            layout.addView(textView)

        }
    }
}
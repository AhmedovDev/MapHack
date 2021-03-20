package ru.ahmedov.maphack.ui.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_task.*
import kotlinx.android.synthetic.main.fragment_home.*
import ru.ahmedov.maphack.MainActivity
import ru.ahmedov.maphack.R
import ru.ahmedov.maphack.data.global.models.Task
import ru.ahmedov.maphack.data.global.models.Tour
import ru.ahmedov.maphack.ui.TaskAdapter
import ru.ahmedov.maphack.ui.TourAdapter

class HomeFragment : Fragment() {

    var tour: MutableList<Tour> = ArrayList()

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_restaurants.run {
            layoutManager = LinearLayoutManager(recycler_view_restaurants.context)
        }

        tour.add(Tour("Дагестан","4 дня","Бесплатно", R.drawable.sulakskii_kanon))
        tour.add(Tour("Авторский тур по Чеченской Республике","7 дней","8000₽", R.drawable.checna))

        val adapter = TourAdapter(tour)
        recycler_view_restaurants.adapter = adapter
        adapter.setOnRestaurantClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
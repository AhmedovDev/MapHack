package ru.ahmedov.maphack.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_task.*
import ru.ahmedov.maphack.MainActivity
import ru.ahmedov.maphack.R
import ru.ahmedov.maphack.data.global.models.Task

class TaskActivity : AppCompatActivity() {

    var tasks : MutableList<Task> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        go_to_map.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("OPTION",0)
            startActivity(intent)
        }

        collapseAnimation("Мои задачи")
        tasks.add(Task("Выполнено","5 мин","Доставить письмо","пр. Р.Гамзатова 64"))
        tasks.add(Task("Выполнено","17 мин","Добавить местоположение","Адрес не определен"))
        tasks.add(Task("Не выполнено","0","Доставка письма","Арбатино 22"))
        tasks.add(Task("Не выполнено","0","Доставка письма","Красноармейская 17"))
        tasks.add(Task("Не выполнено","0","Доставка посылки","Заманова 8"))

        recyclerView_basket.run {
            layoutManager = LinearLayoutManager(recyclerView_basket.context)}

        val adapter = TaskAdapter(tasks,clickListenerdrop = {})
        recyclerView_basket.adapter = adapter
        adapter.setOnBasketClickListener {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun collapseAnimation(title: String) {
        var isShow = true
        var scrollRange = -1
        collapsToolbar_basket.setCollapsedTitleTextColor(Color.BLACK)
        collapsToolbar_basket.setExpandedTitleColor(Color.BLACK)

        appBarLayout_basket.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
                collapsToolbar_basket.setCollapsedTitleTextColor(Color.BLACK)
                collapsToolbar_basket.setExpandedTitleColor(Color.BLACK)

            }
            if (scrollRange + verticalOffset == 0) {
                collapsToolbar_basket.title = title
                collapsToolbar_basket.setCollapsedTitleTextColor(Color.BLACK)
                collapsToolbar_basket.setExpandedTitleColor(Color.BLACK)


                isShow = true
            } else if (isShow) {
                collapsToolbar_basket.setCollapsedTitleTextColor(Color.BLACK)
                collapsToolbar_basket.setExpandedTitleColor(Color.BLACK)

//careful there should a space between double quote otherwise it wont work
                collapsToolbar_basket.title = title

                isShow = false
            }
        })
    }
}
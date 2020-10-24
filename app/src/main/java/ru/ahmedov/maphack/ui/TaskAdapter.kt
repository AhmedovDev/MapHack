package ru.ahmedov.maphack.ui

import android.graphics.Color
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_task.view.*
import org.jetbrains.annotations.Nullable
import ru.ahmedov.maphack.R
import ru.ahmedov.maphack.data.global.models.Task

private typealias OnBasketListener = ((Task) -> Unit)

private var mSelectedItem = -1

var sum = 0
var countChange = 0

class TaskAdapter(
    val basket: List<Task>,
    private val clickListenerdrop: (Int) -> Unit
) :
    RecyclerView.Adapter<TaskAdapter.BasketViewHolder>() {

    private var clickListener: OnBasketListener? = null
    var timer: CountDownTimer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_task, parent, false)

        return BasketViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basket[position], clickListener!!)
//        if(basket.items[position].extraId != null){

    }


    override fun getItemCount(): Int = basket.size

    fun setOnBasketClickListener(listener: OnBasketListener?) {
        clickListener = listener
    }


    inner class BasketViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {


        fun bind(
            task: Task,
            clickListener: OnBasketListener
        ) {
            containerView.run {
                order_price.text = task.type
                order_status.text = task.status
                if(task.status == "Не выполнено"){
                    order_status.setTextColor(Color.parseColor("#000000"))
                    order_status.background =
                        resources.getDrawable(R.drawable.order_status_grey_bg)
                }

                if (task.time == "0") {
                    order_create_date.visibility = View.GONE
                } else {
                    order_create_date.text = task.time
                }

                order_address.text = task.address

                itemView.setOnClickListener { clickListener(task) }
            }

        }
    }
}
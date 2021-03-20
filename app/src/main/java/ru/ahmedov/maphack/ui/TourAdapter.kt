package ru.ahmedov.maphack.ui

import android.graphics.drawable.TransitionDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_tour.view.*
import ru.ahmedov.maphack.R
import ru.ahmedov.maphack.data.global.models.Tour

private typealias OnRestaurantClickListener = ((Tour) -> Unit)

class TourAdapter(private val restaurant: List<Tour>) :
    RecyclerView.Adapter<TourAdapter.RestaurantViewHolder>() {

    private var clickListener: OnRestaurantClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tour, parent, false)

        return RestaurantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurant[position], clickListener)
    }

    override fun getItemCount(): Int = restaurant.size

    fun setOnRestaurantClickListener(listener: OnRestaurantClickListener?) {
        clickListener = listener
    }


    inner class RestaurantViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        var isLike = false

        fun bind(
            restaurant: Tour,
            clickListener: OnRestaurantClickListener?
        ) {
            with(containerView) {
                restaurant_title.text = restaurant.name
                min_delivery_price_in_restaurant.text = restaurant.price
              imageView_restaurant.setImageDrawable(itemView.context.getDrawable(restaurant.image))
                delivery_time_in_restaurant.text = restaurant.period

                like_click.setOnClickListener {

                        if (isLike) {
                            (like.background as TransitionDrawable).resetTransition()
                            isLike = false
                        } else {
                            (like.background as TransitionDrawable).startTransition(300)
                            isLike = true
                        }
                    }
                }

            itemView.setOnClickListener { clickListener!!.invoke(restaurant) }

        }
    }
}

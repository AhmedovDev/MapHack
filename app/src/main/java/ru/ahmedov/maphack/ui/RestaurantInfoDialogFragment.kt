package ru.ahmedov.maphack.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_dialog_restaurant_info.*
import ru.ahmedov.maphack.R


@Suppress("UNREACHABLE_CODE")
class RestaurantInfoDialogFragment() : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dialog!!.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheetInternal =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheetInternal).state = BottomSheetBehavior.STATE_EXPANDED
        }
        return inflater.inflate(R.layout.fragment_dialog_restaurant_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        name.text = restaurantInfo.title
//        review_count.text = "Время работы c ${restaurantInfo.worksFrom} до ${restaurantInfo.worksTo}"
//        minOrder.text = "Заказ от ${restaurantInfo.minOrder} ₽"
//        deliveryPriceAndFree.text = "Доставка ${restaurantInfo.delivery} ₽ • От ${restaurantInfo.freeOrder} бесплатно"
//        address.text = restaurantInfo.address
//        description.text = restaurantInfo.info

        var a = 0

        understand_button.setOnClickListener {
            if (a == 1) {
                understand_button.text = "Завершить"
                dialog!!.cancel()
            }
            a++
            val intent = Intent(context, AddLocationActivity::class.java)
            startActivity(intent)

        }

    }

    fun showInfo() {


    }

}

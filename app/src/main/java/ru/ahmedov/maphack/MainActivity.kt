package ru.ahmedov.maphack

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PointF
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.*
import com.yandex.mapkit.directions.driving.DrivingSession.DrivingRouteListener
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.network.NetworkError
import com.yandex.runtime.network.RemoteError
import kotlinx.android.synthetic.main.activity_main.*
import ru.ahmedov.maphack.ui.RestaurantInfoDialogFragment
import ru.ahmedov.maphack.ui.TaskActivity
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), UserLocationObjectListener, DrivingRouteListener {
    private var userLocationLayer: UserLocationLayer? = null
    var mapObjects: MapObjectCollection? = null
    var mark: PlacemarkMapObject? = null
    private var drivingRouter: DrivingRouter? = null
    private var drivingSession: DrivingSession? = null

    private val mapObjectTapListener =
        MapObjectTapListener { mapObject, point ->

            setInfoBottomSheetDialogFragment()

            true
        }

    fun setInfoBottomSheetDialogFragment() {
        val restaurantInfoDialogFragment = RestaurantInfoDialogFragment()
        restaurantInfoDialogFragment.isCancelable = true
        restaurantInfoDialogFragment.enterTransition = 1000
        restaurantInfoDialogFragment.show(supportFragmentManager, restaurantInfoDialogFragment.tag)
    }


    private fun submitRequest(userPos: Point, secondPoint: Point = Point(43.021886121556236, 46.82819654015869)) {
        val drivingOptions = DrivingOptions()
        val vehicleOptions = VehicleOptions()
        val requestPoints = ArrayList<RequestPoint>()

        requestPoints.add(
            RequestPoint(
                userPos,
                RequestPointType.WAYPOINT,
                null
            )
        )
        requestPoints.add(
            RequestPoint(
               secondPoint,
                RequestPointType.WAYPOINT,
                null
            )
        )


//        requestPoints.add(
//            RequestPoint(
//                Point(42.9830531,47.500561),
//                RequestPointType.WAYPOINT,
//                null
//            ))

//        requestPoints.add(
//            RequestPoint(
//                Point(42.986200, 47.508850),
//                RequestPointType.WAYPOINT,
//                null
//            )
//        )
        drivingSession =
            drivingRouter?.requestRoutes(requestPoints, drivingOptions, vehicleOptions, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey("7b702352-5d27-4d62-b726-9f8d5d19fc52");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        mapView.map.isRotateGesturesEnabled = false
        mapView.map.move(
            CameraPosition(Point(42.9851531, 47.502561), 8F, 0F, 0F),
            Animation(
                Animation.Type.SMOOTH, 4f
            ), null
        )

        var v = intent.getIntExtra("OPTION", 1)
        if (v == 0) {
            tasks.text = "3 из 5"
//                check_work.visibility = View.VISIBLE
            show_restaurants.text = "Приступить"
            textView2.text = "Доставить письмо \nАрбатино 22"

        }

        drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()
        mapObjects = mapView.map.mapObjects.addCollection()

        var markLocation = mapObjects!!.addPlacemark(Point(42.386152, 46.962585))
        markLocation.opacity = 1f
        markLocation.setIcon(ImageProvider.fromResource(this, R.drawable.woterpad))
        markLocation.isDraggable = true

        var markPos = mapObjects!!.addPlacemark(Point(43.010346, 47.230085))
        markPos.opacity = 1f
        markPos.setIcon(ImageProvider.fromResource(this, R.drawable.mountain))
        markPos.isDraggable = true


        var markPosition = mapObjects!!.addPlacemark(Point(41.845392, 48.530369))
        markPosition.opacity = 1f
        markPosition.setIcon(ImageProvider.fromResource(this, R.drawable.forest))
        markPosition.isDraggable = true

        var markPosition1 = mapObjects!!.addPlacemark(Point(42.057669, 48.288776))
        markPosition1.opacity = 1f
        markPosition1.setIcon(ImageProvider.fromResource(this, R.drawable.wall))
        markPosition1.isDraggable = true

        var markPosition3 = mapObjects!!.addPlacemark(Point(42.301965, 46.996226))
        markPosition1.opacity = 1f
        markPosition1.setIcon(ImageProvider.fromResource(this, R.drawable.gamsutl))
        markPosition1.isDraggable = true

        var markPosition2 = mapObjects!!.addPlacemark(Point(43.021886121556236, 46.82819654015869))
        markPosition2.opacity = 1f
        markPosition2.setIcon(ImageProvider.fromResource(this, R.drawable.canon))
        markPosition2.isDraggable = true

        mapObjects!!.addTapListener(mapObjectTapListener)
        goToAuth.setOnClickListener {
            val intent = Intent(applicationContext, TaskActivity::class.java)
            startActivity(intent)
        }
        var a = 0
        var min = 0
        var sec = 0

        show_restaurants.setOnClickListener {
            if (a == 0) {
                showLocation()
                textView2.visibility = View.VISIBLE
                show_restaurants.text = "Завершить"
                tasks.text = "1 из 6"
            }
            if (a == 1) {
                tasks.text = "2 из 6"
//                check_work.visibility = View.VISIBLE
                show_restaurants.text = "Отправиться"
                textView2.text = "Бархан Сарыкум"
            }
            if (a == 2) {
                submitRequest(Point(43.021886121556236, 46.82819654015869),Point(43.010346, 47.230085))
                show_restaurants.text = "Завершить"
            }
            if(a == 3){
                textView2.text = "Гуниб"
                show_restaurants.text = "Отправиться"
            }
            a++
        }



        show_user_location.setOnClickListener {
            val theWebPage = WebView(this)
            theWebPage.settings.javaScriptEnabled = true
            theWebPage.settings.pluginState = WebSettings.PluginState.ON
            theWebPage.settings.useWideViewPort = true;
            setContentView(theWebPage)
            theWebPage.loadUrl("https://docviewer.yandex.ru/view/1046542638/?page=1&*=LM4xOlq3stJ5%2BWljOLlVUsdTLlR7InVybCI6InlhLWRpc2stcHVibGljOi8vR2lKNWx4YnRRVFRxWlVVMFdMTjc3NGFWNXZyMXhhZ2tmUFoxMkZFRk5KZnJVNnZBMndrckRJUFhscUVXd1REVHEvSjZicG1SeU9Kb25UM1ZvWG5EYWc9PTov0J%2FRgNC10LfQtdC90YLQsNGG0LjRjyAtINCU0LDQs9C10YHRgtCw0L0ucGRmIiwidGl0bGUiOiLQn9GA0LXQt9C10L3RgtCw0YbQuNGPIC0g0JTQsNCz0LXRgdGC0LDQvS5wZGYiLCJub2lmcmFtZSI6ZmFsc2UsInVpZCI6IjEwNDY1NDI2MzgiLCJ0cyI6MTYxNjE2MjQxOTI4MCwieXUiOiIyMzc2MzY3NTcxNTg5NTM2MDA4In0%3D")
            }

    }

    fun showLocation(){
        checkSelfLocation()

        val mapKit = MapKitFactory.getInstance()
        userLocationLayer = mapKit.createUserLocationLayer(mapView.mapWindow)
//        userLocationLayer!!.isVisible = true
//        userLocationLayer!!.isHeadingEnabled = true
        val locationManager = mapKit.createLocationManager()

        userLocationLayer?.setObjectListener(this)
//            userLocationLayer?.resetAnchor()
        mapKit.createLocationManager().requestSingleUpdate(object : LocationListener {
            override fun onLocationUpdated(p0: Location) {

                submitRequest(
                    Point(
                        p0.position.latitude,
                        p0.position.longitude
                    )
                )
                Log.v("userposition", "${p0.position.latitude},${p0.position.longitude}")
                if (mark != null) {
                    mapObjects?.remove(mark!!)
                }

                mark =
                    mapObjects!!.addPlacemark(
                        Point(
                            p0.position.latitude,
                            p0.position.longitude
                        )
                    )
                mark?.opacity = 1f
                mark?.setIcon(
                    ImageProvider.fromResource(
                        applicationContext,
                        R.drawable.location_png
                    )
                )
                mark?.isDraggable = true

                mapView.map.move(
                    CameraPosition(
                        Point(
                            p0.position.latitude,
                            p0.position.longitude
                        ), 6F, 4F, 0F
                    )
                )
                address_user.setText(getCity(p0.position.latitude, p0.position.longitude))

            }

            override fun onLocationStatusUpdated(p0: LocationStatus) {
            }


        })


        clear_address.setOnClickListener {
            address_user.setText("")
        }

        address_user.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (address_user.text.isEmpty()) {
                    clear_address.visibility = View.GONE
                } else {
                    clear_address.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


    private fun getCity(latitude: Double, longitude: Double): String? {
        val geocoder = Geocoder(this, Locale.getDefault())
        var city: String
        try {
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null) {
                val returnedAddress: Address = addresses[0]
                city =
                    "${returnedAddress.locality} ${returnedAddress.thoroughfare} ${returnedAddress.subThoroughfare}"
            } else {
                city = "Error"
            }
        } catch (e: IOException) {
            e.printStackTrace()
            city = "Error"
        }
        return city
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onObjectAdded(userLocationView: UserLocationView) {

        userLocationLayer!!.setAnchor(
            PointF((mapView.width * 0.5).toFloat(), (mapView.height * 0.5).toFloat()),
            PointF((mapView.width * 0.5).toFloat(), (mapView.height * 0.83).toFloat())
        )
        userLocationView.arrow.setIcon(
            ImageProvider.fromResource(
                this, R.drawable.location_png
            )
        )
    }

    override fun onObjectRemoved(p0: UserLocationView) {}

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {}

    private fun checkSelfLocation(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            ActivityCompat.requestPermissions(this, permissions, 0)
            return false
        } else {
            return true
        }
    }

    override fun onDrivingRoutes(p0: MutableList<DrivingRoute>) {
        for (route in p0) {
            mapObjects!!.addPolyline(route.geometry)
        }
    }

    override fun onDrivingRoutesError(p0: Error) {
        var errorMessage = "Маршрут не найден"
        if (p0 is RemoteError) {
            errorMessage = "Маршрут невозможен"
        } else if (p0 is NetworkError) {
            errorMessage = "Нет интернета"
        }

        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

}

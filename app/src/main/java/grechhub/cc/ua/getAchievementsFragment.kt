package grechhub.cc.ua

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import grechhub.cc.ua.databinding.FragmentGetAchievementsBinding
import java.util.*


class getAchievementsFragment : Fragment(),IWorkWithFindAchivenment {

    private var _binding: FragmentGetAchievementsBinding?=null
    private val binding get() = _binding!!

    var locationtemp: Location? = null
    var listener: IWorkWithGPSandActivity?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentGetAchievementsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener?.setInterfaceFrafment(this)
        listener?.startGPS()
        binding.bFindAcivka.text="Зачекайте, йде пошук місця знаходження"
        binding.bFindAcivka.isEnabled=false
        showTitleText()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is IWorkWithGPSandActivity){
            listener=context
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        listener=null
    }
    ////////


    override fun showLocationOnGPS(location: Location?) {
        if (location == null) return
        if (location.provider == LocationManager.GPS_PROVIDER) {
            locationtemp=location
           // binding.tvLocationShow.text=location?.latitude.toString()+"/"+location.longitude.toString()
            binding.tvLocationShow.text=String.format("%.6f", location?.latitude)+"/"+String.format("%.6f", location.longitude)
            binding.bFindAcivka.text="Знайти ачівку!"
            binding.bFindAcivka.isEnabled=true
            binding.tvPlace.text="Ваше місце знаходження:"

        }
    }

    override fun showEnablrGPS(string: String) {
        if(string=="GPS: true"){
            binding.tvGpsOnOrDown.text="GPS: Увімкнений"
        }else{
            binding.tvGpsOnOrDown.text="GPS: Вимкнений. Увімкніть!"
        }

    }

    override fun blockAccesToGPS() {
        binding.tvPlace.text="Заблоковано! Зайдіть в Параметри телефону та надайте доступ до GPS цьому додатку"
    }

    fun showTitleText(){
        binding.tvGpstitle.text="Ачівки можут залежити від Вашого місця знаходження, від часу, також можуть залежити одночасно від декількох параметрів.\nНе забувайте увімкнути GPS на Вашому мобільному пристої!\n\nУспіхів у пошуках!"
    }


}
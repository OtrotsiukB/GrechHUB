package grechhub.cc.ua

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import grechhub.cc.ua.data.Achievement
import grechhub.cc.ua.databinding.FragmentGetAchievementsBinding
import grechhub.cc.ua.dbroom.DatabaseR
import kotlinx.coroutines.*
import java.lang.Thread.sleep


class GetAchievementsFragment : Fragment(),IWorkWithFindAchivenment {

    private var _binding: FragmentGetAchievementsBinding?=null
    private val binding get() = _binding!!
    var locationtemp: Location? = null
    var listener: IWorkWithGPSandActivity?=null
    var coroutineSputnic:CoroutineScope? = null
    var statusSputnik:Int=0//0 выключен 1 настраивается красный 2 зеленый работает
    var locationsDb: DatabaseR? = null
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
        binding.bFindAcivka.text="Зачекайте, йде пошук Вашого місця знаходження"
        binding.bFindAcivka.isEnabled=false
        showTitleText()
        coroutineSputnic=CoroutineScope(Dispatchers.IO)
        startChengeSputnic()

        binding.bFindAcivka.setOnClickListener {
            if(locationtemp!=null) {
                val achivenments = CheckAchievements.checkAchievements(locationtemp!!)

                if(achivenments.isNotEmpty()) {

                    val achivenmentsFromDB = locationsDb?.DaoInDB()?.getAllAchievements()

                    val achivenmentsMutable = mutableListOf<Achievement>()

                   for(n in achivenments){
                       var newAchiv=true
                       if (achivenmentsFromDB != null) {
                           if (achivenmentsFromDB.isNotEmpty()) {
                               for(u in achivenmentsFromDB){
                                  if(n.nameAchivement==u.nameAchivement){
                                      newAchiv = false
                                  }
                               }
                           }
                       }
                       if(newAchiv){
                           achivenmentsMutable.add(n)
                       }
                   }

                    if(achivenmentsMutable.size>0) {
                        locationsDb?.DaoInDB()?.insertAchievements(achivenmentsMutable.toList())
                        var newAchivenment = ""
                        for (n in achivenmentsMutable) {
                            newAchivenment += n.nameAchivement + ", "
                        }
                        AlertDialog.Builder(activity)
                            .setTitle("Ачівка!")
                            .setMessage("Вітаємо! Ви знайшли ачівки: $newAchivenment перейти до списку ачівок?")
                            .setPositiveButton("Звичайно!") { _, _ ->
                                findNavController().navigate(R.id.action_getAchievementsFragment_to_showAchievementsFragment)
                            }
                            .setNegativeButton("Ні") { _, _ ->
                                //Toast.makeText(context, "you called cancel", Toast.LENGTH_SHORT).show()
                            }
                            .show()

                    }else{
                        val toast =
                            Toast.makeText(requireContext(), "Нових ачівок не знайдено!", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }else{
                    val toast =
                        Toast.makeText(requireContext(), "Нових ачівок не знайдено!", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is IWorkWithGPSandActivity){
            listener=context
        }
        locationsDb = DatabaseR.create(context.applicationContext)

    }

    override fun onStop() {
        super.onStop()
        coroutineSputnic?.cancel()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        listener=null

    }
    ////////

    fun startChengeSputnic(){
        coroutineSputnic?.launch{
            while (true) {
                when (statusSputnik) {
                    0 ->    {sleep(500)}
                    1 ->    {
                            ensureActive()
                            binding.iSputnic.setImageResource(R.drawable.res_sputnic_step_red)
                            sleep(500)
                            ensureActive()
                            binding.iSputnic.setImageResource(R.drawable.res_sputnic_step_zero)
                            }
                    2 ->    {
                            ensureActive()
                            binding.iSputnic.setImageResource(R.drawable.res_sputnic_step_green)
                            sleep(500)
                            ensureActive()
                            binding.iSputnic.setImageResource(R.drawable.res_sputnic_step_zero)
                            }

                }
                sleep(500)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showLocationOnGPS(location: Location?) {
        if (location == null) return
        if (location.provider == LocationManager.GPS_PROVIDER) {
            locationtemp=location
           // binding.tvLocationShow.text=location?.latitude.toString()+"/"+location.longitude.toString()
            binding.tvLocationShow.text=String.format("%.6f", location.latitude)+"/"+String.format("%.6f", location.longitude)
            binding.bFindAcivka.text="Знайти ачівку!"
            binding.bFindAcivka.isEnabled=true
            binding.tvPlace.text="Ваше місце знаходження:"
            statusSputnik=2
        }
    }


    @SuppressLint("SetTextI18n")
    override fun showEnablrGPS(string: String) {
        if(string=="GPS: true"){
            binding.tvGpsOnOrDown.text="GPS: Увімкнений"
            statusSputnik=1
        }else{
            binding.tvGpsOnOrDown.text="GPS: Вимкнений. Увімкніть!"
            statusSputnik=0
        }

    }

    @SuppressLint("SetTextI18n")
    override fun blockAccesToGPS() {
        binding.tvPlace.text="Заблоковано! Зайдіть в Параметри телефону та надайте доступ до GPS цьому додатку"
    }

    @SuppressLint("SetTextI18n")
    fun showTitleText(){
        binding.tvGpstitle.text="Ачівки можут залежити від Вашого місця знаходження, від часу, також можуть залежити одночасно від декількох параметрів.\nНе забувайте увімкнути GPS на Вашому мобільному пристої!\n\nУспіхів у пошуках!"
    }


}
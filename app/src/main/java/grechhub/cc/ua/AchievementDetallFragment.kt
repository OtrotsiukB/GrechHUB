package grechhub.cc.ua

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import grechhub.cc.ua.data.Achievement
import grechhub.cc.ua.databinding.FragmentAchievementDetallBinding



class AchievementDetallFragment : Fragment() {
    var achivenment:Achievement?=null
    private var _binding: FragmentAchievementDetallBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            achivenment = it.getParcelable("Achievement")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentAchievementDetallBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(achivenment!=null) {
            binding.iDescription.setImageResource(achivenment?.imageDescriptoin!!)
            binding.tvDescriptionDetall.text = achivenment?.descriptoin
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
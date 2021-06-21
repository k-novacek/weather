package kevin.weather.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import kevin.weather.AppExecutors
import kevin.weather.R
import kevin.weather.binding.FragmentDataBindingComponent
import kevin.weather.databinding.PlacesFragmentBinding
import kevin.weather.di.Injectable
import kevin.weather.ui.common.RetryCallback
import kevin.weather.util.autoCleared
import javax.inject.Inject

class PlaceFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val placeViewModel: PlaceViewModel by viewModels {
        viewModelFactory
    }

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<PlacesFragmentBinding>()

    private val params by navArgs<PlaceFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<PlacesFragmentBinding>(
            inflater,
            R.layout.places_fragment,
            container,
            false
        )
        dataBinding.retryCallback = object: RetryCallback {
            override fun retry() {
                placeViewModel.retry()
            }
        }
        binding = dataBinding
//        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.move)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        placeViewModel.setId(params.name, params.state)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.place = placeViewModel.place
    }
}
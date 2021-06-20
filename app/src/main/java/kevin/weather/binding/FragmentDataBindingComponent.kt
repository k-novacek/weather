package kevin.weather.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment

abstract class FragmentDataBindingComponent(fragment: Fragment) : DataBindingComponent {
    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters() = adapter
}
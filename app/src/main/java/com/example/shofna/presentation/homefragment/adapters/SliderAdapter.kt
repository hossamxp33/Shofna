package com.example.shofna.presentation.homefragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter

import com.example.shofna.R
import com.example.shofna.helper.ResourceUtil
import com.example.shofna.model.Slider
import kotlinx.android.synthetic.main.viewpagerslide_home1.view.*


class SliderAdapter(activity: FragmentActivity, sliders: List<Slider>) : PagerAdapter() {

    private val context: Context
    private val slidersData: List<Slider> = sliders

    init {
        this.context = activity
    }

    override fun getCount(): Int {
        return  slidersData.size
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1 //To change body of created functions use File | Settings | File Templates.
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.viewpagerslide_home1, container, false)
        ResourceUtil().loudImage(context,view.im_slider,slidersData[position].photo)
        container.addView(view)
//        val radius =context. resources.getDimension(R.dimen.name)
//        val shapeAppearanceModel = view.im_slider.shapeAppearanceModel.toBuilder()
//            .setBottomLeftCornerSize(radius)
//            .setBottomRightCornerSize(radius)
//            .build()
//        view.im_slider.shapeAppearanceModel = shapeAppearanceModel
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
//    override fun getPageWidth(position: Int): Float {
//        return 0.93f
//    }

}

package com.xh.school

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.jaeger.library.StatusBarUtil
import com.xh.module.base.BaseActivity
import com.xh.module.base.adapter.TabFragmentPagerAdapter
import com.xh.module.base.utils.FragmentUtils
import com.xh.module.base.view.TabIconBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    //UI
    private val mTabEntities = ArrayList<CustomTabEntity>()

    private val mIconUnselectIds = intArrayOf(
        R.drawable.ic_school_class,
        R.drawable.ic_teach,
        R.drawable.ic_bbs,
        R.drawable.ic_me
    )
    private val mIconSelectIds = intArrayOf(
        R.drawable.ic_school_class_select,
        R.drawable.ic_teach_selected,
        R.drawable.ic_bbs_selected,
        R.drawable.ic_me_selected
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTabLayout()
        initPager()
    }

    fun initTabLayout() {
        //获取数据 在values/arrays.xml中进行定义然后调用
        var tabTitle = resources.getStringArray(R.array.tab_titles)
        //将fragment装进列表中
        var fragmentList = ArrayList<Fragment>()
        fragmentList.add(FragmentUtils.getSchoolFragment())
        fragmentList.add(FragmentUtils.getTeachFragment())
        fragmentList.add(FragmentUtils.getBbsFragment())
        fragmentList.add(FragmentUtils.getMeFragment())
        //viewpager加载adapter
        vp.adapter = TabFragmentPagerAdapter(supportFragmentManager, fragmentList, tabTitle)
        for (i in tabTitle.indices) {
            mTabEntities.add(TabIconBean(tabTitle[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        tabLayout.setTabData(mTabEntities)
    }

    private fun initPager() {
        tabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                vp.currentItem = position
            }

            override fun onTabReselect(position: Int) {
            }
        })

        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                tabLayout.currentTab = position
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        //默认选中第一个
        vp.currentItem = 0
        vp.offscreenPageLimit = 5
        vp.setNoScroll(true)
    }

    override fun setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null)
    }
}

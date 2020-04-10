package com.xh.school

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.xh.module.base.BaseActivity
import com.xh.module.base.utils.FragmentUtils
import com.xh.school.adapter.TabFragmentPagerAdapter
import com.xh.school.entity.TabIconBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    //UI
    private val mTabEntities = ArrayList<CustomTabEntity>()

    private val mIconUnselectIds = intArrayOf(
        R.drawable.ic_list,
        R.drawable.ic_list,
        R.drawable.ic_list
    )
    private val mIconSelectIds = intArrayOf(
        R.drawable.ic_list_select,
        R.drawable.ic_list_select,
        R.drawable.ic_list_select
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTabLayout()
    }

    fun initTabLayout() {
        //获取数据 在values/arrays.xml中进行定义然后调用
        var tabTitle = resources.getStringArray(R.array.tab_titles)
        //将fragment装进列表中
        var fragmentList = ArrayList<Fragment>()
        fragmentList.add(FragmentUtils.getHomeFragment())
        fragmentList.add(FragmentUtils.getHomeFragment())
        fragmentList.add(FragmentUtils.getHomeFragment())
        //viewpager加载adapter
        vp.adapter = TabFragmentPagerAdapter(supportFragmentManager, fragmentList, tabTitle)
        for (i in tabTitle.indices) {
            mTabEntities.add(TabIconBean(tabTitle[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        tabLayout.setTabData(mTabEntities)

        tabLayout.showDot(1);
    }
}

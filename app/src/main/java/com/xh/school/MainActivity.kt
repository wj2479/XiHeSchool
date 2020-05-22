package com.xh.school

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.jaeger.library.StatusBarUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.tbruyelle.rxpermissions2.RxPermissions
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
//        R.drawable.ic_teach,
        R.drawable.ic_bbs,
        R.drawable.ic_me
    )
    private val mIconSelectIds = intArrayOf(
        R.drawable.ic_school_class_select,
//        R.drawable.ic_teach_selected,
        R.drawable.ic_bbs_selected,
        R.drawable.ic_me_selected
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()
        initTabLayout()
        initPager()
    }

    fun initTabLayout() {
        //获取数据 在values/arrays.xml中进行定义然后调用
        var tabTitle = resources.getStringArray(R.array.tab_titles)
        //将fragment装进列表中
        var fragmentList = ArrayList<Fragment>()
        fragmentList.add(FragmentUtils.getSchoolFragment())
//        fragmentList.add(FragmentUtils.getTeachFragment())
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

                // 改变状态栏的颜色和字体颜色
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    when (position) {
                        0, 2 -> {
                            QMUIStatusBarHelper.setStatusBarDarkMode(this@MainActivity)
                        }
                        1 -> {
                            //实现状态栏图标和文字颜色为暗色
                            QMUIStatusBarHelper.setStatusBarLightMode(this@MainActivity)
                        }
                    }
                }

                vp.currentItem = position
            }

            override fun onTabReselect(position: Int) {
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


    /**
     * 检查权限
     */
    private fun requestPermissions() {
        var rxPermission = RxPermissions(this)

        rxPermission.request(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
        ).subscribe({ bool ->
            if (bool) {

            }
        })
    }
}

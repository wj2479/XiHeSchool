package com.xh.module_school.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.Constant;
import com.xh.module.base.entity.Role;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.utils.FragmentUtils;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_school.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 校园的主界面
 */

@Route(path = RouteUtils.School_Fragment_School_Menu)
public class SchoolMenuFragment extends BaseFragment {

    /**
     * 内置了10个布局，目的是为了配合Fragment使用
     */
    int[] viewIds = new int[]{R.id.frame1Layout, R.id.frame2Layout, R.id.frame3Layout,
            R.id.frame4Layout, R.id.frame5Layout, R.id.frame6Layout,
            R.id.frame7Layout, R.id.frame8Layout, R.id.frame9Layout, R.id.frame10Layout};

    public SchoolMenuFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_school_menu;
    }

    @Override
    protected void initView(View rootView) {
        List<Role> schoolRoles = new ArrayList<>();

        //筛选出学校的角色
        for (Role role : DataRepository.userInfo.getRoles()) {
            if (role.getType() == Constant.ROLE_TYPE_SCHOOL) {
                schoolRoles.add(role);
            }
        }
//        Collections.sort(schoolRoles, new Comparator<Role>() {
//            @Override
//            public int compare(Role o1, Role o2) {
//                return (int) (o2.getId() - o1.getId());
//            }
//        });

        int index = 0;

        for (int i = 0; i < schoolRoles.size(); i++) {

            Role role = schoolRoles.get(i);

            switch (role.getId().intValue()) {
                case Constant.ROLE_CODE_CLASS_MASTER:
                    getChildFragmentManager().beginTransaction().add(viewIds[index], FragmentUtils.getFragment(RouteUtils.School_Fragment_Class_Master_Menu)).commitNowAllowingStateLoss();
                    break;
                case Constant.ROLE_CODE_SCHOOL_MASTER:
                    getChildFragmentManager().beginTransaction().add(viewIds[index], FragmentUtils.getFragment(RouteUtils.School_Fragment_School_Master_Menu)).commitNowAllowingStateLoss();
                    break;
                case Constant.ROLE_CODE_TEACHER:
                    getChildFragmentManager().beginTransaction().add(viewIds[index], FragmentUtils.getFragment(RouteUtils.School_Fragment_Teacher_Menu)).commitNowAllowingStateLoss();
                    break;
            }
            index++;

        }


    }

}

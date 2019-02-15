package com.lhxia.mvp.base

import com.lhxia.kotmvp.core.BaseMPVFragment
import com.lhxia.kotmvp.core.Contract

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/14
 * @Description : a new file
 */
open class BaseFragment<P : Contract.Presenter>: BaseMPVFragment<P>()
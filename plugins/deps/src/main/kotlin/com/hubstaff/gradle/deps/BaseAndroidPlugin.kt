package com.hubstaff.gradle.deps

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

open abstract class BaseAndroidPlugin: Plugin<Project> {


    protected val Project.android: BaseExtension
        get() = extensions.findByName("android") as? BaseExtension
            ?: error("Project '$name' is not an Android module")

}

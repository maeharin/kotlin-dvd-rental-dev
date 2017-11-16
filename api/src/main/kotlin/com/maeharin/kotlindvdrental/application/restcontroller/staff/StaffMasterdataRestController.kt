package com.maeharin.kotlindvdrental.application.restcontroller.staff

import com.maeharin.kotlindvdrental.application.applicationservice.MasterDataApplicationService
import com.maeharin.kotlindvdrental.application.restcontroller.staff.resource.CategoryResource
import com.maeharin.kotlindvdrental.application.restcontroller.staff.resource.LanguageResource
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/staff/masterdatas")
@Api(tags = arrayOf("masterdata"), description = "マスタデータAPI")
class StaffMasterdataRestController(
        private val masterdataApplicationService: MasterDataApplicationService
) {
    @GetMapping("/languages")
    @ApiOperation("言語マスタ取得", nickname = "staff_get_languages")
    fun getLanguages(): List<LanguageResource>
            = masterdataApplicationService.findAllLanguages().map(::LanguageResource)

    @GetMapping("/categories")
    @ApiOperation("カテゴリマスタ取得", nickname = "staff_get_categories")
    fun getCategories(): List<CategoryResource>
            = masterdataApplicationService.findAllCategories().map(::CategoryResource)
}